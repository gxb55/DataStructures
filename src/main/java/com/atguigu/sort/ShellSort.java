package com.atguigu.sort;

import java.util.Arrays;

/**
 * @ClassName ShellSort
 * @Author guoxiaobing
 * @Date 2020/7/13 19:19
 * @Version 1.0
 * @Description 希尔排序，每次交换，避免插入排序那样的来回移动元素
 */
public class ShellSort {
  public static void main(String[] args) {
      /*long time1 =System.currentTimeMillis();
      int arr[] = new int[80000];
      for(int i=0;i<80000;i++){
          arr[i]= (int) (Math.random()*90000);
      }
      //sort(arr);
      sort1(arr);
      System.out.println(System.currentTimeMillis()-time1);

       time1 =System.currentTimeMillis();
      int arr1[] = new int[80000];
      for(int i=0;i<80000;i++){
          arr1[i]= (int) (Math.random()*90000);
      }
      bubbleSort(arr1);
      System.out.println(System.currentTimeMillis()-time1);*/

      int arr3[] = {101,21, 33, 1, 151};
      shellSort(arr3);
      System.out.println(Arrays.toString(arr3));

  }

  public static void sort(int[] arr){
      int temp;

      for( int gap=arr.length/2;gap>0;gap=gap/2){//步长
          for(int i =gap;i<arr.length;i++){//分为几组
              for(int j =i-gap;j>=0;j-=gap){//具体每组中的元素对比
                  if(arr[j]>arr[j+gap]){
                      temp=arr[j+gap];
                      arr[j+gap]=arr[j];
                      arr[j]=temp;
                  }
              }
          }
          System.out.println(Arrays.toString(arr));
      }
  }

  public static void sort1(int[] arr){
      for(int gap = arr.length/2;gap>0;gap/=2){//步长
          for(int i=gap;i<arr.length;i++){//从第一个步长的位置到数组的最后 下面就是计算第一个步长开始后，这个位置前面相隔步长的数的比较
              int j=i;
              int temp=arr[i];
              if(arr[j]<arr[j-gap]){
                  while (j-gap>=0 && temp<arr[j-gap]){
                      arr[j]=arr[j-gap];
                      j-=gap;
                  }
                  arr[j]=temp;
              }

          }

         // System.out.println("步长是："+gap+" 这轮排序后的数组是："+Arrays.toString(arr));
      }
  }
  public static void bubbleSort(int[] arr){
      int temp;
      boolean flag = false;
      for(int i=0;i<arr.length;i++){
          for(int j=0;j<arr.length-1-i;j++){
              if(arr[j]>arr[j+1]){//升序
                temp=arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;
                flag = true;
              }
          }
          if(!flag){
              break;
          }else{
              flag = false;
          }
      }
  }

    /**
     * 自己写的
     * 思想就是分组加插入排序，每次操作的都是那一组的数据，每次分组完操作的都会再次分组，
     * 最后分组是1的时候其实就是对于整体做了一次插入排序，但是由于之前分组过了所以，最后一轮的插入排序
     * 会快很多的
     * @param arr
     */
  public static void shellSort(int[] arr){
      int index,indexVal;
      for(int gap=arr.length/2;gap>0;gap=gap/2){//分组 每次分组是之前的一般
          for(int i=gap;i<arr.length;i++){//从分组的第一个元素开始往后算，每次计算的时候都是相隔一个步长的距离
              index = i;
              indexVal=arr[i];
              if(arr[i]>arr[i-gap]){//降序
                  while (index-gap>=0 && indexVal>arr[index-gap]){//拿当前的这个值和同一组的数值比较，如果位置不合适则交换
                      arr[index]=arr[index-gap];
                      index-=gap;
                  }
                  arr[index]=indexVal;
              }
          }
      }

  }
}
