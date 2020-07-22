package com.atguigu.search;

import java.util.Arrays;

/**
 * @ClassName FibonacciSearch
 * @Author guoxiaobing
 * @Date 2020/7/15 17:25
 * @Version 1.0
 * @Description 斐波那契数列 查找的方法
 * f[k]=f[k-1]+f[k-2]
 * f[k]-1 = f[k-1]-1 + f[k-2]-1 +1
 * mid = low+f[k-1]-1
 */
public class FibonacciSearch {
  public static void main(String[] args) {
      int[] arr = {1,8, 10,11,15,48,56,68,69,70,78,85, 89, 1000, 1234};
      System.out.println(search(arr,1234));
  }
  public static int[] getFibonacci(int n){
      int[] arr = new int[n];
      for(int i=0;i<arr.length;i++){
          if(i==0 || i==1){
              arr[i]=1;
          }else{
              arr[i]=arr[i-1]+arr[i-2];
          }
      }
      return arr;
  }

  public static int search(int[] arr,int val){
        int low=0;
        int high=arr.length-1;
        int k=0;
        int mid;
      int[] fibo = getFibonacci(50);
      while (fibo[k]-1<high){
        k++;
        }
          int[] ints = Arrays.copyOf(arr, fibo[k]);
      for(int i=high+1;i<ints.length;i++){
          ints[i]=arr[high];
      }
      while (high>=low){
          mid=low+fibo[k-1]-1;
          if(val<ints[mid]){
              k--;
              high=mid-1;
          }else if(val>ints[mid]){
              k-=2;
              low=mid+1;
          }else{
              if(mid<high){
                  return mid;
              }else{
                  return high;
              }
          }
      }
        return -1;
  }
}
