import org.junit.Test;

/**
 * 70. 爬楼梯
 */
public class ClimbStairs {

    /**
     * 动态规划
     *
     * 不能用递归，递归会超时
     */
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 动态规划 优化空间复杂度
     * 每次只需要 dp[n-1] 和 dp[n-2]，可以用两个变量来表示，这两个变量循环滚动
     */
    public int climbStairs2(int n) {
        // a 表示 dp[n-2]，b 表示 dp[n-1]，c 表示 dp[n]，
        int a = 1;
        int b = 1;
        int c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }


    @Test
    public void test() {
        System.out.println(climbStairs2(45));
    }

}













