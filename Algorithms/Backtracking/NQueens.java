package Algorithms.Backtracking;

/*
===========================================================
Backtracking – N-Queens & Sudoku Solver
===========================================================
Topics Covered:
 1) N-Queens – find all solutions
 2) N-Queens II – count solutions only
 3) Sudoku Solver
===========================================================
*/

import java.util.*;

public class NQueens {

    // =========================================================================
    // 1️⃣  N-QUEENS  –  Place N queens on N×N board, no two attack each other
    // =========================================================================

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        nQueensHelper(board, 0, result);
        return result;
    }

    private static void nQueensHelper(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(boardToList(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                nQueensHelper(board, row + 1, result);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;
        // check column above
        for (int i = 0; i < row; i++) if (board[i][col] == 'Q') return false;
        // check upper-left diagonal
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;
        // check upper-right diagonal
        for (int i = row-1, j = col+1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q') return false;
        return true;
    }

    /** Optimised using bitmask – O(n!) but faster constant factor */
    public static List<List<String>> solveNQueensBitmask(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        solveBitmask(queens, n, 0, 0, 0, 0, result);
        return result;
    }

    private static void solveBitmask(int[] queens, int n, int row,
                                      int cols, int diag1, int diag2,
                                      List<List<String>> result) {
        if (row == n) { result.add(queensToBoard(queens, n)); return; }
        int available = ((1 << n) - 1) & ~(cols | diag1 | diag2);
        while (available != 0) {
            int bit = available & (-available);
            available -= bit;
            int col = Integer.numberOfTrailingZeros(bit);
            queens[row] = col;
            solveBitmask(queens, n, row + 1,
                    cols | bit, (diag1 | bit) << 1, (diag2 | bit) >> 1, result);
            queens[row] = -1;
        }
    }

    // =========================================================================
    // 2️⃣  N-QUEENS II  –  Count only
    // =========================================================================

    public static int totalNQueens(int n) {
        return countQueens(n, 0, 0, 0, 0);
    }

    private static int countQueens(int n, int row, int cols, int diag1, int diag2) {
        if (row == n) return 1;
        int count = 0;
        int available = ((1 << n) - 1) & ~(cols | diag1 | diag2);
        while (available != 0) {
            int bit = available & (-available);
            available -= bit;
            count += countQueens(n, row + 1, cols | bit, (diag1 | bit) << 1, (diag2 | bit) >> 1);
        }
        return count;
    }

    // =========================================================================
    // 3️⃣  SUDOKU SOLVER
    // =========================================================================

    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    private static boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') continue;
                for (char ch = '1'; ch <= '9'; ch++) {
                    if (isValidSudoku(board, r, c, ch)) {
                        board[r][c] = ch;
                        if (solve(board)) return true;
                        board[r][c] = '.';
                    }
                }
                return false;  // no valid digit works → backtrack
            }
        }
        return true;
    }

    private static boolean isValidSudoku(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) return false;          // row check
            if (board[i][col] == ch) return false;          // col check
            // 3x3 box check
            int br = 3*(row/3) + i/3, bc = 3*(col/3) + i%3;
            if (board[br][bc] == ch) return false;
        }
        return true;
    }

    // ── helpers ──────────────────────────────────────────────────────────────
    private static List<String> boardToList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) list.add(new String(row));
        return list;
    }

    private static List<String> queensToBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int q : queens) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[q] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("=== N-Queens (n=4) ===");
        List<List<String>> sol = solveNQueens(4);
        sol.forEach(b -> { b.forEach(System.out::println); System.out.println(); });

        System.out.println("N-Queens count (n=8): " + totalNQueens(8));  // 92

        System.out.println("\n=== Sudoku Solver ===");
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        for (char[] row : board) System.out.println(Arrays.toString(row));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation         | Time       | Space
------------------|------------|------
N-Queens          | O(n!)      | O(n²)
N-Queens Bitmask  | O(n!)      | O(n)
N-Queens count    | O(n!)      | O(n)
Sudoku Solver     | O(9^m)     | O(1) m=empty cells
===========================================================
*/
