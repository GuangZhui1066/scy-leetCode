package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 47. 全排列2
 */
public class AllPermutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        boolean[] visited = new boolean[len];

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> onePermutation = new ArrayList<>();

        // 对输入数组排序
        Arrays.sort(nums);

        dfs(nums, visited, len, 1, onePermutation, result);

        return result;
    }

    /**
     * 在原本的 DFS 遍历过程中做一些剪枝操作
     *
     * 以 1 开头的排列遍历树：
     *
     *             1
     *        /   ｜                       \
     *       2   2(同一层相同的数字不再遍历)    3
     *      / \                           / \
     *     2  3                          2   2 (同一层相同的数字不再遍历)
     *     ｜ ｜                         ｜
     *     3  2                          2
     */
    private void dfs(int[] nums, boolean[] visited, int len, int step, List<Integer> onePermutation, List<List<Integer>> result) {
        if (step > len) {
            result.add(new ArrayList<>(onePermutation));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i-1] && visited[i-1]) {
                continue;
            }

            visited[i] = true;
            onePermutation.add(nums[i]);
            dfs(nums, visited, len, step+1, onePermutation, result);

            visited[i] = false;
            onePermutation.remove(step-1);
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{1,2,2,3};
        permuteUnique(nums);
    }

}














