package doublepointer;

/**
 * 165. 比较版本号
 */
public class CompareVersion {

    /**
     * 方法一：分割字符串，
     */
    public int compareVersion(String version1, String version2) {
        // 这里要加转义符
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;

        for (int i = 0; i < len1 || i < len2; i++) {
            int xd1 = 0, xd2 = 0;
            if (i < len1) {
                xd1 = Integer.parseInt(v1[i]);
            }
            if (i < len2) {
                xd2 = Integer.parseInt(v2[i]);
            }
            if (xd1 > xd2) {
                return 1;
            }
            if (xd2 > xd1) {
                return -1;
            }
        }

        return 0;
    }


    /**
     * todo
     * 方法二：双指针
     * 对于版本号，从前往后遍历字符，计算每个修订号的 int 值
     */
    public int compareVersion2(String version1, String version2) {
        return 0;
    }

}
