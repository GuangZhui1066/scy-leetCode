package tree.algorithm;

import java.util.LinkedList;
import java.util.Queue;

import tree.common.TreeNode;

/**
 * 广度优先搜索 (Breadth First Search)
 * 用队列实现
 */
public class BFS {

    public void bfs(TreeNode root){
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 出队
            TreeNode node = queue.poll();
            System.out.println(node.val);

            // 左子节点入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 右子节点入队
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

}
