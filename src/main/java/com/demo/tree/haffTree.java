package com.demo.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: 2
 * @description: 霍夫曼树 最优二叉树
 * @author: guoxiaobing
 * @create: 2020-08-16 22:56
 */
public class haffTree {
    public static void main(String[] args) {
        int[] arr = {993, 990, 9, 34, 23, 12, 45, 67, 78, 89, 56, 45, 3, 5, 37, 17, 29};
        Node root = builderHaff(arr);
        preLoop(root);
    }

    public static Node builderHaff(int[] arr) {
        List<Node> list = new ArrayList<>();
        for (int a : arr) {
            list.add(new Node('a', a));
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node node = new Node('b', leftNode.sum + rightNode.sum);
            node.setLeftNode(leftNode);
            node.setRightNode(rightNode);

            list.add(node);
            list.remove(leftNode);
            list.remove(rightNode);
        }
        return list.get(0);
    }

    public static void preLoop(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root);
        preLoop(root.getLeftNode());
        preLoop(root.getRightNode());
    }
}

class Node implements Comparable<Node> {
    char val;
    int sum;
    Node leftNode;
    Node rightNode;

    public Node(char val, int sum) {
        this.val = val;
        this.sum = sum;
    }

    /**
     * 为正交换位置，现在是升序排列
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        return this.sum - o.sum;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", sum=" + sum +
                '}';
    }
}
