package tv.freewheel.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import tv.freewheel.redis.Command;
import tv.freewheel.redis.Context;
import tv.freewheel.redis.Reply;
import tv.freewheel.redis.ProtocolException.ProtocolConnectException;
import tv.freewheel.redis.Reply.ReplyType;



public class ContextTest {

	private Context context;
	private Command c = new Command();
	private Reply reply = new Reply();

	public void setup(int bufferSize) {
		if (bufferSize == Context.DEFAULT_BUFFER_SIZE) {
			context = new Context("localhost", 6379);
		} else {
			context = new Context("localhost", 6379, bufferSize);
		}
		assertFalse(context.isConnected());
		context.connect();
		assertTrue(context.isConnected());
	}

	@After
	public void teardown() {
		if (context != null) {
			context.disconnect();
			context = null;
		}
	}

	private Reply execute(Object... bulks) {
		prepareCommand(bulks);
		context.execute(c, reply);
		reply.toString();
		return reply;
	}

	@Test
	public void testExecute() throws IOException {
		setup(1024);
		prepareCommand("DEL", "a");
		context.execute(c);

		prepareCommand("SET", "a", "1");
		reply = context.execute(c);
		assertEquals(ReplyType.STATUS, reply.type);
		assertTrue(reply.isOK());
	}

	@Test
	public void testSimpleCommand() throws IOException {
		byte[] data = new byte[11107];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) i;
		}
		for (int bufferSize : new int[] { 1, 2, 3, 5, 7, 11, 63, 1023,
				Context.DEFAULT_BUFFER_SIZE }) {
			setup(bufferSize);

			execute("DEL", "a");
			execute("GET", "a");
			assertEquals(ReplyType.STRING, reply.type);
			assertNull(reply.str);

			execute("SET", "a", data);
			assertEquals(ReplyType.STATUS, reply.type);
			assertTrue(reply.isOK());
			execute("GET", "a");
			assertEquals(ReplyType.STRING, reply.type);
			assertEquals(data.length, reply.str.length);
			assertTrue(Arrays.equals(data, reply.str));

			execute("SET", "a", "123456\r7");
			assertEquals(ReplyType.STATUS, reply.type);
			assertTrue(reply.isOK());

			execute("GET", "a");
			assertEquals(ReplyType.STRING, reply.type);
			assertEquals("123456\r7", new String(reply.str));

			execute("DEL", "a");
			assertEquals(ReplyType.INTEGER, reply.type);
			assertEquals(1, reply.integer);

			execute("DEL", "h");

			execute("HSET", "h", "a", "1");
			assertEquals(ReplyType.INTEGER, reply.type);
			assertEquals(1, reply.integer);

			execute("GET", "h");
			assertEquals(ReplyType.ERROR, reply.type);

			execute("HGET", "h", "a");
			assertEquals(ReplyType.STRING, reply.type);
			assertEquals("1", new String(reply.str));

			execute("HMSET", "h", "a", "1", "b", "2");
			assertEquals(ReplyType.STATUS, reply.type);
			assertTrue(reply.isOK());

			execute("HMGET", "h", "a", "b");
			assertEquals(ReplyType.ARRAY, reply.type);
			assertEquals(2, reply.replies.size());
			assertEquals("1", new String(reply.replies.get(0).str));
			assertEquals("2", new String(reply.replies.get(1).str));

			execute("DEL", "l");
			for (int i = 0; i < 3; i++) {
				execute("RPUSH", "l", String.valueOf(i));
				assertEquals(ReplyType.INTEGER, reply.type);
				assertEquals(i + 1, reply.integer);
			}
			execute("LRANGE", "l", "0", "1");
			assertEquals(ReplyType.ARRAY, reply.type);
			assertEquals(2, reply.replies.size());
			assertEquals("0", new String(reply.replies.get(0).str));
			assertEquals("1", new String(reply.replies.get(1).str));

			execute("DEL", "s");
			execute("SADD", "s", "0", "1");
			execute("SCARD", "s");
			assertEquals(ReplyType.INTEGER, reply.type);
			assertEquals(2, reply.integer);
			execute("SMEMBERS", "s");
			assertEquals(ReplyType.ARRAY, reply.type);
			assertEquals(2, reply.replies.size());
			assertEquals("0", new String(reply.replies.get(0).str));
			assertEquals("1", new String(reply.replies.get(1).str));

			execute("DEL", "z");
			execute("ZADD", "z", "0", "0");
			execute("ZADD", "z", "1", "1");
			execute("ZADD", "z", "2", "2");
			execute("ZCARD", "z");
			assertEquals(ReplyType.INTEGER, reply.type);
			assertEquals(3, reply.integer);
			execute("ZRANGE", "z", "0", "1");
			assertEquals(2, reply.replies.size());
			assertEquals("0", new String(reply.replies.get(0).str));
			assertEquals("1", new String(reply.replies.get(1).str));

			teardown();
		}
	}

	private void sendCommand(Object... bulks) {
		prepareCommand(bulks);
		context.sendCommand(c);
	}

	private void prepareCommand(Object... bulks) {
		reply.clear();
		c.clear();
		for (Object s : bulks) {
			if (s instanceof byte[]) {
				c.append((byte[]) s);
			} else {
				c.append(s.toString());
			}
		}
	}

	@Test
	public void testTransaction() throws IOException {
		setup(Context.DEFAULT_PORT);
		sendCommand("MULTI");
		sendCommand("DEL", "a");
		sendCommand("GET", "a");
		sendCommand("EXEC");
		context.flush();
		List<Reply> replies = context.getReplies(4);
		assertEquals(ReplyType.STATUS, replies.get(0).type);
		assertTrue(replies.get(0).isOK());

		assertEquals(ReplyType.STATUS, replies.get(1).type);
		assertTrue(replies.get(1).isQueued());

		assertEquals(ReplyType.STATUS, replies.get(2).type);
		assertTrue(replies.get(2).isQueued());

		assertEquals(ReplyType.ARRAY, replies.get(3).type);
		assertEquals(2, replies.get(3).replies.size());

		List<Reply> replies2 = replies.get(3).replies;
		assertEquals(ReplyType.INTEGER, replies2.get(0).type);
		assertEquals(0, replies2.get(0).integer);
		assertEquals(ReplyType.STRING, replies2.get(1).type);
		assertTrue(replies2.get(1).nil);
		assertNull(replies2.get(1).str);

		sendCommand("MULTI");
		sendCommand("EXEC");
		context.flush();
		replies = context.getReplies(2);
		assertEquals(ReplyType.STATUS, replies.get(0).type);
		assertTrue(replies.get(0).isOK());
		assertEquals(ReplyType.ARRAY, replies.get(1).type);
		System.out.println(replies);
		assertEquals(0, replies.get(1).replies.size());
	}

	@Test
	public void testSkipReplies() {
		setup(Context.DEFAULT_PORT);
		sendCommand("MULTI");
		sendCommand("DEL", "a");
		sendCommand("GET", "a");
		sendCommand("EXEC");
		context.flush();

		context.skipReplies(4);
		execute("DEL", "a");
		assertEquals(ReplyType.INTEGER, reply.type);
		assertEquals(0, reply.integer);
	}

	@Test
	public void testConnectFailed() {
		context = new Context("127.0.0.1", 16379, 1024);
		try {
			context.connect();
			fail();
		} catch (ProtocolConnectException e) {
		}
	}

	@Test
	public void testConnectWithTimeout() {
		context = new Context("127.0.0.1", 6379, 1024, 1000);
		context.connect();
		assertTrue(context.isConnected());
	}

	@Test
	public void testConnectMultiTimes() {
		context = new Context("127.0.0.1", 6379, 1024);
		context.connect();
		try {
			context.connect();
			fail();
		} catch (IllegalStateException e) {
		}
	}

}
