package com.auguigu.sparsearray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: git_springcloud
 * @description: 稀疏数组
 * @author: guoxiaobing
 * @create: 2020-06-03 21:17
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         * 不饱和的二维数组变成稀疏数组，稀疏数组再变成二维数组。
         * 0表示没有棋子；1表示蓝子；2表示黑子 行 列
         */
        //1.创建二维数组
        int[][] arr = new int[11][11];
        System.out.println(arr[0][0]);
        arr[1][2] = 2;
        arr[2][3] = 1;
        int t = 1;
        List<Map<String, Integer>> list = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            System.out.println();
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
                if (arr[i][j] != 0) {
                    t++;
                    Map<String, Integer> map = new HashMap();
                    map.put("i", i);
                    map.put("j", j);
                    map.put("val", arr[i][j]);
                    list.add(map);
                }
            }
        }
        System.out.println();
        int[][] spareArr = new int[t][3];
        spareArr[0][0] = 11;//行
        spareArr[0][1] = 11;//列
        spareArr[0][2] = t;//有值的个数
        System.out.println("转换后的稀疏数组输出-------");
        for (int i = 0; i < list.size(); i++) {
            spareArr[i + 1][0] = list.get(i).get("i");
            spareArr[i + 1][1] = list.get(i).get("j");
            spareArr[i + 1][2] = list.get(i).get("val");
        }

        for (int i = 0; i < spareArr.length; i++) {
            System.out.println();
            for (int j = 0; j < spareArr[i].length; j++) {
                System.out.print(spareArr[i][j] + " ");
            }
        }
        System.out.println();
        System.out.println("");
    }
}