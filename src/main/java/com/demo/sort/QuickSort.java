package com.demo.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Author guoxiaobing
 * @Date 2020/7/15 15:13
 * @Version 1.0
 * @Description 快速排序
 */
public class QuickSort {
  public static void main(String[] args) {
      int arr[] = {33,101,21, 33, 155, 151,11,-1};//,21, 33, 155, 151,11,-1
      sort(0,arr.length-1,arr);
      System.out.println(Arrays.toString(arr));
  }
  public static void sort(int left,int right,int[] arr){
      if(left>right){
          return;
      }
      int i =left;
      int j=right;
      int val =arr[left];
      int temp;
      while (j>i){
          while (j>i&& arr[j]>=val){
              j--;
          }
          while (j>i && arr[i]<=val){
              i++;
          }
          if(j>i){
             temp=arr[i];
             arr[i]=arr[j];
             arr[j]=temp;
          }
      }
      arr[left]=arr[i];
      arr[i]=val;
      System.out.println(Arrays.toString(arr));
      sort(left,j-1,arr);
      sort(j+1,right,arr);

  }
}
