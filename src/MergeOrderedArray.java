import org.junit.Test;

/**
 * 88. 合并两个有序数组
 */
public class MergeOrderedArray {

    /**
     * 方法一：双指针
     *
     * 用双指针，把两个有序数组中的元素合并到一个新数组中
     * 然后把新数组的值赋给 nums1
     *
     * 空间复杂度：O(m+n)
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
    }


    /**
     * 方法二：逆向双指针
     *
     * 用双指针，倒序的把两个有序数组中的元素合并到 nums1 中
     * 因为是从后往前写 nums1，所以不会对 nums1 中的元素进行覆盖
     *
     * 空间复杂度：O(1)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int index = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[index--] = nums1[p1];
                p1--;
            } else {
                nums1[index--] = nums2[p2];
                p2--;
            }
        }

        if (p2 >= 0) {
            for (int i = index; i >= 0; i--) {
                nums1[i] = nums2[p2--];
            }
        }
    }


    @Test
    public void test() {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int m = 3, n = 3;

        merge(nums1, m, nums2, n);
    }
}
