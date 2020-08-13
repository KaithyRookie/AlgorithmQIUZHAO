import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 实现思路：
 * 定义内部节点类，一个val 用于保存字符，一个节点数组，可以是动态扩容的，也可以是固定的(比如只支持26个小写字母，就可以建一个长度为26的节点数组)
 * 另外定义三种状态变量，0，1，2，对应节点的三种状态，节点，成词的节点，叶子节点
 *
 * 在节点数组中的搜索可以优化，考虑利用hash 散列数组，
 *
 * @author kaithy.xu
 * @date 2020-06-22 21:40
 */
public class Trie {

    private static final Node ROOT = new Node();

    private static class Node {
        private static final int DEFAULT_SIZE = 26;
        private static final int NORMAL_TYPE=0;
        private static final int MEDIUM_TYPE=1;
        private static final int FINAL_TYPE=2;

        Node[] array;
        private AtomicInteger size;

        AtomicInteger freq;
        int val;
        private int type;

        public Node (){
            array = new Node[DEFAULT_SIZE];
            this.size = new AtomicInteger(0);
        }

        public Node(int val, int freq) {
            this();
            this.val = val;
            this.freq = new AtomicInteger(freq);
            this.type = NORMAL_TYPE;
        }

        public boolean containsKey(char val) {
            return array[val-'a'] != null;
        }

        public void setMediumType() {
            this.type = MEDIUM_TYPE;
        }

        public void setEndType() {
            this.type = FINAL_TYPE;
        }

        public boolean isEnd() {
            return this.type != NORMAL_TYPE;
        }
    }


    public void insert(String word) {
        Node node = ROOT;
        for (Character c : word.toCharArray()) {
            if(!node.containsKey(c)) {
                node.size.getAndIncrement();
                node.array[c-'a'] = new Node(c,0);
            }
            node = node.array[c-'a'];
            if(node.isEnd()) {
                node.setMediumType();
            }
        }
        if(node.size.get() > 0) {
            node.setMediumType();
        }else {
            node.setEndType();
        }
    }

    private Node searchPrefix(String word) {
        if(word.length() == 0) {
            return null;
        }
        Node node = ROOT;
        for (Character c : word.toCharArray()) {
            if(!node.containsKey(c)) {
                return null;
            }
            node = node.array[c-'a'];
            node.freq.getAndIncrement();
        }
        return node == ROOT ? null : node;
    }

    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String word){
        Node node = searchPrefix(word);
        return node != null;
    }

    public void printByBFS() {
        Node node = ROOT;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node t = queue.remove();
            System.out.println((char)t.val+" : "+t.freq+" : "+t.type);
            for (int i = 0; i < t.array.length; i++) {
                if(t.array[i] != null) {
                    queue.add(t.array[i]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        trie.insert("banana");
        trie.insert("app");

        trie.printByBFS();
        System.out.println(trie.startsWith("a"));

    }
}
