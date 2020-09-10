package com.atguigu.huffmantree;

import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName HuffmanTree
 * @Author guoxiaobing
 * @Date 2020/8/5 19:09
 * @Version 1.0
 * @Description 霍夫曼树的构建
 */
public class HuffmanTree {
  public static void main(String[] args) {
      int[] arr = {13,7,8,3,29,6,1};
      Node huffmanTree = createHuffmanTree(arr);
      preLoop(huffmanTree);
      /*List<Node> nodes = new ArrayList<>();
      for(int value:arr){
          nodes.add(new Node(value));
      }
      Node node = buildTree(nodes);
      preLoop(node);*/
  }

    /**
     * 1.把数组变成node存入list
     * 2.取出最小的两个，组成一颗树，根的值为最小两个数相加
     * 3.将最小的两个数移除，将组成的新树加入到list中
     * 4.将list排序，重复上述行为指导list的size为1，即只有一颗树在里面，那这颗树就是我们需要的那个
     * @param arr
     * @return
     */
  public static Node createHuffmanTree(int[] arr){
      List<Node> nodes = new ArrayList<>();
      for(int value:arr){
          nodes.add(new Node(value));
      }
      while (nodes.size()>1){
          //排序，升序从小到大
          Collections.sort(nodes);
          Node leftNode = nodes.get(0);
          Node rightNode = nodes.get(1);
          Node root = new Node(leftNode.getValue()+rightNode.getValue());
          root.setLeft(leftNode);
          root.setRight(rightNode);
          nodes.remove(leftNode);
          nodes.remove(rightNode);
          nodes.add(root);
         // System.out.println(nodes.toString());
      }
      return nodes.get(0);
  }

  public static void preLoop(Node node){
      if(node == null){
          return;
      }
      System.out.println(node);
      preLoop(node.getLeft());
      preLoop(node.getRight());
  }

  public static Node buildTree(List<Node> nodes){
        if(nodes.size()>1){

            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新的树
            Node root = new Node(leftNode.getValue()+rightNode.getValue());
            root.setRight(rightNode);
            root.setLeft(leftNode);
            //删除用过的，将新产生的数放入到list中
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(root);
            buildTree(nodes);
        }
      return nodes.get(0);
  }
}

class Node implements Comparable<Node> {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        /*
        >0 交换位置  从小到大
         */
        return this.value - o.getValue();
    }
}
