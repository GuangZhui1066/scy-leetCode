package hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        // 存放 <数组元素值, 该元素在数据中的位置> 键值对，可以高效地根据数值找到对应的元素位置 —— O(1)
        Map<Integer, Integer> valIdxMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (valIdxMap.containsKey(need)) {
                return new int[] {i, valIdxMap.get(need)};
            }
            valIdxMap.put(nums[i], i);
        }
        return null;
    }

}















