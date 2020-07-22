package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BinarySearch @Author guoxiaobing @Date 2020/7/14 17:09 @Version 1.0 @Description 二分查找
 * 数组必须是有序的不然没法找
 */
public class BinarySearch {
  public static void main(String[] args) {
    int[] arr = {1, 8, 10, 89,1000,1000,1000,1000,  1234};
    System.out.println(search2(arr, 0, arr.length, 1000));
  }

    /**
     * 单个值的，就是说找到一个匹配的就返回
     * @param arr
     * @param left
     * @param right
     * @param val
     * @return
     */
  public static int search(int[] arr, int left, int right, int val) {
    if (left > right || val>arr[right-1]|| val<arr[0]) {
      return -1;
    }
    int mid = (left + right) / 2;

    int temp = arr[mid];
    if (temp > val) {
      return search(arr, left, mid - 1, val);
    } else if (temp < val) {
      return search(arr, mid + 1, right, val);
    } else {
      return (left + right) / 2;
    }
  }

    /**
     * 多个值匹配的过程，返回的是一个list
     * @param arr
     * @param left
     * @param right
     * @param val
     * @return
     */
    public static List search2(int[] arr, int left, int right, int val) {
        if (left > right || val>arr[right-1]|| val<arr[0]) {
            return new ArrayList();
        }
        int mid = (left + right) / 2;

        int temp = arr[mid];
        if (temp > val) {
            return search2(arr, left, mid - 1, val);
        } else if (temp < val) {
            return search2(arr, mid + 1, right, val);
        } else {
            List list = new ArrayList();
            int pre =mid-1;
            while (true){
                if(pre<0|| arr[pre]!=val){
                    break;
                }
                list.add(pre);
                pre--;
            }
            list.add(mid);
            int next = mid+1;
            while (true){
                if(next>arr.length-1|| arr[next]!=val){
                    break;
                }
                list.add(next);
                next++;
            }
            return list;
        }
    }
}
