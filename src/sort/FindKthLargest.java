package sort;

import org.junit.Test;

/**
 * 215. 数组中的第K个最大元素
 */
public class FindKthLargest {

    /**
     * 方法一：快速排序
     *
     * 每次排序成左右两部分后，根据左右两部分的长度判断第k大的数在左边还是右边，然后只选一边继续排序
     * 在随机数据下，复杂度为 O(N)
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        return quickSort(nums, 0, len - 1, k);
    }

    public int quickSort(int[] nums, int left, int right, int k) {
        int i = left, j = right;
        int mid = nums[(left + right) / 2];
        while (i < j) {
            while (nums[i] > mid) {
                i++;
            }
            while (nums[j] < mid) {
                j--;
            }
            if (i == j) {
                break;
            }

            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;

            if (nums[i] == mid) {
                j--;
            }
            if (nums[j] == mid) {
                i++;
            }
        }

        if (i == j + 1) {
            i--;
        }

        if (k == (i-left) + 1) {
            return nums[i];
        } else if (k > (i-left) + 1) {
            return quickSort(nums, i+1, right, k-(i-left)-1);
        } else {
            return quickSort(nums, left, i-1, k);
        }
    }



    /**
     * 方法二：堆排序
     */
    public int findKthLargest2(int[] nums, int k) {
        int size = nums.length;
        buildMaxHeap(nums, size);

        for (int i = 0; i < k - 1; i++) {
            nums[0] = nums[size - 1];
            size--;
            maxHeapify(nums, size, 0);
        }
        return nums[0];
    }

    private void buildMaxHeap(int[] nums, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, size, i);
        }
    }

    private void maxHeapify(int[] nums, int size, int i) {
        int left = i * 2 + 1, right = i * 2 + 2;
        if (left >= size) {
            return;
        }

        int larggerChild = left;
        if (right < size && nums[right] > nums[left]) {
            larggerChild = right;
        }

        if (nums[larggerChild] > nums[i]) {
            int temp = nums[i];
            nums[i] = nums[larggerChild];
            nums[larggerChild] = temp;

            maxHeapify(nums, size, larggerChild);
        }
    }


    @Test
    public void test() {
        int[] arr = new int[] {3,1,2,4};
        int k = 2;

        System.out.println(findKthLargest2(arr, k));
    }

}






















