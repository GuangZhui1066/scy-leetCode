package knapsack;

import org.junit.Test;

/**
 * 494. 目标和
 */
public class FindTargetSum {

    int result = 0;

    /**
     * 方法一：DFS
     *
     * 时间复杂度: O(2^n)
     * 空间复杂度: O(n). 因为递归栈的深度最大为 n
     */
    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return result;
    }

    private void dfs(int[] nums, int target, int i, int currentSum) {
        if (i == nums.length) {
            if (currentSum == target) {
                result++;
            }
            return;
        }

        dfs(nums, target, i + 1, currentSum + nums[i]);
        dfs(nums, target, i + 1, currentSum - nums[i]);
    }


    /**
     * 方法二：0-1 背包问题
     *
     * 假设数组中正数的和为 a，负数的和为 b，那么：
     *    a + b = target
     *    a - b = sum(nums)
     * 求出 a = (target + sum) / 2
     * 因此问题转换为在 nums 数组中找到部分元素，使其总和为 (target + sum) / 2
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 计算 capacity
        if ((sum + target) % 2 == 1) {
            return 0;
        }
        int capacity = (sum + target) / 2;
        if (capacity < 0) {
            return 0;
        }

        //// dp[i][j] 表示从前 i 个元素中选取一部分使其总和为 j 的组合数
        //int[][] dp = new int[len+1][capacity+1];
        //for (int i = 0; i <= len; i++) {
        //    dp[i][0] = 1;
        //}
        //
        //for (int i = 1; i <= len; i++) {
        //    for (int j = 0; j <= capacity; j++) {
        //        if (j - nums[i-1] >= 0) {
        //            dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
        //        } else {
        //            dp[i][j] = dp[i-1][j];
        //        }
        //    }
        //}
        //
        //return dp[len][capacity];


        // 把上面二维的 dp 利用滚动数组优化成一维数组 (二维数组的 dp 中，dp[i][] 的值只和 dp[i-1][] 有关)
        int[] dp = new int[capacity+1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            // 这里需要倒转 j 的遍历顺序，保证取到的 dp[j] 是上一轮循环中的值
            for (int j = capacity; j >= 0; j--) {
                if (j - nums[i-1] >= 0) {
                    dp[j] = dp[j] + dp[j-nums[i-1]];
                } else {
                    dp[j] = dp[j];
                }
            }
        }

        return dp[capacity];
    }


    @Test
    public void test() {
        int[] nums = {1,1,1,1,1};
        int target = 3;

        //int[] nums = {0,0,0,1};
        //int target = 1;

        //int[] nums = {100};
        //int target = -200;

        System.out.println(findTargetSumWays2(nums, target));
    }

}
