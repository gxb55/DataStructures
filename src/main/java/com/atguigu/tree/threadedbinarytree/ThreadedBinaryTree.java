package com.atguigu.tree.threadedbinarytree;

/**
 * @program: 2
 * @description: 线索二叉树，以中序为例子
 * @author: guoxiaobing
 * @create: 2020-08-02 15:30
 */
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
       // node3.setLeft(node6);
        BinaryTree binaryTree = new BinaryTree(root);

        //binaryTree.threadedNodes(root);
        HeroNode left = node3.getLeft();
        System.out.println(left);
        HeroNode right = node3.getRight();
        System.out.println(right);
        System.out.println("中序线索化二叉树便利：");
      //  binaryTree.threadedList();//8 3 10 1 14 6


        binaryTree.proThreadedNodes(root);
       /* binaryTree.proThreadedNodes(root);
        HeroNode left = node5.getLeft();
        System.out.println(left);
        HeroNode right = node5.getRight();
        System.out.println(right);*/

    }
}
class BinaryTree{
    private HeroNode root;
    public BinaryTree(HeroNode root) {
        this.root = root;
    }
    //节点的前一个节点 pre总是保留当前节点的前一个节点
    private HeroNode pre = null;
    /**
     * 中序线索化二叉树
     * @param node
     */
    public void threadedNodes(HeroNode node) {
         if (node == null){
             return;
         }
         threadedNodes(node.getLeft());
         if(node.getLeft()==null){//左节点指向他的前驱节点，前驱节点有可能为空
             node.setLeft(pre);
             node.setLeftType(1);
         }
         if(pre !=null && pre.getRight() ==null){//右节点指向他的后继节点
             //前一个节点的右子树指向当前节点，其实就是右节点指向他的后继节点
            pre.setRight(node);
            pre.setRightType(1);
         }
         pre = node;
         threadedNodes(node.getRight());
    }

  /**
   * 先序线索二叉树的构建，全部都是基于递归便利来做的，
   *
   * @param node
   */
  public void proThreadedNodes(HeroNode node) {
    if (node == null) {
      return;
    }
    System.out.println(node);
    if (node.getLeft() == null) { // 左节点指向他的前驱节点  pre就是前驱节点
      node.setLeft(pre);
      node.setLeftType(1);
    }
    if (pre != null && pre.getRight() == null) { // 右节点指向他的后继节点，pre是当前节点的前驱节点，所以是pre设置right即可
      pre.setRight(node);
      pre.setRightType(1);
    }
    pre = node;
    if (node.getLeftType() != 1) { // 注意先序遍历的时候可能会产生回路所以要判断
      proThreadedNodes(node.getLeft()); // 左
    }
    if (node.getRightType() != 1) {
      proThreadedNodes(node.getRight()); // 右
    }
  }

    /**
     * 中序便利线索化二叉树
     * 1.线索化二叉树后要达到的结果是：
     *      左子树是前驱节点
     *      右字数是后继节点
     *
     */
    public void threadedList(){
        HeroNode node = root;
        while (node!=null){
            while (node.getLeftType()==0){//0 正常的左子节点
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType()==1){//1 线索化的右子节点
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

   //如果leftType == 0 表示的是左子树 如果leftType==1 表示的是指向的是他的前驱节点
    private int leftType;
    //rightType == 0 表示的是右子树 rightType==1 表示的是指向的是他的后继节点
    private int rightType;



    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
