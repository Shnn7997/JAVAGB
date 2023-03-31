public class EightQueens {
    private static final int SIZE = 8;

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];

        if (solve(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("Нет решения");
        }
    }

    private static boolean solve(int[][] board, int col) {
        if (col >= SIZE) {
            return true;
        }

        for (int row = 0; row < SIZE; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;

                if (solve(board, col + 1)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        for (int i = row, j = col; i < SIZE && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }

            System.out.println();
        }
    }
}
