package tree;

import java.util.Arrays;

import org.junit.Test;
import tree.common.TreeNode;

/**
 * 298. 二叉树最长连续序列
 *
 * 题目：
 *   给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。
 *   最长连续序列路径是依次递增 1 的路径。
 *   该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」关系连接而产生的任意路径。
 *   这个最长连续的路径，必须是从父结点到子结点，反过来是不可以的。
 *
 * 示例 1：
 * 输入: root
 *             1
 *            /
 *           3
 *         / \
 *        4   2
 *       /
 *      5
 * 最长序列是 [3,4,5]
 * 输出: 3
 *
 * 示例 2：
 * 输入: root
 *           2
 *            \
 *             3
 *            /
 *           2
 *         /
 *        1
 * 最长序列是 [2,3]
 * 输出: 2
 */
public class LongestConsecutive {

    public int maxLen = 1;

    public int longestConsecutive(TreeNode root) {
        dfs(root, 1);
        return maxLen;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        for (TreeNode child : Arrays.asList(node.left, node.right)) {
            if (child == null) {
                continue;
            }
            if (child.val == node.val + 1) {
                maxLen = Math.max(maxLen, depth + 1);
                dfs(child, depth + 1);
            } else {
                dfs(child, 1);
            }
        }
    }


    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n3;
        n3.left = n4;
        n3.right = n2;
        n4.left = n5;

        TreeNode n21 = new TreeNode(2);
        TreeNode n22 = new TreeNode(3);
        TreeNode n23 = new TreeNode(2);
        TreeNode n24 = new TreeNode(1);
        n21.right = n22;
        n22.left = n23;
        n23.left = n24;

        int maxLen = longestConsecutive(n1);
        //int maxLen = longestConsecutive(n21);
        System.out.println(maxLen);
    }

}
