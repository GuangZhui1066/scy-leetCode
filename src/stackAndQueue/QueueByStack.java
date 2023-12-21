package stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232. 用栈实现队列
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class QueueByStack {

    // 按照栈顺序 (后进先出) 依次压入 push 进来的元素
    Deque<Integer> orderStack;
    // 将 orderStack 中的元素依次 pop 出来并压入，因此 reverseStack 中的元素是队列顺序 (先进先出) 的
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
