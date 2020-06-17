package com.atguigu.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Josepfu @Author guoxiaobing @Date 2020/6/16 17:33 @Version 1.0 @Description 约瑟夫问题，环形链表
 */
public class Josepfu {
  public static void main(String[] args) {
    CircleSingleLinkedList circle = new CircleSingleLinkedList();
    circle.addBoy(125);
    circle.list();
    circle.countBoy(10, 20, 125);
  }
}

class CircleSingleLinkedList {
  private Boy first = new Boy(-1);

  public void addBoy(int nums) {
    if (nums < 1) {
      return;
    }
    Boy currBoy = null;
    for (int i = 1; i <= nums; i++) {
      Boy next = new Boy(i);
      if (first.getNo() == -1) {
        first = next;
        first.setNext(first);
        currBoy = first;
        continue;
      }
      currBoy.setNext(next);
      next.setNext(first);

      currBoy = next;
    }
  }

  /**
   * @param startNo 从哪个小孩开始数
   * @param countNum 数几下
   * @param nums 小孩的总数
   */
  public void countBoy(int startNo, int countNum, int nums) {
    if (first.getNo() == -1 || startNo < 0 || startNo > nums) {
      System.out.println("参数不合理！");
    }
    Boy helper = first; // 数数小孩的前一个节点，因为要借助他来删除数数的小孩
    Boy temp = first; // 数数的小孩
    while (true) {//初始化的时候将helper移到队列的尾部即first的前一个位置
      if (helper.getNext().getNo() == temp.getNo()) {
        break;
      }
        helper = helper.getNext();
    }

    for(int i=0;i<startNo-1;i++){
        temp = temp.getNext();
        helper =helper.getNext();
    }
    while (true) {
      for (int i = 0; i < countNum - 1; i++) {
        helper = helper.getNext();
        temp = temp.getNext();
      }
      // 找到了这个小孩 让他出队
      System.out.println(temp.getNo()+"  出圈了");
      temp = temp.getNext();
      helper.setNext(temp);
      if(helper.getNo() == temp.getNo()){
        System.out.println(temp.getNo()+"  最后出圈了");
          break;
      }
    }

  }

  public void list() {
    if (first.getNo() == -1) {
      System.out.println("环形链表为空！");
      return;
    }
    Boy temp = first;
    while (true) {
      System.out.println(temp.getNo());
      if (temp.getNext().getNo() == first.getNo()) { // 如果便利到了最后一个则结束了;
        break;
      }
      temp = temp.getNext();
    }
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
