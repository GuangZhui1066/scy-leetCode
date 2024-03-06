package linkedlist;

import linkedlist.common.ListNode;
import linkedlist.common.ListUtil;
import org.junit.Test;

/**
 * 反转链表中的指定部分
 *
 * 例如：
 *   对于链表：1 -> 3 -> 4 -> 7 -> 8 -> 9
 *   反转 4 - 8 之间的部分
 *   反转后链表为：1 -> 3 -> 8 -> 7 -> 4 -> 9
 */
public class ReverseListInterval {

    /**
     * 头节点可能被反转，也可能不被反转
     * 对于头节点要特殊处理的情况，可以加一个 dummy 节点，来避免特殊处理
     */
    public ListNode reverseListInterval(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy, cur = head, next;
        ListNode beforeTail, reverseHead;
        while (cur != null) {
            next = cur.next;
            // 反转指定部分
            if (cur.val == left) {
                beforeTail = pre;
                reverseHead = cur;
                while (cur.val != right) {
                    pre = cur;
                    cur = next;
                    next = cur.next;
                    cur.next = pre;
                }
                // 拼接反转的部分和没反转的部分
                beforeTail.next = cur;
                reverseHead.next = next;
            }
            pre = cur;
            cur = next;
        }

        return dummy.next;
    }


    @Test
    public void test() {
        String str = "[1,3,4,7,8,9]";
        ListNode head = ListUtil.constructListByStr(str);

        //ListNode result = reverseListInterval(head, 4, 8);
        //ListNode result = reverseListInterval(head, 1, 4);
        //ListNode result = reverseListInterval(head, 1, 9);
        //ListNode result = reverseListInterval(head, 7, 9);
        //ListNode result = reverseListInterval(head, 1, 1);
        //ListNode result = reverseListInterval(head, 9, 9);
        ListNode result = reverseListInterval(head, 4, 4);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
