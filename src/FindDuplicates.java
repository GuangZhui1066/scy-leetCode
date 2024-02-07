import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 442. 数组中重复的数据
 *
 * 要求：空间复杂度 O(1)，时间复杂度 O(N)
 */
public class FindDuplicates {

    /**
     * 参考 LCR 120.
     * 不断交换元素，使得 nums[0] == 1, nums[1] == 2, nums[2] == 3 ...
     */
    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i]-1]) {
                int temp = nums[i];
                nums[i] = nums[temp-1];
                nums[temp-1] = temp;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }

        return result;
    }


    @Test
    public void test() {
        //int[] documents = new int[]{3,4,2,1,1,5};
        int[] documents = new int[]{4,5,3,2,2,1};

        System.out.println(findDuplicates(documents));
    }

}
