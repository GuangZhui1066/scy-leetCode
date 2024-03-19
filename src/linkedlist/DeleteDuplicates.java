package linkedlist;

import linkedlist.common.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 */
public class DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head, next;
        while (cur != null) {
            next = cur.next;
            while (next != null && cur.val == next.val) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }

        return head;
    }

}
