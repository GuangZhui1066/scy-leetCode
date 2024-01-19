package monotonicstack.common;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Test;

/**
 * 单调栈
 *
 * 问题场景：
 *   给定一个数组 arr[N]，对于每一个元素 arr[i]，求出其左边离它最近的并且比他小的元素。
 *   暴力解法的时间复杂度为 O(N^2)。单调栈解法的复杂度是 O(N).
 *
 * 定义：
 *   从栈底到栈顶，元素呈单调递增 (或单调递减) 的顺序
 *
 * 操作：
 *   当新元素满足单调性质时，将新元素入栈；
 *   当新元素不满足单调性质时，将栈顶元素循环出栈，直到新元素满足单调性质，再将新元素入栈。
 *
 */
public class MonotonicStack {

    /**
     * 返回 arr 中每个元素右边最近的、比自己大的元素的位置
     *
     * -1 表示不存在
     */
    public int[] monotonicStack(int[] arr) {
        int[] answer = new int[arr.length];
        Arrays.fill(answer, -1);

        // 单调递减栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = 1; i < arr.length; i++) {
            // 如果新元素大于栈顶，就弹出栈顶元素，直到新元素满足单调递减的顺序，再把新元素入栈
            // 对于这些被弹出栈的元素，这个新元素就是在它右边离它最近并且比它大的数
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                Integer pop = stack.pop();
                answer[pop] = arr[i];
            }
            stack.push(i);
        }

        return answer;
    }


    @Test
    public void test() {
        int[] arr = new int[]{13,14,15,11,9,12,16,13};
        int[] answer = monotonicStack(arr);
        System.out.println(answer);
    }

}
