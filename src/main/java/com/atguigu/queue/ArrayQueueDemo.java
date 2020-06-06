package com.atguigu.queue;

import java.util.Arrays;

/**
 * @program: prepare
 * @description: 用数组来模拟队列
 * @author: guoxiaobing
 * @create: 2020-06-04 22:19
 */
public class ArrayQueueDemo {
  public static void main(String[] args) throws Exception {
    // testArrayQueue();
    ArrayQueue1 queue = new ArrayQueue1(3);

    queue.add(5);
    queue.add(4);
    queue.add(4);
    System.out.println(queue.get());
    System.out.println(queue.get());
    System.out.println(queue.get());

    queue.add(4);
    queue.loop();
    System.out.println(queue.isEmpty());
    System.out.println(queue.isFull());
  }

  private static void testArrayQueue() throws Exception {
    ArrayQueue queue = new ArrayQueue(5);
    queue.add(1);
    queue.add(12);
    queue.add(123);
    queue.add(124);
    queue.add(125);
    queue.add(126);
    int i = queue.get();
    System.out.println(i);
    i = queue.get();
    System.out.println(i);
    i = queue.get();
    System.out.println(i);
    i = queue.get();
    System.out.println(i);
    i = queue.get();
    System.out.println(i);
  }
}

/** 尚硅谷的思虑 */
class ArrayQueue1 {
  private int[] arr;
  private int front; // 下标的前一个位置即 -1
  private int near; // 下标的位置 -1;-1的原因是因为每次都是先++在使用，-1++ 第一个位置就是0；
  private int maxSize;

  public ArrayQueue1(int maxSize) {
    this.maxSize = maxSize;
    arr = new int[maxSize];
    near = -1;
    front = -1;
  }

  public Boolean isFull() {
    return near == maxSize - 1;
  }

  public Boolean isEmpty() {
    return near == front;
  }

  public void add(int num) {
    if (isFull()) {
      System.out.println("队列满了无法存入！");
    } else {
      near++;
      arr[near] = num;
    }
  }

  public int get() {
    if (isEmpty()) {
      throw new RuntimeException("队列中没有值！");
    } else {
      front++;
      return arr[front];
    }
  }

  public int peek() {
    if (isEmpty()) {
      throw new RuntimeException("队列中没有值！");
    } else {
      return arr[front + 1];
    }
  }

  public void loop() {

    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + "--");
    }
    System.out.println();
  }
}

/** 我的方法，每次取得时候迭代一次保证最新的值在下标为0的位置 只用一个表示就是near */
class ArrayQueue {
  private int maxSize; // 最大容量
  private int rear; // 队列尾部 下标刚好有值
  private int[] arr; // 数组

  public ArrayQueue(int maxSize) {
    rear = -1;
    this.maxSize = maxSize;
    arr = new int[maxSize];
  }

  public boolean isEmpty() {
    return rear == -1;
  }

  public boolean isFull() {
    return rear == maxSize - 1;
  }

  public boolean add(int val) {
    if (!isFull()) { // 队列没有满则可以存入
      ++rear;
      arr[rear] = val;
    } else {
      System.out.println("存不进去了");
      return false;
    }
    return true;
  }

  public int get() throws Exception {
    if (isEmpty()) {
      throw new Exception("队列中没有值了！");
    }
    int val = arr[0];
    for (int i = 0; i < arr.length - 1; i++) {
      arr[i] = arr[i + 1];
    }
    rear--;
    return val;
  }
}
