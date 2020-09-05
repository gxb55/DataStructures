package com.atguigu.huffmancode;

import java.util.*;

/**
 * @ClassName HuffmanCode
 * @Author guoxiaobing
 * @Date 2020/8/15 14:57
 * @Version 1.0
 * @Description 霍夫曼编码，数据压缩
 */
public class HuffmanCode {
  public static void main(String[] args) {
    String content = "i like like like java do you like a java";
      byte[] bytes = content.getBytes();//40
      System.out.println(bytes.length);
      List<Node> nodes = getNodes(bytes);
      System.out.println(nodes);
      createHuffmanTree(nodes).preOrder();
  }
  private static Node createHuffmanTree(List<Node> nodes){
      while (nodes.size()>1){
          Collections.sort(nodes);
          Node leftNode = nodes.get(0);
          Node rightNode = nodes.get(1);
          Node parentNode = new Node(null,leftNode.weigh+rightNode.weigh);
          parentNode.left = leftNode;
          parentNode.right = rightNode;
          nodes.remove(leftNode);
          nodes.remove(rightNode);
          nodes.add(parentNode);
      }
      return nodes.get(0);
  }
  private static List<Node> getNodes(byte[] bytes){
      List<Node> list = new ArrayList();
      Map<Byte,Integer> counts = new HashMap<>();
      for(byte b:bytes){
          if(counts.containsKey(b)){
              counts.put(b,counts.get(b)+1);
          }else{
              counts.put(b,1);
          }
      }
      counts.entrySet().stream().forEach(x->{
          Node node = new Node(x.getKey(),x.getValue());
          list.add(node);
      });
    return list;
  }
}
class Node implements Comparable<Node>{
     Byte data;//存放的是数据
     int weigh;//权值，存放的是字符出现的次数
     Node left;
     Node right;

    public Node(Byte data, int weigh) {
        this.data = data;
        this.weigh = weigh;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大 为正交换位置，为负不交换
        return this.weigh - o.weigh;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weigh=" + weigh +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }
}
