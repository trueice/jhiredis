package tv.freewheel.redis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

import tv.freewheel.redis.Codec;

public class CodecTest {

	@Test
	public void testEncodeInt() {
		for (int i : new int[] { Integer.MIN_VALUE, -65535, -65536, -70000, -1,
				0, 1, 65535, 65536, 70000, Integer.MAX_VALUE }) {
			assertEquals(String.valueOf(i), new String(Codec.encode(i)));
		}
	}

	@Test
	public void testEncodeLong() {
		for (long l : new long[] { Long.MIN_VALUE, -65535L, -65536L, -70000L,
				-1L, 0L, 1L, 65535L, 65536L, 70000L, Long.MAX_VALUE }) {
			assertEquals(String.valueOf(l), new String(Codec.encode(l)));
		}
	}

	@Test
	public void testIso8859_1() throws UnsupportedEncodingException {
		for (String s : new String[] { "abc", "\r\n\t", "\u0000a1\u0080\u00ff" }) {
			assertTrue(Arrays.equals(s.getBytes("iso-8859-1"),
					Codec.iso8859_1(s)));
		}
	}
}
