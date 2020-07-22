package com.demo.recursion;

/**
 * @ClassName Map
 * @Author guoxiaobing
 * @Date 2020/7/14 10:43
 * @Version 1.0
 * @Description 复习迷宫问题
 */
public class Map {
  public static void main(String[] args) {
    int[][] arr = new int[10][10];
    for(int i=0;i<arr.length;i++){
        arr[i][0]=1;
        arr[i][9]=1;
    }
    for(int i=0;i<arr[0].length;i++){
        arr[0][i]=1;
        arr[9][i]=1;
    }
    arr[4][1]=1;
    arr[4][2]=1;
    arr[4][3]=1;
    print(arr);
    site(1,1,arr);
      System.out.println("迷宫结束了。。");
    print(arr);
  }

    /**
     * 递归要有结束条件，即什么情况下递归结束，而不能不结束
     * 1.墙 2.走过的路 3.走过的路切没有走通 0.未走过的
     * @param i
     * @param j
     * @param arr
     * @return
     */
  public static boolean site(int i,int j,int[][] arr){
      if(arr[8][8]==2){
          return true;
      }
      if(arr[i][j]==0){//说明没有走过
          arr[i][j] =2;//下 右 左 上
          if(site(i+1,j,arr)){//
              return true;
          }else if (site(i,j+1,arr)){
              return true;
          }else if (site(i,j-1,arr)){
              return true;
          }else if (site(i-1,j,arr)){
              return true;
          }else{
              arr[i][j] =3;
              return false;
          }
      }else {
          return false;//只有0可以走其他的都不行，这里直接return false;
      }

  };
  public static void print(int[][] arr){
      for(int i=0;i<arr.length;i++){
          for(int j=0;j<arr[i].length;j++){
              System.out.print(arr[i][j]+"  ");
          }
          System.out.println();
      }
  }
}
