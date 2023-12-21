package tree;

import java.util.ArrayDeque;
import java.util.Deque;

import tree.common.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {

    /**
     * 解法1：找到从根节点到两个节点的路径，相同的前缀部分即为公共祖先
     *
     * 比如二叉树：
     *          3
     *        /   \
     *       5     1
     *      / \   / \
     *     6  2  0  8
     *       / \
     *      7  4
     *
     *  节点 7 的路径为 3 - 5 - 2 - 7 (7是栈顶)
     *  节点 6 的路径为 3 - 5 - 6     (6是栈顶)
     *  公共祖先为 3 5
     *  最近公共祖先为 5
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> pPath = new ArrayDeque<>();
        Deque<TreeNode> qPath = new ArrayDeque<>();

        getNodePath(root, p, pPath);
        getNodePath(root, q, qPath);

        // 让两个栈长度相同
        int pLength = pPath.size(), qLength = qPath.size();
        int popTimes = Math.abs(pLength - qLength);
        Deque<TreeNode> longerPath = (pLength > qLength) ? pPath : qPath;
        while (popTimes > 0) {
            longerPath.pop();
            popTimes--;
        }

        // 两个栈一起 pop，直到 pop 出相同的元素
        while (true) {
            TreeNode pAnces = pPath.pop();
            TreeNode qAnces = qPath.pop();
            if (pAnces == qAnces) {
                return pAnces;
            }
        }
    }

    /**
     * 查询出 node 节点在 root 树上的路径
     */
    private boolean getNodePath(TreeNode root, TreeNode node, Deque<TreeNode> path) {
        if (root == null) {
            return false;
        }

        path.push(root);
        if (root.val == node.val) {
            return true;
        }

        if (getNodePath(root.left, node, path)) {
            return true;
        } else if (getNodePath(root.right, node, path)) {
            return true;
        } else {
            path.pop();
            return false;
        }
    }



    /**
     * 解法2：递归
     *
     * 如果 p 和 q 只存在于左子树中，那么 pq在root中的最近公共祖先 就是 pq在root的左子树中的最近公共祖先
     * 右子树同理
     * 如果 p 和 q 有的存在于左子树中，有的存在于右子树中，那么pq的最近公共祖先就是当前的root
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode leftAnces = lowestCommonAncestor2(root.left, p, q);
        TreeNode rightAnces = lowestCommonAncestor2(root.right, p, q);

        if (leftAnces != null && rightAnces != null) {
            return root;
        } else if (leftAnces != null) {
            return leftAnces;
        } else if (rightAnces != null) {
            return rightAnces;
        } else {
            return null;
        }
    }

}

















