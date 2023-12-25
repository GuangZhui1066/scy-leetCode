package graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 200. 岛屿数量
 */
public class NumIslands {

    /**
     * 方法一：DFS
     */
    public static int numIslands1(char[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        boolean[][] visited = new boolean[height][width];

        int islandsNum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    islandsNum++;
                    // 找到这块岛屿联通的所有节点，都标记上
                    dfs(grid, visited, height, width, i, j);
                }
            }
        }

        return islandsNum;
    }

    private static void dfs(char[][] grid, boolean[][] visited, int height, int width, int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;

        if (grid[i][j] == '1') {
            dfs(grid, visited, height, width, i+1, j);
            dfs(grid, visited, height, width, i-1, j);
            dfs(grid, visited, height, width, i, j+1);
            dfs(grid, visited, height, width, i, j-1);
        }
    }


    /**
     * 方法二：BFS
     */
    public static int numIslands2(char[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        boolean[][] visited = new boolean[height][width];

        int islandsNum = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    islandsNum++;
                    // 找到这块岛屿联通的所有节点，都标记上
                    bfs(grid, visited, height, width, i, j);
                }
            }
        }

        return islandsNum;
    }

    private static void bfs(char[][] grid, boolean[][] visited, int height, int width, int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] index = queue.poll();
            int curi = index[0];
            int curj = index[1];

            if (curi < 0 || curi >= height || curj < 0 || curj >= width) {
                continue;
            }
            if (visited[curi][curj]) {
                continue;
            }
            visited[curi][curj] = true;

            if (grid[curi][curj] == '1') {
                queue.offer(new int[]{curi+1, curj});
                queue.offer(new int[]{curi-1, curj});
                queue.offer(new int[]{curi, curj+1});
                queue.offer(new int[]{curi, curj-1});
            }
        }

    }

    public static void main(String[] args) {
        char[][] grid1 = {{'1', '1', '0', '0', '0'},
                         {'1', '1', '0', '0', '0'},
                         {'0', '0', '1', '0', '0'},
                         {'0', '0', '0', '1', '1'}};

        char[][] grid2 = {{'1', '1', '1'},
                          {'0', '1', '0'},
                          {'1', '1', '1'}};

        System.out.println(numIslands1(grid1));
    }

}

















