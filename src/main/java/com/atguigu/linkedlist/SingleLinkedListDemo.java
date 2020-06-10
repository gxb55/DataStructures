package com.atguigu.linkedlist;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @ClassName SingleLinkedList @Author guoxiaobing @Date 2020/6/9 19:27 @Version 1.0 @Description
 * 单链表
 */
public class SingleLinkedListDemo {
  public static void main(String[] args) {
    /*HeroNode node = new HeroNode(1, "宋江", "及时雨");
    node.add(new HeroNode(2, "吴用", "智多星"));
    node.add(new HeroNode(3, "林冲", "豹子头"));
    node.add(new HeroNode(4, "鲁达", "花和尚"));
    node.loop();*/
    SingleLinkedList linkedList = new SingleLinkedList();
    /*linkedList.add(new HeroNode(1, "宋江", "及时雨"));
    linkedList.add(new HeroNode(2, "吴用", "智多星"));
    linkedList.add(new HeroNode(3, "林冲", "豹子头"));
    linkedList.add(new HeroNode(4, "鲁达", "花和尚"));*/


    linkedList.addByOrder(new HeroNode(3, "林冲", "豹子头"));
    linkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
    linkedList.addByOrder(new HeroNode(2, "吴用", "智多星"));
    linkedList.addByOrder(new HeroNode(4, "鲁达", "花和尚"));
    linkedList.loop();
    System.out.println(linkedList.length());
  }
}

class SingleLinkedList {
  private HeroNode head = new HeroNode(0, "", "");

  public void add(HeroNode node) {
    HeroNode temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = node;
  }

  /**
   * 按照no的顺序来添加元素
   *
   * @param node
   */
  public void addByOrder(HeroNode node) {
    int num = node.no;
    int otherNum;
    HeroNode temp = head;
    while (true){
      if(temp.next == null){
        break;
      }

      temp =temp.next;

    }
    //赋值
    temp.next=node;
  }

  public void loop() {
    if (head.next == null) {
      System.out.println("空的。。");
    }
    HeroNode temp = head.next;
    while (true) {
      if (temp == null) {
        break;
      }
      System.out.println(temp);
      temp = temp.next;
    }
  }

  public int length() {
    if (head.next == null) {
      return 0;
    }
    int len = 0;
    HeroNode temp = head.next;
    while (true) {
      if (temp == null) {
        break;
      }
      len++;
      temp = temp.next;
    }
    return len;
  }
}

class HeroNode {
  public int no;
  public String name;
  public String nickName;
  public HeroNode next;

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

  public HeroNode(int no, String name, String nickName) {
    this.name = name;
    this.nickName = nickName;
    this.no = no;
  }

  public void add(HeroNode node) {
    HeroNode newNode = this;
    while (newNode.next != null) {
      newNode = newNode.next;
    }
    newNode.next = node;
  }

  public void loop() {
    HeroNode newNode = this;
    while (newNode != null) {
      System.out.println(newNode.toString());
      newNode = newNode.next;
    }
  }

  @Override
  public String toString() {
    return "HeroNode{"
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
        + '}';
  }
}
