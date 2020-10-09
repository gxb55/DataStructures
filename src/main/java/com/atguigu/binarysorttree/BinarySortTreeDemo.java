package com.atguigu.binarysorttree;

/**
 * @ClassName BinarySortTreeDemo @Author guoxiaobing @Date 2020/9/15 15:59 @Version 1.0 @Description 二叉搜索树，也称为bst
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);

        System.out.println("删除后是： ");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树不能便利哦");
        }
    }

    public void delNode(int index) {
        if (root == null) {
            return;
        }
        Node targetNode = search(index);
        if (targetNode == null) { // 在树里面没有找到要删除的节点直接返回
            return;
        }
        if (root.left == null && root.right == null) { // 树只有一个节点就是要删除的那个节点，将root置为null即可
            root = null;
            return;
        }
        Node parent = searchParent(index);
        // 1.删除的是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left != null && parent.left.value == index) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == index) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right == null) { // 删除的节点有一个子树
            if (parent != null) {
                if (parent.left.value == index) {
                    parent.left = targetNode.left;
                } else {
                    parent.right = targetNode.left;
                }
            } else {
                root = targetNode.left;
            }

        } else if (targetNode.right != null && targetNode.left == null) { // 删除的节点有一个子树
            if (parent != null) {
                if (parent.left.value == index) {
                    parent.left = targetNode.right;
                } else {
                    parent.right = targetNode.right;
                }
            } else {
                root = targetNode.right;
            }
        } else { // 要删除的节点有两个子树，从右子树中找到最小的树，然后删除那个树，把树的值赋给target
            // int minVal = delRightTreeMin(targetNode.right);
            int maxVal = delLeftTreeMax(targetNode.left);
            targetNode.value = maxVal;
        }
    }

    private int delLeftTreeMax(Node left) {
        Node temp = left;
        while (temp.right != null) {
            temp = temp.right;
        }
        delNode(temp.value);
        return temp.value;
    }

    private int delRightTreeMin(Node right) {
        Node temp = right;
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }

    public Node search(int index) {
        if (root == null) {
            return null;
        } else {
            return root.search(index);
        }
    }

    public Node searchParent(int index) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(index);
        }
    }

    /*  */
    /** 自己写的删除方法 1.删除的是叶子节点 2.删除的节点只有一个叶子节点 3.删除的节点有左右两个节点 */
    /*
    public void deleteNode(int index){
      System.out.println("删除的节点");
      Node targetNode = getTargetNode(index,root);
      System.out.println(targetNode);
      Node parentNode = getParentNode(index, root, root);
      System.out.println(parentNode);
    
    }
    public Node getTargetNode(int index,Node node){
      if(node==null){
          return null;
      }
      if(index>node.value){
          node = node.right;
          return getTargetNode(index,node);
      }else if (index<node.value){
          node = node.left;
          return getTargetNode(index,node);
      }else{
          return node;
      }
    }
    public Node getParentNode(int index,Node node,Node parentNode){
      if(node==null){
          return null;
      }
      if(index>node.value){
          parentNode = node;
          node = node.right;
          return getParentNode(index,node,parentNode);
      }else if (index<node.value){
          parentNode = node;
          node = node.left;
          return getParentNode(index,node,parentNode);
      }else{
          return parentNode;
      }
    }*/
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找要删除的节点
     *
     * @param index
     * @return
     */
    public Node search(int index) {
        if (this.value == index) {
            return this;
        }
        if (index >= this.value) {
            if (this.right != null) {
                return this.right.search(index);
            } else {
                return null;
            }
        } else if (index < this.value) {
            if (this.left != null) {
                return this.left.search(index);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        // 如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
        if (value < this.value && this.left != null) {
            return this.left.searchParent(value); // 向左子树递归查找
        } else if (value >= this.value && this.right != null) {
            return this.right.searchParent(value); // 向右子树递归查找
        } else {
            return null; // 没有找到父结点
        }
    }

    /**
     * 二叉搜索树的定义是 左《根《右子树
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value >= this.value) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        } else {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }
    }

    /** 中序便利 左 根 右 */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }
}
