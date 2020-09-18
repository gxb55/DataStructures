package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName Graph
 * @Author guoxiaobing
 * @Date 2020/9/18 13:59
 * @Version 1.0
 * @Description 图
 */
public class Graph {
    private ArrayList<String> vertexList;//点的结合
    private int[][] deges;//边之间的关系，即邻接矩阵
    private int numOfEdges;// 边的书面
  public static void main(String[] args) {
    //
  }

    /**
     *
     * @param n n是定点的个数
     */
  public Graph(int n){
      deges = new int[n][n];
      numOfEdges = 0;
      vertexList = new ArrayList<String>(n);
  }

    /**
     * 插入定点
     * @param vertex
     */
  public void insertVertex(String vertex){
      vertexList.add(vertex);
  }

    /**
     * A B C D E
     * 无向图所以A-B B-A
     *
     * @param v1 定点的下标 比如 A
     * @param v2 定点的下标 比如B
     * @param weight 是否连接 0不连接 ；1连接
     */
  public void insertEdge(int v1,int v2,int weight){
      deges[v1][v2]=weight;
      deges[v2][v1]=weight;
      numOfEdges++;//边的数量++
  }

  //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex(){
      return vertexList.size();
    }
    //返回边的个数
    public int getNumofEdges(){
      return numOfEdges;
    }
    //返回节点i 对应下标的数据比如 0-A 1-B
    public String getValueByIndex(int i){
      return vertexList.get(i);
    }
    //返回v1 v2直接的权值
    public int getWeight(int v1,int v2){
      return deges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
      for(int[] arr :deges){
          System.out.println(Arrays.toString(arr));
      }
    }
}
