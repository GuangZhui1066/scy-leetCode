package knapsack;

import java.util.Arrays;

import org.junit.Test;

/**
 * 322. 零钱兑换
 */
public class CoinChange {

    /**
     * 方法一：动态规划
     */
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                // 有解
                if (i >= coins[j] && dp[i-coins[j]] != -1) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
            // 无解
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }

        return dp[amount];
    }


    /**
     * 方法二：递归 + 记忆化搜索
     *
     * 递归过程中会碰到重复的子问题，可以把每个子问题的结果记录下来避免重复计算
     */
    public int coinChange2(int[] coins, int amount) {
        // 记录已经计算过的子问题的解
        int[] result = new int[amount+1];
        Arrays.fill(result, Integer.MAX_VALUE);

        recurse(amount, coins, result);
        return result[amount];
    }

    private int recurse(int amount, int[] coins, int[] result) {
        if (amount == 0) {
            result[0] = 0;
            return 0;
        }

        int minCoinNum = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (amount < coin) {
                continue;
            }

            int subAmount = amount - coin;
            // 已经记录过
            if (result[subAmount] < Integer.MAX_VALUE) {
                if (result[subAmount] != -1) {
                    minCoinNum = Math.min(minCoinNum, 1 + result[subAmount]);
                }
            }
            // 没有记录过
            else {
                int subResult = recurse(subAmount, coins, result);
                if (subResult != -1) {
                    minCoinNum = Math.min(minCoinNum, 1 + subResult);
                }
            }
        }

        // 无解
        if (minCoinNum == Integer.MAX_VALUE) {
            result[amount] = -1;
            return -1;
        }
        result[amount] = minCoinNum;
        return minCoinNum;
    }


    @Test
    public void test() {
        int[] coins = new int[]{186,419,83,408};
        int amount = 6249;
        int result = coinChange2(coins, amount);
        System.out.println(result);
    }

}














