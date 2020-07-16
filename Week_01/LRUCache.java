package com.kaithy.xu.tecent.mediumQuestion;

import java.util.HashMap;
import java.util.Map;

/**
 * 结题思路：
 * 利用双向链表+散列表的形式来记录数据
 * 散列表负责记录每个key对应的节点，以便通过O(1)的方式来获取到key对应的节点
 * 双向链表保存每个节点的“新旧程度”，这边自己采取的是“新”节点放到链表的末尾，所以可以疼爱头结点直接获取到最“旧”的节点
 * 
 */
public class LRUCache {

    private static final Node SENTINEL = new Node();

    private Map<Integer, Node> map;

    private Node HEAD;

    private int capacity;

    private int count;

    static class Node {
        int key;

        int val;

        Node next;

        Node prev;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        count = 0;

        map = new HashMap<>(capacity*3/2);

        HEAD = new Node();
        HEAD.next = SENTINEL;
        SENTINEL.prev = HEAD;

    }

    public int get(int key) {
        Node t = map.get(key);
        if(null != t)
            transfer(t);
        return null == t ? -1 : t.val;
    }

    public void put(int key, int value) {
        Node t = map.get(key);

        if(null == t) {
            if(count >= capacity) {
                t = HEAD.next;
                HEAD.next = t.next;
                t.next.prev = HEAD;
                count--;
                map.remove(t.key,t);
            }
            t = new Node(key,value);
            Node p = SENTINEL.prev;
            p.next = t;
            t.prev = p;

            t.next = SENTINEL;
            SENTINEL.prev = t;

            map.put(key,t);
            ++count;
        }else {
            transfer(t);
            t.val = value;
        }
    }

    private void transfer(Node t) {
        Node p = t.prev;
        Node n = t.next;
        p.next = n;
        n.prev = p;

        Node sp = SENTINEL.prev;
        sp.next = t;
        t.next = SENTINEL;

        t.prev = sp;
        SENTINEL.prev = t;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1,1);
        cache.put(2,2);

        System.out.println(cache.get(1));;
        cache.put(3,3);
        System.out.println(cache.get(2));;
        cache.put(4,4);
        System.out.println(cache.get(1));;

        System.out.println(cache.get(3));;
        System.out.println(cache.get(4));;
    }

}
