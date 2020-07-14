package com.atguigu.sort;

import java.util.Arrays;

/** @ClassName InsertSort
 *  @Author guoxiaobing
 *  @Date 2020/7/9 19:11
 *  @Version 1.0
 *  @Description 插入排序
 *  */
public class InsertSort {
  public static void main(String[] args) {
      int arr[] = {101,21, 33, 1, 151};
      teacherSort(arr);
      System.out.println(Arrays.toString(arr));
  }

  public static void sort(int[] arr) {
      int index,indexVal;
      for(int i=1;i<arr.length;i++){
          index=i-1;
          indexVal=arr[i];
          while (index>=0 && arr[index]>indexVal){
              arr[index+1]=arr[index];
              index--;
          }
          arr[index+1]=indexVal;
      }
  }


    /**
     * 插入排序 ，有点难度，重点在于
     * 一轮循环前面的是排好序的后面的是未排序的
     * 每次从未排序的拿出来一个去排序的里面找到对应的位置
     * 从数组的第二个数开始比较，用while来控制循环是否继续进行
     * 其中 arr[i+1]=arr[i];相当于把数组的每一位往后移动了一下，
     * @param arr
     */
    public static void teacherSort(int[] arr){
        int index,indexVal;
        for(int i=1;i<arr.length;i++){
            index=i-1;
            indexVal=arr[i];
            while (index>=0&&arr[index]<indexVal){//降序
                arr[index+1]=arr[index];
                index--;
            }
            arr[index+1]=indexVal;
        }
  }












}
