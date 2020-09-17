package com.atguigu.huffmancode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName HuffmanCode
 * @Author guoxiaobing
 * @Date 2020/8/15 14:57
 * @Version 1.0
 * @Description 霍夫曼编码，数据压缩
 */

/**
 * 1.将文件改为字节数组
 * 2.将字节数组中字符出现的次数组成list<node>
 * 3.将list中的node改为树,树中的叶子节点才会存放data即有值，
 * 4.构造一个map key为值 value为从树到这个值经过的路径，左边为0，右边为1
 * 5.构建byte数组，每个字符对应的霍夫曼编码放入到byte数组中去，构建8位一个
 */
public class HuffmanCode {
  public static void main(String[] args) {
    String content = "i like like like java do you like a java";
      byte[] bytes = content.getBytes();//40
      byte[] result = huffmanZip(bytes);
      System.out.println("压缩后的长度是："+result.length);
      System.out.println("压缩后的长度是："+ Arrays.toString(result));



      /*System.out.println(bytes.length);
      List<Node> nodes = getNodes(bytes);
      System.out.println(nodes);
      Node huffmanTree = createHuffmanTree(nodes);
      huffmanTree.preOrder();

      Map<Byte, String> codes = getCodes(huffmanTree);
      System.out.println(codes.toString());
      byte[] zip = zip(bytes, codes);
      System.out.println("压缩后的长度是："+zip.length);*/
  }

  private static byte[] huffmanZip(byte[] contentByte){
      //构建node的list，即字母出现的次数和字母本身
      List<Node> nodes = getNodes(contentByte);
      //构建一颗霍夫曼树，
      Node huffmanTree = createHuffmanTree(nodes);
        //根据霍夫曼树获取霍夫曼编码，
      Map<Byte, String> codes = getCodes(huffmanTree);
      System.out.println(codes.toString());

      //根据霍夫曼编码获取霍夫曼数组，即没个字母会有一个对应的霍夫曼编码，使用霍夫曼编码来标识内容
      byte[] zip = zip(contentByte, codes);
      return zip;
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
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();
    //为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if(root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }
    /**
     * 功能 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes中
     * @param node 赫夫曼树的根节点
     * @param code 左边为 0 右边为1
     * @param stringBuilder 拼接的值
     */
    //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
      if(node==null){
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node.data == null){//不是叶子节点
            getCodes(node.left,"0",stringBuilder2);
            getCodes(node.right,"1",stringBuilder2);
        }else{//叶子节点
            huffmanCodes.put(node.data,stringBuilder2.toString());
        }
    }

    /**
     *
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder huffmanBuilder = new StringBuilder();
        for(byte x:bytes){
            huffmanBuilder.append(huffmanCodes.get(x));
        }
        byte[] hufumanBytes = null;
        if(huffmanBuilder.length() % 8 == 0){
            hufumanBytes = new byte[huffmanBuilder.length()/8];
        }else{
            hufumanBytes = new byte[huffmanBuilder.length()/8 +1 ];
        }
        int index = 0;
        for(int i=0;i<huffmanBuilder.length();i+=8){
            String strByte;
            if(i+8 > huffmanBuilder.length()) {//不够8位
                strByte = huffmanBuilder.substring(i);
            }else{
                strByte = huffmanBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            hufumanBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return hufumanBytes;
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
