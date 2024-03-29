import org.junit.Test;

/**
 * LCR 120. 寻找文件副本
 *
 * 题目：
 *   在一个长度为 n 的数组 nums 里，所有元素都在 0 ～ n-1 的范围内。
 *   数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 *   请找出数组中任意一个重复的数字。
 *
 * 要求：空间复杂度 O(1)
 */
public class FindRepeatDocument {

    /**
     * 思路一：
     *   用 HashMap 存储每个数字的值和位置的对应关系，然后遍历查找每个数字有没有出现过。
     *   但是空间复杂度 O(N)
     *
     * 思路二：
     *   遍历，不断交换元素，使得 documents[0] == 0, documents[1] == 1, documents[2] == 2 ...
     */
    public int findRepeatDocument(int[] documents) {
        int len = documents.length;

        for (int i = 0; i < len; i++) {
            while (documents[i] != i) {
                // 数字重复
                if (documents[documents[i]] == documents[i]) {
                    return documents[i];
                }
                // 交换 i 和 documents[i] 位置上的元素
                else {
                    int temp = documents[i];
                    documents[i] = documents[temp];
                    documents[temp] = temp;
                }
            }
        }

        return -1;
    }


    @Test
    public void test() {
        int[] documents = new int[]{2,5,3,0,5,0};
        //int[] documents = new int[]{3,4,2,1,1,0};

        System.out.println(findRepeatDocument(documents));
    }

}
