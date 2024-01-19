package monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Test;

/**
 * 84. 柱状图中最大的矩形
 */
public class LargestRectangleArea {

    /**
     * 遍历每个柱子，以每个柱子的高度为矩形的高，求出此矩形的面试
     * 从遍历的结果中选出最大值
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;

        // 找到每个柱子 左边比自己矮的柱子中最近的柱子的位置
        // -1 表示左边没有比自己矮的
        int[] leftNearestLower = new int[len];
        Arrays.fill(leftNearestLower, -1);
        // 找到每个柱子 右边比自己矮的柱子中最近的柱子的位置
        // len 表示右边没有比自己矮的
        int[] rightNearestLower = new int[len];
        Arrays.fill(rightNearestLower, len);

        // 单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                Integer pop = stack.pop();
                rightNearestLower[pop] = i;
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                Integer pop = stack.pop();
                leftNearestLower[pop] = i;
            }
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            int curHeight = heights[i];
            // 以当前柱子的高度为矩形的高，所能形成的矩形的最大宽度
            int curWidth = rightNearestLower[i] - leftNearestLower[i] - 1;
            maxArea = Math.max(maxArea, curHeight * curWidth);
        }

        return maxArea;
    }


    @Test
    public void test() {
        int[] heights = new int[]{2,1,5,6,2,3};
        int area = largestRectangleArea(heights);
        System.out.println(area);
    }

}










