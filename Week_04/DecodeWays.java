/**
 * 91. 解码方法
 *
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * @author kaithy.xu
 * @date 2020-08-09 17:09
 */
public class DecodeWays {

    /**
     * 动态规划
     * 
     * 状态表：一维数组dp，dp[i+1] 保存的时字符串 s[0，i] 的编码数
     * 
     * 状态转移方程：
     * if s[i] == '0' 
     *      if(s[i-1] <= '2') 
     *          dp[i+1] = dp[i-1]
     *      else
     *          return 0
     * else if s[i-1] == '1' || s[i-1] == '2' && s[i-1] >= '0' && s[i-1] <= 6
     *      dp[i+1] = dp[i] + dp[i-1]
     * else
     *      dp[i+1] = dp[i]
     *      
     * 如果 s[i] == 0 且 s[i-1] <= '2' 时， 因为s[i] = 0，无法单独编码，dp[i] = 0, 所以 dp[i+1] = dp[i-1] + 0
     * 如果 s[i-1] == '1' || s[i-1] == '2' && s[i-1] >= '0' && s[i-1] <= 6 时，dp[i+1] = dp[i-1] + dp[i]
     * 否则 s[i-1] 无法单独编码，dp[i-1] = 0,所以 dp[i+1] = dp[i]
     * 
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int length = s.length();
        if(s.charAt(0) == '0'){
            return 0;
        }

        int[] memo = new int[length+1];

        memo[0] = 1;
        memo[1] = 1;

        for (int i = 1; i < length; i++) {

            if(s.charAt(i) == '0') {
                if(s.charAt(i-1) == '1' || s.charAt(i-1) == '2') {
                    memo[i+1] = memo[i-1];
                }else {
                    return 0;
                }
            }else if(s.charAt(i-1) == '1') {
                memo[i+1] = memo[i] + memo[i-1];
            }else if(s.charAt(i-1) == '2' && s.charAt(i) >= '0' && s.charAt(i) <= '6') {
                memo[i+1] = memo[i] + memo[i-1];
            }else {
                memo[i+1] = memo[i];
            }
        }

        return memo[length];
    }

    public int numDecodings2(String s) {
        int length = s.length();
        if(s.charAt(0) == '0'){
            return 0;
        }

        int[] memo = new int[length+1];

        memo[0] = 1;
        memo[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= length; i++) {
            int first = Integer.parseInt(s.substring(i-1, i));
            int second = Integer.parseInt(s.substring(i-2, i));

            if(first > 0 && first <= 9) {
                memo[i] += memo[i-1];
            }
            if(second >= 10 && second <= 26) {
                memo[i] += memo[i-2];
            }
        }
        return memo[length];
    }

    public static void main(String[] args) {
        String s = "22";

        System.out.println(new DecodeWays().numDecodings(s));
    }
}
