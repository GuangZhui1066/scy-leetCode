package sort.common;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapClass {

    /**
     * 优先队列（最大堆 / 最小堆）
     * 用 PriorityQueue 表示
     */
    public static void main(String[] args) {

        // 最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });

        // 最大堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        // 添加元素
        minHeap.add(3);
        minHeap.add(4);
        minHeap.add(1);
        minHeap.add(5);
        minHeap.add(7);

        // 查看堆顶元素
        System.out.println(minHeap.peek());

        // 删除堆顶元素
        System.out.println(minHeap.remove());

        // 删除堆中某元素
        System.out.println(minHeap.remove(3));

    }

}
