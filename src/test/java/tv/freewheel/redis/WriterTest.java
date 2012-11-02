package tv.freewheel.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import tv.freewheel.redis.Command;
import tv.freewheel.redis.Writer;
import tv.freewheel.redis.ProtocolException.ProtocolIOException;



public class WriterTest {

	private Command c = new Command().append("SET").append("a").append("1");

	@Test
	public void testWriteCommand() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int bufferSize : new int[] { 1, 2, 16, 64, 1024 }) {
			Writer writer = new Writer(out, bufferSize);
			writer.write(c);
			writer.flush();
			assertEquals("*3\r\n$3\r\nSET\r\n$1\r\na\r\n$1\r\n1\r\n",
					out.toString());
			out.reset();
		}
	}

	@Test
	public void testIOException() {
		OutputStream out = new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				throw new IOException();
			}

		};
		Writer writer = new Writer(out, 1024);
		try {
			writer.write(c);
			writer.flush();
			fail();
		} catch (ProtocolIOException e) {
		}
	}
}
