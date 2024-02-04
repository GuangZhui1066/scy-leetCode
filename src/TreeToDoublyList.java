import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;
import tree.common.TreeNode;

/**
 * 426. 二叉搜索树转换化为排序的双向链表
 *
 * 题目：
 *   将一个二叉搜索树 就地转化为一个已排序的双向循环链表。
 *   对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *   特别地，我们希望可以就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
 */
public class TreeToDoublyList {

    /**
     * 方法一：递归
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode[] list = doublyList(root);
        // 拼接头尾
        list[0].left = list[1];
        list[1].right = list[0];
        return list[0];
    }

    /**
     * 返回把 root 链表化以后的 链表头 和 链表尾
     * 链表头是把 root 的左子树链表化以后的链表头，链表尾是把 root 的右子树链表化以后的链表尾
     */
    public TreeNode[] doublyList(TreeNode root) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        // 链表化以后的左子树
        TreeNode[] leftList = doublyList(root.left);
        // 链表化以后的右子树
        TreeNode[] rightList = doublyList(root.right);

        // 把左子树链表、根节点、右子树链表，这三部分拼成双向链表
        root.left = leftList[1];
        if (leftList[1] != null) {
            leftList[1].right = root;
        }
        root.right = rightList[0];
        if (rightList[0] != null) {
            rightList[0].left = root;
        }

        return new TreeNode[]{leftList[0] == null ? root : leftList[0],
                              rightList[1] == null ? root : rightList[1]};
    }


    /**
     * 方法二：迭代
     *
     * 中序遍历，二叉搜索树中序遍历的结果就是按照大小顺序遍历
     */
    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        // 链表头，即二叉树中最小的元素
        TreeNode head = null;
        // temp 为当前处理的节点，pre 为之前处理的节点
        TreeNode temp = null, pre = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            temp = stack.pop();

            // 找到最小的元素，即链表头
            if (head == null) {
                head = temp;
            }

            // 把当前节点和之前的节点拼接为双向链表
            if (pre == null) {
                pre = temp;
            } else {
                temp.left = pre;
                pre.right = temp;
                pre = temp;
            }
            cur = temp.right;
        }

        // 拼接链表头和链表尾
        head.left = temp;
        temp.right = head;
        return head;
    }



    /**
     *         4
     *        / \
     *       2   5
     *      /\
     *     1  3
     */
    @Test
    public void test() {
        TreeNode head = null;

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n4.left = n2;
        n4.right = n5;
        n2.left = n1;
        n2.right = n3;
        head = treeToDoublyList2(n4);

        //n2.left = n1;
        //n2.right = n3;
        //head = treeToDoublyList(n2);

        //head = treeToDoublyList(n1);

        //head = treeToDoublyList(null);

        System.out.println(head);
    }

}
