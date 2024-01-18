package knapsack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

/**
 * 279. 完全平方数
 */
public class SquareNumber {

    /**
     * 方法一：动态规划
     *
     * 思路：
     *   可以把 n 拆为一个完全平方数 + 剩下的部分：n = (x * x) + m。   比如 12 = 1 * 1 + 11;  12 = 2 * 2 + 8;  12 = 3 * 3 + 3.
     *   那么 n 的解就等于 m 的解 + 1。 因此要求出 n 的最小解，就只需要找到具有最小解的 m。
     *   上述例子中，只需找到 11, 8, 3 里的最小解，即 8 (8=4+4，8 的最小解是 2，因此 12 的最小解是 3)
     *
     * 时间复杂度：O(N * N^0.5)
     * 空间复杂度：O(N)
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);
            }
        }

        return dp[n];
    }


    /**
     * 方法二：BFS
     *
     * 思路：
     * 例如 n = 12，可以构建如下的树。父节点与子节点的差是一个完全平方数
     *
     *                   12
     *            /       ｜    \
     *          11        8     3
     *      /   ｜  \     /\    ｜
     *    10    7   2    7 4    2
     *   /｜\   /\  ｜     /\
     *  9 6 1  6 3  1    3  0
     *  ... ｜ ...  ｜
     *      0       0
     *
     *  每一条从根到叶子节点的路径，就是一个完全平方数的组合。 路径的长度就是拆分出来的完全平方数的个数。
     *  例如路径 12 - 11 - 10 - 9 - 0，表示 12 = 1 + 1 + 1 + 9.
     *  例如路径 12 - 8 - 4 - 0，表示 12 = 4 + 4 + 4.
     */
    public int numSquares2(int n) {
        Queue<Integer> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(n);

        // 当前层数
        int level = 0;
        Set<Integer> visited = new HashSet<>();

        // 每次循环表示计算完当前层
        while (!queue.isEmpty()) {
            level++;

            // 把当前层的所有节点都计算一遍
            int curLevelNodeNum = queue.size();
            for (int i = 0; i < curLevelNodeNum; i++) {
                Integer cur = queue.poll();
                // 生成当前节点下一层的所有子节点，入队
                for (int j = (int) Math.sqrt(cur); j >= 1; j--) {
                    // 子节点的值
                    int remain = cur - j * j;
                    // 找到路径终点
                    if (remain == 0) {
                        return level;
                    }
                    // 如果当前值之前已经出现过，那这条路径就不可能是最短的，不计算 (例如第四层要生成一个子节点3，但是3已经在第二层生成过，那后者的路径肯定比前者短)
                    if (visited.contains(remain)) {
                        continue;
                    }
                    // 子节点入队、标记
                    queue.offer(remain);
                    visited.add(remain);
                }
            }
        }

        return 0;
    }


    /**
     * 方法三：背包问题
     *
     * 看作背包问题，从 1 ～ n^0.5 这几个数中重复选出几个，使其平方和等于背包总容量。
     */
    public int numSquares3(int n) {
        // 计算物品的个数 —— n^0.5
        int m = 0;
        while (m * m <= n) {
            m++;
        }
        m--;

        // dp[i][j] 表示从 1 ～ i 这几个数字中重复选出部分数字，使其平方和等于 j，并且所选元素个数最小的值
        // 状态转移方程：dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - i * i] + 1)
        int[][] dp = new int[m+1][n+1];
        // 初始化为很大的值
        for (int[] arr : dp) {
            Arrays.fill(arr, n+1);
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j >= i * i) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - i * i] + 1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[m][n];
    }


    @Test
    public void test() {
        int n = 12;
        int result = numSquares3(n);
        System.out.println(result);
    }

}













