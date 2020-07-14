package com.demo.sort;

import java.util.Arrays;

/**
 * @ClassName SelectSort
 * @Author guoxiaobing
 * @Date 2020/7/10 9:37
 * @Version 1.0
 * @Description 自测选择排序
 */
public class SelectSort {
  public static void main(String[] args) {
      int[] arr={4,-1,5,89,10,-52,4111};
      sort(arr);
      System.out.println(Arrays.toString(arr));
  }

    /**
     * 选择排序，外层是要排的数组，内层是用来对比的，即外层从0开始一个一个排好
     * 剩下没有排好的从内层的循环中对比，找到一个就交换位置，交换位置的时候判断是否需要交换
     * @param arr
     */
  public static void sort(int[] arr){
      int min,index;
      for(int i=0;i<arr.length-1;i++){//最后一个元素不用排序，因为前面的n-1个都拍好了，那他的位置肯定就是现在的位置了。
          min=arr[i];
          index =i;
          for(int j=i+1;j<arr.length;j++){
              if(arr[j]<min){//先找最小的即，升序，即从小到大排列
                  min=arr[j];
                  index=j;
              }
          }
          if(i!=index){//从数组中找到了更小的值则更换位置
              arr[index]=arr[i];
              arr[i]=min;
          }
      }
  }
}
