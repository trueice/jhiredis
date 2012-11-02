package tv.freewheel.redis;

import java.util.ArrayList;
import java.util.List;

public class Command {
	public final List<byte[]> bulks = new ArrayList<byte[]>(4);

	public Command() {
	}

	public Command(CommandType type) {
		append(type);
	}

	public Command clear() {
		bulks.clear();
		return this;
	}

	public Command append(CommandType type) {
		bulks.add(type.bytes);
		return this;
	}

	public Command append(String s) {
		bulks.add(Codec.iso8859_1(s));
		return this;
	}

	public Command append(byte[] bytes) {
		bulks.add(bytes);
		return this;
	}

	public Command append(int i) {
		bulks.add(Codec.encode(i));
		return this;
	}

	public Command append(long l) {
		bulks.add(Codec.encode(l));
		return this;
	}

}