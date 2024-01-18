package knapsack;

import org.junit.Test;

/**
 * 416. 分割等和子集
 */
public class PartitionSubset {

    /**
     * 方法一：动态规划
     *
     * 计算出集合中数字的总和，取其一半即为背包容量 C
     * 选取 nums 中的数字放入背包，看能不能刚好把背包放满
     *
     * 因此，是 0-1 背包问题
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // nums 中数字的和为奇数，无法分割
        if (sum % 2 != 0) {
            return false;
        }

        // 物品数量
        int n = nums.length;
        // 背包容量
        int capacity = sum / 2;

        // dp[i][j] 表示能否在前 i 个元素中选出部分元素使其总和为 j
        // 状态转移方程为: dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i]]
        boolean[][] dp = new boolean[n][capacity+1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= nums[i]) {
                    // 前者表示不放入第 i 件物品，后者表示放入第 i 件物品
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n-1][capacity];
    }


    /**
     * 方法二
     *
     * 方法一中的状态转移方程 dp[i][] 的值只与 dp[i-1][] 有关，因此可以把二维 dp 数组优化为一维 dp 数组
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int n = nums.length;
        int capacity = sum / 2;

        boolean[] dp = new boolean[capacity+1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = capacity; j >= nums[i]; j--) {
                // 计算之前，dp[j] 还是 i-1 时计算出来的值
                dp[j] = dp[j] | dp[j-nums[i]];
            }
        }

        return dp[capacity];
    }


    @Test
    public void test() {
        int[] nums = new int[]{1,5,5,11};
        System.out.println(canPartition(nums));
    }

}











