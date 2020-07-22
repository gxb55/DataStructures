package com.demo.search;

import java.util.Arrays;

/**
 * @ClassName SeqSearch
 * @Author guoxiaobing
 * @Date 2020/7/15 15:42
 * @Version 1.0
 * @Description 查找的方法
 */
public class SeqSearch {
  public static void main(String[] args) {
      int arr[] = {33,101,21, 33, 155, 151,11};
      int arr1[] = {-1, 11, 21, 33, 33, 101, 151, 155};
      System.out.println("线程查找方法是："+seqSearch(arr,1545));
      System.out.println("二分查找方法是："+binarySearch(0,arr1.length-1,arr1,101));
      int arr2[]=new int[100];
        for (int i = 0; i < arr2.length; i++) {
          arr2[i]=i+1;
        }
          System.out.println("二分查找的选择查找是方法是："+insertValSearch(0,arr2.length-1,arr2,56));


       beautyArr(15);
  }

    /**
     * 线性查找，对数组进行遍历，找到返回下标，找不到返回-1
     * @param arr
     * @param findVal
     * @return
     */
  public static int seqSearch(int[] arr,int findVal){
      for(int i=0;i<arr.length;i++){
          if(findVal==arr[i]){
              return i;
          }
      }
      return -1;
  }

    /**
     * 二分查找法，数组要有序才行，每次查找之前的一半
     * @param left
     * @param right
     * @param arr
     * @param findVal
     * @return
     */

  public static int binarySearch(int left,int right,int[] arr,int findVal){
      if(findVal>arr[right] || findVal<arr[left]||left>right){
          return -1;
      }
      int mid=(left+right)/2;
      int midVal=arr[mid];
      if(findVal>midVal){
          return  binarySearch(mid+1,right,arr,findVal);
      }else if(findVal<midVal){
          return binarySearch(left,mid-1,arr,findVal);
      }else {//如果是返回list这里只用在mid左右进行遍历判断，如果值跟要查的值一样就放入，
          return mid;
      }
  }

    /**
     * 和二分查找基本一样，唯一不同的是mid值的计算
     * @param left
     * @param right
     * @param arr
     * @param findVal
     * @return
     */
  public static int insertValSearch(int left,int right,int[] arr,int findVal){
      if(findVal>arr[right] || findVal<arr[left]||left>right){
          return -1;
      }
      int mid = left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
      int midVal =arr[mid];
      if(findVal>midVal){
          return  insertValSearch(mid+1,right,arr,findVal);
      }else if(findVal<midVal){
          return insertValSearch(left,mid-1,arr,findVal);
      }else {
          return mid;
      }
  }

    /**
     * 产生长度为len的斐波那契数列
     * @param len
     */
  public static void beautyArr(int len){
      int[] arr =new int[len];
      for(int i=0;i<arr.length;i++){
          if(i==0 || i==1){
              arr[i]=1;
          }else{
              arr[i]=arr[i-1]+arr[i-2];
          }
      }
      System.out.println(Arrays.toString(arr));

  }
}
