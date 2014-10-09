package cn.mob.gamerec.util;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/9/29
 */
public class RedisPool {
    private final static ShardedJedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        List<JedisShardInfo> shardInfoList = new ArrayList<JedisShardInfo>();
        JedisShardInfo jedisShardInfo = new JedisShardInfo("");
        shardInfoList.add(jedisShardInfo);
        pool = new ShardedJedisPool(config, shardInfoList);
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }
}
