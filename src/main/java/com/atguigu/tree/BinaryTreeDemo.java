package com.atguigu.tree;

/**
 * @ClassName BinaryTreeDemo
 * @Author guoxiaobing
 * @Date 2020/7/22 19:35
 * @Version 1.0
 * @Description 二叉树
 */
public class BinaryTreeDemo {
}

class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    /*
    先序便利 根 左 右
     */
    public void preOrder1(HeroNode node){
        if(node==null){
            return;
        }
        if(node!=null){
            System.out.println(node);
        }else if(node.getLeft()!=null){
            preOrder1(node.getLeft());
        }else if(node.getRight()!=null){
            preOrder1(node.getRight());
        }
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    public void postOrder(){
        if (this.left!=null){
            this.left.postOrder();
        }
        if(this.right!=null){
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
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
