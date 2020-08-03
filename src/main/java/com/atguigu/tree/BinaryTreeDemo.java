package com.atguigu.tree;

/**
 * @ClassName BinaryTreeDemo @Author guoxiaobing @Date 2020/7/22 19:35 @Version 1.0 @Description 二叉树
 */
public class BinaryTreeDemo {

  public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();
    HeroNode root = new HeroNode(1,"宋江");
    HeroNode node2 = new HeroNode(2,"吴用");
    HeroNode node3 = new HeroNode(3,"卢俊义");
    HeroNode node4 = new HeroNode(4,"林冲");
    HeroNode node5 = new HeroNode(5,"关胜");
    root.setLeft(node2);
    root.setRight(node3);
    node3.setRight(node4);
    node3.setLeft(node5);
    tree.setRoot(root);
    tree.preOrder();
   /* tree.infixOrder();
    tree.postOrder();*/


    /*System.out.println("查找 5的node");
    System.out.println(tree.preOrderSearch(5));
    System.out.println(tree.infixOrderSearch(5));
    System.out.println(tree.postOrderSearch(5));*/
    System.out.println("删除 5的node");
    tree.delNode(3);
    tree.preOrder();
  }
}

class BinaryTree{
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        System.out.println("先序遍历");
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("树是空的。。");
        }
    }

    public void infixOrder() {
        System.out.println("中序遍历");
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("树是空的。。");
        }
    }

    public void postOrder() {
        System.out.println("后序遍历");
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("树是空的。。");
        }
    }

    public HeroNode preOrderSearch(int no){
        if (this.root != null) {
          return   this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no){
        if (this.root != null) {
            return   this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no){
        if (this.root != null) {
            return   this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode(int no){
        if(this.root == null){
            System.out.println("树是空的！！");
        }else{
            if(this.root.getId() == no){
                this.root=null;//root就是要删除的直接置空即可
            }else{//递归删除
                this.root.delNode(no);
            }
        }
    }
}

class HeroNode {
  private int id;
  private String name;
  private HeroNode left;
  private HeroNode right;

  /*
  先序便利 根 左 右
   */
  public void preOrder1(HeroNode node) {
    if (node == null) {
      return;
    }
    if (node != null) {
      System.out.println(node);
    } else if (node.getLeft() != null) {
      preOrder1(node.getLeft());
    } else if (node.getRight() != null) {
      preOrder1(node.getRight());
    }
  }

    /**
     * 先序
     */
  public void preOrder() {
    System.out.println(this);
    if (this.left != null) {
      this.left.preOrder();
    }
    if (this.right != null) {
      this.right.preOrder();
    }
  }

    /**
     * 中序
     */
  public void infixOrder() {
    if (this.left != null) {
      this.left.infixOrder();
    }
    System.out.println(this);
    if (this.right != null) {
      this.right.infixOrder();
    }
  }

    /**
     * 后序
     */
  public void postOrder() {
    if (this.left != null) {
      this.left.postOrder();
    }
    if (this.right != null) {
      this.right.postOrder();
    }
    System.out.println(this);
  }

  public HeroNode(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
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

  @Override
  public String toString() {
    return "HeroNode{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

    /**
     * 查找先序
     * @param no
     * @return
     */
  public HeroNode preOrderSearch(int no){
      if(no == this.getId()){
          return this;
      }
      HeroNode temp = null;
      if(this.getLeft()!=null){
        temp=  this.getLeft().preOrderSearch(no);
      }
      if(temp!=null){
          return temp;
      }
      if (this.getRight()!=null){
          temp=    this.getRight().preOrderSearch(no);
      }
      return temp;
  }

    /**
     * 中序
     * @param no
     * @return
     */
  public HeroNode infixOrderSearch(int no){
      HeroNode temp = null;
      if(this.getLeft()!=null){
          temp =this.getLeft().infixOrderSearch(no);
      }
      if(temp!=null){
          return temp;
      }
      if(this.getRight()!=null){
        temp =  this.getRight().infixOrderSearch(no);
      }
      if(temp!=null){
          return temp;
      }
      if(this.getId() == no){
          return this;
      }
      return temp;
  }

  public HeroNode postOrderSearch(int no){
      HeroNode temp = null;
      if(this.getLeft()!=null){
          temp =   this.getLeft().postOrderSearch(no);
      }
      if(temp!=null){
          return temp;
      }
      if(this.getRight()!=null){
          temp = this.getRight().postOrderSearch(no);
      }
      if(temp!=null){
          return temp;
      }
      if(no == this.getId()){
          return this;
      }
      return temp;

  }

    public void delNode(int no) {
      if(this.getLeft()!=null && this.getLeft().getId() == no){
          this.setLeft(null);
          return;
      }
      if(this.getRight()!=null && this.getRight().getId() == no){
          this.setRight(null);
          return;
      }
      if(this.getLeft()!=null){
          this.getLeft().delNode(no);
      }
      if(this.getRight()!=null){
          this.getRight().delNode(no);
      }
    }
}
