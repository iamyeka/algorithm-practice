package dp;

/**
 * 给定两个字符串，计算将S1转换为S2需要的最小步数
 * 可以对一个字符串执行三种操作：
 * 1. 插入一个字符
 * 2. 删除一个字符
 * 3. 替换一个字符
 * <p>
 * 下方解法涉及到动态规划（Dynamic Programming）的使用
 *
 * @author wrj
 * @date 2020/5/23
 */
public class MinDistance {
    public static int minDistance(String s1, String s2) {
        // 假定s1和s2都非null，但可能为空字符串

        int m = s1.length(), n = s2.length();
        // dp[i - 1][j - 1]存储 s1[0..i] 和 s2[0..j] 的最小操作步数
        int[][] dp = new int[m + 1][n + 1];

        // 如果s2等于空字符
        for (int i = 0; i < m; i++) {
            dp[i][0] = i;
        }

        // 如果s1等于空字符
        for (int j = 0; j < n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = min(
                            dp[i - 1][j] + 1, // 删除
                            dp[i][j - 1] + 1, // 插入
                            dp[i - 1][j - 1] + 1 // 替换
                    );
        return dp[m][n];
    }

    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
