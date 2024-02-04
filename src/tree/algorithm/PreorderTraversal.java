package tree.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.common.TreeNode;

/**
 * 144. 二叉树的前序遍历
 *
 *   访问顺序：根、左、右
 */
public class PreorderTraversal {

    /**
     * 方法一：递归
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }


    /**
     * 方法二：迭代
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // 树中的节点是按照前序的顺序入栈，按照中序的顺序出栈
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                // 前序遍历和中序遍历的区别只是下面这一行的位置
                // 前序遍历是在入栈的时候访问，中序遍历是在出栈的时候访问
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }

        return result;
    }

}
