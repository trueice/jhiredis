package tv.freewheel.redis;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.Assert;

import tv.freewheel.redis.ProtocolException;
import tv.freewheel.redis.Reader;
import tv.freewheel.redis.Reply;
import tv.freewheel.redis.ProtocolException.ProtocolEOFException;
import tv.freewheel.redis.ProtocolException.ProtocolIOException;
import tv.freewheel.redis.Reply.ReplyType;



public class ReaderTest {

	private void assertProtocolException(String s,
			Class<? extends ProtocolException> klass) {
		try {
			readReply(s);
			Assert.fail("expect protocol exception");
		} catch (ProtocolException e) {
			assertEquals(klass, e.getClass());
		}
	}

	private void assertProtocolException(String s) {
		assertProtocolException(s, ProtocolException.class);
	}

	private Reply readReply(String s) {
		ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
		Reader r = new Reader(in, 1024);
		return r.nextReply(null, false);
	}

	@Test
	public void testReadLine() {
		Reply reply = readReply("-err\ro\rr\r\r\n");
		assertEquals(ReplyType.ERROR, reply.type);
		assertEquals("err\ro\rr\r", new String(reply.error));
	}

	@Test
	public void testInvalidType() {
		assertProtocolException("a", ProtocolException.class);
	}

	@Test
	public void testCRLF() {
		assertProtocolException("$3\r\nabcde\r\n", ProtocolException.class);
		assertProtocolException("$3\r\nabc\rd", ProtocolException.class);
	}

	@Test
	public void testEof() {
		assertProtocolException("-err", ProtocolEOFException.class);
	}

	@Test
	public void testIOException() {
		InputStream in = new InputStream() {

			@Override
			public int read() throws IOException {
				throw new IOException();
			}

		};
		Reader r = new Reader(in, 1024);
		try {
			r.nextReply(null, false);
			Assert.fail("expect io exception");
		} catch (ProtocolIOException e) {
		}
	}

	@Test
	public void testInteger() {
		Reply reply = readReply(":-1\r\n");
		assertEquals(ReplyType.INTEGER, reply.type);
		assertEquals(-1, reply.integer);

		reply = readReply(":1\r\n");
		assertEquals(ReplyType.INTEGER, reply.type);
		assertEquals(1, reply.integer);

		assertProtocolException(":-\r\n");
		assertProtocolException(":+\r\n");
		assertProtocolException(":*\r\n");
		assertProtocolException(":a\r\n");
		assertProtocolException(":\r\n");
	}

}
