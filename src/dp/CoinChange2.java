package dp;

import org.junit.Test;

/**
 * 518. 零钱兑换2
 */
public class CoinChange2 {

    /**
     * 动态规划
     *
     * dp[i] 表示总和为 i 有几种组合
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;

        /**
         * 错误写法
         * 如果外层对 amount 循环，内层对 coins 循环，就会统计重复。比如 5 = 1 + 4 (2+2) = 2 + 3 (1+2)
         */
        //for (int i = 1; i <= amount ; i++) {
        //    for (int coin : coins) {
        //        if (i >= coin && dp[i-coin] > 0) {
        //            dp[i] += dp[i-coin];
        //        }
        //    }
        //}

        /**
         * 正确写法
         * 应该外层对 coins 循环，内层对 amount 循环。
         * 这样就可以保证统计的所有硬币组合里，硬币的顺序和 coins 数组顺序一致，不会重复统计。
         */
        for (int coin : coins) {
            for (int i = coin; i <= amount ; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }


    @Test
    public void test() {
        int amount = 5;
        int[] coins = new int[]{1,2,5};

        System.out.println(change(amount, coins));
    }

}














