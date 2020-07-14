package com.demo.sort;

import java.util.Arrays;

/**
 * @ClassName InsertSort
 * @Author guoxiaobing
 * @Date 2020/7/10 9:50
 * @Version 1.0
 * @Description 插入排序，单层循环，循环中嵌套while（true），每次往后移动一个位置
 */
public class InsertSort {
  public static void main(String[] args) {
      int[] arr={10101,4,-1,5,89,10,-52,4111};
      sort(arr);
      System.out.println(Arrays.toString(arr));
  }
  public static void sort(int[] arr){
      int index,indexVal;
    for (int i = 1; i < arr.length; i++) {
      index=i-1;//跟这个元素之前的比较，i-1去除调自身
      indexVal=arr[i];
      while (index>=0 &&arr[index]>indexVal){//a>b 继续往后找就是为了将b放在a前面就是升序排列，从小到大排列
         arr[index+1]=arr[index];
          index--;
      }
      index++;//因为当他再减一后就不满足条件了，所以他的位置是在index后面一个位置的
      arr[index]=indexVal;
    }
  }
}
