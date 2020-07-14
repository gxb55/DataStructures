package com.demo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Queue8 @Author guoxiaobing @Date 2020/7/7 14:24 @Version 1.0 @Description 无情八皇后问题
 * 一个8*8的棋盘上面放上去八个皇后，这八个皇后不能在一条线上，包括横着竖着斜着都不可以，问题点是递归
 */
public class Queue8 {
  static int sum = 0;

  public static void main(String[] args) {
    int arr[] = new int[8];
    Queue8 queue8 = new Queue8();
    // queue8.stand(0, arr);
    // System.out.println("一共有"+sum);
    System.out.println(queue8.myAtoi("  2147483648p8"));
    System.out.println("5".matches("\\d"));
    System.out.println("sdf".substring(1));
  }

  public void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public void stand(int n, int[] arr) {
    if (n == 8) { // 在找第八个就是说下标是九的
      print(arr);
      sum++;
      return;
    }
    for (int i = 0; i < 8; i++) {
      arr[n] = i;
      if (judge(arr, n)) {
        stand(n + 1, arr);
      }
    }
  }

  /**
   * @param arr
   * @param n 第几个元素 从0开始
   */
  public boolean judge(int[] arr, int n) {
    for (int i = 0; i < n; i++) {
      if (arr[n] == arr[i]) { // 同一列
        return false;
      }
      if (Math.abs(n - i) == Math.abs(arr[n] - arr[i])) { // 斜线
        return false;
      }
    }
    return true;
  }

  public int myAtoi(String str) {
    str = str.trim();
    if(str.length()==0){
        return 0;
    }
    String first = "";
    if ("-".equals(str.substring(0, 1)) || "+".equals(str.substring(0, 1))) {
      first = str.substring(0, 1);
      str = str.substring(1, str.length());
    }
    String num = "";
    for (int i = 0; i < str.length(); i++) {
     // if (str.charAt(i) > 47 && str.charAt(i) < 58) {
      if (String.valueOf(str.charAt(i)).matches("\\d")) {
        num += str.charAt(i);
      } else {
        break;
      }
    }
      while (!"".equals(num)&&num.charAt(0)==48 ){
          num = num.substring(1,num.length());
      }
    if ("".equals(num)) {
      return 0;
    }


    String max = String.valueOf(Integer.MAX_VALUE);
    if (max.length() > num.length()) {
      num = first + num;
      return Integer.parseInt(num);
    } else if (max.length() < num.length()) {
      if ("-".equals(first)) {
        return Integer.MIN_VALUE;
      } else {
        return Integer.MAX_VALUE;
      }
    } else {//1095502006  2147483647
      for (int i = 0; i < max.length(); i++) {
        if (max.charAt(i) < num.charAt(i)) {
          if ("-".equals(first)) {
            return Integer.MIN_VALUE;
          } else {
            return Integer.MAX_VALUE;
          }
        }else if(max.charAt(i) > num.charAt(i)){
            return Integer.parseInt(first +num);
        }
      }
      return Integer.parseInt(first +num);
    }
  }

  public boolean check(String str){
      Pattern pattern = Pattern.compile("/\\d/");
      Matcher matcher = pattern.matcher(str);

      return matcher.matches();
  }
}
