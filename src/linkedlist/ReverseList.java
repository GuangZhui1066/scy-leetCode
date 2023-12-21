package linkedlist;

import linkedlist.common.ListNode;

/**
 * 206. 反转链表
 */
public class ReverseList {

    /**
     * 方法一：迭代
     */
    public ListNode reverseList1(ListNode head) {
        ListNode cur, prev, post;
        cur = head;
        prev = null;
        while (cur != null) {
            post = cur.next;
            cur.next = prev;
            prev = cur;
            cur = post;
        }

        return prev;
    }


    /**
     * 方法二：递归
     */
    public ListNode reverseList2(ListNode head) {
        // 尾巴节点
        if (head == null || head.next == null) {
            return head;
        }

        // head:        1 -> 2 -> 3 -> 4
        // head.next:   2 -> 3 -> 4
        ListNode revNode = reverseList2(head.next);
        // revNode:     4 -> 3 -> 2 <- 1
        head.next.next = head;
        // revNode:     4 -> 3 -> 2 <=> 1
        head.next = null;
        // revNode:     4 -> 3 -> 2 -> 1

        return revNode;
    }

}
