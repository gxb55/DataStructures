package com.atguigu.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: 2
 * @description: 复习斐波那契数列查找数
 * @author: guoxiaobing
 * @create: 2020-09-07 21:28
 */
public class FibonacciSearch1 {
    public static void main(String[] args) {
        FibonacciSearch1 search = new FibonacciSearch1();
        List<Integer> list = new ArrayList<>();
        search.getFibo(10, list);
        int[] arr = {1, 8, 9, 10, 89, 1000, 1234,1238,1256};
        //System.out.println(list.toString());
        int i = search.search(arr, 1238);
        System.out.println(i);
    }

    /**
     * 1.构建斐波那契数列
     * 2.根据斐波那契数列求到mid
     * 3.根据mid和输入的值对比决定最终去哪找
     *
     * @param arr
     * @param n
     * @return
     */
    public int search(int[] arr, int n) {
        if (n > arr[arr.length - 1] || n < arr[0]) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        List<Integer> list = new ArrayList<>();
        getFibo(50, list);
        int k = arr.length;
        int t = 0;
        while (arr.length > list.get(t)-1) {
            t++;
        }
        int temp[] = Arrays.copyOf(arr, list.get(t)-1);
        for (int i = high; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        int mid;
        while (high >= low) {
            mid = low + list.get(t - 1) - 1;
            if (n < temp[mid]) {
                t -= 1;
                high=mid-1;
            } else if (n > temp[mid]) {
                t -= 2;
                low=mid+1;
            } else {
                if (mid < high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    public void getFibo(int n, List<Integer> list) {
        if (n > 0) {
            if (list.size() == 0) {
                list.add(1);
            } else if (list.size() == 1) {
                list.add(1);
            } else {
                list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
            }
            n--;
            getFibo(n, list);
        } else {
            return;
        }
    }
}