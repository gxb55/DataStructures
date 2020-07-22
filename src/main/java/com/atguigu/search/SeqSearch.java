package com.atguigu.search;

/**
 * @ClassName SeqSearch
 * @Author guoxiaobing
 * @Date 2020/7/14 17:01
 * @Version 1.0
 * @Description 查找 线性查找
 */
public class SeqSearch {
  public static void main(String[] args) {
    int arr[]={1,9,11,-1,34,89};
      int search = search(101, arr);
      if(search==-1){
          System.out.println("没有找到哦");
      }else {
          System.out.println("找到了哦 "+search);
      }
  }
  public static int search(int i,int[] arr){
    for (int j = 0; j < arr.length; j++) {
      if(arr[j]==i){
          return j;
      }
    }
    return  -1;
  }
}
