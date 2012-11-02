package tv.freewheel.redis;

import java.util.ArrayList;
import java.util.List;

public class Reply {

	public static enum ReplyType {
		STRING, ARRAY, INTEGER, STATUS, ERROR, UNKNOWN
	}

	public ReplyType type;

	public byte[] error;

	public byte[] status;

	public long integer;

	public byte[] str;

	public boolean nil;

	public List<Reply> replies = new ArrayList<Reply>(0);

	public Reply() {
	}

	public Reply(ReplyType type) {
		this.type = type;
	}

	public Reply(ReplyType type, byte[] error) {
		this.type = type;
		this.error = error;
	}

	public Reply clear() {
		type = ReplyType.UNKNOWN;
		error = null;
		status = null;
		integer = 0;
		str = null;
		replies.clear();

		return this;
	}

	public boolean hasError() {
		return error != null;
	}

	public boolean isOK() {
		return status != null && status.length == 2 && status[0] == 'O'
				&& status[1] == 'K';
	}

	public boolean isQueued() {
		if (!(status != null && status.length == 6)) {
			return false;
		}
		return status[0] == 'Q' && status[1] == 'U' && status[2] == 'E'
				&& status[3] == 'U' && status[4] == 'E' && status[5] == 'D';
	}

	public void addReply(Reply reply) {
		replies.add(reply);
	}

	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder("Reply [type=");
		ss.append(type);
		if (status != null) {
			ss.append(", status=").append(new String(status));
		}
		if (integer != 0) {
			ss.append(", integer=").append(integer);
		}
		if (error != null) {
			ss.append(", error=").append(new String(error));
		}
		if (str != null) {
			ss.append(", str=").append(new String(str));
		}
		if (type == ReplyType.ARRAY) {
			ss.append(", replies=[");
			for (Reply reply : replies) {
				ss.append(reply).append(",");
			}
			if (replies.size() > 0) {
				ss.setLength(ss.length() - 1);
			}
			ss.append("]");
		}
		ss.append("]");
		return ss.toString();
	}

}