package tv.freewheel.redis;

import java.io.IOException;

public class ProtocolException extends RuntimeException {

	private static final long serialVersionUID = 14837881049447577L;

	public ProtocolException(String message) {
		super(message);
	}

	public ProtocolException(String message, Throwable cause) {
		super(message, cause);
	}

	public static class ProtocolIOException extends ProtocolException {

		private static final long serialVersionUID = -1438603889224816378L;

		public ProtocolIOException(String message, IOException cause) {
			super(message, cause);
		}

	}

	public static class ProtocolEOFException extends ProtocolException {

		private static final long serialVersionUID = -1438603889224816378L;

		public ProtocolEOFException(String message) {
			super(message);
		}

	}

	public static class ProtocolConnectException extends ProtocolException {

		private static final long serialVersionUID = -1438603889224816378L;

		public ProtocolConnectException(String message, IOException cause) {
			super(message, cause);
		}

	}

}
