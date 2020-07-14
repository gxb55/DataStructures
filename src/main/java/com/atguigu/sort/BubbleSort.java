package com.atguigu.sort;

import java.util.Arrays;

/** @ClassName BubbleSort
 * @Author guoxiaobing
 * @Date 2020/7/7 19:35
 * @Version 1.0
 * @Description 冒泡排序
 * 时间复杂度是O(n2)
 * */
public class BubbleSort {
  public static void main(String[] args) {
      long time1 =System.currentTimeMillis();
    int arr[] = new int[80000];
    for(int i=0;i<80000;i++){
        arr[i]= (int) (Math.random()*90000);
    }
    //int arr[] = {1,2, 3, 10, 11};
    BubbleSort sort = new BubbleSort();
    sort.sort(arr);
   // sort.teachSort(arr);//11747
      //System.out.println(Arrays.toString(arr));
    System.out.println(System.currentTimeMillis()-time1);

    Arrays.sort(arr);
  }

  public void sort(int[] arr) {
    if (arr.length < 2) {
      return;
    }
    int t;
    //System.out.println(Arrays.toString(arr));
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          t = arr[i];
          arr[i] = arr[j];
          arr[j] = t;
        }
      }
     // System.out.println(Arrays.toString(arr));
    }
  }
  public void teachSort(int[] arr){
      if(arr.length<2){
          return;
      }
      int temp;
      boolean falg =false;
      for(int j=0;j<arr.length-1;j++){
          for(int i=0;i<arr.length-1-j;i++){
              if(arr[i]<arr[i+1]){//判断条件决定了是升序还是降序
                  temp = arr[i];
                  arr[i] =arr[i+1];
                  arr[i+1]=temp;
                  falg =true;
              }
          }
          if(!falg){
              break;
          }else{
              falg =false;
          }
        //  System.out.println("第"+(j+1)+"次："+Arrays.toString(arr));
      }

  }
}
