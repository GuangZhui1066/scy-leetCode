package tree;

import org.junit.Test;
import tree.common.TreeNode;

/**
 * 549. 二叉树中最长的连续序列
 *
 * 题目：
 *   给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。
 *   最长连续序列路径是依次递增 1 的路径，或者依次递减 1 的路径。
 *   该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」或者「子 - 父」关系连接而产生的任意路径。
 *   这个最长连续的路径，可以是父结点到子结点，也可以是子结点到父结点。
 *
 * 示例 1：
 * 输入: root
 *           4
 *          / \
 *         3   5
 *       / \
 *      1   2
 * 最长序列是 [2,3,4,5] or [5,4,3,2]
 * 输出: 4
 *
 * 示例 2：
 * 输入: root
 *           5
 *          / \
 *         2   6
 *       / \    \
 *      1   3    7
 *         / \
 *        4   5
 * 最长序列是 [1,2,3,4] or [4,3,2,1]
 * 输出: 4
 *
 * 示例 3：
 * 输入: root
 *           3
 *          / \
 *         2   4
 *       / \    \
 *      1   3    5
 *         / \
 *        4   5
 * 最长序列是 [1,2,3,4,5] or [5,4,3,2,1]
 * 输出: 5
 */
public class LongestConsecutive2 {

    int maxLen = 0;

    /**
     * DFS
     * 记录从每个节点开始，往下的最长递增的序列长度 和 往下的最长递减的序列长度
     * 那么，包含当前节点的最长序列的长度 = 两者之和 - 1
     */
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return maxLen;
    }

    private int[] dfs(TreeNode node) {
        // 从左孩子开始，往下的最长递增序列长度 和 往下的最长递减序列长度
        int[] leftLen;
        // 从右孩子开始...
        int[] rightLen;

        // 从当前节点开始，往左的最长递增序列长度 和 往左的最长递减序列长度
        int curLeftIncr = 1, curLeftDesc = 1;
        // 从当前节点开始，往右的...
        int curRightIncr = 1, curRightDesc = 1;

        if (node.left != null) {
            leftLen = dfs(node.left);

            if (node.left.val == node.val + 1) {
                curLeftIncr = leftLen[0] + 1;
            }
            if (node.left.val == node.val - 1) {
                curLeftDesc = leftLen[1] + 1;
            }
        }
        if (node.right != null) {
            rightLen = dfs(node.right);

            if (node.right.val == node.val + 1) {
                curRightIncr = rightLen[0] + 1;
            }
            if (node.right.val == node.val - 1) {
                curRightDesc = rightLen[1] + 1;
            }
        }

        int curIncr = Math.max(curLeftIncr, curRightIncr);
        int curDesc = Math.max(curLeftDesc, curRightDesc);
        int curLen = curIncr + curDesc - 1;
        maxLen = Math.max(maxLen, curLen);
        return new int[] {curIncr, curDesc};
    }


    @Test
    public void test() {
        // test case 1
        //TreeNode n1 = new TreeNode(4);
        //TreeNode n2 = new TreeNode(3);
        //TreeNode n3 = new TreeNode(5);
        //TreeNode n4 = new TreeNode(1);
        //TreeNode n5 = new TreeNode(2);
        //n1.left = n2;
        //n1.right = n3;
        //n2.left = n4;
        //n2.right = n5;

        // test case 2
        //TreeNode n1 = new TreeNode(5);
        //TreeNode n2 = new TreeNode(2);
        //TreeNode n3 = new TreeNode(6);
        //TreeNode n4 = new TreeNode(1);
        //TreeNode n5 = new TreeNode(3);
        //TreeNode n6 = new TreeNode(7);
        //TreeNode n7 = new TreeNode(4);
        //TreeNode n8 = new TreeNode(5);
        //n1.left = n2;
        //n1.right = n3;
        //n2.left = n4;
        //n2.right = n5;
        //n3.right = n6;
        //n5.left = n7;
        //n5.right = n8;

        // test case 3
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(4);
        TreeNode n8 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n5.left = n7;
        n5.right = n8;

        int result = longestConsecutive(n1);
        System.out.println(result);
    }

}






