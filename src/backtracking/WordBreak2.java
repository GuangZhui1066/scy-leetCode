package backtracking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 140. 单词拆分2
 */
public class WordBreak2 {

    List<List<String>> result = new ArrayList<>();

    /**
     * DFS
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        dfs(s, 0, wordDict, new ArrayList<>());

        List<String> strReuslt = new ArrayList<>();
        for (List<String> oneResult : result) {
            strReuslt.add(String.join(" ", oneResult.toArray(new String[0])));
        }
        return strReuslt;
    }

    private void dfs(String s, int index, List<String> wordDict, List<String> oneReuslt) {
        if (index >= s.length()) {
            result.add(new ArrayList<>(oneReuslt));
            return;
        }

        for (int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            if (s.substring(index).startsWith(word)) {
                oneReuslt.add(word);
                dfs(s, index + word.length(), wordDict, oneReuslt);
                // 回溯
                //oneReuslt.remove(word);  这样写不一定会删除队尾的元素
                oneReuslt.remove(oneReuslt.size()-1);
            }
        }
    }


    @Test
    public void test() {
        //String s = "catsanddog";
        //List<String> wordDict = new ArrayList<>();
        //wordDict.add("cat");
        //wordDict.add("cats");
        //wordDict.add("and");
        //wordDict.add("sand");
        //wordDict.add("dog");

        String s = "aaaaaaa";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("aa");
        wordDict.add("a");
        wordDict.add("aaaa");

        List<String> result = wordBreak(s, wordDict);
        System.out.println(result);
    }

}
