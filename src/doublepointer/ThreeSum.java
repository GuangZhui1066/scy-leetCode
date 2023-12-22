package doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 */
public class ThreeSum {

    /**
     * 双指针法
     *
     *   先将数组排序，然后把 i 从左往右遍历。
     *   对于每个 i，把 j 和 k 分别作为两头的指针，根据三数之和的大小向中间移动。
     *   i, j, k 在移动时，注意要跳过重复的值
     *
     *   图示如下：
     *   nums:  [-4,  -1,  -1,  0,  1,  2]
     *                 i   j ->      <- k
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int jkSum = nums[j] + nums[k];
                if (jkSum > -nums[i]) {
                    k--;
                } else if (jkSum < -nums[i]) {
                    j++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j+1] == nums[j]) {
                        j++;
                    }
                    while (j < k && nums[k-1]==nums[k]) {
                        k--;
                    }
                    j++;
                    k--;
                }
            }

        }

        return result;
    }

}







//        sort(nums.begin(), nums.end());
//        vector<vector<int> > ret;
//        int n = nums.size();
//        for(int i=0; i<n-2; i++) {
//            if(nums[i] > 0)
//                break;
//            if(i>0 && nums[i]==nums[i-1])
//                continue;
//            int left = i + 1;
//            int right = n - 1;
//            while(left < right) {
//                if((nums[i]+nums[left]+nums[right]) < 0) {
//                    left++;
//                } else if((nums[i]+nums[left]+nums[right]) > 0) {
//                    right--;
//                } else {
//                    ret.push_back(vector<int>{nums[i], nums[left], nums[right]});
//                    while(left<right && nums[left]==nums[left+1])
//                        left++;
//                    while(left<right && nums[right]==nums[right-1])
//                        right--;
//                    left++;
//                    right--;
//                }
//            }
//        }
//        return ret;
//    }
//};

/*
vector<vector<int>> result;
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] > 0) {
                return result;
            }
            // 正确去重方法
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.size() - 1;
            while (right > left) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    result.push_back(vector<int>{nums[i], nums[left], nums[right]});
                    // 去重逻辑应该放在找到一个三元组之后
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    // 找到答案时，双指针同时收缩
                    right--;
                    left++;
                }
            }

        }
        return result;
*/












