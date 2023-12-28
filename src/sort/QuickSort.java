package sort;

import org.junit.Test;

/**
 * 手撕快速排序
 * 从大到小排序
 */
public class QuickSort {

    /**
     * [left ~ right] 为当前需要排序的区间
     * 每次在当前区间中选出一个元素 mid 作为基准，然后把比 mid 大的元素全部移动到比 mid 小的元素的右边
     * 然后递归地排序左边比 mid 大的元素 和 右边比 mid 小的元素
     *
     * 具体做法：
     *    用两个指针，指针 i 从 left 到 right 遍历，指针 j 从 right 到 left 遍历
     *    当 i 遍历到一个比 mid 大的元素就停下，当 j 遍历到一个比 mid 小的元素就停下，然后交换 arr[i] 和 arr[j]
     */
    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = arr[(left + right) / 2];
        int i = left, j = right;
        while (i < j) {
            while (arr[i] > mid) {
                i++;
            }
            while (arr[j] < mid) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(arr, i, j);

            if (arr[i] == mid) {
                j--;
            }
            if (arr[j] == mid) {
                i++;
            }
        }

        // 到这里时 i == j 或者 i == j + 1
        // i == j       比如: 4 3 2
        // i == j + 1   比如: 4 6 5 5, left=0, right=3, mid=6
        if (i == j + 1) {
            i--;
        }

        quickSort(arr, left, j-1);
        quickSort(arr, i+1, right);
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


    @Test
    public void test() {
        //int[] arr = new int[] {2,3,1,3,3,43,2,4,5,6,2,1,4,5,56,4,2,12,54,1,4,5532443,21,3,43,4};
        int[] arr = new int[] {3,2,3,1,2,4,5,5,6};
        int len = arr.length;

        quickSort(arr, 0, len-1);

        String result = "";
        for (int ele : arr) {
            result += ele + " ";
        }
        System.out.println(result);
    }

}





















