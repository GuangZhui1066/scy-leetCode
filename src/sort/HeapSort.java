package sort;

import org.junit.Test;

/**
 * 堆排序
 *
 * 堆也是二叉树。最大堆就是父节点大于子节点，根节点是最大的元素
 * 堆和二叉查找树不同：
 *    二叉查找树是：左子节点比父节点小，右子节点比父节点大
 *    最大堆是：左右子节点都比父节点小 (左右子节点之间没有大小关系)
 *
 * 对于二叉数，如果根节点的编号 = 0，左子节点的编号 = 父节点编号 * 2 + 1，右子节点的编号 = 父节点编号 * 2 + 2
 * 因此，可以用数组来实现堆
 */
public class HeapSort {

    public void heapSort(int[] arr) {
        int[] heap = buildMaxHeap(arr, arr.length);
        StringBuilder result = new StringBuilder();

        int size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int curMax = heap[1];
            result.append(curMax).append(" ");

            size = removeMax(heap, size);
        }

        System.out.println(result);
    }


    /**
     * 构建最大堆：
     *    先将待排序的数组放入一颗完全二叉树中，然后开始调整堆。
     *    从最后一个父节点开始调整：比较此父节点与其子节点中的最大者，如果父节点小，就交换父节点与此子节点最大者，然后继续递归调整刚才交换的子节点
     *    然后继续调整前一个父节点，直到根节点
     */
    public int[] buildMaxHeap(int[] nums, int size) {
        // 将数组中的元素改为从 1 开始编号，存入 heap[] 数组，其中元素下标为 [1 ~ size]
        int[] heap = new int[size+1];
        for (int i = 0; i < size; i++) {
            heap[i+1] = nums[i];
        }

        // 调整堆
        for (int i = size / 2; i >= 1; i--) {
            // i 表示当前调整的父节点
            // 最后一个子节点的下标是 size，最后一个父节点的下标是 size / 2
            maxHeapify(heap, size, i);
        }

        return heap;
    }

    public void maxHeapify(int[] heap, int size, int i) {
        // 没有子节点
        if (i * 2 > size) {
            return;
        }

        // left 为当前父节点的左子节点，right 是右子节点
        int left = i * 2, right = i * 2 + 1;
        int biggerChild = left;
        if (right <= size && heap[right] > heap[left]) {
            biggerChild = right;
        }

        // 如果子节点大于父节点，就交换，然后调整子节点
        if (heap[i] < heap[biggerChild]) {
            int temp = heap[i];
            heap[i] = heap[biggerChild];
            heap[biggerChild] = temp;

            maxHeapify(heap, size, biggerChild);
        }
    }

    /**
     * 删除最大元素：
     *    把最后一个元素和根节点交换，把 size 减一，然后调整根节点
     *    返回删除后的 size
     */
    private int removeMax(int[] heap, int size) {
        int maxEle = heap[1];
        heap[1] = heap[size];
        heap[size] = maxEle;

        size--;
        maxHeapify(heap, size, 1);

        return size;
    }


    @Test
    public void testSize() {
        int[] arr = new int[] {3,2,3,1,2,4,5,5,6};
        int[] arr2 = new int[] {2,3,1,3,3,43,2,4,5,6,2,1,4,5,56,4,2,12,54,1,4,68,21,3,43,4};
        int[] arr3 = new int[] {3,1,2,4};

        heapSort(arr);
        heapSort(arr2);
        heapSort(arr3);
    }

}











