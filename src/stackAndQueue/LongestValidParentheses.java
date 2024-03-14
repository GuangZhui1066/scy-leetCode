package stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * 32. 最长有效括号
 */
public class LongestValidParentheses {

    /**
     * 方法一：dp
     *
     * dp[i] 表示 s 中 "以 i 结尾" 的子串的最大解
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }

        int[] dp = new int[len];
        if (s.charAt(0) == '(' && s.charAt(1) == ')') {
            dp[1] = 2;
        }

        for (int i = 2; i < len; i++) {
            if (s.charAt(i) == '(') {
                continue;
            } else {
                if (s.charAt(i-1) == '(') {
                    // 形式为：xxx()
                    dp[i] = dp[i-2] + 2;
                } else {
                    if (dp[i-1] > 0 && i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(') {
                        // 形式为：xxx ( 合法串 )
                        dp[i] = dp[i-1] + 2;
                        if (i-dp[i-1]-2 >= 0 && dp[i-dp[i-1]-2] > 0) {
                            // 形式为：xxx 合法串 ( 合法串 )
                            dp[i] += dp[i-dp[i-1]-2];
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }


    /**
     * 方法二：栈
     *
     * 从左往右遍历，如果是左括号就把下标就压入栈中，如果是右括号就弹出栈顶元素
     * 在栈底存放最后一个没有被匹配的右括号的下标
     */
    public int longestValidParentheses2(String s) {
        int len = s.length();
        int result = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                Integer pop = stack.pop();
                // 栈顶元素是 ")"，弹出后 i 变成最后一个没有被匹配的右括号
                if (pop == -1 || s.charAt(pop) == ')') {
                    stack.push(i);
                }
                // 弹出的栈顶元素是 "("，说明从栈底到 i 这段子串是一个合法的子串
                else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }

        return result;
    }


    @Test
    public void test() {
        String s = "()(())";
        //String s = "()(()";
        //String s = "(()";
        //String s = ")()())";
        //String s = "())";
        //String s = "()((()())((";

        System.out.println(longestValidParentheses(s));
    }

}








