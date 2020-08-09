/**
 * 221. 最大正方形
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * @author kaithy.xu
 * @date 2020-08-09 18:14
 */
public class MaximalSquare {

    /**
     * 动态规划
     *
     * 使用二维数组dp，dp[i][j]表示以 matrix[i][j] 为右下角顶点构成的正方形的边长
     *
     * 状态转移方程：
     * if matrix[i][j] == '0'
     *      dp[i][j] = 0
     * else
     *      if(dp[i-1][j-1] == 0 || dp[i-1][j] ==0 || dp[i][j-1] == 0)
     *          dp[i][j] = 1
     *      else
     *          dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     *  用 result 保留最长边长 result = max(result, dp[i][j])
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int row = matrix.length;
        if(row == 0) {
            return 0;
        }

        int col = matrix[0].length;

        int[][] dp = new int[row][col];
        int result =  0;
        for (int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            result = Math.max(result, dp[i][0]);
        }
        for (int i = 0; i < col; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            result = Math.max(result, dp[0][i]);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                
                if(matrix[i][j] == '1') {
                    if(dp[i-1][j-1] == 0 || dp[i-1][j] ==0 || dp[i][j-1] == 0) {
                        dp[i][j] = 1;
                    }else {
                        int min = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
                        dp[i][j] = min + 1;
                    }
                    result = Math.max(result,dp[i][j]);
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        
        return (int) Math.pow(result,2);
    }

    public static void main(String[] args) {
//        char[][] matrix = {
//            {'1','0','1','0','0'},
//            {'1','0','1','1','1'},
//            {'1','1','1','1','1'},
//            {'1','0','1','1','1'}
//        };

        char[][] matrix = {
            {'1'}
        };

        System.out.println(new MaximalSquare().maximalSquare(matrix));
    }
}
