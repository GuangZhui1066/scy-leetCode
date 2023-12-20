package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 */
public class LongestSubstring {

    /**
     * 滑动窗口
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int longestLen = 1;

        // 保存当前子串中的所有字符 <字符, 在字符串中的位置>
        Map<Character, Integer> exsit = new HashMap<>();
        exsit.put(s.charAt(0), 0);

        int left = 0, right = 1;
        while (right < len) {
            Character newEle = s.charAt(right);
            Integer repeatIndex = exsit.get(newEle);
            if (repeatIndex == null) {
                // 没有重复
                exsit.put(newEle, right);
                longestLen = Math.max(longestLen, right - left + 1);
                right++;
            } else {
                // 这个字符已经出现过，将重复字符之前的全部清除
                for (int i = left; i <= repeatIndex; i++) {
                    exsit.remove(s.charAt(i));
                }
                exsit.put(newEle, right);
                left = repeatIndex + 1;
                right++;
            }
        }

        return longestLen;
    }

}


























