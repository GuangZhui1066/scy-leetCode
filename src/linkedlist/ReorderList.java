package linkedlist;

import linkedlist.common.ListNode;
import linkedlist.common.ListUtil;
import org.junit.Test;

/**
 * 143. 重排链表
 */
public class ReorderList {

    /**
     * 找到链表的后半部分，翻转，然后间隔插入前半部分
     */
    public void reorderList(ListNode head) {
        // 节点数量
        int cnt = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            cnt++;
        }

        int postIndex = (cnt + 1) / 2 + 1;
        // 前半部分的链表尾
        ListNode prevTail = head;
        for (int i = 1; i < postIndex - 1; i++) {
            prevTail = prevTail.next;
        }
        // 后半部分的链表头
        ListNode postHead = prevTail.next;
        // 截断
        prevTail.next = null;

        // 翻转后半部分
        postHead = reverse(postHead);

        // 间隔地拼接两个链表
        ListNode prevCur = head, prevNext;
        ListNode postCur = postHead, postNext;
        for (int i = 0; i < cnt / 2; i++) {
            prevNext = prevCur.next;
            postNext = postCur.next;
            prevCur.next = postCur;
            postCur.next = prevNext;
            postCur = postNext;
            prevCur = prevNext;
        }
    }

    private ListNode reverse(ListNode postHead) {
        ListNode cur = postHead, prev = null, post;
        while (cur != null) {
            post = cur.next;
            cur.next = prev;
            prev = cur;
            cur = post;
        }
        return prev;
    }


    @Test
    public void test() {
        //String str = "[1,2,3,4,5,6,7]";
        String str = "[1]";
        //String str = "[1,2]";
        ListNode head = ListUtil.constructListByStr(str);

        reorderList(head);
        ListUtil.printList(head);
    }

}
