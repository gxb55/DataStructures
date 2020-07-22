package com.atguigu.hashtab;

/**
 * @ClassName HashTabDemo @Author guoxiaobing @Date 2020/7/20 19:11 @Version 1.0 @Description
 * 模拟hashmap hashMap其实就是数组+链表，链表中存的是元素
 */
public class HashTabDemo {
  public static void main(String[] args) {
    HashTab hashTab = new HashTab(16);
    hashTab.addOrder(new Emp(1, "张三"));
    hashTab.addOrder(new Emp(2, "李四"));
    hashTab.addOrder(new Emp(3, "33"));
    hashTab.addOrder(new Emp(4, "44"));
    hashTab.addOrder(new Emp(5, "55"));
    hashTab.addOrder(new Emp(6, "66"));
    hashTab.addOrder(new Emp(64, "64"));
    hashTab.addOrder(new Emp(16, "16"));
    hashTab.addOrder(new Emp(32, "32"));

      hashTab.list();
    //hashTab.findEmpById(10);
    hashTab.del(32);
    hashTab.list();
  }
}

class HashTab {
  private EmpLinkedList[] empLinkedList;
  private int size;

  public HashTab(int size) {
    empLinkedList = new EmpLinkedList[size];
    this.size = size;

    for (int i = 0; i < empLinkedList.length; i++) {
      empLinkedList[i] = new EmpLinkedList();
    }
  }

  /**
   * 根据id查找emp
   *
   * @param id
   */
  public void findEmpById(int id) {
    int i = hashFun(id);
    Emp empById = empLinkedList[i].findEmpById(id);
    System.out.println(empById);
  }

  public void del(int id) {
    int i = hashFun(id);
    empLinkedList[i].del(id);
  }

  /**
   * emp对应的链表是那个，然后调用链表的add方法
   *
   * @param emp
   */
  public void add(Emp emp) {
    int id = hashFun(emp.getId());
    if (null == empLinkedList[id]) {
      empLinkedList[id] = new EmpLinkedList();
    }
    empLinkedList[id].add(emp);
  }

  /**
   * emp对应的链表是那个，然后调用链表的add方法
   *
   * @param emp
   */
  public void addOrder(Emp emp) {
    int id = hashFun(emp.getId());
    if (null == empLinkedList[id]) {
      empLinkedList[id] = new EmpLinkedList();
    }
    empLinkedList[id].addOrder(emp);
  }

  public int hashFun(int id) {
    return id % size; // 这个数永远小于数组的长度
  }

  /** 便利每条链表 */
  public void list() {

    for (int i = 0; i < empLinkedList.length; i++) {
      empLinkedList[i].list(i);
    }
  }
}

class EmpLinkedList {
  private Emp head; // 头结点默认是空的

  /**
   * 默认id是自增的，每次有了新的雇员都放在链表的最后
   *
   * @param emp
   */
  public void add(Emp emp) {
    if (null == head) { // 第一个雇员
      head = emp;
    } else { // 非第一个雇员
      Emp temp = head;
      while (null != temp.getNext()) {
        temp = temp.getNext();
      }
      temp.setNext(emp);
    }
  }

  /**
   * id不是顺序的，但是要将id按照升序来排列
   *
   * @param emp
   */
  public void addOrder(Emp emp) {
    if (null == head) { // 第一个雇员
      head = emp;
    } else { // 非第一个雇员
      Emp temp = head;
      boolean flag = false;
      if(temp.getId()>emp.getId()){
        emp.setNext(head);
        head = emp;
        return;
      }
      while (temp.getNext()!=null) {
        if(temp.getNext().getId()<emp.getId()){
          temp =temp.getNext();
        }else{
          emp.setNext(temp.getNext());
          temp.setNext(emp);
          flag = true;
          break;
        }
      }
     if(!flag){//最后没有找到合适的位置则直接添加到最后
       temp.setNext(emp);
     }
    }
  }

  /** 便利链表 */
  public void list(int i) {
    System.out.print("第" + i + "条链表。");
    if (head == null) {
      return;
    }
    Emp temp = head;
    while (temp != null) {
      System.out.print(temp.toString() + "   ");
      temp = temp.getNext();
    }
    System.out.println();
  }

  public Emp findEmpById(int id) {
    if (head == null) {
      return null;
    }
    Emp temp = head;
    while (temp != null) {
      if (temp.getId() == id) {
        break;
      }
      temp = temp.getNext();
    }
    return temp;
  }

  public void del(int id) {
    if (head == null) {
      System.out.println("你所要删除的雇员不存在，id: " + id);
      return;
    }
    if (id == head.getId()) {
      head = head.getNext();
      return;
    }
    Emp temp = head;
    while (temp.getNext() != null) {
      if (temp.getNext().getId() == id) {
        temp.setNext(temp.getNext().getNext());
        break;
      }
    }
  }
}

class Emp {
  public int id;
  public String name;
  public Emp next;

  public Emp(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public Emp getNext() {
    return next;
  }

  public void setNext(Emp next) {
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
    return "Emp{" + "id=" + id + ", name='" + name + '\'' + '}';
  }
}
