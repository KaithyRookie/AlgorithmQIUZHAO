import java.util.Arrays;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * @author kaithy.xu
 * @date 2020-08-14 14:23
 */
public class SudokuSolver {

    public void solveSudoku(char[][] board) {

        int n =3;
        int N = n * n;

        boolean[][] rows = new boolean[N][N+1];
        boolean[][] cols = new boolean[N][N+1];
        boolean[][][] boxes = new boolean[3][3][N+1];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j] - '0';
                if(1 <= num && num <= 9) {
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[i/3][j/3][num] = true;
                }
            }
        }

        placeNumber(board, rows, cols, boxes, 0, 0);
    }

    private boolean placeNumber(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][][] boxes, int row, int col) {
        if(col == board[0].length) {
            col = 0;
            row++;
            if(row == board.length) {
                return true;
            }
        }
        if(board[row][col] == '.') {
            for (int i = 1; i <= 9; i++) {
                boolean canUsed = !(rows[row][i] || cols[col][i] || boxes[row/3][col/3][i]);

                if(canUsed) {
                    board[row][col] = (char)(i+'0');

                    rows[row][i] = true;
                    cols[col][i] = true;
                    boxes[row/3][col/3][i] = true;

                    if(placeNumber(board, rows, cols, boxes,row, col+1)) {
                        return true;
                    }

                    board[row][col] = '.';

                    rows[row][i] = false;
                    cols[col][i] = false;
                    boxes[row/3][col/3][i] = false;
                }

            }

        }else {
            return placeNumber(board, rows, cols, boxes, row, col+1);
        }

        return false;

    }


    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};


        new SudokuSolver().solveSudoku(board);

        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

}
