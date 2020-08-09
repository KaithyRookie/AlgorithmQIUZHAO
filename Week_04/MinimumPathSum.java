/**
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author kaithy.xu
 * @date 2020-08-09 16:46
 */
public class MinimumPathSum {

    /**
     * DP
     * base case:
     * memo[0][0] = grid[0][0]
     * for i in [1,row) : memo[i][0] = memo[i-1]+grid[i][0]
     * for i in [1,col): memo[0][i] = memo[0][i-1]+grid[0][i]
     *
     * 状态转移方程：
     *
     * memo[i][j] = Math.min(memo[i-1][j], memo[i][j-1]) + grid[i][j]
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        if(row == 0) {
            return -1;
        }

        int col = grid[0].length;

        int[][] memo = new int[row][col];

        memo[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            memo[i][0] = memo[i-1][0]+grid[i][0];
        }

        for (int i = 1; i < col; i++) {
            memo[0][i] = memo[0][i-1] + grid[0][i];
        }


        for (int i = 1; i < row; i++) {

            for (int j = 1; j < col; j++) {
                memo[i][j] = Math.min(memo[i-1][j], memo[i][j-1]) + grid[i][j];
            }
        }

        return memo[row-1][col-1];
    }

    /**
     * 解法二，优化空间复杂度至O(n)
     * 使用一维数组统计每一行的dp结果
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int row = grid.length;
        if(row == 0) {
            return -1;
        }

        int col = grid[0].length;

        int[] memo = new int[col];

        memo[0] = grid[0][0];
        for (int i = 1; i < col; i++) {
            memo[i] = memo[i-1] + grid[0][i];
        }

        for (int i = 1; i < row; i++) {
            memo[0] += grid[i][0];
            for (int j = 1; j < col; j++) {
                memo[j] = Math.min(memo[j],memo[j-1]) + grid[i][j];
            }
        }

        return memo[col-1];
    }

    public static void main(String[] args) {
        int[][] grid= {
            {1,3,1},
            {1,5,1},
            {4,2,1}
        };

        System.out.println(new MinimumPathSum().minPathSum2(grid));
    }
}
