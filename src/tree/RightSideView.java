package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.common.TreeNode;

/**
 * 199. 二叉树的右视图
 */
public class RightSideView {

    /**
     * 层序遍历
     * 把每层的最后一个节点放入 list
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> rightView = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = null;
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            rightView.add(node.val);
        }

        return rightView;
    }

}










