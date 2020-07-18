import java.util.LinkedList;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 解题思路：
 * 使用辅助栈，用于保存每次push 时，将当前元素与最小栈栈顶元素中的最小值push进辅助栈中
 * 因为每次都往辅助栈中push进迄今为止的最小值，所以辅助栈的栈顶元素是与保存数据的最小栈中的最小值是处于同步状态
 * 时间复杂度: O(1)
 * 空间复杂度: O(n) 需要额外的一个辅助栈保存最小值
 *
 * 解法2：
 * 由于辅助栈中保存的是迄今为止的最小值，可以用一个带有迄今为止最小值的链表来进行模拟最小栈，
 * 每次插入节点前获取头部节点的最小值，并取当前插入数值与头部节点保存的最小值中的最小的数作为当前头部节点的最小值
 * 时间复杂度: O(1)
 * 空间复杂度: O(n) 每个节点依旧需要一个变量来保存最小值，但是相较于两个栈，整体的内存占用会减少
 *
 * 解法3：
 * 只使用一个栈与一个变量min，每次push x的时候，比较 x 与 min 的大小，如果x更小，则先将 min入栈，再将x入栈。
 * 整体思路依旧与1相同，都是基于一个认识：在弹出当前栈中的最小值之前，min一定是整个栈的最小值
 * 在弹出栈中元素时，如果弹出的是当前的最小值，则需要再弹出一次，将紧接着的栈顶元素作为最小值
 *
 * @author kaithy.xu
 * @date 2020-07-18 10:37
 */
public class MinStack {

    private LinkedList<Integer> stack;

    private LinkedList<Integer> minStack;

    private static final Node HEAD = new Node();

    private static final Node SENTINEL = new Node();

    private int min;

    public MinStack() {

        stack = new LinkedList<>();

        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);

        //解法2
        SENTINEL.min = Integer.MAX_VALUE;
        HEAD.next = SENTINEL;

        //解法3
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {

        int min = Math.min(x, minStack.peek());
        minStack.push(min);
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    /**
     * 解法3
     * @param x
     */
    public void push_3(int x) {
        if(x <= min) {
            stack.push(min);
            min = x;
        }

        stack.push(x);

    }


    public void pop_3() {
        int x = stack.pop();
        if(x == min) {
            min = stack.pop();
        }
    }

    public int top_3() {
        return stack.peek();
    }

    public int getMin_3() {

        return min;
    }


    /**
     * 解法2
     * @param x
     */
    public void push_2(int x) {

        Node n = HEAD.next;

        Node c = new Node(x);
        HEAD.next = c;
        c.next = n;
        c.min = Math.min(n.min,x);
    }


    public void pop_2() {
        Node n = HEAD.next;
        if(n != SENTINEL)
            HEAD.next = n.next;
    }

    public int top_2() {
        return HEAD.next.val;
    }

    public int getMin_2() {

        return HEAD.next.min;
    }

    private static class Node {

        int val;

        int min;

        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node() {
        }
    }

}
