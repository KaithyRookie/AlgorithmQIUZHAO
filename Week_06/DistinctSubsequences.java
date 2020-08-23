/**
 * 115. 不同的子序列
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 *
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * @author kaithy.xu
 * @date 2020-08-23 17:14
 */
public class DistinctSubsequences {

    /**
     * 动态规划
     * DP方程：
     * 当 t.charAt(i-1) == s.charAt(j-1) 时 dp[i][j] = dp[i-1][j-1] + dp[i][j-1]
     * 否则 dp[i][j] = dp[i][j-1]
     * 
     * 其中dp[i][j] 指 t 的前 i 个字符 在 s 的前 j 个字符中出现的次数
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        int[][] dp = new int[len2+1][len1+1];

        for (int i = 0; i <= len1; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= len2; i++) {

            for (int j = 1; j <= len1; j++) {

                if(t.charAt(i-1) == s.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else {
                    dp[i][j] = dp[i][j-1];
                }

            }
        }
        

        return dp[len2][len1];
    }

    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";

        System.out.println(new DistinctSubsequences().numDistinct(s, t));
    }
}
