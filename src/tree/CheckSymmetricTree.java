package tree;

import tree.common.TreeNode;

/**
 * 101. 对称二叉树
 * https://leetcode.cn/problems/symmetric-tree/
 */
public class CheckSymmetricTree {

    /**
     * 方法一：递归
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return isSymmetricNode(root.left, root.right);
        }
    }

    private boolean isSymmetricNode(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            return left.val == right.val
                && isSymmetricNode(left.left, right.right)
                && isSymmetricNode(left.right, right.left);
        } else {
            return false;
        }
    }

}
