package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

import linkedlist.common.ListNode;
import linkedlist.common.ListUtil;
import org.junit.Test;

/**
 * 23. 合并K个升序链表
 */
public class MergeKLists {

    /**
     * 最小堆
     *
     * 假设 k 个链表，链表长度为 N
     * 如果按照合并两个有序链表的思路，每次从 k 个头节点中选出一个最小的，拼接在结果链表上。然后把最小的这个链表头往后移一个节点
     * 那么遍历完这 k 个链表的所有节点的复杂度是 O(N * k)
     * 每次从 k 个元素中选出最小元素的复杂度是 O(k)
     * 因此整体的复杂度是 O(N * k^2)
     *
     * 每次从 k 个元素中选出最小元素这一步，可以用最小堆来实现，每次选出最小元素的复杂度为 O(logk)
     * 所以整体的复杂度可以优化为 O(N * k * logk)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len <= 0) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;

        // 最小堆
        // 堆中存放的是最小头节点所在的链表编号，范围是 [0, len-1]
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return lists[a].val - lists[b].val;
            }
        });

        // 初始时把所有头节点都加入最小堆
        for (int i = 0; i < len; i++) {
            if (lists[i] != null) {
                minHeap.add(i);
            }
        }
        if (minHeap.isEmpty()) {
            return null;
        }
        // 最小的头节点所在的链表
        Integer curMinList = minHeap.remove();
        ListNode curMinListHead = lists[curMinList];
        // 把这个链表的头节点后移
        lists[curMinList] = curMinListHead.next;
        // 把这个最小的头节点拼接到最终的结果链表上
        pre.next = curMinListHead;
        pre = curMinListHead;
        pre.next = null;

        while (true) {
            if (lists[curMinList] != null) {
                minHeap.add(curMinList);
            }
            if (minHeap.isEmpty()) {
                break;
            } else {
                // 每次弹出一个最小的头节点，拼接
                curMinList = minHeap.remove();
                curMinListHead = lists[curMinList];
                lists[curMinList] = curMinListHead.next;
                pre.next = curMinListHead;
                pre = curMinListHead;
                pre.next = null;
            }
        }

        return dummy.next;
    }


    @Test
    public void test() {
        ListNode l1 = ListUtil.constructListByStr("[1,4,5]");
        ListNode l2 = ListUtil.constructListByStr("[1,3,4]");
        ListNode l3 = ListUtil.constructListByStr("[2,6]");
        ListNode[] lists = {l1, l2, l3};

        ListNode result = mergeKLists(lists);
        System.out.println(result);
    }

}
