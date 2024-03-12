package unionfindset.common;

/**
 * 并查集，主要用于处理不相交集合的合并问题，比如连通子图等
 *
 * 集合以树的形式表示，一棵树表示了一个集合
 * 树中的所有节点就是这个集合中的所有元素，每棵树都有一个根节点，作为这个集合的代表
 *
 * 并查集的基本操作有：
 *     初始化：       初始化一个新的并查集，其中包含 n 个单元素集合
 *     union(x, y)： 把元素 x 和元素 y 所在的集合合并
 *     find(x)：     找到元素 x 所在集合的根节点
 */
public class UnionFindSet {

    // 元素数量
    int num;
    // 记录每个元素的父节点
    int[] parent;

    public UnionFindSet(int num) {
        this.num = num;
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        // 路径压缩，把 x 直接挂在根节点下面
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        // 已经在同一个集合中，无需合并
        if (xParent == yParent) {
            return;
        }
        // 不在同一个集合中，合并
        parent[xParent] = yParent;
    }

    /**
     * 统计集合的数量
     */
    public int count() {
        int cnt = 0;
        for (int i = 0; i < num; i++) {
            if (parent[i] == i) {
                cnt++;
            }
        }
        return cnt;
    }

}
