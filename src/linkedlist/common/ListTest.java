package linkedlist.common;

public class ListTest {

    public static void main(String[] args) {
        String str = "[1,2,3,4,5]";
        ListNode listNode = ListUtil.constructListByStr(str);
        ListUtil.printList(listNode);
    }

}
