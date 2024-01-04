import org.junit.Test;

/**
 * 121. 买卖股票的最佳时机
 */
public class StockBestTime {

    /**
     * 解法一：
     *
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


    /**
     * 解法二 (官方)
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int maxProfit = 0;

        // 记录在每天在当天之前出现过的最低价
        int[] curMinPrice = new int[len];
        curMinPrice[0] = prices[0];
        for (int i = 1; i < len; i++) {
            if (prices[i] < curMinPrice[i-1]) {
                curMinPrice[i] = prices[i];
            } else {
                curMinPrice[i] = curMinPrice[i-1];
            }

            // 计算在之前的最低价买入、今天卖出的收益
            maxProfit = Math.max(maxProfit, prices[i] - curMinPrice[i-1]);
        }

        return maxProfit;
    }


    @Test
    public void test() {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit2(prices));
    }

}














