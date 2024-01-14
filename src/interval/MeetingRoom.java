package interval;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * 252. 会议室
 *
 * 题目：
 *   给定一个会议时间安排的数组 intervals，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi]，请你判断一个人是否能够参加这里面的全部会议
 *
 * 示例 1：
 *   输入: intervals = [[0,30],[5,10],[15,20]]
 *   输出: false
 *
 * 示例 2：
 *   输入: intervals = [[7,10],[2,4]]
 *   输出: true
 */
public class MeetingRoom {

    public boolean canAttendMeetings(int[][] intervals) {
        // 按照会议开始时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int lastMeetEnd = 0;
        for (int[] interval : intervals) {
            if (interval[0] < lastMeetEnd) {
                return false;
            }
            lastMeetEnd = interval[1];
        }

        return true;
    }


    @Test
    public void test() {
        int[][] intervals = new int[3][];
        intervals[0] = new int[]{0,5};
        intervals[1] = new int[]{5,10};
        intervals[2] = new int[]{15,20};

        boolean result = canAttendMeetings(intervals);
        System.out.println(result);
    }

}







