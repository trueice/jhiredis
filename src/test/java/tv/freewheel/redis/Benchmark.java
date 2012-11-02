package tv.freewheel.redis;


import redis.clients.jedis.Jedis;
import tv.freewheel.redis.Command;
import tv.freewheel.redis.CommandType;
import tv.freewheel.redis.Context;
import tv.freewheel.redis.Reply;

public class Benchmark {

	private final String name;
	private final int times;
	private final Runnable task;

	public Benchmark(String name, int times, Runnable task) {
		this.name = name;
		this.times = times;
		this.task = task;
	}

	public void run() {
		long startTime = System.nanoTime();
		for (int i = 0; i < times; i++) {
			task.run();
		}
		double estimatedTime = (System.nanoTime() - startTime) / 1000000000.0;
		double speed = times / estimatedTime;
		System.out.printf(
				"[%15s] times=%6d, spend %2.3f seconds, speed=%6.3f op/s\n",
				name, times, estimatedTime, speed);
	}

	private static void clearEnv(Context context, byte[] key) {
		Command command = new Command(CommandType.DEL).append(key);
		context.execute(command);
	}

	private static void runBenchmark(String name, int times, Runnable task) {
		Benchmark benchmark = new Benchmark(name, times, task);
		benchmark.run();
	}

	public static void main(String[] args) {
		int times = 10000;

		final byte[] key = "_t_".getBytes();
		final byte[] field = "a".getBytes();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 1024; i++) {
			char ch = (char) ('a' + i % 26);
			builder.append(ch);
		}
		final byte[] value = builder.toString().getBytes();

		final Context context = new Context("127.0.0.1", Context.DEFAULT_PORT);
		context.connect();

		final Command command = new Command();
		final Reply reply = new Reply();

		command.clear().append(CommandType.SET).append(key).append(value);
		clearEnv(context, key);
		runBenchmark("jhiredis set", times, new Runnable() {

			@Override
			public void run() {
				context.execute(command, reply);
			}

		});

		clearEnv(context, key);
		final Jedis jedis = new Jedis("127.0.0.1", Context.DEFAULT_PORT);

		runBenchmark("jedis set", times, new Runnable() {

			@Override
			public void run() {
				jedis.set(key, value);
			}

		});

		command.clear().append(CommandType.GET).append(key);
		runBenchmark("jhiredis get", times, new Runnable() {

			@Override
			public void run() {
				context.execute(command, reply);
			}

		});

		runBenchmark("jedis get", times, new Runnable() {

			@Override
			public void run() {
				jedis.get(key);
			}

		});

		clearEnv(context, key);
		command.clear().append(CommandType.INCR).append(key);
		runBenchmark("jhiredis incr", times, new Runnable() {

			@Override
			public void run() {
				context.execute(command, reply);
			}

		});

		clearEnv(context, key);
		runBenchmark("jedis incr", times, new Runnable() {

			@Override
			public void run() {
				jedis.incr(key);
			}

		});

		clearEnv(context, key);
		command.clear().append(CommandType.HSET).append(key).append(field)
				.append(value);
		runBenchmark("jhiredis hset", times, new Runnable() {

			@Override
			public void run() {
				context.execute(command, reply);
			}

		});

		clearEnv(context, key);
		runBenchmark("jedis hset", times, new Runnable() {

			@Override
			public void run() {
				jedis.hset(key, field, value);
			}

		});

	}
}
