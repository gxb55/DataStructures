package com.demo;

/**
 * @ClassName DoubleLinkedListDemo @Author guoxiaobing @Date 2020/7/6 16:56 @Version
 * 1.0 @Description 双向链表
 */
public class DoubleLinkedListDemo {
  public static void main(String[] args) {
      DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
      doubleLinkedList.addBySort(new DoubleNode(5, "宋江"));
      doubleLinkedList.addBySort(new DoubleNode(2, "武松"));
      doubleLinkedList.addBySort(new DoubleNode(8, "张三"));
      doubleLinkedList.addBySort(new DoubleNode(6, "吴用"));
      doubleLinkedList.addBySort(new DoubleNode(1, "孙二娘"));
      doubleLinkedList.loop();
      System.out.println("逆序输出。。");
      System.out.println(doubleLinkedList.size());
      doubleLinkedList.del(5);
      System.out.println("删除后输出。。");
      doubleLinkedList.loop();


  }
}

class DoubleLinkedList {
  private DoubleNode head = new DoubleNode(0, "head");

  public void add(DoubleNode node) {
    if (null == node) {
      return;
    }
    DoubleNode temp = head;
    while (null != temp.getNext()) {
      temp = temp.getNext();
    }
    temp.setNext(node);
    node.setPre(temp);
  }

    public void addBySort(DoubleNode node) {
        if (null == node) {
            return;
        }
        DoubleNode temp = head;
        while (true){
            if(null == temp.getNext()){
                temp.setNext(node);
                node.setPre(temp);
                break;
            }
            if(temp.getNext().getId()>node.getId()){
                DoubleNode next = temp.getNext();
                temp.setNext(node);
                node.setPre(temp);

                node.setNext(next);
                next.setPre(node);
               break;
            }
            temp = temp.getNext();
        }
    }

  public void del(int id){
      DoubleNode temp = head.getNext();
      while (null != temp) {
          if(id == temp.getId()){
              temp.getPre().setNext(temp.getNext());
              break;
          }
          temp = temp.getNext();
      }
  }

  public void loop(){
      DoubleNode temp = head.getNext();
      while (null != temp) {
         System.out.println(temp.toString());
          temp = temp.getNext();
      }
  }
  public int size(){
      int sum =0;
      DoubleNode temp = head.getNext();
      while (null != temp) {
          temp = temp.getNext();
          sum++;
      }
      return sum;
  }
}

class DoubleNode {
  private int id;
  private String val;
  private DoubleNode next;
  private DoubleNode pre;

  public DoubleNode(int id, String val) {
    this.id = id;
    this.val = val;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getVal() {
    return val;
  }

  public void setVal(String val) {
    this.val = val;
  }

  public DoubleNode getNext() {
    return next;
  }

  public void setNext(DoubleNode next) {
    this.next = next;
  }

  public DoubleNode getPre() {
    return pre;
  }

  public void setPre(DoubleNode pre) {
    this.pre = pre;
  }

  @Override
  public String toString() {
    return "DoubleNode{" + "id=" + id + ", val='" + val + '\'' + '}';
  }
}
