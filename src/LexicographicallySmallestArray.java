import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * 2948. 交换得到字典序最小的数组
 */
public class LexicographicallySmallestArray {

    /**
     * 分组排序
     *
     * 把 nums 分组，每组的上下界都与其他组的上下界相差 limit 以上
     * 然后把每个组在各自的组内按从小到大排序即可
     *
     * 比如数组 nums = [1,7,6,18,2,1], limit = 3
     * 分组后为 [1,x,x,x,2,1] 和 [x,7,6,18,x]
     * 把每个组在组内排序，得到 [1,x,x,x,1,2] 和 [x,6,7,18,x]
     * 所以最终结果为 [1,6,7,18,1,2]
     */
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int len = nums.length;

        // 对 nums 中的数字排序，排序后 sortIdx[i] 的值表示在 nums 数组中第 i 小的元素的位置
        Integer[] sortIdx = new Integer[len];
        for (int i = 0; i < len; i++) {
            sortIdx[i] = i;
        }
        Arrays.sort(sortIdx, new Comparator<Integer>() {
            @Override
            public int compare(Integer idx1, Integer idx2) {
                return nums[idx1] - nums[idx2];
            }
        });

        // 分组，组内排序
        int[] result = new int[len];
        // 每个组的左右边界
        int left = 0, right;
        for (int i = 1; i < len; i++) {
            if (nums[sortIdx[i]] - nums[sortIdx[i-1]] > limit) {
                // 当前元素 i 与上一组的最大值相差大于 limit，因此当前元素 i 属于下一组
                right = i;
                // 对上一组中的元素在 nums 数组中的位置进行排序
                int lastGroupLen = right - left;
                int[] curGroupIdx = new int[lastGroupLen];
                for (int j = 0; j < lastGroupLen; j++) {
                    curGroupIdx[j] = sortIdx[i-lastGroupLen+j];
                }
                Arrays.sort(curGroupIdx);
                // 把这一组排序好后的结果写入 result 对应的位置上
                for (int j = 0; j < lastGroupLen; j++) {
                    result[curGroupIdx[j]] = nums[sortIdx[i-lastGroupLen+j]];
                }

                left = i;
            }
        }
        // 对最后一组进行排序
        right = len;
        int lastGroupLen = right - left;
        int[] curGroupIdx = new int[lastGroupLen];
        for (int j = 0; j < lastGroupLen; j++) {
            curGroupIdx[j] = sortIdx[len-lastGroupLen+j];
        }
        Arrays.sort(curGroupIdx);
        for (int j = 0; j < lastGroupLen; j++) {
            result[curGroupIdx[j]] = nums[sortIdx[len-lastGroupLen+j]];
        }

        return result;
    }


    /**
     * 暴力解法，会超时
     */
    public int[] lexicographicallySmallestArray2(int[] nums, int limit) {
        int len = nums.length;

        while (true) {
            int swapCnt = 0;
            // 对每个元素，找到该元素后面最小的、并且与该元素的差值小于 limit 的元素，然后交换这两个元素
            for (int i = 0; i < len - 1; i++) {
                int postSmallest = i;
                for (int j = i + 1; j < len; j++) {
                    if (nums[j] < nums[i] && nums[i] - nums[j] <= limit) {
                        postSmallest = j;
                    }
                }
                if (i != postSmallest) {
                    swapCnt++;
                    swap(nums, i, postSmallest);
                }
            }
            if (swapCnt == 0) {
                break;
            }
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    @Test
    public void test() {
        int[] nums = {4,52,38,59,71,27,31,83,88,10};
        int limit = 14;

        System.out.println(lexicographicallySmallestArray(nums, limit));
    }

}
