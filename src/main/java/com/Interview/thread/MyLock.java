package com.Interview.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: 2
 * @description: 自旋锁
 * @author: guoxiaobing
 * @create: 2020-08-03 22:33
 */
public class MyLock {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        new Thread(()->{
            try {
                Thread.sleep(3000);
                myLock.lock();
                System.out.println("我获取了锁哦，A");
                Thread.sleep(2000);
                myLock.unLock();
            } catch (Exception e) {
            }
        },"A").start();

        new Thread(()->{
            try {
                myLock.lock();
                System.out.println("我获取了锁哦，B");
                Thread.sleep(5000);
                myLock.unLock();
            } catch (Exception e) {
            }
        },"B").start();
    }
    public void lock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){ }
        System.out.println(thread.getName()+"已经获取了锁....................");
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        while (atomicReference.compareAndSet(thread,null)){};
        System.out.println(thread.getName()+"已经释放了锁...................");
    }
}