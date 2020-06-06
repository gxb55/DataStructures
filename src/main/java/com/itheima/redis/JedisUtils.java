package com.itheima.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisUtils
 * @Author guoxiaobing
 * @Date 2020/6/5 18:52
 * @Version 1.0
 * @Description TODO
 */
public class JedisUtils {
    private JedisUtils(){};
    public static  JedisPool jedisPool = null;
    static {
        JedisPoolConfig pool = new JedisPoolConfig();
        pool.setMaxTotal(30);
        pool.setMaxIdle(10);
         jedisPool = new JedisPool(pool,"127.0.0.1",6379);
    }
    public static Jedis getJedis(){

        return jedisPool.getResource();
    }

  public static void main(String[] args) {
    JedisUtils.getJedis();
  }
}
