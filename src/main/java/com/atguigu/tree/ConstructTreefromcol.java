package com.atguigu.tree;

import java.util.Arrays;

/**
 * @ClassName ConstructTreefromcol
 * @Author guoxiaobing
 * @Date 2020/8/24 11:30
 * @Version 1.0
 * @Description 根据先序便利和中序便利还原树
 */
class BTree{
    BTree left;
    BTree right;
    int val;
}

/**
 * 先序  根  左  右
 * 中序  左  根  右
 * 后序  左  右  根
 */
public class ConstructTreefromcol {
    public static void main(String[] args) throws Exception {
        int[] preorder={1,2,4,7,3,5,6,8};
        int[] inorder={4,7,2,1,5,3,8,6};
        BTree root=ConstructTree(preorder,inorder);;
    }

    /**
     *
     * @param preorder 先序便利的顺序  根  左  右
     * @param inorder 中序便利的顺序   左  根  右
     * @return
     * @throws Exception
     */
    private static BTree ConstructTree(int[] preorder, int[] inorder) throws Exception {
        if(preorder==null || inorder==null)
            return null;
        if(preorder.length!=inorder.length)
            throw new Exception("输入的中序和前序遍历不对");

        BTree root=new BTree();

        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==preorder[0]){
                root.val=inorder[i];
                System.out.println(root.val);
                root.left=ConstructTree(Arrays.copyOfRange(preorder, 1, i+1),
                        Arrays.copyOfRange(inorder, 0, i));
                root.right=ConstructTree(Arrays.copyOfRange(preorder, i+1, preorder.length),
                        Arrays.copyOfRange(inorder, i+1, inorder.length));

            }
        }
        return root;
    }

}

