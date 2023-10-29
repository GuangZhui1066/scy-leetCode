import java.util.*;

class SpiralMatrix {

    /**
     * 状态机思想
     *   根据当前位置、当前的方向、访问的当前边界来确定下一个访问节点的位置、下次访问的方向
     *   当访问到边界后，访问方向需要改变，并且访问边界也会缩小
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();

        // 已经访问过的元素数量
        int cnt = 0;
        // 当前访问的元素位置
        int i = 0, j = 0;
        // 访问边界，当访问到此边界后需要调整方向
        int lb = 0, rb = n - 1, ub = 0, db = m - 1;
        // 当前的方向 1为向右 2为向下 3为向左 4为向上
        int direction = 1;
        while (cnt < m * n) {
            int val = matrix[i][j];
            result.add(val);
            cnt++;

            if (direction == 1) {
                if (j == rb) {
                    direction = 2;
                    ub++;
                    i++;
                } else {
                    j++;
                }
            } else if (direction == 2) {
                if (i == db) {
                    direction = 3;
                    j--;
                    rb--;
                } else {
                    i++;
                }
            } else if (direction == 3) {
                if (j == lb) {
                    direction = 4;
                    i--;
                    db--;
                } else {
                    j--;
                }
            } else if (direction == 4) {
                if (i == ub) {
                    direction = 1;
                    j++;
                    lb++;
                } else {
                    i--;
                }
            }
        }

        return result;
    }

}