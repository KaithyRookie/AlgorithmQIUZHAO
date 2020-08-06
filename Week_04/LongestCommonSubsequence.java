/**
 * 1143. 最长公共子序列
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * @author kaithy.xu
 * @date 2020-08-05 21:37
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int t1Length = text1.length();
        int t2Length = text2.length();
        if(t1Length <= 1 || t2Length <= 1) {
            if(t1Length == 1 && t2Length == 1) {
                return text1.charAt(0) == text2.charAt(0) ? 1 : 0;
            }
            return Math.min(t1Length, t2Length);
        }
        int[][] memo = new int[t1Length][t2Length];
        if(text1.charAt(0) == text2.charAt(0)) {
            memo[0][0] = 1;
        }

        for (int i = 1; i < t1Length; i++) {
            if(text1.charAt(i) == text2.charAt(0) || memo[i-1][0] == 1) {
                memo[i][0] = 1;
            }
        }

        for (int i = 1; i < t2Length; i++) {
            if(text2.charAt(i) == text1.charAt(0) || memo[0][i-1] == 1) {
                memo[0][i] = 1;
            }
        }

        for (int i = 1; i < t1Length; i++) {

            for (int j = 1; j < t2Length; j++) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    memo[i][j] = memo[i-1][j-1] + 1;
                }else {
                    memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
                }
            }
        }

        return memo[t1Length-1][t2Length-1];
    }



    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "";

        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence(text1, text2));
    }

}
