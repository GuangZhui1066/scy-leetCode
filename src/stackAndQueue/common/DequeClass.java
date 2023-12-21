package stackAndQueue.common;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeClass {

    /**
     * Deque 双端队列
     *      可以作为队列使用
     *          offer() —— 向队列尾部添加元素
     *          poll()  —— 从队列头部移除元素
     *          peek()  —— 查看队列头部元素
     *      也可以作为栈使用
     *          push()   —— 向栈顶压入元素
     *          pop()    —— 从栈顶弹出元素
     *          peek()   —— 查看栈顶元素
     *
     * LinkedList 和 ArrayDeque 都实现了 Deque 接口
     *      ArrayDeque 性能更好
     */

    public static void main(String[] args) {

        // 用作栈
        Deque<String> stack = new ArrayDeque<>();

        stack.push("C++");
        stack.push("Java");
        stack.push("Go");

        System.out.println("Deque As Stack :");
        System.out.println(stack.peek());

        System.out.println(stack.pop());

        System.out.println(stack.peek());


        // 用作队列
        Deque<String> queue = new ArrayDeque<>();

        queue.offer("C++");
        queue.offer("Java");
        queue.offer("Go");

        System.out.println("Deque As Queue :");
        System.out.println(queue.peek());

        System.out.println(queue.poll());

        System.out.println(queue.peek());

    }



}
