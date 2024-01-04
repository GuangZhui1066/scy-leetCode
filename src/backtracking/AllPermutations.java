package backtracking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 46. 全排列
 */
public class AllPermutations {

    /**
     * DFS + 回溯
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;

        boolean[] visited = new boolean[len];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> onePermutation = new ArrayList<>();

        dfs(nums, visited, len, 1, onePermutation, result);

        return result;
    }

    private void dfs(int[] nums, boolean[] visited, int len, int step, List<Integer> onePermutation, List<List<Integer>> result) {
        if (step > len) {
            result.add(new ArrayList<>(onePermutation));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                onePermutation.add(nums[i]);
                dfs(nums, visited, len, step+1, onePermutation, result);

                // 回溯
                visited[i] = false;
                onePermutation.remove(step-1);
            }
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{1,2,3};
        permute(nums);
    }

}
















