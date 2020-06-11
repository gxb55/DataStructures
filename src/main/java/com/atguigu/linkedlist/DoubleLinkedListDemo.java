package com.atguigu.linkedlist;

/**
 * @ClassName DoubleLinkedListDemo @Author guoxiaobing @Date 2020/6/11 19:58 @Version
 * 1.0 @Description 双向链表
 */
public class DoubleLinkedListDemo {
  public static void main(String[] args) {
    //
  }
}

class DoubleSingleLinkedList {
  private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

  public DoubleHeroNode getHead() {
    return head;
  }

  public void list() {
    if (head.next == null) {
      System.out.println("链表是空的！");
    }
    DoubleHeroNode temp = head.next;
    while (temp != null) {
      System.out.println(temp);
      temp = temp.next;
    }
  }
}

class DoubleHeroNode {
  public int no;
  public String name;
  public String nickName;
  public DoubleHeroNode next; // 下一个
  public DoubleHeroNode pre; // 上一个

  public DoubleHeroNode(int no, String name, String nickName) {
    this.name = name;
    this.nickName = nickName;
    this.no = no;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public DoubleHeroNode getNext() {
    return next;
  }

  public void setNext(DoubleHeroNode next) {
    this.next = next;
  }

  public DoubleHeroNode getPre() {
    return pre;
  }

  public void setPre(DoubleHeroNode pre) {
    this.pre = pre;
  }

  @Override
  public String toString() {
    return "DoubleHeroNode{"
        + "no="
        + no
        + ", name='"
        + name
        + '\''
        + ", nickName='"
        + nickName
        + '\''
        + ", next="
        + next
        + ", pre="
        + pre
        + '}';
  }
}
