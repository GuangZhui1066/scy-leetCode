package linkedlist;

import linkedlist.common.ListNode;
import linkedlist.common.ListUtil;
import org.junit.Test;

/**
 * 82. 删除排序链表中的重复元素2
 */
public class DeleteDuplicates2 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = head, next;
        ListNode oldCur;

        while (cur != null) {
            oldCur = cur;
            next = cur.next;

            while (next != null && cur.val == next.val) {
                cur = next;
                next = cur.next;
            }

            if (cur == oldCur) {
                pre = cur;
                cur = next;
            } else {
                pre.next = next;
                cur = next;
            }
        }

        return dummy.next;
    }


    @Test
    public void test() {
//        String listStr = "[1,2,3,4]";
//        String listStr = "[1,2,2,3,4]";
//        String listStr = "[1,2,2,3,4,4]";
//        String listStr = "[1,2,3,3,4,4,5]";
//        String listStr = "[1,1,1,2,3,4,4]";
        String listStr = "[1,1,1,2,2,3,3]";

        ListNode head = ListUtil.constructListByStr(listStr);
        System.out.println(deleteDuplicates(head));
    }

}
