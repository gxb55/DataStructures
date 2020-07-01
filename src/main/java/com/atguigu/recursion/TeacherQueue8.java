package com.atguigu.recursion;

/**
 * @ClassName TeacherQueue8
 * @Author guoxiaobing
 * @Date 2020/7/1 18:39
 * @Version 1.0
 * @Description 尚硅谷版 八皇后问题
 */
public class TeacherQueue8 {
    int max=8;
    int sum=0;
    int []array= new int[max];
  public static void main(String[] args) {
      TeacherQueue8 queue8 = new TeacherQueue8();
      queue8.check(0);
      System.out.println(queue8.sum);
  }

    /**
     * 第n个元素，每次递归都要找到出递归的条件，即什么情况下递归结束
     * 如果没有条件或者条件不对容易死循环容易抛错 stackoverflowerror
     * 方法都在栈中所以是栈溢出
     * @param n
     */
  public void check(int n){
      if(n==max){//n=8的时候说明该找第8个了但是下标是从0开始所以 0 - 7  八个皇后找到了
          print();
          sum++;
          return;
      }
      for(int i=0;i<max;i++){
          array[n]=i;//假设第一个位置放好了，
          if(judge(n)){//如果当前位置合法则继续递归找第二列的位置，第二列的位置也是从下标为0的开始
              check(n+1);
          }
      }
  }

    /**
     *
     * @param n 第几个元素即第几列 array =[] 第一个元素就是第一行，列值就是对应的value
     *          即【1,2,5】 分别是 第一行第一列 第二行第二列 第三行第五列
     * @return
     */
  public boolean judge(int n){
      for(int i=0;i<n;i++){//行不断的在增加
          /**
           * array[i]==n 说明某一行的列数等于传入的n的列数比如  【1,3,1】 即
           * array[0]==array[2] 即同一列 同一行不用判断因为下标不一样就不在同一列
           *
           * Math.abs(n-i)==Math.abs(array[n]-array[i]) 解读
           *  Math.abs(n-i) n,i 表示行 即下标相差的距离 即行相差的距离
           *  Math.abs(array[n]-array[i]) array[n]，array[i] 内容表示 列 ，即列相差的距离
           *  如果这个距离的绝对值相等则说明在同一条斜线上面 比如 【1,5，3】
           *  即同时往左 同时往下 ，不同的方向移动了同样的距离就正方形就是对角线就是在同一条斜线上
           *  2 = 2
           *
           */
          if(array[i]==array[n] ||Math.abs(n-i)==Math.abs(array[n]-array[i])){
            return false;
          }
      }
      return true;
  }
  public void print(){
      for(int i=0;i<array.length;i++){
          System.out.print(array[i] +" ");
      }
      System.out.println();
  }
}
