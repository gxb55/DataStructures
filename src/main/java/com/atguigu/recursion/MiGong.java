package com.atguigu.recursion;

/**
 * @ClassName MiGong
 * @Author guoxiaobing
 * @Date 2020/6/28 19:51
 * @Version 1.0
 * @Description 迷宫问题
 */
public class MiGong {
  public static void main(String[] args) {
    //创建地图 二维数组来模拟 行列
      int[][] map = new int[8][7];
      builderMap(map);
      for(int i=0;i<map.length;i++){
          for(int j=0;j<map[i].length;j++){
              System.out.print(map[i][j]+"  ");
          }
          System.out.println();
      }
     // setWay(map,1,1);
      setWay2(map,1,1);
      System.out.println("递归后的迷宫是。。。。。。。。");
      for(int i=0;i<map.length;i++){
          for(int j=0;j<map[i].length;j++){
              System.out.print(map[i][j]+"  ");
          }
          System.out.println();
      }
  }

    /**
     *
     * @param map 地图
     * @param i 初始化为起点
     * @param j
     * @return 是否能走通
     * 约定 1 墙；2 走过了 ；3走过了，走不通 ；0未走过
     * int[7][6]==2 结束说明迷宫走完了
     *
     * 下 右 上 左
     */
  public static boolean setWay(int[][] map,int i,int j){
      if(map[6][5]==2){//走到了结尾的位置，胜利了切找到合适的位置了
          return true;
      }else if (map[i][j]==0){//说明这个位置没有人走可以走
          map[i][j]=2;//假定这个位置是没有问题的就是可以走的
          //定下走的策略是 下右上左
          if(setWay(map,i+1,j)){//下
           return true;
          }else if (setWay(map,i,j+1)){//右
              return true;
          }else if (setWay(map,i-1,j)){//上
              return true;
          }else if (setWay(map,i,j-1)){//左
              return true;
          }else {
              map[i][j]=3;//上面的路都试过了不行就将这个点置为死路
              return false;
          }
      }else{//剩下 1  3,要不是墙不能走 要不就是走过了的死路
          return false;
      }
  }

    /**
     *
     * @param map
     * @param i
     * @param j
     * @return 上 下 左 右
     */
    public static boolean setWay2(int[][] map,int i,int j){
        if(map[6][5]==2){//走到了结尾的位置，胜利了切找到合适的位置了
            return true;
        }else if (map[i][j]==0){//说明这个位置没有人走可以走
            map[i][j]=2;//假定这个位置是没有问题的就是可以走的
            //定下走的策略是 上 下 左 右
            if(setWay2(map,i-1,j)){//上
                return true;
            }else if (setWay2(map,i+1,j)){//下
                return true;
            }else if (setWay2(map,i,j-1)){//左
                return true;
            }else if (setWay2(map,i,j+1)){//右
                return true;
            }else {
                map[i][j]=3;//上面的路都试过了不行就将这个点置为死路
                return false;
            }
        }else{//剩下 1  3,要不是墙不能走 要不就是走过了的死路
            return false;
        }
    }

    private static void builderMap(int[][] map) {
        //使用1标识墙 使用2标识走的路
        for(int i=0;i<map[0].length;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        for(int i=0;i<map.length;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1]=1;
        map[3][2]=1;

        /*map[1][2]=1;
        map[2][2]=1;*/


    }
}
