package linkedlist.common;

public class ListUtil {


    /**
     * 构建链表
     *
     * 输入：
     *   [1,2,3,4,5]
     *
     * 输出：
     *   1 -> 2 -> 3 -> 4 -> 5
     */
    public static ListNode constructListByStr(String str) {
        Integer[] intArr = getIntArrayByStr(str);
        ListNode head = new ListNode(intArr[0]);
        ListNode prev = head;
        for (int i = 1; i < intArr.length; i++) {
            ListNode node = new ListNode(intArr[i]);
            prev.next = node;
            prev = node;
        }
        return head;
    }


    /**
     * 画出链表
     */
    public static void printList(ListNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        while (node.next != null) {
            sb.append(" -> ").append(node.next.val);
            node = node.next;
        }

        System.out.println(sb);
    }


    public static Integer[] getIntArrayByStr(String str) {
        String arrayStr = str.substring(1, str.length() - 1);
        String[] strArray = arrayStr.split(",");
        Integer[] intArr = new Integer[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArr[i] = Integer.parseInt(strArray[i]);
        }
        return intArr;
    }

}
