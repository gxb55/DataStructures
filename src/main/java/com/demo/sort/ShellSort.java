package com.demo.sort;

import java.util.Arrays;

/**
 * @ClassName ShellSort
 * @Author guoxiaobing
 * @Date 2020/7/15 9:19
 * @Version 1.0
 * @Description 先分组然后再用选择排序来排序数组，这样的好处是避免让一个数比较很多次之后
 * 才能找到合适的位置，在数组中找到步长，每次按照步长来比较，即分组的。
 *
 * 为什么要学习优秀的算法，同样是排序人家比你快两倍为什么不用好的算法？？
 */
public class ShellSort {
  public static void main(String[] args) {
      int arr[] = {33,101,21, 33, 155, 151,11};
      sort(arr);
      System.out.println(Arrays.toString(arr));
  }

  public static void sort(int[] arr){
      int index,val;
    for (int gap = arr.length/2; gap > 0; gap/=2) {//即是分组的步长，也是分组的个数
        System.out.println("步长是："+gap);
      for (int i= gap;i<arr.length;i++){//从第一个步长的位置开始找，每次比较一组的
          index=i;
          val=arr[i];
          while (index-gap>=0 && arr[index-gap]>val){//升序
              arr[index]=arr[index-gap];
              index-=gap;
          }
          arr[index]=val;
          System.out.println("交换后是："+Arrays.toString(arr));
      }

    }
  }
}
