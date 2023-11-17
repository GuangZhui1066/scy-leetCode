package tree.algorithm;

import tree.common.TreeNode;

/**
 * 深度优先搜索 (Depth First Search)
 * 用递归实现
 */
public class DFS {

    /**
     * 前序遍历
     */
    public void preOrder(TreeNode node){
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(TreeNode node){
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(TreeNode node){
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

}
