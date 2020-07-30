import basic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author kaithy.xu
 * @date 2020-07-29 21:46
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        TreeNode poison = new TreeNode(0);
        queue.add(poison);
        while (!queue.isEmpty()) {
            List<Integer> record = new ArrayList<>();
            while (queue.peek() != poison) {
                TreeNode tmp = queue.poll();
                record.add(tmp.val);
                if(null != tmp.left)
                    queue.add(tmp.left);
                if(null != tmp.right) {
                    queue.add(tmp.right);
                }
            }
            result.add(record);
            queue.poll();
            if(!queue.isEmpty())
                queue.add(poison);
        }

        return result;
    }
    
}
