package com.atguigu.tree;

import java.util.*;
/**
 * @ClassName RedBlackTree @Author guoxiaobing @Date 2020/9/5 15:12 @Version 1.0 @Description TODO
 */
public class RedBlackTree<T extends Comparable> {

  private static final boolean RED = false;
  private static final boolean BLACK = true;
  private Node root;

  public RedBlackTree() {
    root = null;
  }

  public RedBlackTree(T t) {
    this.root = new Node(t, null, null, null);
  }

  /** 增加一个数据 */
  public void add(T e) {
    // 如果根节点为空设置为根节点，默认是黑色
    if (root == null) {
      this.root = new RedBlackTree.Node(e);
    } else {
      // 根节点不为空的情况
      RedBlackTree.Node current = root;
      RedBlackTree.Node parent = null;
      int cmp = 0;
      do {
        // 二叉搜索树的查找方法，找到合适的节点
        parent = current;
        cmp = e.compareTo(current.data);
        if (cmp > 0) {
          current = current.right;
        } else {
          current = current.left;
        }
      } while (current != null);
      RedBlackTree.Node newNode = new RedBlackTree.Node(e, parent, null, null);
      if (cmp > 0) {
        parent.right = newNode;
      } else {
        parent.left = newNode;
      }
      // 新增一个黑色节点，肯定违反了红黑树的黑高一致规则，重新调整树结构
      fixAfterInsertion(newNode);
    }
  }

  /**
   * 插入后修复红黑树
   *
   * @param x 插入的节点
   */
  private void fixAfterInsertion(Node x) {
    // 调整考虑如下几种情况：
    // 1. x肯定不是根节点了，根节点的话就不需要调整，也就走不到这一步了
    // 2. x的父节点是根节点，那么只需要将x设置为红色就行了，不会违反红黑树的规则
    // 3. 所以重点调整就不需要考虑1和2了
    // 4. x的父节点和叔叔节点都是红色，这个时候，只需要将x叔、父节点设置为黑色，祖父节点设置为红色
    // 然后，将祖父节点设置为x递归处理
    // 5. x的叔叔节点是黑色，这时候就需要进行旋转处理了

    // 新节点设置为红色
    x.color = RED;
    while (x != null && x != root && x.parent.color == RED) {
      // x的父节点是x的祖父节点的左子节点
      if (parentOf(x).isLeftChild()) {
        // x 的叔叔节点是右子节点
        Node uncle = rightOf(grandfatherOf(x));
        if (colorOf(uncle) == RED) {
          // 叔、父都是红色，按上面注释的情形4处理
          setColor(parentOf(x), BLACK);
          setColor(uncle, BLACK);
          setColor(grandfatherOf(x), RED);
          x = grandfatherOf(x);
        } else {
          // x是父节点的右子节点
          if (x.isRightChild()) {
            x = parentOf(x);
            // x和x的父节点不在一样“直线路径”上，对x的父节点左旋一下弄成看起来是一条直线的路径
            rotateLeft(x);
          }
          setColor(parentOf(x), BLACK);
          setColor(grandfatherOf(x), RED);
          rotateRight(grandfatherOf(x));
        }
      } else { // x的父节点是右结点
        // x 的叔叔节点
        Node uncle = leftOf(grandfatherOf(x));
        if (colorOf(uncle) == RED) {
          // 叔、父都是红色，按上面注释的情形4处理
          setColor(parentOf(x), BLACK);
          setColor(uncle, BLACK);
          setColor(grandfatherOf(x), RED);
          x = grandfatherOf(x);
        } else {
          // x是左节点
          if (x == leftOf(parentOf(x))) {
            x = parentOf(x);
            // x和x的父节点不在一样“直线路径”上，对x的父节点右旋一下弄成看起来是一条直线的路径
            rotateRight(x);
          }
          setColor(parentOf(x), BLACK);
          setColor(grandfatherOf(x), RED);
          rotateLeft(grandfatherOf(x));
        }
      }
    }
    // 根节点永远为黑
    root.color = BLACK;
  }

  private void rotateLeft(Node p) {
    if (p != null) {
      Node r = p.right;
      Node q = r.left;
      p.right = q;
      if (q != null) {
        q.parent = p;
      }
      r.parent = p.parent;
      if (p.parent == null) {
        root = r;
      } else if (p == p.parent.left) {
        p.parent.left = r;
      } else {
        p.parent.right = r;
      }
      r.left = p;
      p.parent = r;
    }
  }

  private void rotateRight(Node p) {
    if (p != null) {
      Node l = p.left;
      Node q = l.right;
      p.left = q;
      if (q != null) {
        q.parent = p;
      }
      l.parent = p.parent;
      if (p.parent == null) {
        root = l;
      } else if (p == p.parent.left) {
        p.parent.left = l;
      } else {
        p.parent.right = l;
      }
      l.right = p;
      p.parent = l;
    }
  }

  private Node parentOf(Node p) {
    return p == null ? null : p.parent;
  }

  private Node grandfatherOf(Node p) {
    return parentOf(parentOf(p));
  }

  private Node leftOf(Node p) {
    return p == null ? null : p.left;
  }

  private Node rightOf(Node p) {
    return p == null ? null : p.right;
  }

  private boolean colorOf(Node p) {
    return p != null ? p.color : BLACK;
  }

  private void setColor(Node p, boolean c) {
    if (p != null) {
      p.color = c;
    }
  }

  public Node getNode(T e) {
    Node p = root;
    while (p != null) {
      int cmp = e.compareTo(p.data);
      if (cmp < 0) {
        p = p.left;
      } else if (cmp > 0) {
        p = p.right;
      } else {
        return p;
      }
    }
    return null;
  }

  /** 移除一个节点 */
  public void remove(T e) {
    Node target = getNode(e);
    if (target == null) {
      return;
    }
    // 如果待移除节点的两个子节点都存在，那么找到它的前趋节点代替它
    if (target.left != null && target.right != null) {
      Node node = target.left;
      while (node.right != null) {
        node = node.right;
      }
      // 将前趋节点的数据覆盖待删除节点的数据
      target.data = node.data;
      // 针对代替节点进行操作
      target = node;
    }
    Node relacement = target.left != null ? target.left : target.right;
    // 如果target还有一个子节点，那这里需要调整下，但是如果target是从上面找到的原本待删除节点的前趋节点，
    // 那么target至多有一个左子节点
    if (relacement != null) {
      relacement.parent = target.parent;
      if (target.isRoot()) {
        root = relacement;
      } else if (target.isLeftChild()) {
        target.parent.left = relacement;
      } else {
        target.parent.right = relacement;
      }
      // 删除target
      target.left = target.right = target.parent = null;
      if (colorOf(target) == BLACK) {
        // 删除了一个黑色节点，需要修复红黑树来满足黑高一致的规则
        fixAfterDeletion(relacement);
      }
    } else if (target.isRoot()) {
      root = null;
    } else {
      // target没有非空子节点了
      // 删除了一个黑色节点，需要修复红黑树来满足黑高一致的规则,红色就不用了
      if (colorOf(target) == BLACK) {
        fixAfterDeletion(target);
      }
      // 删除target
      if (target.isLeftChild()) {
        target.parent.left = null;
      } else {
        target.parent.right = null;
      }
      target.parent = null;
    }
  }

  public void fixAfterDeletion(Node x) {
    // 直到x是根节点或者x 的颜色是红色为止，否则一直处理
    while (x != root && colorOf(x) == BLACK) {
      if (x.isLeftChild()) {
        // s是x的兄弟节点
        Node sib = rightOf(parentOf(x));
        if (colorOf(sib) == RED) {
          // 因为x 路径上将要少一个黑色，所以这时候就需要从它的父母兄弟那里借一个过来帮个忙了
          setColor(sib, BLACK);
          // sib是红色，则它的父节点原本一定是黑色，否则就违反了红黑树不能两个连续红色节点的规则了
          setColor(parentOf(sib), RED);
          rotateLeft(parentOf(x));
          sib = rightOf(parentOf(x));
        }
        // sib的两个子节点都是黑色
        if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
          // 设置sib 是红色是因为，x的子树下将少一个黑色节点，那就让它的兄弟节点的子树下也先少一个黑色节点
          setColor(sib, RED);
          // 让x指向它的父节点，如果x的父节点是红色，那么结束循环，然后将其设置为黑色，丢失的那个黑色节点也就补回来了
          // 如果不是红色，那就继续往下调整
          x = parentOf(x);
        } else {
          // sib的子节点中只有一个是黑色
          // 那就把这个红色子节点右旋放到右边子节点上，这是因为，x这边还少一个黑色节点呢，需要借一个黑色
          // 最终sib 这边少一个黑色，将这个红色设置为黑色补上
          if (colorOf(rightOf(sib)) == BLACK) {
            setColor(leftOf(sib), BLACK);
            setColor(sib, RED);
            rotateRight(sib);
            sib = rightOf(parentOf(x));
          }
          setColor(sib, colorOf(parentOf(x)));
          setColor(parentOf(x), BLACK);
          setColor(rightOf(x), BLACK);
          rotateLeft(parentOf(x));
          x = root;
        }
      } else {
        // 如果x 是右子节点，就是上面的情况反过来
        Node sib = leftOf(parentOf(x));
        if (colorOf(sib) == RED) {
          setColor(sib, BLACK);
          setColor(parentOf(x), RED);
          rotateRight(parentOf(x));
          sib = leftOf(parentOf(x));
        }
        // sib的两个子节点都是黑色
        if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
          // 设置sib 是红色是因为，x的子树下将少一个黑色节点，那就让它的兄弟节点的子树下也先少一个黑色节点
          setColor(sib, RED);
          // 让x指向它的父节点，如果x的父节点是红色，那么结束循环，然后将其设置为黑色，丢失的那个黑色节点也就补回来了
          // 如果不是红色，那就继续往下调整
          x = parentOf(x);
        } else {
          if (colorOf(leftOf(sib)) == BLACK) {
            setColor(rightOf(sib), BLACK);
            setColor(sib, RED);
            rotateLeft(sib);
            sib = leftOf(parentOf(x));
          }
          setColor(sib, colorOf(parentOf(x)));
          setColor(parentOf(x), BLACK);
          setColor(leftOf(x), BLACK);
          rotateRight(parentOf(x));
          x = root;
        }
      }
    }
    setColor(x, BLACK);
  }

  // 中序遍历
  public List<Node> inIterator(Node node) {
    List<Node> nodes = new ArrayList<Node>();
    if (node.left != null) {
      nodes.addAll(inIterator(node.left));
    }
    nodes.add(node);
    if (node.right != null) {
      nodes.addAll(inIterator(node.right));
    }
    return nodes;
  }

  public List<Node> inIterator() {
    return root != null ? inIterator(root) : new ArrayList<Node>(0);
  }

  private static class Node {
    Object data;
    Node parent;
    Node left;
    Node right;
    boolean color = BLACK;

    public Node(Object data) {
      this.data = data;
    }

    public Node(Object data, Node parent, Node left, Node right) {
      this.data = data;
      this.parent = parent;
      this.left = left;
      this.right = right;
    }

    public boolean isLeftChild() {
      return this == this.parent.left;
    }

    public boolean isRightChild() {
      return this == this.parent.right;
    }

    public boolean isRoot() {
      return this.parent == null;
    }

    @Override
    public String toString() {
      return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj.getClass() == Node.class) {
        Node target = (Node) obj;
        return data.equals(target.data)
            && left == target.left
            && right == target.right
            && parent == target.parent
            && color == target.color;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
    // 遍历的结果就该是：1,6,8,11,13,15,17,22,25,27
    Integer[] integers = {6, 15, 25, 8, 17, 22, 11, 1, 13, 27};
    for (Integer i : integers) {
      tree.add(i);
      System.out.println(tree.inIterator());
    }
    for (Integer i : integers) {
      tree.remove(i);
      System.out.println(tree.inIterator());
    }
  }
}
