package dp;

/**
 * 53. 最大子数组和
 */
public class MaxSubArray {

    /**
     * 动态规划方程：
     * f(i) 表示 nums 所有以第 i 个元素结尾的子数组中最大的和
     *
     *           /  nums[i]                 f(i-1) <= 0
     *   f(i) =
     *           \  nums[i] + f(i-1)        f(i-1) > 0
     */
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int maxSum = nums[0];

        int [] maxSubSum = new int[len];
        maxSubSum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            maxSubSum[i] = nums[i] + Math.max(maxSubSum[i-1], 0);
            maxSum = Math.max(maxSum, maxSubSum[i]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

}
























