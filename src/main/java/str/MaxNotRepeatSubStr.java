package str;

import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最长不含重复字符的子字符串长度
 *
 * @author wrj
 * @date 2020/9/23
 */
public class MaxNotRepeatSubStr {

    public static void main(String[] args) {
        String str = "abba";
        Assert.assertEquals(solution1(str), 3);
        Assert.assertEquals(solution2(str), 2);
    }

    /**
     * 滑动窗口解法1
     * @param str 字符串
     * @return 子字符串长度
     */
    private static int solution1(String str) {
        Set<Character> set = new HashSet<>();
        int res = 0;

        for (int l = 0, r = 0; r < str.length(); r++) {
            char c = str.charAt(r);

            while (set.contains(c)) {
                set.remove(str.charAt(l++));
            }

            set.add(c);

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     * 滑动窗口解法2
     * @param str 字符串
     * @return 子字符串长度
     */
    private static int solution2(String str) {
        // 用map记录每个字符最后一次出现的下标索引
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, res = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 如果出现重复，将左指针更新为该字符上一次出现的索引位置的后一位，这样可保证[left, i]至今没有重复的元素
            if (map.containsKey(c)) {
                // 这里用到取最大值，没有直接用left = map.get(c) + 1，是因为可能出现abba这种情况
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, i);

            res = Math.max(res, i - left + 1);
        }

        return res;
    }

}
