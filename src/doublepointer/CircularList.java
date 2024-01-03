package doublepointer;

import linkedlist.common.ListNode;

/**
 * 141. 环形链表
 */
public class CircularList {

    /**
     * 快慢指针
     * 一个快指针，一个慢指针。如果链表中有环，那么两个指针必然相遇。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head.next, slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

}





















