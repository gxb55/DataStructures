package com.atguigu.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName OwnCircleArrayQueue @Author guoxiaobing @Date 2020/6/8 19:33 @Version 1.0 @Description
 * 环形队列思考起来
 */
public class OwnCircleArrayQueue {
  public static void main(String[] args) {

      //测试
      System.out.println("测试数组模拟环形队列的案例");

      //创建一个环形队列
      CircleArrayQueue queue= new CircleArrayQueue(5);//队列的有效数据最大为4
      char key = ' ';
      Scanner scanner= new Scanner(System.in);
      boolean loop = true;
      //输出一个菜单
      while (loop){
          System.out.println("s(show):显示对列");
          System.out.println("e(exit):退出程序");
          System.out.println("a(add):添加数据到队列");
          System.out.println("g(get):从对列取出数据");
          System.out.println("h(head):查看队列头的数据");
          key = scanner.next().charAt(0);//接收一个字符


          switch (key){
              case 's':
                  queue.loop();
                  break;
              case 'a':
                  System.out.println("输出一个数");
                  int value = scanner.nextInt();
                  queue.addQueue(value);
                  break;
              case'g'://从对列取出数据
                  try{
                      int res = queue.getQueue();
                      System.out.printf("取出的数据是%d\n", res);
                  }catch (Exception e ){
                      System.out.println(e.getMessage());
                  }
                  break;
              case'h'://查看队列头的数据
                  try{
                      int res = queue.peek();
                      System.out.println(res);
                  }catch (Exception e ){
                      System.out.println(e.getMessage());
                  }
                  break;
              case 'e':
                  scanner.close();
                  loop = false;
                  break;
              default:
                  break;
          }
          System.out.println("arr:"+Arrays.toString(queue.getArr())+" front:"+queue.getFront()+" near:"+queue.getNear());
      }
      System.out.println("程序退出");

  }
}

class CircleArrayQueue {
  private int front; // 指向队列的首个元素
  private int near; // 指向队列最后一个元素的 后一个位置
  private int[] arr;
  private int maxSize;

    public int getFront() {
        return front;
    }

    public int getNear() {
        return near;
    }

    public int[] getArr() {
        return arr;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public CircleArrayQueue(int maxSize) {
    this.maxSize = maxSize;
    arr = new int[maxSize];
  }

  public Boolean isFull() {
    return (near + 1) % maxSize == front;
  }

  public Boolean isEmpty() {
    return near == front;
  }

  public void addQueue(int num) {
    if (isFull()) {
      throw new RuntimeException("队列满了！！");
    }
    arr[near] = num;
    near = (near + 1) % maxSize;
  }

  public int getQueue() {
    if (isEmpty()) {
      throw new RuntimeException("队列空了！！");
    }
    int num = arr[front];
    front = (front + 1) % maxSize;
    return num;
  }

  public void loop() {
    for (int i = front; i < maxSize(); i ++) {
      System.out.println(arr[i]+"--");
    }
  }

  public int peek(){
      if(isEmpty()){
          throw new RuntimeException("队列空了！！");
      }
      return arr[front];
  }

  private int maxSize() {
    return (near + maxSize - front) % maxSize;
  }
}
