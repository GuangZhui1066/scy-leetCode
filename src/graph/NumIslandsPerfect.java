package graph;

import java.util.Arrays;

import org.junit.Test;

/**
 * 在岛屿数量的基础上：定义完美岛屿 —— 没有与其他岛屿相接的岛屿 (相接包括水平、竖直、斜对角)
 * 求完美岛屿数量
 *
 * 测试用例见 @Test
 */
public class NumIslandsPerfect {

    public int numIslands(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        int[][] visited = new int[height][width];

        int islandsCnt = 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    // 找到这块岛屿联通的所有节点，都标记上
                    dfs(grid, visited, height, width, i, j, islandsCnt);
                    islandsCnt++;
                }
            }
        }
        islandsCnt--;

        // 判断是否是完美岛屿
        boolean[] isPerfectIsland = new boolean[islandsCnt+1];
        Arrays.fill(isPerfectIsland, true);
        int[][] dirs = {{1,1},{1,-1},{-1,-1},{-1,1}};
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] == 0) {
                    continue;
                }
                // 判断斜对角是否有其他岛屿
                for (int[] dir : dirs) {
                    int newi = i + dir[0];
                    int newj = j + dir[1];
                    if (newi < 0 || newi >= height || newj < 0 || newj >= width) {
                        continue;
                    }
                    if (visited[newi][newj] > 0 && visited[newi][newj] != visited[i][j]) {
                        isPerfectIsland[visited[i][j]] = false;
                        break;
                    }
                }
            }
        }

        // 统计完美岛屿的数量
        int perfectIslandNum = 0;
        for (int i = 1; i <= islandsCnt; i++) {
            if (isPerfectIsland[i]) {
                perfectIslandNum++;
            }
        }
        return perfectIslandNum;
    }

    private void dfs(int[][] grid, int[][] visited, int height, int width, int i, int j, int islandsCnt) {
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return;
        }
        // 已经标记过
        if (visited[i][j] > 0) {
            return;
        }

        if (grid[i][j] == 1) {
            visited[i][j] = islandsCnt;
            dfs(grid, visited, height, width, i+1, j, islandsCnt);
            dfs(grid, visited, height, width, i-1, j, islandsCnt);
            dfs(grid, visited, height, width, i, j+1, islandsCnt);
            dfs(grid, visited, height, width, i, j-1, islandsCnt);
        }
    }


    @Test
    public void test() {
        //// 预期结果：3
        //int[][] grid = {{1, 0, 1, 1},
        //                {0, 0, 1, 0},
        //                {1, 0, 1, 1},
        //                {1, 0, 1, 0}};

        //// 预期结果：0
        //int[][] grid = {{1, 1, 0, 0},
        //                {0, 1, 0, 0},
        //                {0, 0, 1, 1},
        //                {0, 0, 1, 1}};

        // 预期结果：4
        int[][] grid = {{0, 0, 0, 0, 0, 1, 1},
                        {0, 1, 1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0},
                        {1, 1, 0, 1, 0, 0, 0},
                        {0, 0, 1, 1, 1, 0, 0}};

        System.out.println(numIslands(grid));
    }

}
