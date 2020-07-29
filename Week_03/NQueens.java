import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * @author kaithy.xu
 * @date 2020-07-29 20:38
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {

        //使用数组记录皇后的位置，下标为行，值为皇后所在的列
        int[] record = new int[n];

        List<List<String>> result = new ArrayList<>();

        calNQueue(0, n, record, result);

        return result;
    }

    /**
     * 递归遍历
     * @param row
     * @param n
     * @param record
     * @param result
     */
    private void calNQueue(int row, int n, int[] record, List<List<String>> result) {
        if(row == n) {
            recordQueue(record, n, result);
            return;
        }

        for (int column = 0; column < n; column++) {
            if(isOk(column,record,row,n)) {
                //当通过校验后，就记录下当前皇后的放置位置，继续向下递归遍历
                record[row] = column;
                calNQueue(row+1, n, record, result);
            }
        }
    }

    /**
     * 记录棋盘
     * @param record
     * @param n
     * @param result
     */
    private void recordQueue(int[] record, int n, List<List<String>> result) {
        List<String> tmp = new ArrayList<>(n);
        for (int row = 0; row < n; row++) {
            StringBuilder builder = new StringBuilder();
            for (int column = 0; column < n; column++) {

                if(record[row] == column) {
                    builder.append("Q");
                }else {
                    builder.append(".");
                }
            }
            tmp.add(builder.toString());
        }
        result.add(tmp);
    }

    /**
     * 从当前行开始向上判断当前列，左右两个斜对角线是否已经保存了皇后
     * @param column
     * @param record
     * @param row
     * @param n
     * @return
     */
    private boolean isOk(int column, int[] record, int row, int n) {
        int left = column-1,right = column+1;
        for (int i = row-1; i >= 0; --i) {
            if(record[i] == column) {
                return false;
            }
            //右对角线上是否存在
            if(right < n && record[i] == right) {
                return false;
            }

            if(left >= 0 && record[i] == left) {
                return false;
            }

            left--;
            right++;
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> result = new NQueens().solveNQueens(n);

        for (List<String> list : result) {
            System.out.println(list);
        }
    }
}
