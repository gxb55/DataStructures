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
    private boolean[] isVisited;//是否被访问过

    public static void main(String[] args) {
        //点的个数 点 边
        int n = 5;
        Graph graph = new Graph(n);//点
        String[] vertexs = {"A", "B", "C", "D", "E"};
        for (String x : vertexs) {
            graph.insertVertex(x);
        }//点的名称
        //边 A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.showGraph();
        System.out.println("深度优先遍历：");
        graph.dfs();

    }

    /**
     * @param n n是定点的个数
     */
    public Graph(int n) {
        deges = new int[n][n];
        numOfEdges = 0;
        vertexList = new ArrayList<String>(n);
        isVisited = new boolean[n];
    }

    /**
     * 插入定点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * A B C D E
     * 无向图所以A-B B-A
     *
     * @param v1     定点的下标 比如 A
     * @param v2     定点的下标 比如B
     * @param weight 是否连接 0不连接 ；1连接
     */
    public void insertEdge(int v1, int v2, int weight) {
        deges[v1][v2] = weight;
        deges[v2][v1] = weight;
        numOfEdges++;//边的数量++
    }

    //图中常用的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumofEdges() {
        return numOfEdges;
    }

    //返回节点i 对应下标的数据比如 0-A 1-B
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1 v2直接的权值
    public int getWeight(int v1, int v2) {
        return deges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] arr : deges) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 得到第一个邻接 节点的下标w
     *
     * @param index return -1;
     * @return 如果存在返回下标，如果不存在返回-1
     */
    public int getFirsNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (deges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到第n个节点的下一个邻接节点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (deges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param isVisited 是否被访问过的数组
     * @param i 第几个节点
     */
    public void dfs(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i)+" --> ");
        isVisited[i]=true;
        int w= getFirsNeighbor(i);
        while (w!=-1){
            if(!isVisited[w]){//如果没有被访问过则进行递归
                dfs(isVisited,w);
            }
            w=getNextNeighbor(i,w);//如果被访问过了，则寻找w后面的未访问的节点
        }
    }
    public void dfs(){
        //深度优先，第一个节点找完开始找第二个
        for (int i=0;i<getNumofEdges();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

}
