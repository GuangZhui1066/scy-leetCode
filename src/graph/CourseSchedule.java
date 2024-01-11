package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 207. 课程表
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];

        // 用二维列表代替二维数组，来表示图中的边。节省时间和空间
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }

        for (int[] req : prerequisites) {
            edge.get(req[1]).add(req[0]);
            inDegree[req[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            cnt++;
            for (Integer post : edge.get(cur)) {
                inDegree[post]--;
                if (inDegree[post] == 0) {
                    queue.offer(post);
                }
            }
        }

        return cnt == numCourses;
    }

}














