package interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Test;

/**
 * 253. 会议室2
 *
 * 题目：
 *   给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回所需会议室的最小数量
 *
 * 示例 1：
 *   输入：intervals = [[0,30], [5,10], [15,20]]
 *   输出：2
 */
public class MeetingRoom2 {

    public int minMeetingRooms(int[][] intervals) {
        // 按照会议开始时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 最小堆，存放每个会议的结束时间。堆顶是最早结束的会议
        // 当某个会议开始时，把这个会议的结束时间添加到堆中；当这个会议结束时，把这个会议从堆中移除。
        // 因此堆中存放的都是还在进行的会议，堆中元素的数量就是当前需要的会议室的数量
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // 第一场会议开始，往堆中添加第一场会议的结束时间
        minHeap.add(intervals[0][1]);
        int maxHeapSize = 1;

        // 会议依次开始
        for (int i = 1; i < intervals.length; i++) {
            // 当前开始的会议
            int[] interval = intervals[i];
            int curMeetStart = interval[0];
            int curMeetEnd = interval[1];

            // 堆顶的会议 (最早结束的会议) 已经结束，把它从堆中移除 (释放它的会议室)
            if (curMeetStart >= minHeap.peek()) {
                minHeap.remove();
            }
            // 把当前开始的会议添加到堆中
            minHeap.add(curMeetEnd);

            maxHeapSize = Math.max(maxHeapSize, minHeap.size());
        }

        return maxHeapSize;
    }


    @Test
    public void test() {
        int[][] intervals = new int[4][];
        intervals[0] = new int[]{0,30};
        intervals[1] = new int[]{5,10};
        intervals[2] = new int[]{15,20};
        intervals[3] = new int[]{8,18};

        int result = minMeetingRooms(intervals);
        System.out.println(result);
    }

}









