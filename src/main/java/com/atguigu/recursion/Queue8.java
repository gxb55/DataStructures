package com.atguigu.recursion;

import java.util.Arrays;

/**
 * @ClassName Queue8
 * @Author guoxiaobing
 * @Date 2020/6/30 19:21
 * @Version 1.0
 * @Description 八皇后的问题
 */
public class Queue8 {
  static int max=8;
 static int []arr =new int[max];

  public static void main(String[] args) {
    int temp[][] = new int[8][8];
   // temp[3][3]=8;
  //  System.out.println(isSafe(temp,2,4));
    System.out.println(isSafe(temp,5,0));
      solveQueue(temp,0,0);
    System.out.println(Arrays.toString(arr));
  }
  public static void solveQueue(int[][] temp, int i, int j){
        for(;j<temp[i].length;j++){
            if(isSafe(temp,i,j)){
                temp[i][j]=8;
                arr[i]=j;
                solveQueue(temp,i+1,j);
            }
        }
  }

  public static Boolean isSafe(int[][] temp,int i,int j){
        for(int k=0;k<temp.length;k++){
            for(int p=0;p<temp[k].length;p++){
                if(i==k&&temp[k][p]==8){//一行
                    return false;
                }else if (j==p&&temp[k][p]==8){//一列
                    return false;
                }else {//交叉
                    for(int q=0;q<temp.length;q++){
                        if(i+q<temp.length&&j-q>0){//左下
                            if (temp[i+q][j-q]==8){
                                return false;
                            }
                        }
                        if(j+q<temp.length&&i-q>0){//右上
                            if (temp[i-q][j+q]==8){
                                return false;
                            }
                        }
                        if (j+q<temp.length&&i+q<temp.length){//右下
                            if (temp[i+q][j+q]==8){
                                return false;
                            }
                        }
                        if(j-q>0&&i-q>0){//左上
                            if (temp[i-q][j-q]==8){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
  }
  public void printf(){
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
  }
}
