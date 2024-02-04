package tree.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.Test;
import tree.common.TreeNode;

/**
 * 145. 二叉树的后序遍历
 *
 *   访问顺序：左、右、根
 */
public class PostorderTraversal {

    /**
     * 方法一：递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }


    /**
     * 方法二：迭代
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            // 右子树为空 或者 已经访问过，不需要再访问右子树
            if (cur.right == null || cur.right == prev) {
                stack.pop();
                result.add(cur.val);
                prev = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }

        return result;
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
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n4.left = n2;
        n4.right = n5;
        n2.left = n1;
        n2.right = n3;

        List<Integer> result = postorderTraversal2(n4);
        System.out.println(result);
    }

}
