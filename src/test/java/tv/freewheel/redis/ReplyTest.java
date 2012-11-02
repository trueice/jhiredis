package tv.freewheel.redis;

import org.junit.Test;

import tv.freewheel.redis.Reply;
import tv.freewheel.redis.Reply.ReplyType;


import static org.junit.Assert.*;


public class ReplyTest {

	@Test
	public void testIsError() {
		Reply reply = new Reply(ReplyType.ERROR, "error".getBytes());
		assertTrue(reply.hasError());

		reply = new Reply(ReplyType.ERROR);
		assertFalse(reply.hasError());
	}

	@Test
	public void testStatus() {
		Reply reply = new Reply(ReplyType.STATUS);
		reply.status = "OK".getBytes();
		assertTrue(reply.isOK());
		assertFalse(reply.isQueued());

		reply = new Reply(ReplyType.STATUS);
		reply.status = "QUEUED".getBytes();
		assertFalse(reply.isOK());
		assertTrue(reply.isQueued());
	}

}
