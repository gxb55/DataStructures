package com.demo;

import java.util.Arrays;

/** @ClassName ArraySort @Author guoxiaobing @Date 2020/7/1 20:11 @Version 1.0 @Description 冒泡排序 */
public class ArraySort {
  public static void main(String[] args) {
    int arr[] = {8, 0, 1, 45, 7, 2, 3, 6, 90, 12, 1};
    ArraySort sort = new ArraySort();
    System.out.println(Arrays.toString(sort.sort(arr)));
  }

  public int[] sort(int[] arr) {
    if (null == arr || arr.length < 2) {
      return null;
    }
    int t ;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] > arr[i]) { // 后一个大于前一个交换位置 降序
          t = arr[j];
          arr[j] = arr[i];
          arr[i] = t;
        }
      }
    }
    return arr;
  }
}
