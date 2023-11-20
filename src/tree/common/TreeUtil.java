package tree.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtil {


    /**
     * 构建一棵测试树
     *
     *        1
     *       / \
     *      2   3
     *     /\    \
     *    4 5     6
     */
    public static TreeNode getTestTree() {
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        TreeNode node4 = new TreeNode();
        TreeNode node5 = new TreeNode();
        TreeNode node6 = new TreeNode();

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;

        return node1;
    }


    /**
     * 构建树结构
     *
     * 输入：
     *   [1,2,3,4,null,null,5,6,null,7]
     *
     * 输出：
     *         1
     *        / \
     *       2   3
     *      /     \
     *     4       5
     *    /       /
     *   6       7
     */
    public static TreeNode constructTree(String str) {
        return constructTreeByArray(getIntArrayByStr(str));
    }

    public static TreeNode constructTreeByArray(Integer[] array) {
        if (array == null || array.length == 0 || array[0] == null) {
            return null;
        }

        int index = 0;
        int length = array.length;

        TreeNode root = new TreeNode(array[index]);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        TreeNode currNode;
        while (true) {
            index++;
            if (index >= length) {
                return root;
            }
            currNode = nodeQueue.poll();
            Integer leftChildValue = array[index];
            if (leftChildValue != null) {
                currNode.left = new TreeNode(leftChildValue);
                nodeQueue.offer(currNode.left);
            }
            index++;
            if (index >= length) {
                return root;
            }
            Integer rightChildValue = array[index];
            if (rightChildValue != null) {
                currNode.right = new TreeNode(rightChildValue);
                nodeQueue.offer(currNode.right);
            }
        }
    }


    /**
     * 画出树结构
     */
    public static void printTree(TreeNode root) {
        int maxLevel = getTreeDepth(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    public static int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right));
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes == null || nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }

                printWhitespaces(i + i - 1);
                if (nodes.get(j).right != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }


    public static Integer[] getIntArrayByStr(String str) {
        String arrayStr = str.substring(1, str.length() - 1);
        String[] strArray = arrayStr.split(",");
        Integer[] intArr = new Integer[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            Integer intEle;
            String strEle = strArray[i];
            if ("null".equals(strEle)) {
                intEle = null;
            } else {
                intEle = Integer.parseInt(strEle);
            }
            intArr[i] = intEle;
        }
        return intArr;
    }

}
