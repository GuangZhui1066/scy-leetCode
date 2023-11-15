package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * https://leetcode.cn/problems/implement-stack-using-queues/
 */
public class StackByQueue {

    /**
     * 方法一：
     *   每次 pop 的时候，将前 n-1 个元素移动到临时队列中，只留下栈顶元素
     */
    //// 按顺序保存 push 进来的元素
    //Queue<Integer> pushQueue;
    //// 保存 pushQueue 中除了队尾元素 (也就是要实现的栈的栈顶元素) 之外的其他元素
    //Queue<Integer> tempQueue;
    //
    //public StackByQueue() {
    //    pushQueue = new LinkedList<>();
    //    tempQueue = new LinkedList<>();
    //}
    //
    //public void push(int x) {
    //    pushQueue.add(x);
    //}
    //
    //public int pop() {
    //    // 将 pushQueue 中除了最后一个元素之外的元素，按顺序加入 tempQueue
    //    // pushQueue 中只保留队尾元素，即最后 push 进来的元素
    //    while (pushQueue.size() > 1) {
    //        tempQueue.offer(pushQueue.poll());
    //    }
    //    Integer popElement = pushQueue.poll();
    //    // tempQueue 成为新的 pushQueue，tempQueue 清空
    //    pushQueue = tempQueue;
    //    tempQueue = new LinkedList<>();
    //    return popElement;
    //}
    //
    //public int top() {
    //    while (pushQueue.size() > 1) {
    //        tempQueue.offer(pushQueue.poll());
    //    }
    //    Integer topElement = pushQueue.peek();
    //    tempQueue.offer(pushQueue.poll());
    //    pushQueue = tempQueue;
    //    tempQueue = new LinkedList<>();
    //    return topElement;
    //}
    //
    //public boolean empty() {
    //    return pushQueue.isEmpty();
    //}



    /**
     * 方法二：
     *   leetcode 官方题解
     */
    // 每次 push 时，将新 push 进来的元素放在队头，之前 push 进来的元素按照按照倒序排在队尾
    Queue<Integer> pushQueue;
    // 按栈顺序 (后进先出) 存放之前压入的元素
    Queue<Integer> reverseQueue;

    public StackByQueue() {
        pushQueue = new LinkedList<>();
        reverseQueue = new LinkedList<>();
    }

    // 每次 push 之前和 push 之后， pushQueue 都为空
    public void push(int x) {
        pushQueue.offer(x);
        while (!reverseQueue.isEmpty()) {
            pushQueue.offer(reverseQueue.poll());
        }
        reverseQueue = pushQueue;
        pushQueue = new LinkedList<>();
    }

    public int pop() {
        return reverseQueue.poll();
    }

    public int top() {
        return reverseQueue.peek();
    }

    public boolean empty() {
        return reverseQueue.isEmpty();
    }

}
