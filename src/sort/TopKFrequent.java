package sort;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * 347. 前K个高频元素
 */
public class TopKFrequent {

    /**
     * 建一个容量为 k 的最小堆
     * 堆中保存的元素就是【当前最大的 k 个元素】，堆顶就是这最大的 k 个元素中最小的
     * 向堆中添加元素时，
     *    如果要添加的元素小于堆顶元素，就说明要添加的元素比堆中这 k 个元素都小，不需要添加进堆
     *    如果要添加的元素大于堆顶元素，就把原来这个 k 个元素中最小的 (即堆顶) 删除，然后把新元素添加进堆中
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int num : nums) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return cntMap.get(a) - cntMap.get(b);
            }
        });

        for (Integer num : cntMap.keySet()) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else {
                if (cntMap.get(minHeap.peek()) < cntMap.get(num)) {
                    minHeap.remove();
                    minHeap.add(num);
                }
            }
        }

        int[] topK = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            topK[i++] = minHeap.remove();
        }

        return topK;
    }

    @Test
    public void test() {
        int[] arr = new int[] {6,6,6,7,7,8};
        int k = 2;

        int[] rst = topKFrequent(arr, k);
        String res = "";
        for (int i : rst) {
            res += i + " ";
        }
        System.out.println(res);
    }

}













