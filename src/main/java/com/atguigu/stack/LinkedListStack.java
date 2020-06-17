package com.atguigu.stack;

/**
 * @ClassName LinkedListStack @Author guoxiaobing @Date 2020/6/17 17:00 @Version 1.0 @Description
 * 链表模拟栈
 */
public class LinkedListStack {
  public static void main(String[] args) {
    //
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
    int i = 1;
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
  public Boolean isEmpty(){
      return head.getNext() == null;
  }

  public void push(int val){
      if(isFull()){
          System.out.println("栈满不能存");
          return;
      }
      Node node = new Node(val);
      Node temp = head.getNext();
      while (temp!=null){
          temp =temp.getNext();
      }
      temp.setNext(node);
  }

  public int pop(){
      if(isEmpty()){
          throw new RuntimeException("栈空不能取");
      }
      Node temp = head;
      if(temp.getNext()!=null){
          temp = temp.getNext();
      }

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
