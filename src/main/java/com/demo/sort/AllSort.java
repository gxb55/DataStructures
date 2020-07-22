package com.demo.sort;

import java.util.Arrays;

import static com.demo.search.FibonacciSearch.getArr;

/**
 * @ClassName AllSort @Author guoxiaobing @Date 2020/7/17 10:40 @Version 1.0 @Description
 * 种种排序对比，复习加深记忆
 */
public class AllSort {
  public static void main(String[] args) {
    int[] arr = {10101, 4, -1, 5, 89, 10, 4, -52, 4111, 258258, -568};
    // bubbleSort(arr);
    // selectSort(arr);
    // insertSort(arr);
    // shellSort(arr);
    quickSort(0, arr.length - 1, arr);
    System.out.println(Arrays.toString(arr));

    // int byLoop = findByLoop(-1, arr);
    //int byLoop = binaryFind(-568, arr, 0, arr.length - 1);
    int byLoop = fibonacciSearch(-68, arr);

    System.out.println(byLoop);
  }

  /**
   * 相邻的两个数来比较大小，排好序的在数组的最后，如果一轮下来没有交换位置则说明数组已经有序了提前终止
   *
   * @param arr
   */
  public static void bubbleSort(int[] arr) {
    int temp;
    boolean flag = false;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) { // 降序
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          flag = true;
        }
      }
      if (!flag) { // 本次没有交换则直接结束
        break;
      } else {
        flag = false;
      }
    }
  }

  /**
   * 选择排序1.找到最大的或者最小的放在第一位，内层循环一遍找到最大的下标和值，然后如果下标不一致则更换
   *
   * @param arr
   */
  public static void selectSort(int[] arr) {
    int k, val;
    for (int i = 0; i < arr.length; i++) {
      val = arr[i];
      k = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (val < arr[j]) { // 降序
          val = arr[j];
          k = j;
        }
      }
      if (k != i) { // 说明假设的位置是错的
        arr[k] = arr[i];
        arr[i] = val;
      }
    }
  }

  /**
   * 插入排序 找到对应的位置插入元素
   *
   * @param arr
   */
  public static void insertSort(int[] arr) {
    int j, val;
    for (int i = 1; i < arr.length; i++) {
      j = i - 1; // 要比较的下标，当前的下标前面的那个
      val = arr[i]; // 当前位置的值
      while (j >= 0 && arr[j] < val) { // 降序 里面是要排序的内容
        arr[j + 1] = arr[j];
        j--;
      }
      if (j != i) { // 说明下标为i的数字跟他现在的位置不匹配
        j++;
        arr[j] = val;
      }
    }
  }

  /**
   * 希尔排序，插入排序的升级版，对数据先进行分组，分组比较， 直到分为1组的时候即可
   *
   * @param arr
   */
  public static void shellSort(int[] arr) {
    int index, indexVal;
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < arr.length; i++) {
        index = i;
        indexVal = arr[i];
        while (index - gap >= 0 && indexVal > arr[index - gap]) { // 降序
          arr[index] = arr[index - gap];
          index -= gap;
        }
        arr[index] = indexVal;
      }
    }
  }

  public static void quickSort(int begin, int end, int[] arr) {
    if (begin > end) {
      return;
    }
    int i = begin;
    int j = end;
    int temp;
    int val = arr[i];
    while (j > i) {
      while (j > i && val <= arr[j]) {
        j--;
      }
      while (j > i && val >= arr[i]) {
        i++;
      }
      if (j > i) {
        temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }
    arr[begin] = arr[i];
    arr[i] = val;

    quickSort(begin, i - 1, arr);
    quickSort(i + 1, end, arr);
  }

  /**
   * 循环的方式来找其对应的下标 找到返回下标，找不到返回-1
   *
   * @param temp
   * @param arr
   * @return
   */
  public static int findByLoop(int temp, int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (temp == arr[i]) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 有序数组才可以使用
   *
   * @return
   */
  public static int binaryFind(int temp, int[] arr, int left, int right) {
    if (temp > arr[arr.length - 1] || temp < arr[0] || left > right) {
      return -1;
    }
    int mid = (left + right) / 2;
    System.out.println("mid  " + mid);
    int val = arr[mid];
    if (val > temp) {
      return binaryFind(temp, arr, left, mid - 1);
    } else if (temp > val) {
      return binaryFind(temp, arr, mid + 1, right);
    } else {
      return mid;
    }
  }

  public static int insertValueSearch(int temp, int[] arr, int left, int right) {
    if (left > right || temp > arr[arr.length - 1] || temp < arr[0]) {
      return -1;
    }
    int mid = left + (right - left) * temp - arr[left] / arr[right] - arr[left];
    int val = arr[mid];
    if (val > temp) {
      return insertValueSearch(temp, arr, left, mid - 1);
    } else if (temp > val) {
      return insertValueSearch(temp, arr, mid + 1, right);
    } else {
      return mid;
    }
  }

  /**
   * arr[k]=arr[k-1]+arr[k-2]
   * arr[k]-1=arr[k-1]-1+arr[k-2]-1 +1
   * mid = arr[k-1]-1
   *
   * @param temp
   * @param arr
   * @return
   */
  public static int fibonacciSearch(int temp, int[] arr) {
    if ( temp > arr[arr.length - 1] || temp < arr[0]) {
      return -1;
    }
    int newArr[] = getArr(arr.length);
    int k = 0;
    int left =0;
    int right=arr.length-1;
    while (newArr[k] < arr.length) {
      k++;
    }
    int[] ints = Arrays.copyOf(arr, newArr[k]);
    for(int i=right;i<newArr.length;i++){
      ints[i]=arr[right];
    }
    while (right>left){
      int mid = left+newArr[k-1]-1;
      int val =ints[mid];
      if(val>temp){
        k-=2;
        right =mid-1;
      }else if(temp>val){
        k--;
        left=mid+1;
      }else{
        if(mid>right){
          return right;
        }else{
          return mid;
        }
      }
    }
    return -1;
  }
}
