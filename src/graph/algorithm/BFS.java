package graph.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class BFS {

    public static void main(String[] args) {
        int[][] graph = {{1, 1, 0, 0, 0},
                         {1, 1, 0, 0, 0},
                         {0, 0, 1, 0, 0},
                         {0, 0, 0, 1, 1}};

        int height = graph.length;
        int width = graph[0].length;

        // 记录每个位置的元素是否已经访问过。有时可以用 graph 数组本身来表示
        boolean[][] visited = new boolean[height][width];

        bfs(graph, visited, 0, 0);
    }

    /**
     * 对于二维数组表示的图 的 BFS 算法
     */
    public static void bfs(int[][] graph, boolean[][] visited, int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] index = queue.poll();
            int curi = index[0];
            int curj = index[1];

            if (!inGraph(graph, curi, curj) || visited[curi][curj]) {
                continue;
            }

            visited[curi][curj] = true;
            System.out.println(("i: " + curi + ", j: "+ curj));

            queue.offer(new int[]{curi+1, curj});
            queue.offer(new int[]{curi-1, curj});
            queue.offer(new int[]{curi, curj+1});
            queue.offer(new int[]{curi, curj-1});
        }

    }

    public static boolean inGraph(int[][] graph, int i, int j) {
        return 0 <= i && i < graph.length
            && 0 <= j && j < graph[0].length;
    }

}
