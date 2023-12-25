package graph.algorithm;

/**
 * 图的深度优先搜索算法
 */
public class DFS {

    public static void main(String[] args) {
        int[][] graph = {{1, 1, 0, 0, 0},
                         {1, 1, 0, 0, 0},
                         {0, 0, 1, 0, 0},
                         {0, 0, 0, 1, 1}};

        int height = graph.length;
        int width = graph[0].length;

        // 记录每个位置的元素是否已经访问过。有时可以用 graph 数组本身来表示
        boolean[][] visited = new boolean[height][width];

        dfs(graph, visited, 0, 0);
    }

    /**
     * 对于二维数组表示的图 的 DFS 算法
     */
    public static void dfs(int[][] graph, boolean[][] visited, int i, int j) {
        if (!inGraph(graph, i, j) || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        System.out.println(("i: " + i + ", j: "+ j));

        // 访问上、下、左、右四个相邻结点
        dfs(graph, visited, i - 1, j);
        dfs(graph, visited, i + 1, j);
        dfs(graph, visited, i, j - 1);
        dfs(graph, visited, i, j + 1);
    }

    public static boolean inGraph(int[][] graph, int i, int j) {
        return 0 <= i && i < graph.length
            && 0 <= j && j < graph[0].length;
    }

}
