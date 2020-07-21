/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 * 
 * @author kaithy.xu
 * @date 2020-07-19 16:33
 */
public class InorderTraversal {
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        visit(root, list);
        return list;
    }

    private void visit(TreeNode node, List<Integer> list) {
        if(node == null) {
            return;
        }
        visit(node.left, list);
        list.add(node.val);
        visit(node.right, list);
    }
}