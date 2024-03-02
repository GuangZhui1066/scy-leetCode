package linkedlist;

import linkedlist.common.ListNode;
import linkedlist.common.ListUtil;
import org.junit.Test;

/**
 * 在一个递增的链表中，删除重复的元素节点
 *
 * 举例：
 *   1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 *   删除重复节点后：
 *   1 -> 2 -> 5
 */
public class DeleteDuplicate {

    // todo
    public ListNode deleteDuplicate(ListNode head) {
        ListNode dummy = new ListNode(0, head);

        return dummy.next;
    }


    @Test
    public void test() {
        //String listStr = "[1,2,3,3,4,4,5]";
        String listStr = "[1,1,1,2,3,4,4]";

        ListNode head = ListUtil.constructListByStr(listStr);
        System.out.println(deleteDuplicate(head));
    }

}
