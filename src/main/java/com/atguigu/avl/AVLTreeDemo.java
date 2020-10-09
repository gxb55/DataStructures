package com.atguigu.avl;

/**
 * @ClassName AVLTree @Author guoxiaobing @Date 2020/9/16 14:24 @Version 1.0 @Description avl树 二叉排序平衡树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4,3,6,5,7,8};
        // int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println("树的高度： " + avlTree.getRoot().height());
        System.out.println("树的左子树高度： " + avlTree.getRoot().left.height());
        System.out.println("树的右子树高度： " + avlTree.getRoot().right.height());
        System.out.println("根节点： " + avlTree.getRoot());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return this.left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return this.right.height();
    }

    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 左旋代码，左旋完还是一个avl树 分为以下六步 A新增的结点；B 就是root节点 1.Node A = new Node(b.val); //新增一个节点值是根节点的值 2. a.left = b.left
     * //将b节点的左子树赋给新增节点 3.a.right = b.right.left // 将b节点的右子树的左子树赋给新增节点的右子树 4.b.val = b.right.val // 将b节点右节点的值赋给b节点
     * 5.b.right=b.right.right //将b的右节点的值设置为b的右节点的右节点 6.b.left = a// 将新增的节点作为b的左子树
     */
    private void leftRotate() {
        // 1
        Node node = new Node(this.value);
        // 2
        node.left = this.left;
        // 3.
        node.right = this.right.left;
        // 4.
        this.value = this.right.value;
        // 5.
        this.right = this.right.right;
        // 6.
        this.left = node;
    }

    /** 左子树高度过高则进行右旋转 步骤和上面的左旋转类似可以看动图 */
    private void rightRotate() {
        Node node = new Node(this.value);
        node.right = this.right;
        node.left = this.left.right;

        this.value = this.left.value;
        this.left = this.left.left;
        this.right = node;
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

        // 新增完节点后进行旋转保证还是平衡树 avl
        if ((rightHeight() - leftHeight()) > 1) { // 右边大于左边，则进行左旋转
            if (right != null && right.leftHeight() > right.rightHeight()) {
                this.right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }

            return; // 左旋右旋只会有一个
        }

        if ((leftHeight() - rightHeight()) > 1) { // 右旋转的时候要判断左子树的 左右两个子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) { // 仔细思考
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
        return; // 左旋右旋只会有一个
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
