package tree;

import tree.common.TreeNode;

/**
 * 404. 左叶子之和
 * https://leetcode.cn/problems/sum-of-left-leaves/
 */
public class SumOfLeft {

    /**
     * 方法一：递归
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode leftNode = root.left;
        // 没有左子节点
        if (leftNode == null) {
            return sumOfLeftLeaves(root.right);
        }
        // 左子节点是叶子节点
        else if (leftNode.left == null && leftNode.right == null) {
            return leftNode.val + sumOfLeftLeaves(root.right);
        }
        // 左子节点不是叶子节点
        else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }

}