package tv.freewheel.redis;

import tv.freewheel.redis.Command;
import tv.freewheel.redis.CommandType;
import tv.freewheel.redis.Context;
import tv.freewheel.redis.Reply;

public class Hello {

	public static void main(String[] args) {
		Context context = new Context("localhost", 6379);
		context.connect();
		Command command = new Command();
		Reply reply = new Reply();
		command.clear().append(CommandType.SET).append("hello").append("world");
		context.execute(command, reply);
		System.out.println(reply);
	}
	
}
