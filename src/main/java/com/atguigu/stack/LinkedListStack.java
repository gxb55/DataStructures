package com.atguigu.stack;

/**
 * @ClassName LinkedListStack @Author guoxiaobing @Date 2020/6/17 17:00 @Version 1.0 @Description
 *
 * 栈 stack 先进后出 从栈顶进入栈顶出，刚进来的元素压入栈底 所以用数组和链表模拟比较方便
 * 链表模拟栈
 */
public class LinkedListStack {
  public static void main(String[] args) {
      LinkedStack stack = new LinkedStack(5);
      stack.push(1);
      stack.push(2);

      int size = stack.size();
      System.out.println(size);
      System.out.println("便利。。。");
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      stack.push(3);
      stack.push(4);
      stack.push(1);
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println(stack.pop());
  }
}

class LinkedStack {
  private Node head = new Node(0);
  private int maxSize;

  public LinkedStack(int maxSize) {
    this.maxSize = maxSize;
  }

  public int size() {
    if (head.getNext() == null) {
      return 0;
    }
    int i = 0;
    Node temp = head.getNext();
    while (temp != null) {
      i++;
      temp = temp.getNext();
    }
    return i;
  }

  public Boolean isFull() {
    return size() == maxSize;
  }

  public Boolean isEmpty() {
    return head.getNext() == null;
  }

  public void push(int val) {
    if (isFull()) {
      System.out.println("栈满不能存");
      return;
    }
    Node node = new Node(val);
    Node temp = head;
    while (temp.getNext() != null) {
      temp = temp.getNext();
    }
    temp.setNext(node);
  }

  public int pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈空不能取");
    }
    Node temp = head;
    while (temp.getNext().getNext() != null) {
      temp = temp.getNext();
    }
    int val = temp.getNext().getNo();
    temp.setNext(null);
    return val;
  }
}

class Node {
  private int no;
  private Node next;

  public Node(int no) {
    this.no = no;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }
}
