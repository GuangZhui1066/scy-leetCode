package dp;

import org.junit.Test;

/**
 * 2400. 恰好移动k步到达某一位置的方法数目
 */
public class KStepWays {

    int mod = 1000000007;

    /**
     * 动态规划
     *
     * dp[i][j] 表示从位置 startPos 移动 j 步恰好到达到位置 i 的方法数目
     * dp[i][j] = dp[i-1][j-1] + dp[i+1][j-1]
     */
    public int numberOfWays(int startPos, int endPos, int k) {
        // 1 <= startPos, endPos, k <= 1000
        // 因为是无限数轴，所以位置范围是 [-999, 2001]，因此需要映射成 [1, 3001]
        int[][] dp = new int[3002][k+1];
        dp[startPos+1000][0] = 1;

        for (int j = 1; j <= k; j++) {
            for (int i = startPos + 1000 - k; i <= startPos + 1000 + k; i++) {
                dp[i][j] = dp[i-1][j-1] + dp[i+1][j-1];
                dp[i][j] %= mod;
            }
        }

        return dp[endPos+1000][k];
    }


    @Test
    public void test() {
        int startPos = 1;
        int endPos = 2;
        int k = 3;

        System.out.println(numberOfWays(startPos, endPos, k));
    }

}











