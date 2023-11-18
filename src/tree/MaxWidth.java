package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.common.TreeNode;

/**
 * 662. 二叉树最大宽度
 * https://leetcode.cn/problems/maximum-width-of-binary-tree/
 */
public class MaxWidth {

    /**
     * 错误解法：
     *   空间复杂度太高，会运行超时
     *
     * 把二叉树当成满二叉树，每层遍历
     *
     *         1                               1
     *        / \                        /          \
     *       2   3                    2              3
     *      /     \     --->       /    \           /  \
     *     4       5              4     nil       nil   5
     *    /       /              / \    / \      /\    / \
     *   6       7             6  nil nil nil  nil nil 7 nil
     *                        /\  /\  /\  /\   /\  /\ /\  /\
     *                       nil ..........................nil
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxWidth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (true) {
            // 每次遍历一层，把当前层的所有节点都 poll 出来 (包括 nil 节点)
            List<TreeNode> currentLevelNodes = new ArrayList<>();
            while (!queue.isEmpty()) {
                currentLevelNodes.add(queue.poll());
            }

            // 计算当前层的宽度：最左边非null节点和最右边非null节点之间的距离
            int currentLevelWidth = calcCurrentLevelWidth(currentLevelNodes);
            // 当前层的节点全部都是 nil，说明已经到了最后一层的下面一层，退出
            if (currentLevelWidth == -1) {
                break;
            }
            maxWidth = Math.max(currentLevelWidth, maxWidth);

            // 将下一层的节点入队
            for (TreeNode node : currentLevelNodes) {
                if (node == null) {
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }

        return maxWidth;
    }

    private int calcCurrentLevelWidth(List<TreeNode> nodes) {
        int size = nodes.size();
        // 最左边非 null 节点的位置
        int leftNotNullIndex = size;
        // 最右边非 null 节点的位置
        int rightNotNullIndex = -1;

        for (int i = 0; i < size; i++) {
            if (nodes.get(i) != null) {
                leftNotNullIndex = Math.min(leftNotNullIndex, i);
            }
            if (nodes.get(size - i - 1) != null) {
                rightNotNullIndex = Math.max(rightNotNullIndex, size - i - 1);
            }
        }

        // 当前层全部都是 null 节点
        if (leftNotNullIndex == size && rightNotNullIndex == -1) {
            return -1;
        }
        // 当前层只有一个非 null 节点
        else if (leftNotNullIndex == size || rightNotNullIndex == -1) {
            return 1;
        }
        // 当前层至少有两个非 null 节点
        else {
            return rightNotNullIndex - leftNotNullIndex + 1;
        }
    }

}
