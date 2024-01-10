package graph.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * 拓扑排序
 *
 * 问题描述：
 *    对一个有向无环图 (Directed Acyclic Graph, DAG) G 进行拓扑排序，是将 G 中所有顶点排成一个线性序列，
 *    使得图中任意一对顶点 u 和 v，若边 <u,v> ∈ E(G)，则 u 在线性序列中出现在 v 之前。
 *
 * 核心思路：
 *    BFS
 */
public class TopologicalSort {

    public int vertexNum;

    /**
     * 图中的边
     * 如果 edge[i][j] == 1，说明图中存在一条从 i 节点到 j 节点的边
     */
    public int[][] edge;

    /**
     * 图中节点的入度
     */
    public int[] inDegree;


    @Test
    public void test() {
        constructDAG();
        topologicalSort();
    }


    /**
     * 拓扑排序
     */
    public void topologicalSort() {
        Deque<Integer> queue = new ArrayDeque<>();

        // 找到起点 (即入度为0的节点)，入队
        for (int i = 0; i < vertexNum; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            System.out.println(cur);

            // 遍历后继节点
            for (int i = 0; i < vertexNum; i++) {
                if (edge[cur][i] == 1) {
                    // 后继节点的入度减一
                    inDegree[i]--;
                    // 如果后继节点的所有前序节点都已经被遍历过，就把这个后继节点入队
                    if (inDegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

    }


    /**
     * 构建一个 DAG
     * @See graph/DAGCompute/有向图计算.jpg (节点A编号为 0，节点G编号为 6)
     */
    public void constructDAG() {
        vertexNum = 7;
        edge = new int[vertexNum][vertexNum];
        inDegree = new int[vertexNum];

        edge[0][1] = 1;
        edge[0][2] = 1;
        edge[1][4] = 1;
        edge[2][3] = 1;
        edge[3][4] = 1;
        edge[4][6] = 1;
        edge[5][6] = 1;

        inDegree[0] = 0;
        inDegree[1] = 1;
        inDegree[2] = 1;
        inDegree[3] = 1;
        inDegree[4] = 2;
        inDegree[5] = 0;
        inDegree[6] = 2;
    }

}
