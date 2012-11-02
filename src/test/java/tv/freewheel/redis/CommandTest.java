package tv.freewheel.redis;

import java.util.Arrays;

import org.junit.Test;

import tv.freewheel.redis.Command;
import tv.freewheel.redis.CommandType;


import static org.junit.Assert.*;

public class CommandTest {

	private void assertCommand(Command c, String... bulks) {
		assertEquals(bulks.length, c.bulks.size());
		for (int i = 0; i < bulks.length; i++) {
			String bulk = bulks[i];
			assertTrue(Arrays.equals(bulk.getBytes(), c.bulks.get(i)));
		}
	}

	@Test
	public void testAppend() {
		Command c = new Command(CommandType.SET);
		c.append("a").append(1);
		assertCommand(c, "SET", "a", "1");
		c.clear().append("SET").append("a").append(-1);
		assertCommand(c, "SET", "a", "-1");

		c = new Command();
		c.append("SET").append("b").append(1L);
		assertCommand(c, "SET", "b", "1");
		c.clear().append("SET").append("b").append(-1L);
		assertCommand(c, "SET", "b", "-1");

		c.clear().append("SET").append("b".getBytes()).append("1");
		assertCommand(c, "SET", "b", "1");
	}

}
