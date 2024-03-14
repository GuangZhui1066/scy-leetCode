import java.util.Random;

/**
 * 384. 打乱数组
 */
public class ShuffleArray {

    /**
     * Knuth 洗牌算法
     *   从前往后遍历数组，对于元素 i，从 [i, len-1] 中随机选择一个元素与 i 交换
     */

    int[] nums;
    int len;
    Random random;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        len = this.nums.length;
        random = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int[] clone = nums.clone();
        for (int i = 0; i < len; i++) {
            int swap = i + random.nextInt(len-i);
            int temp = clone[i];
            clone[i] = clone[swap];
            clone[swap] = temp;
        }
        return clone;
    }

}
