package com.demo;

import java.util.Arrays;

/**
 * @ClassName CircleArrayQueueDemo @Author guoxiaobing @Date 2020/7/2 9:24 @Version 1.0 @Description
 * 环形队列
 */
public class CircleArrayQueueDemo {
  public static void main(String[] args) {
    Queue queue = new Queue(5);
    queue.put(0);
    queue.put(1);
    queue.put(2);
    queue.put(3);
    queue.put(4);
    System.out.println(queue.size());
    queue.loop();
   /* System.out.println(queue.toString());
    System.out.println(queue.get());
    System.out.println(queue.get());
    System.out.println(queue.get());
    queue.put(78);
    queue.put(79);
    queue.put(80);
    System.out.println(queue.toString());
    System.out.println(queue.isFull());
    *//* System.out.println(queue.get());
    System.out.println(queue.get());*//*
      queue.loop();*/
  }
}

class Queue {
  public int max; // 最大容量队列的
  public int[] array = null; // 我们用数组来模拟队列
  public int head = 0; // 头部数据即队列最前面 下标初始位置
  public int tail = 0; // 尾部数据，即队列最下面

  public Queue(int max) {
    this.max = max + 1;
    array = new int[this.max];
  }

  public boolean isEmpty() { // 头跟尾值相当则说明队列是空的
    return head == tail;
  }

  public boolean isFull() {
    return (head + 1) % max == tail;
  }

  public void put(int n) {
    if (isFull()) {
      return;
    }
    array[head] = n;
    head = (head + 1) % max;
  }

  public int get() {
    if (isEmpty()) {
      throw new RuntimeException("队列是空的！");
    }
    int t = array[tail];
      array[tail] = 0;
    tail = (tail + 1) % max;
    return t;
  }

    /**
     * 巧妙之处
     *  1.如果 head 大于 tail 则队列的长度就是 head -tail ，这个时候(head -tail + max)% max = head -tail 结果是正确的
     *  2.如果head小于tail 其实队列的长度就是  head -tail 是他们之间相差的距离，但是这个距离不是队列的长度，队列的长度应该是
     *  max-(tail-head) --> max-tail+head 即 head+max-tail 这种情况其实不用取余，但是为了不判断head和tail这里采用统一的算法
     *  (head -tail + max)% max
     *
     *  (head-tail+max)%max
     *      如果 head>tail head-tail
     *      如果head<tail max-(tail-head) 因为是环形的所以你想想
     * @return
     */
  public int size(){
      return (head-tail+max)%max;
  }

  public void loop(){
      if (isEmpty()){
          System.out.println("队列是空的，没有数据");
          return;
      }
     for(int i=tail;i<tail+size();i++){
         System.out.print(array[i%max]+" -- ");
     }


  }
  @Override
  public String toString() {
    return "Queue{"
        + "max="
        + max
        + ", array="
        + Arrays.toString(array)
        + ", head="
        + head
        + ", tail="
        + tail
        + '}';
  }
}
