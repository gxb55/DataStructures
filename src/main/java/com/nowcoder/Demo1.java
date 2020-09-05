package com.nowcoder;

/** @ClassName Demo1 @Author guoxiaobing @Date 2020/8/18 19:46 @Version 1.0 @Description */

import java.util.Scanner;

/**
 * 靠谱的数字 数字从1开始，遇到数字7就会跳过，比如6后边直接是8，69后边直接是80，现在给你个数字，问是第几位？
 * <p>比如输入8，输出7，就是第7个数。那89那？请你编程输出。
 */
public class Demo1 {
  public static void main(String[] args) {
      System.out.println(getIndex(890));
      int i = "7".indexOf("1");
      //System.out.println(i);

  }
  public static int getIndex(int num){
      int index=0;
      for(int i=0;i<=num;i++){
          String str =String.valueOf(i);
          if(str.indexOf("7")>=0){
              i++;
          }else{
              System.out.println(i);
              index++;
          }
      }
      return index;
  }


}
