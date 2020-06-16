package com.atguigu.linkedlist;

/**
 * @ClassName DoubleLinkedListDemo @Author guoxiaobing @Date 2020/6/11 19:58 @Version
 * 1.0 @Description 双向链表
 */
public class DoubleLinkedListDemo {
  public static void main(String[] args) {
    DoubleSingleLinkedList linkedList = new DoubleSingleLinkedList();
    linkedList.addByOrder(new DoubleHeroNode(1, "及时雨", "宋江"));
    linkedList.addByOrder(new DoubleHeroNode(2, "吴用", "智多星"));
    linkedList.addByOrder(new DoubleHeroNode(4, "鲁达", "花和尚"));
    linkedList.addByOrder(new DoubleHeroNode(3, "林冲", "豹子头"));
    linkedList.addByOrder(new DoubleHeroNode(8, "卢俊义", "玉麒麟"));
    linkedList.addByOrder(new DoubleHeroNode(5, "孙二娘", "母老虎"));

    linkedList.del(50);
    linkedList.list();
  }
}

class DoubleSingleLinkedList {
  private DoubleHeroNode head = new DoubleHeroNode(0, "", "");

  public DoubleHeroNode getHead() {
    return head;
  }

  public void add(DoubleHeroNode node) {
    if (node == null) {
      return;
    }
    DoubleHeroNode temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = node;
    node.pre = temp;
  }

  public void addByOrder(DoubleHeroNode node) {
    if (node == null) {
      return;
    }
    int num = node.getNo();
    DoubleHeroNode temp = head;
    while (true) {
      if (temp.next == null) {
        temp.next = node;
        node.pre = temp;
        break;
      }
      if (num < temp.next.no) { // 处理比前一个小的
        DoubleHeroNode next = temp.next;
        temp.next = node;
        node.pre = temp;

        next.pre = node;
        node.next = next;
        break;
      }
      temp = temp.next;
    }
  }

  public void del(int no) {
    if (head.next == null) {
      return;
    }
    DoubleHeroNode next = head.next;
    while (next != null) {
      if (next.getNo() == no) {
        DoubleHeroNode pre = next.getPre();
        pre.next = next.next;
        if (next.next != null) { // 删除的不是最后一个节点
          next.next.pre = pre;
        }
        break;
      }
      next = next.next;
    }
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

  public String toString() {
    return "DoubleHeroNode [no="
        + this.no
        + ", name="
        + this.name
        + ", nickname="
        + this.nickName
        + "]";
  }
}
