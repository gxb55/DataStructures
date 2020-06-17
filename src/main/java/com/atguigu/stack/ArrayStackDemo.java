package com.atguigu.stack;

/**
 * @ClassName ArrayStackDemo
 * @Author guoxiaobing
 * @Date 2020/6/17 16:28
 * @Version 1.0
 * @Description 数组模拟栈
 */
public class ArrayStackDemo {
  public static void main(String[] args) {
      ArrayStack stack = new ArrayStack(6);
      stack.push(1);
      stack.push(2);
      stack.push(3);
      stack.push(4);
      stack.push(5);
      stack.push(6);
      stack.push(7);
      stack.loop();
      System.out.println();
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println(stack.pop());



  }
}
class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;//栈鼎 默认 -1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize -1;
    }
    public boolean isEmpty(){
        return top == -1;
    }
    public void push(int val){
        if(isFull()){
            return;
        }
        top++;
        stack[top] = val;
    }
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int val = stack[top];
        top--;
        return val;
    }

    public void loop(){
        if(isEmpty()){
            return;
        }
        for(int i= top;i>-1;i--){
            System.out.println(stack[i]);
        }
    }

}
