package tree;

import java.util.LinkedList;
import java.util.Queue;

import tree.common.TreeNode;

/**
 * 404. 左叶子之和
 * https://leetcode.cn/problems/sum-of-left-leaves/
 */
public class SumOfLeft {

    /**
     * 方法一：递归
     */
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode leftNode = root.left;
        // 没有左子节点
        if (leftNode == null) {
            return sumOfLeftLeaves1(root.right);
        }
        // 左子节点是叶子节点
        else if (isLeafNode(leftNode)) {
            return leftNode.val + sumOfLeftLeaves1(root.right);
        }
        // 左子节点不是叶子节点
        else {
            return sumOfLeftLeaves1(root.left) + sumOfLeftLeaves1(root.right);
        }
    }


    /**
     * 方法二：迭代
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null && isLeafNode(node.left)) {
                result += node.left.val;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return result;
    }

    private boolean isLeafNode(TreeNode left) {
        return left.left == null && left.right == null;
    }

}
