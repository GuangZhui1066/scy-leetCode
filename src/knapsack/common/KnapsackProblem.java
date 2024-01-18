package knapsack.common;

/**
 * 背包问题是一种组合优化的 NP 完全问题。
 *
 * 背包问题：
 *   给定一组物品，每种物品都有自己的重量和价格。在限定的总重量内，如何选择才能使所选物品的总价格最高。
 *
 *   如果每件物品最多选 1 次 (即可以选0/1次)，就是 0-1 背包问题。
 *   如果每件物品可以选无限次，就是 完全背包问题。
 *
 *
 * 背包问题的解法 —— 动态规划
 *   dp[i][j] 表示只在前 i 件物品中选择物品放入容量为 j 的背包，可以获得的最大价值。
 *   dp[n][C] 的值即为问题的最终解。
 *
 *   0-1 背包问题：
 *     状态转移方程：dp[i][j] = max {dp[i−1][j], dp[i−1][j−wi] + vi}. (前者表示放入第 i 件物品，后者表示不放第 i 件物品)
 *     可以优化为使用一维的 dp 数组
 *
 *  完全背包问题：
 *     状态转移方程：dp[i][j] = max {dp[i−1][j], dp[i][j−wi] + vi}.
 *
 */
public class KnapsackProblem {

    /**
     * 物品数量、物品重量和价格
     * 物品编号为 1 ～ n, 第 i 件物品重量为 w[i].
     */
    int n;
    int[] w = new int[n+1];
    int[] v = new int[n+1];

    /**
     * 背包容量
     */
    int capacity;

    /**
     * dp[i][j] 表示只在前 i 件物品中选择物品放入容量为 j 的背包，可以获得的最大价值
     * dp[n][C] 的值即为问题的最终解
     */
    int[][] dp = new int[n+1][capacity+1];


    /**
     * 0-1 背包
     */
    public int zeroOneKnapsack() {
        // 初始化 dp 数组
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= w[i]) {
                    // max函数参数中，前者表示不放入第 i 件物品，后者表示放入第 i 件物品
                    dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - w[i]] + v[i]);
                }
                // 背包容量放不下第 i 件物品
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][capacity];
    }


}
