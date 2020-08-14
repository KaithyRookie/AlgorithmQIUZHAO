/**
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 终极解法：利用位运算进行统计
 * 
 * @author kaithy.xu
 * @date 2020-08-10 21:35
 */
public class NQueensII {

    private int size;
    private int count;

    public int totalNQueens(int n) {
        count = 0;
        size = (1 << n) -1;
        solve(0,0,0);
        return count;
    }

    private void solve(int row, int ld, int rd) {
        if(row == size) {
            count++;
            return;
        }

        int pos = size & (~(row | ld | rd));
        while (0 != pos) {
            int p = pos & (-pos);
            pos -= p;
            solve(row | p, (ld | p) << 1, (rd | p) >> 1 );
        }
    }


    /**
     * 在N皇后的基础上进行改动，每找到一种放置方案，统计+1
     * @param n
     * @return
     */
    public int totalQueens2(int n) {
        count=0;
        int[] region = new int[n];
        calculate(0, n,region);
        return count;
    }

    private void calculate(int row, int n, int[] region) {
        if(row == n) {
            ++count;
            return;
        }

        for (int column = 0; column < n; column++) {

            if(checkIsOk(row, column, region, n)) {
                region[row] = column;
                calculate(row+1, n, region);
            }
        }
    }

    private boolean checkIsOk(int row, int col, int[] region, int n) {
        int leftUp = col-1, rightUp=col+1;
        for (int i = row-1; i >= 0 ; --i) {
            if(region[i] == col) {
                return false;
            }

            if(leftUp >= 0) {
                if(region[i] == leftUp) {
                    return false;
                }
            }

            if(rightUp < n) {
                if(region[i] == rightUp) {
                    return false;
                }
            }

            --leftUp;
            ++rightUp;
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(new NQueensII().totalQueens2(n));
    }
}
