package com.Interview.thread;

import cn.hutool.core.util.RandomUtil;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName MapDemo @Author guoxiaobing @Date 2020/6/17 9:14 @Version 1.0 @Description 多线程下
 * map是不安全的我们来演示下
 */
public class MapDemo {
  private Map map = new HashMap();

  public static void main(String[] args) {
    //
      MapDemo mapDemo = new MapDemo();
     // mapDemo.handlerMap();
      //notSafe();
      test();
  }

  public void handlerMap() {
      int i=0;
      for(;i<30;i++){
          new Thread(()->{
                  map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                  System.out.println(map.toString());
          },String.valueOf(i)).start();
      }

  }

    public static void notSafe() {
       Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new ConcurrentHashMap<>();
        //Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }, "Thread " + i).start();
        }
    }
    public static void test(){
      Map map = new HashMap();
      map.put(null,"1");
      map.put(null,"2");
      map.put(1,null);
      map.put(2,null);
      System.out.println(map.toString());
    }
    /**
     * 面试必问之Map
     *java.util.ConcurrentModificationException
     * 初始化是16 装载因子0.75 容量最大2的30次方
     * 树化，当容量达到64且链表的长度达到8时进行树化，当链表的长度小于6时反树化。
     */

}
