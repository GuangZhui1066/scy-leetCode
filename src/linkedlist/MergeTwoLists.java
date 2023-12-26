package linkedlist;

import linkedlist.common.ListNode;
import org.junit.Test;

/**
 * 21. 合并两个有序链表
 */
public class MergeTwoLists {

    /**
     * 方法一：递归
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }


    /**
     * 方法二：迭代
     *
     * 用双指针分别迭代两个链表，每次选两者中较小的节点添加加在排好序的链表后面
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        // 伪头节点
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        if (list1 == null) {
            pre.next = list2;
        } else {
            pre.next = list1;
        }

        return preHead.next;
    }


    @Test
    public void test() {
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(4);
        node11.next = node12;
        node12.next = node13;

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;

        ListNode listNode = mergeTwoLists2(node11, node21);
        System.out.println(listNode);

    }


}









