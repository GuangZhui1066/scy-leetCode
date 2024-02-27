package dp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 139. 单词拆分
 */
public class WordBreak {


    /**
     * 方法一：dp
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int slen = s.length();

        // dp[i] 能否用 wordDict 中的单词拼接出 s 中 [0,i) 部分的子串
        boolean[] dp = new boolean[slen+1];
        dp[0] = true;
        for (int i = 1; i <= slen; i++) {
            for (String word : wordDict) {
                int wordLen = word.length();
                if (i >= wordLen && word.equals(s.substring(i-wordLen, i))) {
                    dp[i] = dp[i-wordLen];
                }
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[slen];
    }


    /**
     * 方法二：DFS (需要用记忆化搜索来避免超时)
     */
    public boolean wordBreak2(String s, List<String> wordDict) {

        // memo[i] 记录 s 中 [i,-1) 部分的子串是否有解
        // 1 表示有解；-1 表示无解；0 表示未计算过
        // 如果不用 -1 来记录无解的结果的话，可能会导致无解的部分被大量重复计算，从而超时
        int[] memo = new int[s.length()+1];

        return dfs(wordDict, s, 0, memo);
    }

    private boolean dfs(List<String> wordDict, String s, int index, int[] memo) {
        if (index >= s.length()) {
            return true;
        }
        if (memo[index] == 1) {
            return true;
        }
        if (memo[index] == -1) {
            return false;
        }

        for (String word : wordDict) {
            if (s.substring(index).startsWith(word)) {
                // 继续匹配后面的部分
                boolean breakResult = dfs(wordDict, s, index + word.length(), memo);
                if (breakResult) {
                    memo[index] = 1;
                    return true;
                }
            }
        }

        memo[index] = -1;
        return false;
    }


    @Test
    public void test() {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");

        System.out.println(wordBreak2(s, wordDict));
    }

}
