package com.atguigu.tree;

import java.util.Arrays;

/**
 * @ClassName HeapSort
 * @Author guoxiaobing
 * @Date 2020/9/8 19:15
 * @Version 1.0
 * @Description 堆排序
 */
public class HeapSort {
  public static void main(String[] args) {
   int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,33};
      heapSort(arr);
  }

    /**
     * 待排序数组
     * @param arr
     */
  public static void heapSort(int[] arr){
        for(int i= arr.length/2-1;i>=0;i--){//构造大顶堆 其实堆顶元素目前是最大的
            adjustHeap(arr,i,arr.length);
        }
        System.out.println(Arrays.toString(arr));
        int temp ;
        for(int i=arr.length-1;i>=0;i--){
            temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            System.out.println("i:"+i+"  "+Arrays.toString(arr));
            adjustHeap(arr,0,i);
        }
      System.out.println(Arrays.toString(arr));
  }

    /**
     *功能：完成将以 i 对应的非叶子节点的树调整为大顶堆
     * @param arr 待排序数组
     * @param i 非叶子节点在数组中的索引
     * @param length 标识对多少个元素继续调整，length是在逐渐减少
     *               后面的都是排序好了的
     */
  public static void adjustHeap(int[] arr,int i,int length){
      int temp = arr[i];
      for(int k=2*i+1;k<length;k=k*2+1){
          if(k+1<length && arr[k+1]>arr[k]){
              k++;
          }
          if(arr[k]>temp){
              arr[i] =arr[k];
              i=k;
          }else{
              break;
          }
      }
       arr[i] = temp;

  }
}
