# jhiredis - low level redis client for java(java hiredis)

## features

* simple, < 1000LOC if exclude generated CommandType
* high performance, ~ 20,000 op/s on my box
* low memory footprint

## usage

```java
package tv.freewheel.redis;

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
```

## connection pool

no connection pool, but you can use ThreadLocalContext instead.

## benchmark

<pre>
[   jhiredis set] times= 10000, spend 0.547 seconds, speed=18269.278 op/s
[      jedis set] times= 10000, spend 0.767 seconds, speed=13034.309 op/s
[   jhiredis get] times= 10000, spend 0.512 seconds, speed=19533.997 op/s
[      jedis get] times= 10000, spend 0.556 seconds, speed=17984.156 op/s
[  jhiredis incr] times= 10000, spend 0.470 seconds, speed=21258.142 op/s
[     jedis incr] times= 10000, spend 0.542 seconds, speed=18435.049 op/s
[  jhiredis hset] times= 10000, spend 0.495 seconds, speed=20210.962 op/s
[     jedis hset] times= 10000, spend 0.542 seconds, speed=18436.782 op/s
</pre>

## compile & package

```bash
# create eclipse project
$ mvn eclipse:eclipse
# package
$ mvn package
```
