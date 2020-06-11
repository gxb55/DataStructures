package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @ClassName TestStack
 * @Author guoxiaobing
 * @Date 2020/6/11 19:11
 * @Version 1.0
 * @Description 简单演示stack
 */
public class TestStack {
  public static void main(String[] args) {
      Stack<String> stack = new Stack<>();
      stack.add("jack");
      stack.add("tom");
      stack.add("smith");
      while (stack.size()>0){
          System.out.println(stack.pop());
      }
  }
}
