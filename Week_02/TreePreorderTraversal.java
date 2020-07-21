import basic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * @author kaithy.xu
 * @date 2020-07-21 21:07
 */
public class TreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        visitByPreorder(root, list);
        return list;
    }

    private void visitByPreorder(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        list.add(root.val);
        visitByPreorder(root.left, list);
        visitByPreorder(root.right, list);

    }

    private List<Integer> visitByStack(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            root = root.right;
        }

        return list;
    }
}
