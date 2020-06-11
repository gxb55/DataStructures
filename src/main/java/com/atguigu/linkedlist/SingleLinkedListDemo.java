package com.atguigu.linkedlist;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.Stack;

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
    SingleLinkedList linkedList2 = new SingleLinkedList();
    /*linkedList.add(new HeroNode(1, "宋江", "及时雨"));
    linkedList.add(new HeroNode(2, "吴用", "智多星"));
    linkedList.add(new HeroNode(3, "林冲", "豹子头"));
    linkedList.add(new HeroNode(4, "鲁达", "花和尚"));*/

    linkedList.addByOrderTeach(new HeroNode(3, "林冲", "豹子头"));
    linkedList.addByOrderTeach(new HeroNode(2, "吴用", "智多星"));
    linkedList.addByOrderTeach(new HeroNode(8, "鲁达", "花和尚"));
    linkedList.addByOrderTeach(new HeroNode(1, "宋江", "及时雨"));
    linkedList.addByOrderTeach(new HeroNode(5, "卢俊义", "玉麒麟"));
    System.out.println("linkedList 原本的值：");
    linkedList.loop(linkedList.getHead());

    linkedList2.addByOrderTeach(new HeroNode(49, "js", "前端必备"));
    linkedList2.addByOrderTeach(new HeroNode(7, "python", "后起之秀"));
    linkedList2.addByOrderTeach(new HeroNode(12, "c++", "语言的爸爸"));
    linkedList2.addByOrderTeach(new HeroNode(7, "java", "后台大佬"));
    linkedList2.addByOrderTeach(new HeroNode(50, "c", "你们的爷爷"));
    linkedList2.addByOrderTeach(new HeroNode(1, "0 1", "你们的祖宗"));
    System.out.println("linkedList2 原本的值：");
    linkedList.loop(linkedList2.getHead());
    System.out.println("合并之后的值：");
    HeroNode node = SingleLinkedList.mergeHeroNode(linkedList2.getHead(), linkedList.getHead());
    linkedList.loop(node);

    System.out.println(linkedList.getLength(linkedList.getHead()));
    linkedList.change(new HeroNode(5, "卢俊义", "玉麒麟132132"));
    System.out.println("修改后：");
    linkedList.loop(linkedList.getHead());
    linkedList.delete(new HeroNode(8, "卢俊义", "玉麒麟132132"));
    System.out.println("删除后：");
    linkedList.loop(linkedList.getHead());
    System.out.println("倒数第2个节点后：");
    HeroNode lastHeroNode = linkedList.getLastHeroNode(linkedList.getHead(), 2);
    SingleLinkedList.loop(lastHeroNode);
    System.out.println("反转节点后：");
    SingleLinkedList.reversetList(linkedList.getHead());
    SingleLinkedList.loop(linkedList.getHead());
    System.out.println("逆序输出：");
    SingleLinkedList.reversePrint(linkedList.getHead());
  }
}

class SingleLinkedList {
  private HeroNode head = new HeroNode(0, "", "");

  public HeroNode getHead() {
    return head;
  }

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
    HeroNode temp = head;

    while (true) {
      if (temp.next == null) {
        temp.next = node;
        break;
      }
      if (num < temp.next.no) { // 处理比前一个小的
        node.next = temp.next;
        temp.next = node;
        break;
      }
      temp = temp.next;
    }
  }
  // 每次判断的时候判断的是下一个节点，如果下一个节点大于要插入节点的值则就在temp 和temp.next之间
  public void addByOrderTeach(HeroNode node) {
    HeroNode temp = head;
    Boolean flag = false;
    while (true) {
      if (temp.next == null) {
        break;
      }
      if (temp.next.no > node.no) {
        break;
      } else if (temp.next.no == node.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }
    if (flag) {
      System.out.println("no已存在！，no" + node.getNo());
      return;
    }
    node.next = temp.next;
    temp.next = node;
  }

  public void change(HeroNode node) {
    HeroNode temp = head.next;
    while (true) {
      if (temp == null) {
        System.out.println("没有找到该节点，no：" + node.getNo());
        break;
      }
      if (node.getNo() == temp.getNo()) { // no相等 替换掉老的元素
        temp.setName(node.getName());
        temp.setNickName(node.getNickName());
        break;
      }
      temp = temp.next;
    }
  }

  public void delete(HeroNode node) {
    HeroNode temp = head;
    while (true) {
      if (temp == null) {
        System.out.println("没有找到要删除的节点，no：" + node.getNo());
        break;
      }
      if (temp.next.no == node.no) {
        temp.next = temp.next.next;
        break;
      }
      temp = temp.next;
    }
  }

  public static void loop(HeroNode head) {
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

  public int getLength(HeroNode head) {
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
  // 逆序打印链表中的元素，使用栈的结构来做先进后出，
  public static void reversePrint(HeroNode head) {
    if (head.next == null) {
      return;
    }
    HeroNode temp = head.next;
    Stack<HeroNode> stack = new Stack<HeroNode>();
    while (temp != null) {
      stack.add(temp);
      temp = temp.next;
    }
    while (stack.size() > 0) {
      System.out.println(stack.pop());
    }
  }

  // 合并两个有序链表
  public static HeroNode mergeHeroNode(HeroNode one, HeroNode two) {
    if (one.next == null && two.next == null) {
      throw new RuntimeException("队列不可全部为空！");
    }
    if (one.next == null && two.next != null) {
      return two;
    }
    if (one.next != null && two.next == null) {
      return one;
    }
    HeroNode head = new HeroNode(0, "", "");
    HeroNode twoTemp = two.next;
    HeroNode oneTemp = one.next;
    HeroNode cur = head;
    while (twoTemp != null && oneTemp != null) {
      if (oneTemp.no > twoTemp.no) {
        cur.next = twoTemp;
        twoTemp = twoTemp.next;
      } else if (oneTemp.no < twoTemp.no) {
        cur.next = oneTemp;
        oneTemp = oneTemp.next;
      } else {
        cur.next = oneTemp;
        oneTemp = oneTemp.next;
        twoTemp = twoTemp.next;
      }
      cur = cur.next;
    }
    if (twoTemp != null) {
      cur.next = twoTemp;
    }
    if (oneTemp != null) {
      cur.next = oneTemp;
    }
    return head;
  }
  /**
   * 单链表反转 腾讯面试题 1.new一个新的节点（temp） 便利老的链表 每次取出后都放在temp的后面 head.next = temp.next
   *
   * @param head
   */
  public static void reversetList(HeroNode head) {
    HeroNode reverNode = new HeroNode(0, "", "");
    HeroNode temp = head.next;
    HeroNode next = null;
    while (temp != null) {
      next = temp.next;
      temp.next = reverNode.next;
      reverNode.next = temp;
      temp = next;
    }
    head.next = reverNode.next;
  }

  /** 查找单链表中的倒数第k个节点 新浪面试题 */
  public HeroNode getLastHeroNode(HeroNode head, int k) {
    if (head.next == null) {
      return null;
    }
    int length = getLength(head);
    if (length < k || k < 0) {
      System.out.println("链表没有那么长！！");
      return null;
    }
    int num = length - k;
    HeroNode temp = head.next;
    while (num != 0) {
      temp = temp.next;
      num--;
    }
    return temp;
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
