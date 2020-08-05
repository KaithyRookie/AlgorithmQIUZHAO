package algorithm.MiddleQuestion;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 解题思路：
 * 动态规划，找到递推公式：对于空地而言，位置f(i,j)处而言，其走法是 f(i-1,j) + f(i,j-1) 两者之和。
 * 对于阻塞块，f(i,j)=0
 * 利用一个二维数组memo来保存每个位置的走法个数
 *
 * 在遍历第一行与第一列时，遇到阻塞块后，之后的值都将是0，否则都是1
 *
 * 时间复杂度 O(M*N)
 * 空间复杂度 O(M*N)
 *
 *
 * @author kaithy.xu
 * @date 2020-08-05 20:28
 */
public class UniquePathsWithObstacles {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) {
            return 0;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[][] memo = new int[row][col];
        memo[0][0] = 1;
        for (int i = 0; i < row; i++) {
            if(obstacleGrid[i][0] == 0) {
                memo[i][0] = 1;
            }else {
                break;
            }
        }

        for (int i = 0; i < col; i++) {
            if(obstacleGrid[0][i] == 0) {
                memo[0][i] = 1;
            }else {
                break;
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if(obstacleGrid[i][j] == 0)
                    memo[i][j] = memo[i-1][j] + memo[i][j-1];
                else
                    memo[i][j] = 0;
            }
        }

        return memo[row-1][col-1];
    }

    public static void main(String[] args) {
        int[][] array = {{0,0},{1,1},{0,0}};

        System.out.println(new UniquePathsWithObstacles().uniquePathsWithObstacles(array));
    }

}
