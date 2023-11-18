package tree.common;

import tree.MaxWidth;

public class TreeTest {

    public static void main(String[] args) {
        MaxWidth maxWidth = new MaxWidth();
        TreeNode tree = TreeUtil.constructTree(
            "[1,1,1,1,1,1,1,null,null,null,1,null,null,null,null,2,2,2,2,2,2,2,null,2,null,null,2,null,2]");
        //TreeUtil.printTree(tree);
        System.out.println(maxWidth.widthOfBinaryTree(tree));
    }
}
