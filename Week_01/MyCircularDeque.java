/**
 * 641. 设计循环双端队列
 *
 * 数组实现：
 *
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 *
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。
 *
 * @author kaithy.xu
 * @date 2020-07-18 16:36
 */
public class MyCircularDeque {

    private int[] array;

    private int size;

    private int start;

    private int end;

    public MyCircularDeque(int k) {
        size = k+1;
        array = new int[size];
        start=0;
        end=0;

    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()) {
            return false;
        }
        start = (start-1+size) % size;

        array[start] = value;


        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()) {
            return false;
        }

        array[end] = value;
        end = (end+1) % size;

        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()) {
            return false;
        }
        start = (start+1) % size;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()) {
            return false;
        }

        end = (end-1+size) % size;

        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()) {
            return -1;
        }
        return array[start];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty()) {
            return -1;
        }
        int pos = (end-1+size) % size;
        return array[pos];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return start == end;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (end+1) % size == start;
    }

    public static void main(String[] args) {
        MyCircularDeque2 circularDeque = new MyCircularDeque2(3);

        System.out.println(circularDeque.insertFront(9));
        // 返回 true
        System.out.println(circularDeque.getRear());
        // 返回 true
        System.out.println(circularDeque.insertFront(9));
        // 返回 true
        System.out.println(circularDeque.getRear());
        // 已经满了，返回 false
        System.out.println(circularDeque.insertLast(5));
        // 返回 2
        System.out.println(circularDeque.getFront());
        // 返回 true
        System.out.println(circularDeque.getRear());
        // 返回 true
        System.out.println(circularDeque.getFront());
        // 返回 true
        System.out.println(circularDeque.insertLast(8));
        // 返回 4

        System.out.println(circularDeque.deleteLast());

        System.out.println(circularDeque.getFront());

        
    }
}
