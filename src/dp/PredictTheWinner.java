package dp;

import org.junit.Test;

/**
 * 486. 预测赢家
 */
public class PredictTheWinner {

    /**
     * 动态规划
     *
     * dp[i][j] 表示在 nums 数组中 [i, j] 范围中，当前玩家与另一位玩家的分数之差的最大值
     *   当 i = j 时，dp[i][j] = nums[i]
     *   当 i > j 时，数组无意义
     *   当 i < j 时，当前玩家可以选择 nums[i] 或者 nums[j]
     *     如果选择 nums[i]，那么当前玩家与另一位玩家的分数之差为 nums[i] - dp[i+1][j]
     *       注：假设当前玩家为A，另一位玩家为B。那么A选了nums[i]后，B就变成了当前玩家，可以在 [i+1, j] 的范围内选择，B在 [i+1, j] 的范围与A分数差的最大值为 dp[i+1][j]，则A与B分数差的最大值为 -dp[i+1][j]
     *     同理，如果选择 nums[j]，那么当前玩家与另一位玩家的分数之差为 nums[j] - dp[i][j-1]
     *
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n^2)
     */
    public boolean predictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                // 这里 dp[i][j] 的值依赖于 dp[i+1][]，因此 dp[i+1][] 的值要先算出来，所以外层循环中应该是 i--
                // 同理，dp[][j] 的值依赖于 dp[][j-1]，因此 dp[][j-1] 的值要先算出来，所以外层循环中应该是 j++
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }

        return dp[0][len-1] >= 0;
    }


    @Test
    public void test() {
        int[] nums = {1,5,2};
        //int[] nums = {1,5,233,7};

        System.out.println(predictTheWinner(nums));
    }

}