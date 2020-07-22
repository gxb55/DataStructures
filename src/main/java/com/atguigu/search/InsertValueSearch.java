package com.atguigu.search;

import java.util.Arrays;

/**
 * @ClassName InsertValueSearch @Author guoxiaobing @Date 2020/7/14 19:44 @Version 1.0 @Description
 * 差值查找算法
 */
public class InsertValueSearch {
  public static void main(String[] args) {
    int[] arr = new int[100];
    for (int i = 0; i < 100; i++) {
      arr[i] = i + 1;
    }
    System.out.println(Arrays.toString(arr));
    int search = search(0, arr.length - 1, arr, 58);
    System.out.println(search);
  }

  public static int search(int left, int right, int[] arr, int val) {
    System.out.println("::::");
    if (val > arr[right - 1] || val < arr[0] || left > right) {
      return -1;
    }
    int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);

    int midVal = arr[mid];
    if (midVal > val) {
      return search(mid + 1, right, arr, val);
    } else if (val < midVal) {
      return search(left, mid - 1, arr, val);
    } else {
      return mid;
    }
  }
}
