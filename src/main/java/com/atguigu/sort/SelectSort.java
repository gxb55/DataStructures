package com.atguigu.sort;

import java.util.Arrays;

/**
 * @ClassName SelectSort
 * @Author guoxiaobing
 * @Date 2020/7/9 17:02
 * @Version 1.0
 * @Description 选择排序
 * 从数据中找出最小的或者最大，将这个数与数组头或者数组尾的数交换,
 * 外层是数组的数（排好序的）,内层也是数组的内容是未排好序的
 * 时间复杂度是 O(n^2) 速度大于冒泡
 */
public class SelectSort {
  public static void main(String[] args) {
      //int arr[] = {101,21, 33, 1, 151};
      long time1 =System.currentTimeMillis();
      int arr[] = new int[80000];
      for(int i=0;i<80000;i++){
          arr[i]= (int) (Math.random()*90000);
      }
      sort(arr);//2491
      //System.out.println(Arrays.toString(arr));
      System.out.println(System.currentTimeMillis()-time1);
  }
  public static void sort(int[] arr){
      int min;
      int minIndex;
      for(int i=0;i<arr.length-1;i++){//数组不断往后走
          minIndex = i;
          min =arr[i];
          for(int j=i+1;j<arr.length;j++){//与数组的其他元素来比较
               if(min>arr[j]){
                     min=arr[j];
                     minIndex=j;
                 }
             }
          if(minIndex != i){
              arr[minIndex] = arr[i];
              arr[i]=min;
          }
      }
  }
}
