package dp;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * 42. 接雨水
 */
public class CatchRain {

    /**
     * 方法一：动态规划
     *
     * 思路：
     *   对于 height 中的每个柱子 i，在 i 这个柱子接完雨水后的高度 = Math.min(i左边最高的柱子高度, i右边最高的柱子高度).
     *   那么在 i 这个位置上能接的雨水量 = Math.min(i左边最高的柱子高度, i右边最高的柱子高度) - height[i].
     *   因此，对每个 i 都要记录 i左边最高的柱子高度 和 i右边最高的柱子高度 —— 可以用动态规划来计算，不需要暴力
     *
     * 复杂度分析
     *   时间复杂度: 两次遍历，O(N)
     *   空间复杂度: 两个dp数组，O(N)
     */
    public int trap(int[] height) {
        int len = height.length;

        // leftMaxDp[i] 表示 [0, i] 中最高的柱子
        int[] leftMaxDp = new int[len];
        // leftMaxDp[i] 表示 [i, len-1] 中最高的柱子
        int[] rightMaxDp = new int[len];
        leftMaxDp[0] = height[0];
        rightMaxDp[len-1] = height[len-1];

        // dp
        for (int i = 1; i < len; i++) {
            leftMaxDp[i] = Math.max(leftMaxDp[i-1], height[i]);
            rightMaxDp[len-i-1] = Math.max(rightMaxDp[len-i], height[len-i-1]);
        }

        int rain = 0;
        // 累计每个柱子能接的雨水量
        for (int i = 1; i < len - 1; i++) {
            int leftMaxHeight = leftMaxDp[i];
            int rightMaxHeight = rightMaxDp[i];
            int curHeight = height[i];
            if (leftMaxHeight > curHeight && rightMaxHeight > curHeight) {
                int curRain = Math.min(leftMaxHeight, rightMaxHeight) - height[i];
                rain += curRain;
            }
        }

        return rain;
    }


    /**
     * 方法二：双指针
     *
     * 思路：
     *   用两个指针，left 从左往右遍历，right 从右往左遍历。
     *   用 leftMax 记录 left 移动过程中的最大值，用 rightMax 记录 right 移动过程中的最大值。
     *   如果当前 leftMax 比 rightMax 小，说明当前的 left 可以把雨水接到 leftMax 的高度。可以计算出 left 处接到的雨水量，然后移动 left。
     *   反之亦然。当 left 和 right 相遇时，所有的柱子都计算完成。
     *
     * 复杂度分析：
     *   时间复杂度：O(N)
     *   空间复杂度：O(1)
     */
    public int trap2(int[] height) {
        int len = height.length;
        int left = 1, right = len - 2;
        int leftMax = height[0];
        int rightMax = height[len-1];

        int rain = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            int curRain;
            if (leftMax < rightMax) {
                curRain = leftMax - height[left];
                left++;
            } else {
                curRain = rightMax - height[right];
                right--;
            }

            rain += curRain;
        }

        return rain;
    }


    /**
     * 方法三：单调栈
     *
     * 思路：
     *  从左到右遍历柱子，把高度递减的柱子压入单调递减栈中。
     *  遍历过程中如果遇到的新柱子 i 不是递减，那么 i 一定比栈顶的几个柱子高。栈顶的这几个柱子可以"暂时"接雨水接到 i 的高度。
     *  把栈顶的这几个柱子比 i 低的柱子弹出后，继续把 i 压入栈中。这时栈中的柱子仍然是递减的。
     *  把柱子遍历完后计算完毕。
     *
     * 复杂度分析
     *   时间复杂度: O(N)
     *   空间复杂度: 最差情况下所有柱子都入栈，为 O(N)
     */
    public int trap3(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int rain = 0;

        for (int i = 0; i < height.length; i++) {
            int curHeight = height[i];
            while (!stack.isEmpty() && curHeight > height[stack.peek()]) {
                // 栈顶柱子
                Integer pop = stack.pop();
                if (!stack.isEmpty()) {
                    // 栈顶柱子左边第一个比它高的柱子
                    Integer left = stack.peek();

                    // 能接的雨水高度
                    int curRainHeight = Math.min(curHeight, height[left]) - height[pop];
                    // 能接的雨水宽度
                    int curRainWidth = i - left - 1;
                    rain += curRainHeight * curRainWidth;
                }
            }
            stack.push(i);
        }

        return rain;
    }


    @Test
    public void test() {
        int[] height1 = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height2 = new int[]{4,2,0,3,2,5};

        int rain = trap3(height1);
        System.out.println(rain);
    }

}












