package dp;

/**
 * 5. 最长回文子串
 */
public class LongestPalindrome {

    /**
     * 动态规划
     *
     * 动态规划方程：
     * f(i, j) 表示字符串s从i到j的子串是否是回文的
     *
     *              /  true                         (i == j)
     *   f(i, j) =  -  s[i] == s[j]                 (j = i + 1)
     *              \  f(i+1, j-1) && s[i]==s[j]    (j > i + 1)
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 1;
        String maxSubStr = s.substring(0, 1);

        boolean[][] f = new boolean[len][len];
        int i = len - 1, j;
        for (; i >= 0; i--) {
            for (j = i; j < len; j++) {
                if (j == i) {
                    f[i][j] = true;
                } else if (j == i + 1) {
                    f[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    f[i][j] = s.charAt(i) == s.charAt(j) && f[i+1][j-1];
                }
                if (f[i][j] && (j-i+1) > maxLen) {
                    maxLen = j - i + 1;
                    maxSubStr = s.substring(i, j + 1);
                }
            }
        }

        return maxSubStr;
    }

}


























