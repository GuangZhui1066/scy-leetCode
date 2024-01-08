package linkedlist;

import linkedlist.common.ListNode;
import linkedlist.common.ListUtil;
import org.junit.Test;

/**
 * 25. K个一组翻转链表
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        // 伪头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = head;
        // 待翻转的那组链表的开头和结尾
        ListNode needReverseHead, needReverseTail;
        // 已经翻转过的部分的结尾
        ListNode reversedTail = dummy;

        while (cur != null) {
            // 找到要翻转的一组节点的头和尾
            needReverseHead = cur;
            for (int i = 1; i < k; i++) {
                // 这一组剩余节点不足k个，不翻转
                if (cur.next == null) {
                    return dummy.next;
                }
                cur = cur.next;
            }
            needReverseTail = cur;

            // 未翻转部分的起始位置
            cur = cur.next;

            // 把要翻转的那一组截断
            needReverseTail.next = null;

            // 翻转这 k 个节点
            // 把翻转后的头节点 拼接到已经翻转好的那部分链表的后面
            reversedTail.next = reverse(needReverseHead);
            // 更新已经翻转好的部分的尾节点
            reversedTail = needReverseHead;

            // 把后面未翻转的部分拼接到已翻转的部分后面
            reversedTail.next = cur;
        }

        return dummy.next;
    }

    private ListNode reverse(ListNode start) {
        ListNode cur = start, pre = null, post;
        while (cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }
        return pre;
    }


    @Test
    public void test() {
        String str = "[1,2,3,4,5,6,7]";
        int k = 3;
        ListNode head = ListUtil.constructListByStr(str);

        ListNode reverse = reverseKGroup(head, k);
        ListUtil.printList(reverse);
    }

}

















