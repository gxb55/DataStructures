package com.atguigu.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName TeacherHeapSort
 * @Author guoxiaobing
 * @Date 2020/9/8 19:49
 * @Version 1.0
 * @Description TODO
 */
public class TeacherHeapSort {
    public static void main(String[] args) {
        /*int[] arr = new int[8000000];

        for(int i = 0; i < 8000000; ++i) {
            arr[i] = (int)(Math.random() * 8000000.0D);
        }*/
        int[] arr = {1,5,8,9,60,23,-1,56,-8,900};
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        heapSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序!!");

        int j;
        for(j = arr.length / 2 - 1; j >= 0; --j) {
            adjustHeap(arr, j, arr.length);
        }

        for(j = arr.length - 1; j > 0; --j) {
             temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

    }

    public static void adjustHeap(int[] arr, int i, int lenght) {
        int temp = arr[i];

        for(int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            if (k + 1 < lenght && arr[k] < arr[k + 1]) {
                ++k;
            }

            if (arr[k] <= temp) {
                break;
            }

            arr[i] = arr[k];
            i = k;
        }

        arr[i] = temp;
    }
}
