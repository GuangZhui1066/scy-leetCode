import org.junit.Test;

/**
 * 31. 下一个排列
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int len = nums.length;

        // left 表示从后往前第一个降序的数字的位置
        // 比如 1, 3, 8, 6, 4, 2 中，left = 1 (对应数字是3)。需要把 3 和后面比 3 大的数字交换位置，选取比 3 大的数字中最小的数字进行交换，即 4。
        int left = -1;
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i-1]) {
                left = i - 1;
                break;
            }
        }

        // 如果当前的排列从头到尾全都是降序的 (比如 8 6 4 3 2 1)，就说明这已经是字典序最大的排列，将数组翻转得到字典序最小的排列，返回
        if (left == -1) {
            for (int i = 0; i < len / 2; i++) {
                swap(nums, i, len-i-1);
            }
            return;
        }

        // 找到 left 之后的、并且比 left 大的数字中最小的数字的位置 —— right
        // 比如 1, 3, 8, 6, 4, 2 中，right = 4 (数字是4)
        int right;
        for (right = left + 1; right < len-1; right++) {
            if (nums[right+1] <= nums[left]) {
                break;
            }
        }
        // 交换 left 和 right
        // 1 3 8 6 4 2 --> 1 4 8 6 3 2
        swap(nums, left, right);

        // 此时 (left, end] 这部分是降序的，翻转，使其变成升序 (1 4 8 6 3 2 --> 1 4 2 3 6 8)
        for (int i = 0; i < (len-1-left) / 2; i++) {
            swap(nums, left + 1 + i, len - 1 -i);
        }
      }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    @Test
    public void test() {
        //int[] nums = new int[] {1, 2, 8, 5, 3};
        int[] nums = new int[] {1, 3, 8, 6, 4, 2};
        //int[] nums = new int[] {2, 3, 1};
        //int[] nums = new int[] {1, 2, 3};

        nextPermutation(nums);
        System.out.println(nums);
    }

}



















