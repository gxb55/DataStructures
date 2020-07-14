package com.atguigu.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Solution
 * @Author guoxiaobing
 * @Date 2020/7/8 9:37
 * @Version 1.0
 * @Description 力扣
 */
public class Solution {
  public static void main(String[] args) {
    int arr[]={1,8,6,2,5,4,8,3,8};
    //System.out.println( maxArea(arr));
      //char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
      //char[][] matrix = {{'1'}};
     // char[][] matrix = {{'1','0','1','1','1'},{'0','1','0','1','0'},{'1','1','0','1','1'},{'1','1','0','1','1'},{'0','1','1','1','1'}};
     // char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
      char[][] matrix = {{'1','0','1','1','0','1'},{'1','1','1','1','1','1'},{'0','1','1','0','1','1'},{'1','1','1','0','1','0'},{'0','1','1','1','1','1'},{'1','1','0','1','1','1'}};
      //char[][] matrix = {{'1','1','1','1','1','1','1','1'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','1','1','0'},{'1','1','1','1','1','0','0','0'},{'0','1','1','1','1','0','0','0'}};
      System.out.println(maximalRectangle(matrix));
      System.out.println(maximalRectangle1(matrix));

  }
    public static int maxArea(int[] height) {
        int t=0;
        int sum;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                if(height[j]>=height[i]){
                    sum=(height[i])*(j-i);
                    if(t<sum){
                        t=sum;
                    }
                }else{
                    sum=(height[j])*(j-i);
                    if(t<sum){
                        t=sum;
                    }
                }
            }
        }
        return t;
    }
   /* public static int maximalRectangle(char[][] matrix)  {
        int t,k;
        int m,n=0;
        int sum = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                t=i;//1 2
                k=j;
                while(j<matrix[t].length&&matrix[t][j]=='1'){//向右
                    j++;
                }

                while(i<matrix.length&&matrix[i][k]=='1'){
                    i++;
                }
                for( m=t;m<i;m++){
                    for(n=k;n<j;n++){
                        if(matrix[m][n]!='1'){
                            if((m-t)>=(n-k)){
                                j=n;
                            }else{
                                i=m;
                            }
                        }
                    }
                }
                if((m-t)>0&&(n-k)>0){
                    if((m-t)*(n-k)>sum){
                        sum = (m-t)*(n-k);
                    }
                }
                i=t;
                j=k;
            }
        }
        return sum;
    }*/

    public static int maximalRectangle1(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        } return maxarea;
    }
    public static int maximalRectangle(char[][] matrix) {
        int[][] arr = new int[matrix.length][matrix[0].length];
        int result =0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]=='1'){//1的时候特殊处理
                    if(j==0){
                        arr[i][j]=1;
                    }else{
                        arr[i][j]=arr[i][j-1]+1;
                    }

                    int width = arr[i][j];
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, arr[k][j]);
                        result = Math.max(result, width * (i - k + 1));
                    }
                }
            }
        }
        return result;
    }

}
