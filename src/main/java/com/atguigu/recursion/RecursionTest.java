package com.atguigu.recursion;

/**
 * @ClassName RecursionTest
 * @Author guoxiaobing
 * @Date 2020/6/28 19:20
 * @Version 1.0
 * @Description 递归问题
 */
public class RecursionTest {
  public static void main(String[] args) {
    //
      test(5);
      int facorial = facorial(5);
      System.out.println("阶乘的问题结果是 "+facorial);

  }
  //打印问题
  public static void test(int n){
      if(n>2){
          test(n-1);
      }
      System.out.println(n);
  }
  //阶乘问题
  public static int facorial(int n){
      if(n == 1){
          return 1;
      }else{
          return facorial(n-1)*n;
      }
  }
}
