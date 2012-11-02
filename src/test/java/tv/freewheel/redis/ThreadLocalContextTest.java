package tv.freewheel.redis;

import org.junit.Test;

import tv.freewheel.redis.Context;
import tv.freewheel.redis.ThreadLocalContext;


import static org.junit.Assert.*;

public class ThreadLocalContextTest {

	private Context ctx1;
	private Context ctx2;

	@Test
	public void test() throws InterruptedException {
		final ThreadLocalContext threadLocalContext = new ThreadLocalContext(
				"127.0.0.1", Context.DEFAULT_PORT);
		ctx1 = threadLocalContext.getContext();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				ctx2 = threadLocalContext.getContext();
			}

		});
		thread.start();
		thread.join();
		assertNotSame(ctx1, ctx2);

		threadLocalContext.remove();
	}

	@Test
	public void test2() {
		ThreadLocalContext threadLocalContext = new ThreadLocalContext(
				"127.0.0.1:6379");
		assertEquals("127.0.0.1", threadLocalContext.host);
		assertEquals(6379, threadLocalContext.port);

		threadLocalContext = new ThreadLocalContext("127.0.0.1");
		assertEquals("127.0.0.1", threadLocalContext.host);
		assertEquals(Context.DEFAULT_PORT, threadLocalContext.port);
	}
}
