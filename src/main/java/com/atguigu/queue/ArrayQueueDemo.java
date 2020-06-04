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
        ArrayQueue queue = new ArrayQueue(10);
        queue.add(1);
        queue.add(12);
        queue.add(123);
        int i = queue.get();
         i = queue.get();
         i = queue.get();
         i = queue.get();
        System.out.println(i);
    }
}

class ArrayQueue {
    private int maxSize;//最大容量
    private int front;//队列头 下标
    private int rear;//队列尾部 下标
    private int[] arr;//数组

    public ArrayQueue(int maxSize) {
        front = 0;
        rear = -1;
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean add(int val) {
        if (maxSize - 1 >= rear) {//队列没有满则可以存入
            ++rear;
            arr[rear] = val;
        } else {
            return false;
        }
        return true;
    }

    public int get() throws Exception {
        if(front == rear){
            throw new Exception("队列中没有值了！");
        }
        int val = arr[front];
        front++;
        for(int i=0;i<arr.length-1;i++){
            arr[i]=arr[i+1];
        }
        rear--;
        return val;
    }
}