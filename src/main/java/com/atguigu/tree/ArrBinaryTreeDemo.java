package com.atguigu.tree;

/**
 * @ClassName ArrBinaryTreeDemo
 * @Author guoxiaobing
 * @Date 2020/7/30 19:33
 * @Version 1.0
 * @Description 树的顺序遍历
 */
public class ArrBinaryTreeDemo {
  public static void main(String[] args) {
      int arr[] = {1,2,3,4,5,6,7};
      ArrBinary arrBinary = new ArrBinary(arr);
      arrBinary.infixOrder(0);
  }
}
class ArrBinary{
    private int[] arr;

    public ArrBinary(int[] arr) {
        this.arr = arr;
    }

    /**
     * index 下标数组 根 左 右
     * @param index
     */
    public  void preOrder(int index){
        if(this.arr == null ||arr.length==0){
            return;
        }
        System.out.println(arr[index]);
        if((index*2+1)<arr.length){//下标
            preOrder((index*2+1));
        }
        if((index*2+2)<arr.length){
            preOrder((index*2+2));
        }
    }

    /**
     *  左 根 右
     * @param index
     */
    public void infixOrder(int index){
        if(this.arr == null ||arr.length==0){
            return;
        }
        if((index*2)+1<arr.length){
            infixOrder(index*2+1);
        }
        System.out.println(arr[index]);
        if(index*2+2<arr.length){
            infixOrder(index*2+2);
        }
    }
}
