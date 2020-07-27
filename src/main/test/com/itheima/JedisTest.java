package com.itheima;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JedisTest @Author guoxiaobing @Date 2020/6/4 19:45 @Version 1.0 @Description
 * redis的jedis测试类
 */
public class JedisTest {
  private Jedis jedis = null;
//10.200.2.105:7000
  @Before
  public void connectJedis() {
    jedis = new Jedis("10.200.2.105", 7000);
  }

  @Test
  public void testJedis() {
    /** 1.链接redis 2.操作redis 3.关闭链接 */
    String name = jedis.get("18595917022");

    System.out.println(name);
  }

    @Test
    public void testListJedis() {
        /** 1.链接redis 2.操作redis 3.关闭链接 */
        jedis.lpush("jedisList", "guoxb","guoxab","d","ee","dc");
        List list = jedis.lrange("jedisList",0,-1);
        System.out.println(list.toString());
    }

  @Test
  public void testMapJedis() {
    /** 1.链接redis 2.操作redis 3.关闭链接 */
    HashMap map = new HashMap();
    map.put("a","1");
    map.put("c","11");
    map.put("d","10");
    jedis.hmset("jedisMap", map);
    Map<String, String> jedisMap = jedis.hgetAll("jedisMap");
    System.out.println(jedisMap.toString());
  }

  @After
  public void closeJedis() {
    jedis.close();
  }
}
