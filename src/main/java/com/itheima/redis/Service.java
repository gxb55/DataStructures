package com.itheima.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

import java.awt.font.TextHitInfo;
import java.io.Serializable;

/**
 * @ClassName Service @Author guoxiaobing @Date 2020/6/4 20:01 @Version 1.0 @Description 黑马 redis
 */
public class Service {
  public int service(String name) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    try {
      String s = jedis.get("user:" + name);
      if (s == null) {
        jedis.setex("user:" + name, 20, Long.MAX_VALUE - 20 + "");
      } else {
        jedis.incr("user:" + name);
        business();
      }
      return 1;
    } catch (JedisDataException e) {
      System.out.println("user:" + name + "用户的使用已经达到上限！！");
      return -1;
    } finally {
      jedis.close();
    }
  }

  public void business() {
    System.out.println("你调用了我的接口");
  }

  public static void main(String[] args) {
    Mythread mythread = new Mythread();
    mythread.start();
  }
}

class Mythread extends Thread {
  Service service = new Service();

  @Override
  public void run() {
    while (true) {
      int i = service.service("普通用户");
      if (i == -1) {
        System.out.println("接口无法调用了草！！");
        break;
      }
    }
  }
}
