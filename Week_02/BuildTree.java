package algorithm.MiddleQuestion;

import basic.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 
 * 根据一棵树的前序遍历与中序遍历构造二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 解题思路：
 * 先序遍历的数组可以认为每一个节点都可以是递归子树中的根节点
 * 通过先序遍历的数组获取的当前子树的根节点，找到中序遍历数组中该节点所在位置，以该位置为准，将中序遍历数组切分为两半，左半边为左子树，右半边为右子树
 * 为了防止递归回溯时，导致遍历的先序数组的下标异常，所以使用全局变量统一保存遍历指针
 * 算法的时间复杂度为O(nlogn)，空间复杂度为O(n)整棵树
 *
 *
 * 在建树的过程中，最耗费时间的就是在中序遍历数组中找到先序遍历中的根节点，可以使用HashMap现将中序遍历数组中各个值所在位置保存起来，将时间复杂度降为O(n)
 * @author kaithy.xu
 * @date 2020-07-06 21:45
 */
public class BuildTree {

    private int pIndex=0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder,0,inorder.length-1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int iLeft,int iRight) {
        if(iLeft > iRight) {
            return null;
        }
        int val = preorder[pIndex++];
        TreeNode root = new TreeNode(val);
        int index = iLeft;
        while (index <= iRight && inorder[index] != val) {
            index++;
        }
        root.left = build(preorder,inorder,iLeft,index-1);


        root.right = build(preorder, inorder,index+1,iRight);

        return root;
    }

}
