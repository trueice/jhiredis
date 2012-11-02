package tv.freewheel.redis;

import java.io.IOException;
import java.io.OutputStream;

import tv.freewheel.redis.ProtocolException.ProtocolIOException;



public class Writer {
	private static final byte[] CRLF = new byte[] { '\r', '\n' };

	private final OutputStream out;

	private final byte[] buffer;
	private final int bufferSize;
	private int bufferPos;

	public Writer(OutputStream out, int bufferSize) {
		this.out = out;

		this.bufferSize = bufferSize;
		buffer = new byte[bufferSize];
	}

	private void write(byte b) throws ProtocolException {
		if (bufferPos >= bufferSize) {
			flush();
		}
		buffer[bufferPos++] = b;
	}

	private void write(byte[] bb) throws ProtocolException {
		write(bb, 0, bb.length);
	}

	private void write(byte[] bb, int off, int len) throws ProtocolException {
		if (bufferSize - bufferPos >= len) {
			System.arraycopy(bb, off, buffer, bufferPos, len);
			bufferPos += len;
			return;
		}

		int remaining = len;
		while (remaining > 0) {
			int capacity = bufferSize - bufferPos;
			if (capacity == 0) {
				flush();
			}
			if (capacity >= remaining) {
				System.arraycopy(bb, off, buffer, bufferPos, remaining);
				bufferPos += remaining;
				break;
			} else {
				System.arraycopy(bb, off, buffer, bufferPos, capacity);
				bufferPos += capacity;
				off += capacity;
				remaining -= capacity;
			}
		}
	}

	public void flush() throws ProtocolException {
		try {
			if (bufferPos > 0) {
				out.write(buffer, 0, bufferPos);
				out.flush();
				bufferPos = 0;
			}
		} catch (IOException e) {
			throw new ProtocolIOException(e.getMessage(), e);
		}
	}

	/**
	 * <pre>
	 * *<number of arguments> CR LF
	 * $<number of bytes of argument 1> CR LF
	 * <argument data> CR LF
	 * ...
	 * $<number of bytes of argument N> CR LF
	 * <argument data> CR LF
	 * </pre>
	 * 
	 */
	public void write(Command c) throws ProtocolException {
		write((byte) '*');
		write(Codec.encode(c.bulks.size()));
		write(CRLF);

		for (byte[] bytes : c.bulks) {
			write((byte) '$');
			write(Codec.encode(bytes.length));
			write(CRLF);

			write(bytes);
			write(CRLF);
		}
	}

}