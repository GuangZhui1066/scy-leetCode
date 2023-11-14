package tree;

import java.util.LinkedList;
import java.util.Queue;

import tree.common.TreeNode;

/**
 * 101. 对称二叉树
 * https://leetcode.cn/problems/symmetric-tree/
 */
public class CheckSymmetricTree {

    /**
     * 方法一：递归
     */
    //public boolean isSymmetric(TreeNode root) {
    //    if (root == null) {
    //        return true;
    //    } else {
    //        return isSymmetricNode(root.left, root.right);
    //    }
    //}
    //
    //private boolean isSymmetricNode(TreeNode left, TreeNode right) {
    //    if (left == null && right == null) {
    //        return true;
    //    } else if (left != null && right != null) {
    //        return left.val == right.val
    //            && isSymmetricNode(left.left, right.right)
    //            && isSymmetricNode(left.right, right.left);
    //    } else {
    //        return false;
    //    }
    //}


    /**
     * 方法二：迭代
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 队列中两个连续的节点应该是轴对称的
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null) {
                continue;
            } else if (node1 != null && node2 != null) {
                if (node1.val != node2.val) {
                    return false;
                } else {
                    queue.offer(node1.left);
                    queue.offer(node2.right);
                    queue.offer(node1.right);
                    queue.offer(node2.left);
                }
            } else {
                return false;
            }
        }

        return true;
    }

}
