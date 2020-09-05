package com.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName Demo2 @Author guoxiaobing @Date 2020/8/19 16:08 @Version 1.0 @Description
 *
 * <p>组最大数字 给出几组字符串的数字，需要获得组成的最大数字
 *
 * <p>比如输入123，546，8，32，输出854632123
 */
public class Demo2 {
  private static Stack<String> stack = new Stack<>();
  public static void main(String[] args) {
      String[] arr = {"123","546","8","32"};
      List<Long> list = new ArrayList<>();
      getMaxNum(arr,arr.length,0,0,list);
      long max =0;
      for(Long l:list){
          if(l>max){
              max=l;
          }
      }
      System.out.println(max);
  }

    private static void getMaxNum(String[] arr, int length, int find,int index, List<Long> list) {
      if(length == find){
          StringBuilder stringBuilder = new StringBuilder();
          for(String string:stack){
              stringBuilder.append(string);
          }
          list.add(Long.parseLong(stringBuilder.toString()));
          return ;
      }
      for(int i =index;i<arr.length;i++ ){
          stack.add(arr[i]);
          getMaxNum(arr,length,find+1,i+1,list);
          stack.pop();
      }
    }
}
