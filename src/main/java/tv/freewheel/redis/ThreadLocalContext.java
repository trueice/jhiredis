package tv.freewheel.redis;

public class ThreadLocalContext {

	public static class Env {
		public final Context context;
		public final Command command = new Command();
		public final Reply reply = new Reply();

		public Env(Context context) {
			this.context = context;
		}
	}

	public final String host;
	public final int port;
	public final int timeout;
	public final int bufferSize;

	private final ThreadLocal<Env> threadLocal = new ThreadLocal<Env>();

	public ThreadLocalContext(String address) {
		String[] parts = address.split(":");
		this.host = parts[0];
		if (parts.length >= 2) {
			this.port = Integer.parseInt(parts[1]);
		} else {
			this.port = Context.DEFAULT_PORT;
		}
		this.bufferSize = Context.DEFAULT_BUFFER_SIZE;
		this.timeout = 0;
	}

	public ThreadLocalContext(String host, int port, int bufferSize, int timeout) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.bufferSize = bufferSize;
	}

	public ThreadLocalContext(String host, int port) {
		this(host, port, Context.DEFAULT_BUFFER_SIZE, 0);
	}

	private Env createEnv() {
		Context context = new Context(host, port, bufferSize, timeout);
		context.connect();
		return new Env(context);
	}

	public Env get() {
		Env env = threadLocal.get();
		if (env == null) {
			env = createEnv();
			threadLocal.set(env);
		}
		return env;
	}

	public Context getContext() {
		return get().context;
	}

	public void remove() {
		Env env = threadLocal.get();
		if (env != null) {
			env.context.disconnect();
		}
		threadLocal.remove();
	}

}
