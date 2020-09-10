package com.atguigu.tree;

/** @ClassName RBTree @Author guoxiaobing @Date 2020/9/5 14:42 @Version 1.0 @Description 红黑树 */
/*红黑树的每个节点上都有存储位表示节点的颜色，颜色是红(Red)或黑(Black)。
红黑树的特性:
(1) 每个节点或者是黑色，或者是红色。
(2) 根节点是黑色。
(3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
(4) 如果一个节点是红色的，则它的子节点必须是黑色的。
(5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。*/
public class RBTree {
  private RBTNode root;
  private static final boolean RED = false;
  private static final boolean BLACK = true;

  public static void main(String[] args) {
    RBTree rbtNode = new RBTree();
    rbtNode.insertNode(5);
    rbtNode.insertNode(7);
    rbtNode.insertNode(8);
    rbtNode.insertNode(10);
    rbtNode.insertNode(2);
    rbtNode.insertNode(1);
    rbtNode.insertNode(6);

    rbtNode.proLoop(rbtNode.root);
  }
  // 红黑树的类
  public class RBTNode {
    boolean color = BLACK;
    Integer key;
    RBTNode left;
    RBTNode right;
    RBTNode parent;

    public RBTNode(Integer key, RBTNode parent, RBTNode left, RBTNode right) {
      this.key = key;
      this.left = left;
      this.right = right;
      this.parent = parent;
    }
  }

  public void proLoop(RBTNode root) {
    System.out.println(root.key);
    if (root.left != null) {
      proLoop(root.left);
    }
    if (root.right != null) {
      proLoop(root.right);
    }
  }

  public void insertNode(int x) {
    RBTNode rbtNode = new RBTNode(x, null, null, null);
    if (root == null) {
      root = rbtNode;
    } else {
      RBTNode temp = root;
      RBTNode parent = null;
      while (temp != null) {
        parent = temp;
        if (x > temp.key) {
          temp = temp.right;
        } else {
          temp = temp.left;
        }
      }
      if (x > parent.key) {
        parent.right = rbtNode;
      } else {
        parent.left = rbtNode;
      }
      rbtNode.parent = parent;

      // 新增一个黑色节点，肯定违反了红黑树的黑高一致规则，重新调整树结构 每次新增除了跟节点都要重新调整位置
      fixAfterInsertion(rbtNode);
    }
  }

  private void fixAfterInsertion(RBTNode x) {
    // 调整考虑如下几种情况：
    // 1. x肯定不是根节点了，根节点的话就不需要调整，也就走不到这一步了
    // 2. x的父节点是根节点，那么只需要将x设置为红色就行了，不会违反红黑树的规则
    // 3. 所以重点调整就不需要考虑1和2了
    // 4. x的父节点和叔叔节点都是红色，这个时候，只需要将x叔、父节点设置为黑色，祖父节点设置为红色
    // 然后，将祖父节点设置为x递归处理
    // 5. x的叔叔节点是黑色，这时候就需要进行旋转处理了

    // 新节点设置为红色
      x.color = RED;
      
  }

  /**
   * 左旋
   *
   * @param node
   */
  public void leftRotate(RBTNode node) {
    RBTNode x = node;
    RBTNode y = node.right;

    x.right = y.left;
    x.right.parent = x;

    y.left = x;
    x.parent = y;
    y.parent = null;
  }
}
