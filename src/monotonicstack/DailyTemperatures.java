package monotonicstack;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * 739. 每日温度
 */
public class DailyTemperatures {

    /**
     * 单调栈
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] answer = new int[len];

        // 单调递减栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (int i = 1; i < len; i++) {
            // 如果新元素大于栈顶，就弹出栈顶元素，直到新元素满足单调递减的顺序，再把新元素入栈
            // 对于这些被弹出栈的元素，这个新元素就是在它右边离它最近并且比它大的数
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                Integer pop = stack.pop();
                answer[pop] = i - pop;
            }
            stack.push(i);
        }

        return answer;
    }


    @Test
    public void test() {
        int[] temperatures = new int[]{73,74,75,71,69,72,76,73};
        int[] answer = dailyTemperatures(temperatures);
        System.out.println(answer);
    }

}
















