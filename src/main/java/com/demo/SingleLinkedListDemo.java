package com.demo;

import java.util.Stack;

/**
 * @ClassName SingleLinkedListDemo @Author guoxiaobing @Date 2020/7/2 15:39 @Version
 * 1.0 @Description 单向链表
 */
public class SingleLinkedListDemo {
  public static void main(String[] args) {
    SingleLinkedList singleLinkedList = new SingleLinkedList();
    singleLinkedList.addBySort(new Node(5, "宋江"));
    singleLinkedList.addBySort(new Node(2, "武松"));
    singleLinkedList.addBySort(new Node(8, "张三"));
    singleLinkedList.addBySort(new Node(6, "吴用"));
    singleLinkedList.addBySort(new Node(1, "孙二娘"));
    singleLinkedList.loop();
    System.out.println("逆序输出。。");
    // Node rever = singleLinkedList.rever(singleLinkedList.getHead());
    Node rever = singleLinkedList.rever2(singleLinkedList.getHead());
    singleLinkedList.loop(rever);
    System.out.println("倒数第2个。。");
    singleLinkedList.getLastHeroNode(rever, 2);
    /* singleLinkedList.remote(4);
    System.out.println("删除后。。。");
    singleLinkedList.loop();*/
  }
}

class SingleLinkedList {
  private Node head = new Node(0, "");

  public Node getHead() {
    return head;
  }

  public void setHead(Node head) {
    this.head = head;
  }

  public void add(Node node) {
    Node temp = head;
    while (null != temp.getNext()) {
      temp = temp.getNext();
    }
    temp.setNext(node);
  }

  public void addBySort(Node node) {
    if (null == node) {
      return;
    }
    Node temp = head;
    boolean falg = true;

    while (temp.getNext() != null) {
      if (temp.getNext().getId() > node.getId()) { // 当前的id大于要插入的
        Node next = temp.getNext();
        temp.setNext(node);
        node.setNext(next);
        falg = false;
        break;
      }
      temp = temp.getNext();
    }
    if (falg) {
      temp.setNext(node);
    }
  }

  public void loop(Node node) {
    if (null == node.getNext()) {
      return;
    }
    Node temp = node;
    while (null != temp.getNext()) {
      System.out.println(temp.getNext().toString());
      temp = temp.getNext();
    }
  }

  public void loop() {
    if (null == head.getNext()) {
      return;
    }
    Node temp = head;
    while (null != temp.getNext()) {
      System.out.println(temp.getNext().toString());
      temp = temp.getNext();
    }
  }

  public void remote() {
    if (null == head.getNext()) {
      return;
    }
    Node temp = head;
    while (null != temp.getNext().getNext()) {
      temp = temp.getNext();
    }
    temp.setNext(null);
  }

  public void remote(int n) {
    if (null == head.getNext()) {
      return;
    }
    Node temp = head;
    boolean falg = false;
    while (null != temp.getNext().getNext()) {
      if (temp.getNext().getId() == n) {
        falg = true;
        break;
      }
      temp = temp.getNext();
    }
    if (falg) {
      Node next = temp.getNext().getNext();
      temp.setNext(next);
      temp.setNext(null);
    } else if (temp.getNext().getId() == n) {
      temp.setNext(null);
    } else {
      System.out.println("没有找到对应的数据");
    }
  }

  public int length() {
    if (null == head.getNext()) {
      return 0;
    }
    int i = 0;
    Node temp = head;
    while (null != temp.getNext()) {
      i++;
      temp = temp.getNext();
    }
    return i;
  }

  /**
   * 单链表反转 腾讯面试题 1.new一个新的节点（temp） 便利老的链表 每次取出后都放在temp的后面 head.next = temp.next
   *
   * @param head
   */
  public Node rever(Node head) {
    if (null == head.getNext()) {
      return null;
    }
    Node node = new Node(0, "0");
    Node next = node;
    Node temp = head.getNext();
    Stack<Node> stack = new Stack<>();
    while (null != temp) {
      stack.push(temp);
      temp = temp.getNext();
    }

    while (!stack.isEmpty()) {
      Node pop = stack.pop();
      pop.setNext(null);
      next.setNext(pop);
      next = next.getNext();
    }
    return node;
  }

  public Node rever1(Node head) {
    if (null == head.getNext()) {
      return null;
    }
    Node node = new Node(0, "0");
    Node next = node;
    Node opt = null;
    Node temp = head.getNext();
    while (null != temp) {
      opt = temp.getNext();
      /*temp.setNext(next.getNext());
      next.setNext(temp);*/
      temp.setNext(null);
      if (null != next.getNext()) {
        Node n = next.getNext();
        temp.setNext(n);
        next.setNext(temp);
      } else {
        next.setNext(temp);
      }
      temp = opt;
    }
    return node;
  }

  public Node rever2(Node head) {
    Node reverNode = new Node(0,"0");
    if(null == head.getNext()){
      return null;
    }
    Node temp = head.getNext();
    while (null!=temp){
      Node next = temp.getNext();
      temp.setNext(reverNode.getNext());
      reverNode.setNext(temp);
      temp = next;
    }
    return reverNode;
  }

  /** 查找单链表中的倒数第k个节点 新浪面试题 */
  public void getLastHeroNode(Node head, int k) {
    if (k < 0) {
      return;
    }
    int sum = 0;
    Node node = head.getNext();
    while (null != node) {
      node = node.getNext();
      sum++;
    }
    if (k > sum) {
      return;
    }
    int t = sum - k;
    node = head.getNext();
    while (t > 0) {
      node = node.getNext();
      t--;
    }
    System.out.println(node.toString());
    /*
    拿到链表的长度 sum-k 然后正序便利每次减一即可
     */
  }
}

class Node {
  private int id;
  private String name;
  private Node next;

  public Node(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Node{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
