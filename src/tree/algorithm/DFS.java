package tree.algorithm;

import tree.common.TreeNode;

/**
 * 深度优先搜索 (Depth First Search)
 * 用递归实现
 *
 *
 * DFS 的复杂度
 *   时间复杂度：因为要把所有节点都遍历一次，所以时间复杂度是 O(N)
 *   空间复杂度：空间复杂度即为递归栈的复杂度。最坏情况下如果树是链式结构，空间复杂度就是 O(N)；如果是平衡树，空间复杂度就是 O(logN)
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
