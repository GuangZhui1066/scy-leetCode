/**
 * 121. 买卖股票的最佳时机
 */
public class StockBestTime {

    /**
     * 先算出每天相对于前一天的差价  ——  prices[i] - prices[i-1]
     * 然后求出这个差价数组中的最大子序列 (同 leetcode53: 最大子数组和)
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int maxProfit = 0;

        // 计算每天的差价
        int[] raises = new int[len-1];
        int[] dp = new int[len-1];
        for (int i = 0; i < len - 1; i++) {
            // 计算 raises
            raises[i] = prices[i+1] - prices[i];

            // 计算 dp
            if (i == 0) {
                dp[i] = raises[i];
            } else {
                if (dp[i-1] > 0) {
                    dp[i] = dp[i-1] + raises[i];
                } else {
                    dp[i] = raises[i];
                }
            }
            maxProfit = Math.max(dp[i], maxProfit);
        }

        return maxProfit;
    }

}














