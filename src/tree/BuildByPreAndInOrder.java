package tree;

import org.junit.Test;
import tree.common.TreeNode;

/**
 * 105. 从前序和中序遍历序列构造二叉树
 *
 * 根据前序遍历无法确定出唯一的二叉树，因为不知道哪些是左子树哪些是右子数
 * 但是根据前序遍历和中序遍历，可以确定出唯一的二叉树
 */
public class BuildByPreAndInOrder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildByPreorderAndInorder(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    /**
     * 前序遍历中，第一个元素是根节点
     * 在中序遍历中找到这个根节点，前面的部分就是左子树，右边的部分就是右子树
     *
     * pl, pr 表示当前构建的树的前序遍历在 preOrder 中的区间
     * il, ir
     */
    public TreeNode buildByPreorderAndInorder(int[] preOrder, int pl, int pr, int[] inOrder, int il, int ir) {
        int rootVal = preOrder[pl];
        TreeNode root = new TreeNode(rootVal);

        // 左右子树中节点的数量
        int leftNum = 0, rightNum;
        while (inOrder[il+leftNum] != rootVal) {
            leftNum++;
        }
        rightNum = pr - pl - leftNum;

        // 递归地构建左右子树
        if (leftNum > 0) {
            root.left = buildByPreorderAndInorder(preOrder, pl+1, pl+leftNum, inOrder, il, il+leftNum-1);
        }
        if (rightNum > 0) {
            root.right = buildByPreorderAndInorder(preOrder, pr-rightNum+1, pr, inOrder, ir-rightNum+1, ir);
        }

        return root;
    }


    @Test
    public void test() {
        /**
         *      3
         *     / \
         *    9   8
         *       /\
         *      7  4
         */
        int[] preOrder = {3,9,8,7,4};
        int[] inOrder = {9,3,7,8,4};

        TreeNode root = buildTree(preOrder, inOrder);
        System.out.println(root);
    }

}
