/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * @author kaithy.xu
 * @date 2020-08-23 16:57
 */
public class EditDistance {

    /**
     * 动态规划
     * DP方程：
     * word1.charAt(i-1) == word2.charAt(j-1) 时： dp[i][j] = dp[i-1][j-1]
     * 否则 dp[i][j] = MIN(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])
     *
     * 其中dp[i][j] 是指将word1 中 从 0 到 i 位 变为word2中的从 0 到 j 位 所需要执行的操作次数
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];

        /**
         * word2为空时，word1转变为word2所需要的执行的删除次数
         */
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i-1][0]+1;
        }

        /**
         * word1为空时，从word1转变为word2所需要执行的步骤
         */
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = dp[0][i-1]+1;
        }

        for (int i = 1; i <= len1; i++) {

            for (int j = 1; j <= len2; j++) {

                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]),dp[i][j-1])+1;
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";

        System.out.println(new EditDistance().minDistance(word1, word2));
    }

}
