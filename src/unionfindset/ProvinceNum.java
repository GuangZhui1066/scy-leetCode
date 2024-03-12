package unionfindset;

import org.junit.Test;
import unionfindset.common.UnionFindSet;

/**
 * 547. 省份数量
 */
public class ProvinceNum {

    /**
     * 方法一：并查集
     *
     */
    public int findCircleNum(int[][] isConnected) {
        int num = isConnected.length;
        UnionFindSet ufs = new UnionFindSet(num);

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    ufs.union(i, j);
                }
            }
        }

        return ufs.count();
    }


    /**
     * 方法二：DFS
     * 思路和 "200. 岛屿数量" 一样，只不过这道题的输入是邻接矩阵
     */
    public int findCircleNum2(int[][] isConnected) {
        int num = isConnected.length;
        boolean[] visited = new boolean[num];

        int result = 0;
        for (int i = 0; i < num; i++) {
            if (!visited[i]) {
                result++;
                dfs(isConnected, visited, num, i);
            }
        }

        return result;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int num, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int j = 0; j < num; j++) {
            if (isConnected[i][j] == 1 && i != j) {
                dfs(isConnected, visited, num, j);
            }
        }
    }


    @Test
    public void test() {
        int[][] isConnected = {{1,1,0},
                               {1,1,0},
                               {0,0,1}};

        System.out.println(findCircleNum(isConnected));
    }

}









