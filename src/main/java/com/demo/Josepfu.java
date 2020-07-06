package com.demo;

/**
 * @ClassName Josepfu @Author guoxiaobing @Date 2020/7/6 18:42 @Version 1.0 @Description 约瑟夫问题
 * 回顾环形链表
 */
public class Josepfu {
  public static void main(String[] args) {
    CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
    circleSingleLinkedList.addBoys(5);
    circleSingleLinkedList.addBoys(5);
    System.out.println(circleSingleLinkedList.size());
  }
}

class CircleSingleLinkedList {
  private Boy first = new Boy(-1);

  public void addBoy(int num) {
    Boy temp = first;
    for (int i = 0; i < num; i++) {
      Boy boy = new Boy(i);
      if (first.getNo() == -1) {
        temp = boy;
        first = boy;
        temp.setNext(temp);
        continue;
      }
      temp.setNext(boy);
      boy.setNext(first);

      temp = boy;
    }
  }

  public void addBoys(int num) {
    Boy temp = first;
    int sum = size();
    if(sum>0){
        while (temp.getNext().getNo()!=first.getNo()){
            temp=temp.getNext();
        }
    }

    for (int i = sum; i < sum+num; i++) {
      Boy boy = new Boy(i);
      if (first.getNo() == -1) {
        temp = boy;
        first = boy;
        temp.setNext(temp);
        continue;
      }
      temp.setNext(boy);
      boy.setNext(first);

      temp = boy;
    }
  }

  public int size() {
    if (-1 == first.getNo()) {
      return 0;
    }
    Boy temp = first.getNext();
    int sum = 1;
    while (temp.getNo() != first.getNo()) {
      temp = temp.getNext();
      sum++;
    }
    return sum;
  }
}

class Boy {
  private int no;
  private Boy next;

  public Boy(int no) {
    this.no = no;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Boy getNext() {
    return next;
  }

  public void setNext(Boy next) {
    this.next = next;
  }
}
