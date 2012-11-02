package tv.freewheel.redis;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import tv.freewheel.redis.ProtocolException.ProtocolConnectException;



/**
 * see <a href="http://redis.io/topics/protocol">Redis Protocol
 * specification</a>.
 * 
 * @author chhuang
 * 
 */
public class Context {
	public static final int DEFAULT_PORT = 6379;

	public static final int DEFAULT_BUFFER_SIZE = 8 * 1024;

	public final String host;
	public final int port;
	public final int timeout;

	private Socket socket;

	private final int bufferSize;

	private Writer writer;
	private Reader reader;

	public Context(String host, int port) {
		this(host, port, DEFAULT_BUFFER_SIZE);
	}

	public Context(String host, int port, int bufferSize) {
		this(host, port, bufferSize, 0);
	}

	public Context(String host, int port, int bufferSize, int timeout) {
		this.host = host;
		this.port = port;
		this.bufferSize = bufferSize;
		this.timeout = timeout;
	}

	public boolean isConnected() {
		return socket != null;
	}

	public void connect() throws ProtocolException {
		if (socket != null) {
			throw new IllegalStateException("already connected");
		}
		try {
			socket = new Socket();
			socket.setKeepAlive(true);
			if (timeout > 0) {
				socket.setSoTimeout(timeout);
			}
			socket.connect(new InetSocketAddress(host, port));

			writer = new Writer(socket.getOutputStream(), bufferSize);
			reader = new Reader(socket.getInputStream(), bufferSize);
		} catch (IOException e) {
			throw new ProtocolConnectException("connect to " + host + ":"
					+ port + " failed", e);
		}
	}

	public void disconnect() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// ignore
			}
			socket = null;
			writer = null;
			reader = null;
		}
	}

	public void flush() throws ProtocolException {
		writer.flush();
	}

	public void sendCommand(Command c) throws ProtocolException {
		writer.write(c);
	}

	public Reply getReply(Reply reply) throws ProtocolException {
		return reader.nextReply(reply, false);
	}

	public void skipReplies(int n) throws ProtocolException {
		skipReplies(n, null);
	}

	public void skipReplies(int n, Reply reply) throws ProtocolException {
		if (reply == null) {
			reply = new Reply();
		}
		for (int i = 0; i < n; i++) {
			reader.nextReply(reply, true);
			reply.clear();
		}
	}

	public List<Reply> getReplies(int n) throws ProtocolException {
		ArrayList<Reply> replies = new ArrayList<Reply>(n);

		for (int i = 0; i < n; i++) {
			replies.add(reader.nextReply(null, false));
		}

		return replies;
	}

	public Reply execute(Command command, Reply reply) throws ProtocolException {
		sendCommand(command);
		flush();
		return getReply(reply);
	}

	public Reply execute(Command command) throws ProtocolException {
		return execute(command, null);
	}

}