/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 */
class LongestPalindrome {
    public String longestPalindrome(String s) {
    	int len = s.length();
    	if(len == 0) {
            return s;
        }

    	boolean[][] dp = new boolean[len][len];

    	for (int i=0; i < len; ++i) {
    		dp[i][i] = true;
    	}

    	int maxLen = 1;
    	int begin=0;
    	for (int i = len - 1; i >= 0; --i) {

    		for (int j = i+1; j < len; ++j) {
    			
    			if(s.charAt(i) != s.charAt(j)) {
    				dp[i][j] = false;
    			}else {
    				if(j - i < 3) {
    					dp[i][j] = true;
    				}else {
    					dp[i][j] = dp[i+1][j-1];
    				}
    			}

    			if(dp[i][j] && j-i+1 > maxLen) {
    				maxLen = j-i+1;
    				begin = i;
    			}

    		}
    		
    	}

    	return s.substring(begin, begin+maxLen);
    }
}