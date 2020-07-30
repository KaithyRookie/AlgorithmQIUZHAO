import basic.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 515. 在每个树行中找最大值
 * 
 * 您需要在二叉树的每一行中找到最大的值。
 *
 * @author kaithy.xu
 * @date 2020-07-30 18:03
 */
public class FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if(root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        TreeNode poison = new TreeNode(0);
        queue.add(poison);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;

            while (queue.peek() != poison) {
                TreeNode tmp = queue.poll();
                max = Math.max(max, tmp.val);
                if(null != tmp.left)
                    queue.add(tmp.left);
                if(null != tmp.right) {
                    queue.add(tmp.right);
                }
            }
            result.add(max);
            queue.poll();
            if(!queue.isEmpty())
                queue.add(poison);
        }

        return result;
    }
}
