package algorithm.HardQuestion;
/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 *
 * @author kaithy.xu
 * @date 2020-08-23 15:21
 */
public class LongestValidParentheses {

    /**
     * 动态规划
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if(len <2) {
            return 0;
        }
        int maxLen = 0;

        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            if(s.charAt(i) == ')') {

                if(s.charAt(i-1) == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                }else if(i - dp[i-1] > 0 && s.charAt(dp[i-dp[i-1]-1]) == '(') {
                    dp[i] = dp[i-1] + ((i - dp[i-1]) >= 2 ? dp[i-dp[i-1]-2] : 0) + 2;
                }

                maxLen = Math.max(maxLen, dp[i]);

            }

        }

        return maxLen;
    }

    /**
     * 利用栈
     * @param s
     * @return
     */
    public int solutionByStack(String s) {
        int len = s.length();
        if(len <2) {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int maxLen = 0;
        stack.push(-1);
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                }else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }


    public static void main(String[] args) {
        String s = "()(())";

        LongestValidParentheses parentheses = new LongestValidParentheses();
        System.out.println(parentheses.solutionByStack(s));

        System.out.println(parentheses.longestValidParentheses(s));
    }
}
