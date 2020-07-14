package com.demo;

/**
 * @ClassName Josepfu @Author guoxiaobing @Date 2020/7/6 18:42 @Version 1.0 @Description 约瑟夫问题
 * 回顾环形链表
 * 约瑟夫问题是个有名的问题：N个人围成一圈，从第一个开始报数，第M个将被杀掉，最后剩下一个，其余人都将被杀掉。
 */
public class Josepfu {
  public static void main(String[] args) {
    CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
    circleSingleLinkedList.addBoys(41);
    System.out.println(circleSingleLinkedList.size()); //16 ,31
    circleSingleLinkedList.countBoy(1,3,41);
  }
}

class CircleSingleLinkedList {
  private Boy first = new Boy(-1);

    /**
     * @param startNo 从哪个小孩开始数
     * @param countNum 数几下
     * @param nums 小孩的总数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if(first.getNo() == -1){
            return;
        }
        Boy temp = first;
        while (true){
            if(startNo==1){
                break;
            }
            temp =temp.getNext();
            startNo--;
        }
        int t =countNum;
        //temp 就是开始的小孩子
        while (temp.getNext().getNo() != temp.getNo()){//剩下最后一个小孩子
            t--;
            if(t==1){
                System.out.println("出队的小孩是："+temp.getNext().getNo());
                temp.setNext(temp.getNext().getNext());
                t = countNum;
            }
            temp = temp.getNext();
        }
    }

  public void addBoy(int num) {
    Boy temp = first;
    for (int i = 1; i <= num; i++) {
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

    for (int i = sum+1; i <= sum+num; i++) {
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
