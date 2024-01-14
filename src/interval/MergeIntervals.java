package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * 56. 合并区间
 */
public class MergeIntervals {

    /**
     * 先按照区间的左端点将所有区间排序，排序之后能合并的区间一定是连续的
     *
     * 时间复杂度：排序的复杂度为 O(N*logN)，排序之后遍历的复杂度为 O(N)，因此整体的复杂度为 O(N*logN)
     */
    public int[][] merge(int[][] intervals) {
        // 把区间按照左端点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        // 上一个区间的左右端点
        int lastItvLeft = intervals[0][0], lastItvRight = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];

            // 可以合并
            if (interval[0] <= lastItvRight) {
                lastItvRight = Math.max(lastItvRight, interval[1]);
            }
            // 不能合并
            else {
                result.add(new int[]{lastItvLeft, lastItvRight});
                lastItvLeft = interval[0];
                lastItvRight = interval[1];
            }
        }
        result.add(new int[]{lastItvLeft, lastItvRight});

        return result.toArray(new int[result.size()][]);
    }


    @Test
    public void test() {
        int[][] intervals = new int[4][];
        intervals[0] = new int[]{1,3};
        intervals[1] = new int[]{2,6};
        intervals[2] = new int[]{8,10};
        intervals[3] = new int[]{15,18};

        int[][] result = merge(intervals);
        System.out.println(result);
    }

}






