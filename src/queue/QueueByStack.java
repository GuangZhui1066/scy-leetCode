package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232. 用栈实现队列
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class QueueByStack {

    Deque<Integer> orderStack;
    Deque<Integer> reverseStack;

    public QueueByStack() {
        orderStack = new ArrayDeque<>();
        reverseStack = new ArrayDeque<>();
    }

    public void push(int x) {
        orderStack.push(x);
    }

    public int pop() {
        if (!reverseStack.isEmpty()) {
            return reverseStack.pop();
        }
        while (!orderStack.isEmpty()) {
            reverseStack.push(orderStack.pop());
        }
        return reverseStack.pop();
    }

    public int peek() {
        if (!reverseStack.isEmpty()) {
            return reverseStack.peek();
        }
        while (!orderStack.isEmpty()) {
            reverseStack.push(orderStack.pop());
        }
        return reverseStack.peek();
    }

    public boolean empty() {
        return orderStack.isEmpty() && reverseStack.isEmpty();
    }

}
