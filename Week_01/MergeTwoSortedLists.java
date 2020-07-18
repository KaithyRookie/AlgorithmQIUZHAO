import basic.ListNode;

/**
 * 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 解题思路：
 * 解法1 递归：
 * 递归的遍历两个链表，根据两个链表的节点的大小值，选择深入的方向
 * 时间复杂度: O(m+n) 其中m，n分别是两个链表节点数
 * 空间复杂度：O(m+n) 使用了额外空间来保存链表节点
 *
 * 解法2 迭代：
 * 利用while循环遍历两个链表，同时使用一个哨兵节点记录合并后的链表的头部节点
 * 时间复杂度: O(m+n)
 * 空间复杂度：O(1)
 *
 * @author kaithy.xu
 * @date 2019-09-11 12:19
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        l1 = merge(l1, l2);
        return l1;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            if(l1 != null) {
                return l1;
            }
            if(l2 != null) {
                return l2;
            }
            return null;
        }
        ListNode node;
        if(l1.val > l2.val) {
            node = l2;
            node.next = merge(l1, l2.next);
        }else {
            node = l1;
            node.next = merge(l1.next, l2);
        }
        return node;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(-1);

        ListNode label = sentinel;
        while (l1 != null && l2 != null) {

            if(l1.val > l2.val) {
                label.next = l2;
                l2 = l2.next;
            }else {
                label.next = l1;
                l1 = l1.next;
            }
            label = label.next;

        }

        label.next = l1 == null ? l2 : l1;

        return sentinel.next;
    }


    public static void main(String[] args) {

        int[] nums1 = {1,3,4,5};
        int[] nums2 = {1,3,5};
        ListNode l1 = ListNode.acquireListNode(nums1,0);
        ListNode l2 = ListNode.acquireListNode(nums2,0);

        ListNode result = new MergeTwoSortedLists().mergeTwoLists2(l1, l2);

        System.out.println("the result is :");
        while (result != null) {
            System.out.print(result.val+"-->");
            result = result.next;
        }
    }

}
