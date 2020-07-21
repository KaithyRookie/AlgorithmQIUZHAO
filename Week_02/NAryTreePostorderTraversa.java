import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 590. N叉树的后序遍历
 *
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 * @author kaithy.xu
 * @date 2020-07-21 21:24
 */
public class NAryTreePostorderTraversa {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();

        visitByPostOrder(root, list);

        return list;
    }

    private void visitByPostOrder(Node root, List<Integer> list) {
        if(root == null) {
            return;
        }

        for (Node node : root.children) {
            visitByPostOrder(node, list);
        }

        list.add(root.val);
    }

    private List<Integer> visitByStack(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();

        if(root == null) {
            return list;
        }

        stack.push(root);
        
        /*
                         root
                  A        B        C
               A1 A2 A3 B1 B2 B3 C1 C2 C3
        
       HEAD -> |  |  |  |  |  | root | -> TAIL
       
       =========================================       
       
       node = root
       list: HEAD -> root -> TAIL
       HEAD -> |  |  |  | A | B | C | -> TAIL
       ========================================
       
       node = C
       list: HEAD -> C -> root -> TAIL
       HEAD -> |  |  |  | A | B | C1 | C2 | C3 | -> TAIL
       ========================================
       ...
       
       ========================================
       node = B
       list: HEAD -> C1 -> C2 -> C3 -> C -> root -> TAIL
       HEAD -> |  |  |  | A | B1 | B2 | B3 | -> TAIL
       ========================================
       ...
       
       ========================================
       node = A
       list: HEAD -> B1 -> B2 -> B3 -> B -> C1 -> C2 -> C3 -> C -> root -> TAIL
       HEAD -> |  |  |  | A1 | A2 | A3 | -> TAIL
       
         */
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            list.addFirst(node.val);
            for (Node n : node.children) {
                if(n != null) {
                    stack.add(n);
                }
            }
        }
        return list;
    }
}
