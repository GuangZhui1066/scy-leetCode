package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

/**
 * 210. 课程表2
 */
public class CourseSchedule2 {

    /**
     * BFS
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }

        for (int[] req : prerequisites) {
            edge.get(req[1]).add(req[0]);
            inDegree[req[0]]++;
        }

        int[] order = new int[numCourses];
        int cnt = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            order[cnt] = cur;
            cnt++;

            for (Integer post : edge.get(cur)) {
                inDegree[post]--;
                if (inDegree[post] == 0) {
                    queue.offer(post);
                }
            }
        }

        if (cnt == numCourses) {
            return order;
        }
        return new int[0];
    }


    @Test
    public void test() {
        int numCourses = 4;
        int[][] prerequisites = new int[4][];
        prerequisites[0] = new int[]{1,0};
        prerequisites[1] = new int[]{2,0};
        prerequisites[2] = new int[]{3,1};
        prerequisites[3] = new int[]{3,2};

        int[] order = findOrder(numCourses, prerequisites);
        System.out.println(order);
    }

}
