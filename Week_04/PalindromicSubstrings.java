import java.util.HashMap;
import java.util.Map;

/**
 * 647. 回文子串
 * 
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * @author kaithy.xu
 * @date 2020-08-09 21:54
 */
public class PalindromicSubstrings {

    /**
     * 暴力法求解
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        if(length <= 1) {
            return length;
        }
        int[] dp = new int[length];
        
        dp[0] = 1;
        dp[1] = s.charAt(0) == s.charAt(1) ? 3 : 2;

        Map<String, Boolean> memo = new HashMap<>();

        for (int i = 1; i < length; i++) {
            dp[i] = dp[i-1] + 1;
            for (int j = 0; j < i; j++) {
                String t = s.substring(j,i+1);
                if(memo.computeIfAbsent(t, tmp -> check(tmp))) {
                    dp[i]++;
                }

            }
        }
        return dp[length-1];
    }

    private boolean check(String t) {
        int left =0, right = t.length()-1;
        while (left < right) {
            if(t.charAt(left++) != t.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对于一个长度为N的字符串，可能是回文中心的位置有 2*N-1 个
     * 遍历这些中心点，寻找满足的回文子字符串，左右指针从中心点开始向两边移动，当 s.charAt(left) == s.charAt(right) 时，结果+1
     * 时间复杂度O(N^2)
     * 空间复杂度O(1)
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int length = s.length(), ans = 0;

        for (int center = 0; center <= 2*length-1; center++) {
            int left = center >> 1;
            int right = left + center % 2;

            while (left>=0 && right < length && s.charAt(left--) == s.charAt(right++)) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 马拉车算法
     *
     * 假设一个回文串中心为 center，该中心对应的最大回文串右边界为 right。存在一个 i 为当前回文串中心，满足 i > center，
     * 那么也存在一个 j 与 i 关于 center 对称，可以根据 Z[i] 快速计算出 Z[j]。
     *
     *
     * 当 i < right 时，找出 i 关于 center 的对称点 j = 2 * center - i。
     * 此时以 i 为中心，半径为 right - i 的区间内存在的最大回文串的半径 Z[i] 等于 Z[j]。
     *
     *
     * 最后，对 Z 中每一项 v 计算 (v+1) / 2，然后求和。
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int length = s.length(), ans = 0;
        char[] A = new char[length*2+3];
        A[0]='@';
        A[2*length+2] = '$';
        A[1] = '#';

        int t = 2;
        for (Character c : s.toCharArray()) {
            A[t++] = c;
            A[t++] = '#';
        }

        int[] Z = new int[A.length];
        int center = 0, right = 0;

        for (int i = 1; i < Z.length-1; ++i) {
            if(i < right) {
                Z[i] = Math.min(right-i, Z[2*center-1]);
            }

            while (A[i+Z[i]+1] == A[i-Z[i]-1]) {
                Z[i]++;
            }

            if(i+Z[i] > right) {
                center = i;
                right = i + Z[i];
            }
        }

        for (int i = 0; i < Z.length; i++) {
            ans += Z[i]+1 >> 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(new PalindromicSubstrings().countSubstrings3(s));
    }
}
