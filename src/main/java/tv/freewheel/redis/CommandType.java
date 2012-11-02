package tv.freewheel.redis;

/**
 * @see <a
 *      href="https://github.com/antirez/redis-doc/blob/master/commands.json">redis
 *      commands</a>
 */
public enum CommandType {
	/**
	 * Append a value to a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: string<br>
	 */
	APPEND,

	/**
	 * Authenticate to the server.<br>
	 * arguments:
	 * <ol>
	 * <li>password(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: connection<br>
	 */
	AUTH,

	/**
	 * Asynchronously rewrite the append-only file.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	BGREWRITEAOF,

	/**
	 * Asynchronously save the dataset to disk.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	BGSAVE,

	/**
	 * Count set bits in a string.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer) optional</li>
	 * <li>end(integer) optional</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: string<br>
	 */
	BITCOUNT,

	/**
	 * Perform bitwise operations between strings.<br>
	 * arguments:
	 * <ol>
	 * <li>operation(string)</li>
	 * <li>destkey(key)</li>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: string<br>
	 */
	BITOP,

	/**
	 * Remove and get the first element in a list, or block until one is
	 * available.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>timeout(integer)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: list<br>
	 */
	BLPOP,

	/**
	 * Remove and get the last element in a list, or block until one is
	 * available.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>timeout(integer)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: list<br>
	 */
	BRPOP,

	/**
	 * Pop a value from a list, push it to another list and return it; or block
	 * until one is available.<br>
	 * arguments:
	 * <ol>
	 * <li>source(key)</li>
	 * <li>destination(key)</li>
	 * <li>timeout(integer)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: list<br>
	 */
	BRPOPLPUSH,

	/**
	 * Return the number of keys in the selected database.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	DBSIZE,

	/**
	 * Decrement the integer value of a key by one.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	DECR,

	/**
	 * Decrement the integer value of a key by the given number.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>decrement(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	DECRBY,

	/**
	 * Delete a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	DEL,

	/**
	 * Discard all commands issued after MULTI.<br>
	 * since: 2.0.0<br>
	 * group: transactions<br>
	 */
	DISCARD,

	/**
	 * Return a serialized version of the value stored at the specified key..<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: generic<br>
	 */
	DUMP,

	/**
	 * Echo the given string.<br>
	 * arguments:
	 * <ol>
	 * <li>message(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: connection<br>
	 */
	ECHO,

	/**
	 * Execute a Lua script server side.<br>
	 * arguments:
	 * <ol>
	 * <li>script(string)</li>
	 * <li>numkeys(integer)</li>
	 * <li>key(key)</li>
	 * <li>arg(string)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: scripting<br>
	 */
	EVAL,

	/**
	 * Execute a Lua script server side.<br>
	 * arguments:
	 * <ol>
	 * <li>sha1(string)</li>
	 * <li>numkeys(integer)</li>
	 * <li>key(key)</li>
	 * <li>arg(string)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: scripting<br>
	 */
	EVALSHA,

	/**
	 * Execute all commands issued after MULTI.<br>
	 * since: 1.2.0<br>
	 * group: transactions<br>
	 */
	EXEC,

	/**
	 * Determine if a key exists.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	EXISTS,

	/**
	 * Set a key's time to live in seconds.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>seconds(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	EXPIRE,

	/**
	 * Set the expiration for a key as a UNIX timestamp.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>timestamp(posix time)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: generic<br>
	 */
	EXPIREAT,

	/**
	 * Remove all keys from all databases.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	FLUSHALL,

	/**
	 * Remove all keys from the current database.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	FLUSHDB,

	/**
	 * Get the value of a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	GET,

	/**
	 * Returns the bit value at offset in the string value stored at key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>offset(integer)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: string<br>
	 */
	GETBIT,

	/**
	 * Get a substring of the string stored at a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer)</li>
	 * <li>end(integer)</li>
	 * </ol>
	 * since: 2.4.0<br>
	 * group: string<br>
	 */
	GETRANGE,

	/**
	 * Set the string value of a key and return its old value.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	GETSET,

	/**
	 * Delete one or more hash fields.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HDEL,

	/**
	 * Determine if a hash field exists.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HEXISTS,

	/**
	 * Get the value of a hash field.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HGET,

	/**
	 * Get all the fields and values in a hash.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HGETALL,

	/**
	 * Increment the integer value of a hash field by the given number.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * <li>increment(integer)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HINCRBY,

	/**
	 * Increment the float value of a hash field by the given amount.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * <li>increment(double)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: hash<br>
	 */
	HINCRBYFLOAT,

	/**
	 * Get all the fields in a hash.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HKEYS,

	/**
	 * Get the number of fields in a hash.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HLEN,

	/**
	 * Get the values of all the given hash fields.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HMGET,

	/**
	 * Set multiple hash fields to multiple values.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>["field", "value"](["string", "string"])</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HMSET,

	/**
	 * Set the string value of a hash field.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HSET,

	/**
	 * Set the value of a hash field, only if the field does not exist.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>field(string)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HSETNX,

	/**
	 * Get all the values in a hash.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: hash<br>
	 */
	HVALS,

	/**
	 * Increment the integer value of a key by one.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	INCR,

	/**
	 * Increment the integer value of a key by the given amount.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>increment(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	INCRBY,

	/**
	 * Increment the float value of a key by the given amount.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>increment(double)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: string<br>
	 */
	INCRBYFLOAT,

	/**
	 * Get information and statistics about the server.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	INFO,

	/**
	 * Find all keys matching the given pattern.<br>
	 * arguments:
	 * <ol>
	 * <li>pattern(pattern)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	KEYS,

	/**
	 * Get the UNIX time stamp of the last successful save to disk.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	LASTSAVE,

	/**
	 * Get an element from a list by its index.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>index(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LINDEX,

	/**
	 * Insert an element before or after another element in a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>where(enum)</li>
	 * <li>pivot(string)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: list<br>
	 */
	LINSERT,

	/**
	 * Get the length of a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LLEN,

	/**
	 * Remove and get the first element in a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LPOP,

	/**
	 * Prepend one or multiple values to a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LPUSH,

	/**
	 * Prepend a value to a list, only if the list exists.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: list<br>
	 */
	LPUSHX,

	/**
	 * Get a range of elements from a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer)</li>
	 * <li>stop(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LRANGE,

	/**
	 * Remove elements from a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>count(integer)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LREM,

	/**
	 * Set the value of an element in a list by its index.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>index(integer)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LSET,

	/**
	 * Trim a list to the specified range.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer)</li>
	 * <li>stop(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	LTRIM,

	/**
	 * Get the values of all the given keys.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	MGET,

	/**
	 * Atomically transfer a key from a Redis instance to another one..<br>
	 * arguments:
	 * <ol>
	 * <li>host(string)</li>
	 * <li>port(string)</li>
	 * <li>key(key)</li>
	 * <li>destination-db(integer)</li>
	 * <li>timeout(integer)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: generic<br>
	 */
	MIGRATE,

	/**
	 * Listen for all requests received by the server in real time.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	MONITOR,

	/**
	 * Move a key to another database.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>db(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	MOVE,

	/**
	 * Set multiple keys to multiple values.<br>
	 * arguments:
	 * <ol>
	 * <li>["key", "value"](["key", "string"])</li>
	 * </ol>
	 * since: 1.0.1<br>
	 * group: string<br>
	 */
	MSET,

	/**
	 * Set multiple keys to multiple values, only if none of the keys exist.<br>
	 * arguments:
	 * <ol>
	 * <li>["key", "value"](["key", "string"])</li>
	 * </ol>
	 * since: 1.0.1<br>
	 * group: string<br>
	 */
	MSETNX,

	/**
	 * Mark the start of a transaction block.<br>
	 * since: 1.2.0<br>
	 * group: transactions<br>
	 */
	MULTI,

	/**
	 * Inspect the internals of Redis objects.<br>
	 * arguments:
	 * <ol>
	 * <li>subcommand(string)</li>
	 * <li>arguments(string) optional</li>
	 * </ol>
	 * since: 2.2.3<br>
	 * group: generic<br>
	 */
	OBJECT,

	/**
	 * Remove the expiration from a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: generic<br>
	 */
	PERSIST,

	/**
	 * Set a key's time to live in milliseconds.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>milliseconds(integer)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: generic<br>
	 */
	PEXPIRE,

	/**
	 * Set the expiration for a key as a UNIX timestamp specified in
	 * milliseconds.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>milliseconds-timestamp(posix time)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: generic<br>
	 */
	PEXPIREAT,

	/**
	 * Ping the server.<br>
	 * since: 1.0.0<br>
	 * group: connection<br>
	 */
	PING,

	/**
	 * Set the value and expiration in milliseconds of a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>milliseconds(integer)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: string<br>
	 */
	PSETEX,

	/**
	 * Listen for messages published to channels matching the given patterns.<br>
	 * arguments:
	 * <ol>
	 * <li>["pattern"](["pattern"])</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: pubsub<br>
	 */
	PSUBSCRIBE,

	/**
	 * Get the time to live for a key in milliseconds.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: generic<br>
	 */
	PTTL,

	/**
	 * Post a message to a channel.<br>
	 * arguments:
	 * <ol>
	 * <li>channel(string)</li>
	 * <li>message(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: pubsub<br>
	 */
	PUBLISH,

	/**
	 * Stop listening for messages posted to channels matching the given
	 * patterns.<br>
	 * arguments:
	 * <ol>
	 * <li>pattern(pattern) optional</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: pubsub<br>
	 */
	PUNSUBSCRIBE,

	/**
	 * Close the connection.<br>
	 * since: 1.0.0<br>
	 * group: connection<br>
	 */
	QUIT,

	/**
	 * Return a random key from the keyspace.<br>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	RANDOMKEY,

	/**
	 * Rename a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>newkey(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	RENAME,

	/**
	 * Rename a key, only if the new key does not exist.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>newkey(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	RENAMENX,

	/**
	 * Create a key using the provided serialized value, previously obtained
	 * using DUMP..<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>ttl(integer)</li>
	 * <li>serialized-value(string)</li>
	 * </ol>
	 * since: 2.6.0<br>
	 * group: generic<br>
	 */
	RESTORE,

	/**
	 * Remove and get the last element in a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	RPOP,

	/**
	 * Remove the last element in a list, append it to another list and return
	 * it.<br>
	 * arguments:
	 * <ol>
	 * <li>source(key)</li>
	 * <li>destination(key)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: list<br>
	 */
	RPOPLPUSH,

	/**
	 * Append one or multiple values to a list.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: list<br>
	 */
	RPUSH,

	/**
	 * Append a value to a list, only if the list exists.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: list<br>
	 */
	RPUSHX,

	/**
	 * Add one or more members to a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SADD,

	/**
	 * Synchronously save the dataset to disk.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	SAVE,

	/**
	 * Get the number of members in a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SCARD,

	/**
	 * Subtract multiple sets.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SDIFF,

	/**
	 * Subtract multiple sets and store the resulting set in a key.<br>
	 * arguments:
	 * <ol>
	 * <li>destination(key)</li>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SDIFFSTORE,

	/**
	 * Change the selected database for the current connection.<br>
	 * arguments:
	 * <ol>
	 * <li>index(integer)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: connection<br>
	 */
	SELECT,

	/**
	 * Set the string value of a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	SET,

	/**
	 * Sets or clears the bit at offset in the string value stored at key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>offset(integer)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: string<br>
	 */
	SETBIT,

	/**
	 * Set the value and expiration of a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>seconds(integer)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: string<br>
	 */
	SETEX,

	/**
	 * Set the value of a key, only if the key does not exist.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: string<br>
	 */
	SETNX,

	/**
	 * Overwrite part of a string at key starting at the specified offset.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>offset(integer)</li>
	 * <li>value(string)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: string<br>
	 */
	SETRANGE,

	/**
	 * Synchronously save the dataset to disk and then shut down the server.<br>
	 * arguments:
	 * <ol>
	 * <li>NOSAVE(enum) optional</li>
	 * <li>SAVE(enum) optional</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	SHUTDOWN,

	/**
	 * Intersect multiple sets.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SINTER,

	/**
	 * Intersect multiple sets and store the resulting set in a key.<br>
	 * arguments:
	 * <ol>
	 * <li>destination(key)</li>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SINTERSTORE,

	/**
	 * Determine if a given value is a member of a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SISMEMBER,

	/**
	 * Make the server a slave of another instance, or promote it as master.<br>
	 * arguments:
	 * <ol>
	 * <li>host(string)</li>
	 * <li>port(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	SLAVEOF,

	/**
	 * Manages the Redis slow queries log.<br>
	 * arguments:
	 * <ol>
	 * <li>subcommand(string)</li>
	 * <li>argument(string) optional</li>
	 * </ol>
	 * since: 2.2.12<br>
	 * group: server<br>
	 */
	SLOWLOG,

	/**
	 * Get all the members in a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SMEMBERS,

	/**
	 * Move a member from one set to another.<br>
	 * arguments:
	 * <ol>
	 * <li>source(key)</li>
	 * <li>destination(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SMOVE,

	/**
	 * Sort the elements in a list, set or sorted set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>pattern(pattern) optional</li>
	 * <li>["offset", "count"](["integer", "integer"]) optional</li>
	 * <li>pattern(string) optional</li>
	 * <li>order(enum) optional</li>
	 * <li>sorting(enum) optional</li>
	 * <li>destination(key) optional</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	SORT,

	/**
	 * Remove and return a random member from a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SPOP,

	/**
	 * Get a random member from a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SRANDMEMBER,

	/**
	 * Remove one or more members from a set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SREM,

	/**
	 * Get the length of the value stored in a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: string<br>
	 */
	STRLEN,

	/**
	 * Listen for messages published to the given channels.<br>
	 * arguments:
	 * <ol>
	 * <li>["channel"](["string"])</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: pubsub<br>
	 */
	SUBSCRIBE,

	/**
	 * Add multiple sets.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SUNION,

	/**
	 * Add multiple sets and store the resulting set in a key.<br>
	 * arguments:
	 * <ol>
	 * <li>destination(key)</li>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: set<br>
	 */
	SUNIONSTORE,

	/**
	 * Internal command used for replication.<br>
	 * since: 1.0.0<br>
	 * group: server<br>
	 */
	SYNC,

	/**
	 * Return the current server time.<br>
	 * since: 2.6.0<br>
	 * group: server<br>
	 */
	TIME,

	/**
	 * Get the time to live for a key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	TTL,

	/**
	 * Determine the type stored at key.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.0.0<br>
	 * group: generic<br>
	 */
	TYPE,

	/**
	 * Stop listening for messages posted to the given channels.<br>
	 * arguments:
	 * <ol>
	 * <li>channel(string) optional</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: pubsub<br>
	 */
	UNSUBSCRIBE,

	/**
	 * Forget about all watched keys.<br>
	 * since: 2.2.0<br>
	 * group: transactions<br>
	 */
	UNWATCH,

	/**
	 * Watch the given keys to determine execution of the MULTI/EXEC block.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: transactions<br>
	 */
	WATCH,

	/**
	 * Add one or more members to a sorted set, or update its score if it
	 * already exists.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>score(double)</li>
	 * <li>member(string)</li>
	 * <li>score(double) optional</li>
	 * <li>member(string) optional</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZADD,

	/**
	 * Get the number of members in a sorted set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZCARD,

	/**
	 * Count the members in a sorted set with scores within the given values.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>min(double)</li>
	 * <li>max(double)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: sorted_set<br>
	 */
	ZCOUNT,

	/**
	 * Increment the score of a member in a sorted set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>increment(integer)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZINCRBY,

	/**
	 * Intersect multiple sorted sets and store the resulting sorted set in a
	 * new key.<br>
	 * arguments:
	 * <ol>
	 * <li>destination(key)</li>
	 * <li>numkeys(integer)</li>
	 * <li>key(key)</li>
	 * <li>weight(integer) optional</li>
	 * <li>aggregate(enum) optional</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: sorted_set<br>
	 */
	ZINTERSTORE,

	/**
	 * Return a range of members in a sorted set, by index.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer)</li>
	 * <li>stop(integer)</li>
	 * <li>withscores(enum) optional</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZRANGE,

	/**
	 * Return a range of members in a sorted set, by score.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>min(double)</li>
	 * <li>max(double)</li>
	 * <li>withscores(enum) optional</li>
	 * <li>["offset", "count"](["integer", "integer"]) optional</li>
	 * </ol>
	 * since: 1.0.5<br>
	 * group: sorted_set<br>
	 */
	ZRANGEBYSCORE,

	/**
	 * Determine the index of a member in a sorted set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: sorted_set<br>
	 */
	ZRANK,

	/**
	 * Remove one or more members from a sorted set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZREM,

	/**
	 * Remove all members in a sorted set within the given indexes.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer)</li>
	 * <li>stop(integer)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: sorted_set<br>
	 */
	ZREMRANGEBYRANK,

	/**
	 * Remove all members in a sorted set within the given scores.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>min(double)</li>
	 * <li>max(double)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZREMRANGEBYSCORE,

	/**
	 * Return a range of members in a sorted set, by index, with scores ordered
	 * from high to low.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>start(integer)</li>
	 * <li>stop(integer)</li>
	 * <li>withscores(enum) optional</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZREVRANGE,

	/**
	 * Return a range of members in a sorted set, by score, with scores ordered
	 * from high to low.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>max(double)</li>
	 * <li>min(double)</li>
	 * <li>withscores(enum) optional</li>
	 * <li>["offset", "count"](["integer", "integer"]) optional</li>
	 * </ol>
	 * since: 2.2.0<br>
	 * group: sorted_set<br>
	 */
	ZREVRANGEBYSCORE,

	/**
	 * Determine the index of a member in a sorted set, with scores ordered from
	 * high to low.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: sorted_set<br>
	 */
	ZREVRANK,

	/**
	 * Get the score associated with the given member in a sorted set.<br>
	 * arguments:
	 * <ol>
	 * <li>key(key)</li>
	 * <li>member(string)</li>
	 * </ol>
	 * since: 1.2.0<br>
	 * group: sorted_set<br>
	 */
	ZSCORE,

	/**
	 * Add multiple sorted sets and store the resulting sorted set in a new key.<br>
	 * arguments:
	 * <ol>
	 * <li>destination(key)</li>
	 * <li>numkeys(integer)</li>
	 * <li>key(key)</li>
	 * <li>weight(integer) optional</li>
	 * <li>aggregate(enum) optional</li>
	 * </ol>
	 * since: 2.0.0<br>
	 * group: sorted_set<br>
	 */
	ZUNIONSTORE;

	public final byte[] bytes;

	CommandType() {
		bytes = Codec.iso8859_1(this.name());
	}
}