/**
 * 剑指 Offer 49. 丑数
 *
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * @author kaithy.xu
 * @date 2020-07-26 21:38
 */
public class ChouShuLcof {

    public int nthUglyNumber(int n) {

        int a=0, b=0, c=0;
        int[] dp = new int[n];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int n_2 = dp[a]*2, n_3 = dp[b]*3, n_5 = dp[c] * 5;
            dp[i] = Math.min(n_2, Math.min(n_3, n_5));
            if(dp[i] == n_2){
                ++a;
            }
            if(dp[i] == n_3)
                ++b;

            if(dp[i] == n_5)
                ++c;

        }

        return dp[n-1];
    }
}
