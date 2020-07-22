package com.demo.search;

import java.util.Arrays;

/**
 * @ClassName FibonacciSearch
 * @Author guoxiaobing
 * @Date 2020/7/16 9:19
 * @Version 1.0
 * @Description 斐波那契数列来查找，从有序列表中查找
 * 主要是将数组的长度增加为黄金分割的长度
 * arr[k]=arr[k-1]+arr[k-2]
 * arr[k]-1 =arr[k-1]-1+arr[k-2]+1 -1
 */
public class FibonacciSearch {
  public static void main(String[] args) {
      int[] arr = {1,8, 10,11,15,48,56,68,69,70,78,85, 89, 1000, 1234};
      System.out.println(fibonacci(-1,arr));
  }
  public static  int fibonacci(int val,int[] arr){
      int[] arr1 = getArr(arr.length);
      int low = 0;
      int high= arr.length-1;
      int k=0;
      while (arr1[k]-1<high){
          k++;
      }
      int[] ints = Arrays.copyOf(arr, arr1[k]);
      for(int i=arr.length;i<ints.length;i++){
          ints[i]=arr[high];
      }
      while (high>=low){
        int mid = low+arr1[k-1]-1;
        int midVal = ints[mid];
        if(val>midVal){// 1 1 2
            k-=2;
            low=mid+1;
        }else if(val<midVal){
            k--;
            high=mid-1;
        }else{
            if(mid>high){
                return high;
            }else{
                return mid;
            }
        }
      }
      return -1;
  }






  public static int[] getArr(int n){
      if(n<1){
          return null;
      }
      int[] arr = new int[n+2];
      for(int i=0;i<arr.length;i++){
          if(i==0||i==1){
              arr[i]=1;
          }else{
              arr[i]=arr[i-1]+arr[i-2];
          }
      }
      System.out.println("斐波那锲数列 "+Arrays.toString(arr));
      return arr;
  }
}
