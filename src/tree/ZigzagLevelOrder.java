package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

import org.junit.Test;
import tree.common.TreeNode;

/**
 * 103. 二叉树的锯齿形层序遍历
 */
public class ZigzagLevelOrder {

    /**
     * 层序遍历
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        if (root == null) {
            return levelOrder;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean posOrder = true;

        // 双端队列
        Deque<Integer> curLevelVal = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 把当前层的节点全取出来
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 下一层从左到右入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // 从左往右遍历当前层
                if (posOrder) {
                    curLevelVal.offerLast(node.val);
                }
                // 从右往左遍历当前层
                else {
                    curLevelVal.offerFirst(node.val);
                }
            }

            levelOrder.add(new ArrayList<>(curLevelVal));
            curLevelVal.clear();
            posOrder = !posOrder;
        }

        return levelOrder;
    }


    @Test
    public void test() {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        List<List<Integer>> result = zigzagLevelOrder(n1);
        System.out.println(result);
    }

}
















