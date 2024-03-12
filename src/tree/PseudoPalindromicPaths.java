package tree;

import org.junit.Test;
import tree.common.TreeNode;
import tree.common.TreeUtil;

/**
 * 1457. 二叉树中的伪回文路径
 */
public class PseudoPalindromicPaths {

    int result = 0;

    // count[i] 表示数字 i 在当前路径上出现的次数
    int[] count = new int[10];

    /**
     * 能构成伪回文路径的条件是：
     *   路径上每个数字都出现了偶数次，这种情况下路径长度是偶数
     *   或者路径上只有一个数字出现了奇数次，其他数字都出现了偶数次，这种情况下路径长度是奇数
     *
     * 因此用 DFS 遍历每条路径，统计每条路径上各个数字出现的次数
     *   可以用数组记录每个数字出现的次数
     *   也可以用位运算来优化
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, count);
        return result;
    }

    private void dfs(TreeNode node, int[] count) {
        count[node.val]++;

        if (node.left == null && node.right == null) {
            checkPseudoPanlindrome(count);
        }

        if (node.left != null) {
            dfs(node.left, count);
        }
        if (node.right != null) {
            dfs(node.right, count);
        }

        count[node.val]--;
    }

    private void checkPseudoPanlindrome(int[] count) {
        int oddNumCnt = 0;
        for (int i = 1; i <= 9; i++) {
            if (count[i] % 2 == 0) {
                continue;
            } else {
                oddNumCnt++;
            }
        }
        if (oddNumCnt <= 1) {
            result++;
        }
    }


    @Test
    public void test() {
        ///**
        // *       2
        // *      / \
        // *     3   1
        // *    / \   \
        // *   3  1    1
        // */
        //String treeStr = "[2,3,1,3,1,null,1]";

        /**
         *       1
         *      / \
         *     9   1
         *      \   \
         *      1    1
         *          /
         *         7
         *          \
         *           4
         */
        String treeStr = "[1,9,1,null,1,null,1,null,null,7,null,null,4]";

        TreeNode root = TreeUtil.constructTree(treeStr);

        System.out.println(pseudoPalindromicPaths(root));
    }

}










