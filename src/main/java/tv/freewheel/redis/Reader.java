package tv.freewheel.redis;

import java.io.IOException;
import java.io.InputStream;

import tv.freewheel.redis.ProtocolException.ProtocolEOFException;
import tv.freewheel.redis.ProtocolException.ProtocolIOException;
import tv.freewheel.redis.Reply.ReplyType;



/**
 * <pre>
 * Replies
 * Redis will reply to commands with different kinds of replies. It is possible to check the kind of reply from the first byte sent by the server:
 * With a single line reply the first byte of the reply will be "+"
 * With an error message the first byte of the reply will be "-"
 * With an integer number the first byte of the reply will be ":"
 * With bulk reply the first byte of the reply will be "$"
 * With multi-bulk reply the first byte of the reply will be "*"
 * </pre>
 * 
 */
public class Reader {

	private final InputStream in;

	private final int bufferSize;
	private final byte[] buffer;
	private int bufferPos;
	private int bufferLimit;

	private final byte[] lineBuffer;

	public Reader(InputStream in, int bufferSize) {
		this.in = in;

		this.bufferSize = bufferSize;
		buffer = new byte[bufferSize];

		lineBuffer = new byte[1024];
	}

	private void refillBuffer() throws ProtocolException {
		try {
			int size = in.read(buffer, 0, bufferSize);
			if (size == -1) {
				throw new ProtocolEOFException("end of file");
			}
			bufferPos = 0;
			bufferLimit = size;
		} catch (IOException e) {
			throw new ProtocolIOException(e.getMessage(), e);
		}
	}

	private byte readByte() throws ProtocolException {
		if (bufferPos >= bufferLimit) {
			refillBuffer();
		}
		return buffer[bufferPos++];
	}

	/*
	 * TODO, handle buffer overflow?
	 */
	private int readLine() throws ProtocolException {
		int pos = 0;
		for (;;) {
			byte b = readByte();
			lineBuffer[pos++] = b;
			while (b == '\r') {
				b = readByte();
				if (b == '\n') {
					return pos - 1;
				}
				lineBuffer[pos++] = b;
			}
		}
	}

	private byte[] readLineAndDup() throws ProtocolException {
		int len = readLine();
		byte[] data = new byte[len];
		System.arraycopy(lineBuffer, 0, data, 0, len);
		return data;
	}

	private void readCRLF() throws ProtocolException {
		byte b;
		if ((b = readByte()) != '\r') {
			throw new ProtocolException("expected \\r, but was " + b);
		}
		if ((b = readByte()) != '\n') {
			throw new ProtocolException("expected \\n, but was " + b);
		}
	}

	private void read(byte[] bb) throws ProtocolException {
		int remaining = bb.length;
		int off = 0;

		int delta = 0;
		while (remaining > 0) {
			delta = bufferLimit - bufferPos;
			if (delta >= remaining) {
				System.arraycopy(buffer, bufferPos, bb, off, remaining);
				bufferPos += remaining;
				break;
			}

			if (delta > 0) {
				System.arraycopy(buffer, bufferPos, bb, off, delta);
				remaining -= delta;
				off += delta;
				bufferLimit = bufferPos = 0;
			}

			if (remaining == 0) {
				break;
			}

			refillBuffer();
		}
	}

	private long decodeInteger(byte[] buf, int len) throws ProtocolException {
		if (len == 0) {
			throw new ProtocolException("invalid integer length: 0");
		}
		long result = 0;
		boolean negative = false;
		int i = 0;
		int digit;

		byte firstChar = buf[0];
		if (firstChar < '0') { // Possible leading "+" or "-"
			if (firstChar == '-') {
				negative = true;
			} else if (firstChar != '+') {
				throw new ProtocolException("invalid integer: \""
						+ new String(buf, 0, len) + "\"");
			}

			if (len == 1) {
				// Cannot have lone "+" or "-"
				throw new ProtocolException("invalid integer: \""
						+ new String(buf, 0, len) + "\"");
			}
			i++;
		}
		while (i < len) {
			// Accumulating negatively avoids surprises near MAX_VALUE
			digit = buf[i++] - '0';
			if (digit < 0 || digit > 9) {
				throw new ProtocolException("invalid integer: \""
						+ new String(buf, 0, len) + "\"");
			}
			result *= 10;
			result += digit;
		}
		return negative ? -result : result;
	}

	private byte[] readStr() throws ProtocolException {
		int lineLen = readLine();
		int strLen = (int) decodeInteger(lineBuffer, lineLen);
		if (strLen == -1) {
			return null;
		}
		byte[] str = new byte[strLen];

		read(str);
		readCRLF();
		return str;
	}

	private ReplyType readType() throws ProtocolException {
		ReplyType type;
		byte c = readByte();
		switch (c) {
		case '+':
			type = ReplyType.STATUS;
			break;
		case '-':
			type = ReplyType.ERROR;
			break;
		case ':':
			type = ReplyType.INTEGER;
			break;
		case '$':
			type = ReplyType.STRING;
			break;
		case '*':
			type = ReplyType.ARRAY;
			break;
		default:
			throw new ProtocolException("invalid reply type: " + (char) c);
		}
		return type;
	}

	public Reply nextReply(Reply reply, boolean skipAll)
			throws ProtocolException {
		if (reply == null) {
			reply = new Reply();
		}
		ReplyType type = readType();
		int len = 0;

		switch (type) {
		case ERROR:
			reply.error = readLineAndDup();
			break;
		case STATUS:
			reply.status = readLineAndDup();
			break;
		case INTEGER:
			len = readLine();
			reply.integer = decodeInteger(lineBuffer, len);
			break;
		case STRING:
			reply.str = readStr();
			if (reply.str == null) {
				reply.nil = true;
			}
			break;
		case ARRAY:
			len = readLine();
			int size = (int) decodeInteger(lineBuffer, len);
			if (size == -1) {
				reply.nil = true;
				break;
			}
			for (int i = 0; i < size; i++) {
				Reply r = nextReply(skipAll ? reply : null, skipAll);
				reply.addReply(r);
			}
			break;
		default:
			throw new ProtocolException("Unknown Reply");
		}

		reply.type = type;
		return reply;
	}
}