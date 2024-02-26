package backtracking;

import org.junit.Test;

/**
 * 79. 单词搜索
 */
public class WordSearch {

    /**
     * DFS + 回溯
     */
    public boolean exist(char[][] board, String word) {
        int height = board.length;
        int width = board[0].length;

        boolean[][] visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean searchResult = dfs(board, i, j, height, width, visited, word, 0);
                if (searchResult) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j, int height, int width, boolean[][] visited, String word, int step) {
        if (step == word.length()) {
            return true;
        }
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        if (board[i][j] != word.charAt(step)) {
            return false;
        }

        visited[i][j] = true;
        if (dfs(board, i-1, j, height, width, visited, word, step+1)) {
            return true;
        }
        if (dfs(board, i+1, j, height, width, visited, word, step+1)) {
            return true;
        }
        if (dfs(board, i, j-1, height, width, visited, word, step+1)) {
            return true;
        }
        if (dfs(board, i, j+1, height, width, visited, word, step+1)) {
            return true;
        }

        // 回溯
        visited[i][j] = false;
        return false;
    }


    @Test
    public void test() {
        char[][] board = {{'A','B','C','E'},
                          {'S','F','C','S'},
                          {'A','D','E','E'}};
        String word = "ABCCED";
        //String word = "SEE";

        //char[][] board = {{'A','B','C','E'},
        //                  {'S','F','E','S'},
        //                  {'A','D','E','E'}};
        //String word = "ABCESEEEFS";

        //char[][] board = {{'A','B','C','E'},
        //                  {'S','F','C','S'},
        //                  {'A','D','E','E'}};
        //String word = "ABCB";

        //char[][] board = {{'A'}};
        //String word = "A";

        System.out.println(exist(board, word));
    }

}
