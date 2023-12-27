package sort;

import org.junit.Test;

/**
 * 手撕快速排序
 */
public class QuickSort {

    /**
     * [left ~ right] 为当前需要排序的区间
     * 每次在当前区间中选出最中间位置的元素 mid 作为基准，然后把比 arr[mid] 大的元素全部移动到 mid 左边，把比 arr[mid] 小的元素全部移动到 mid 右边
     * 然后递归地排序 mid 左边的部分和 mid 右边的部分
     *
     * 具体做法：
     *    用两个指针，一个从 left 到 mid 遍历，一个从 right 到 mid 遍历
     *    当 left 遍历到一个比 arr[mid] 大的元素就停下，当 right 遍历到一个比 arr[mid] 小的元素就停下，然后交换 arr[left] 和 arr[right]
     */
    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        int i = left, j = right;
        while (i < j) {
            while (arr[i] <= arr[mid] && i < mid) {
                i++;
            }
            while (arr[j] >= arr[mid] && j > mid) {
                j--;
            }
            swap(arr, i, j);

            if (i == mid) {
                mid = j;
                i++;
            } else if (j == mid) {
                mid = i;
                j--;
            } else {
                i++;
                j--;
            }
        }

        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


    @Test
    public void test() {
        int[] arr = new int[] {5,2,3,1,4,2,4,5,7,3,13,554};
        int len = arr.length;

        quickSort(arr, 0, len-1);

        String result = "";
        for (int ele : arr) {
            result += ele + " ";
        }
        System.out.println(result);
    }

}





















