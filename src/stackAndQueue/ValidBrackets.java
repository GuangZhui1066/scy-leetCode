package stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * 20. 有效的括号
 */
public class ValidBrackets {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        if (stack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    @Test
    public void test() {
        String s = "([]{[]})()[]";
        System.out.println(isValid(s));
    }

}












