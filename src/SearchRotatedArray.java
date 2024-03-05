import org.junit.Test;

/**
 * 33. 搜索旋转排序数组
 */
public class SearchRotatedArray {

    /**
     * 二分查找
     *
     * 每次二分出来的两个区间，一定有一个是递增的。
     * 如果 target 属于这个递增的范围内，就在这个递增的区间中查找；否则就在另一个区间中查找
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // 左边的区间是递增的
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 右边的区间是递增的
            else {
                if (nums[mid] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    @Test
    public void test() {
        //int[] nums = {4,5,6,7,0,1,2};
        //int target = 0;
        //int target = 3;

        //int[] nums = {1};
        //int target = 0;

        int[] nums = {5,1,3};
        int target = 5;

        System.out.println(search(nums, target));
    }

}
