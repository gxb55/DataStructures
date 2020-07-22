package com.atguigu.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort
 * @Author guoxiaobing
 * @Date 2020/7/14 14:23
 * @Version 1.0
 * @Description 快速排序
 */
public class QuickSort {
  public static void main(String[] args) {
     /* long time1 =System.currentTimeMillis();
      int arr[] = new int[800000];
      for(int i=0;i<800000;i++){
          arr[i]= (int) (Math.random()*900000);
      }
      //sort1(arr);
      sort(0,arr.length-1,arr);
      System.out.println(System.currentTimeMillis()-time1);*/


      int arr[] = {33,101,21, 33, 155, 151,11};
      //int arr[] = {332,101};

      quickSort(0,arr.length-1,arr);
      System.out.println(Arrays.toString(arr));
  }
  public static void sort(int left,int right,int[] arr){
      /**
       * 1.左边第一个数开始为什么要从右边开始选数 ？ 从右开始选能保证每次左边的那个数即l肯定是小于基准值的，如果从左边开始的话，那有可能左边的这个是要交换的，但是右边没有符合条件了
       * 就没有交换，就会将一个大数放在了基准位置的左边
       * 2.在结束后的一轮里面为什么在递归的时候也有顺序？在结束一轮后，将数组分为两部分，这两部分递归是没有顺序的可以随机调换
       * 3.为什么都是用的r来分割数组的？其实用l和用r是一样的，应为最后l会跟r相等，即l这个位置确定了。
       *
       */
      int l = left;
      int r = right;
      if(l>r){
          return;
      }
      int temp;
      int pivot=arr[l];
      while (r>l){

          while (arr[r]>=pivot&&r>l){
              r--;
          }
          while (arr[l]<=pivot &&r>l){
              l++;
          }
          if(r>l){
              temp=arr[l];
              arr[l]=arr[r];
              arr[r]=temp;
          }
      }
      arr[left]=arr[l];
      arr[l]=pivot;
      /*if(l==r){
          System.out.println("l是：："+l);
      }*/
   //   System.out.println(Arrays.toString(arr));
      sort(left,r-1,arr);
      sort(r+1,right,arr);

  }

  public static void quickSort(int left,int right,int[] arr){
      if(left>right){
          return;
      }
      int l =left;
      int r= right;
      int temp=arr[l];
      int t;
      while (r>l){
          while (arr[r]>=temp && r>l){
              r--;
          }
          while (arr[l]<=temp && r>l){
              l++;
          }
          if(r>l){
            t=arr[l];
            arr[l]=arr[r];
            arr[r]=t;
          }
      }
      arr[left]=arr[l];
      arr[l]=temp;

      quickSort(left,r-1,arr);
      quickSort(r+1,right,arr);


  }


}
