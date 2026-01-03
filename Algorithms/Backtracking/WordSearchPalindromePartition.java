package Algorithms.Backtracking;

/*
===========================================================
Backtracking – Word Search & Palindrome Partitioning
===========================================================
Topics Covered:
 1) Word Search I (find word in 2D grid)
 2) Word Search II (find all words – see Tries/TrieAdvanced)
 3) Palindrome Partitioning I (all partitions)
 4) Palindrome Partitioning II (min cuts)
 5) Rat in a Maze
 6) Letter Combinations of a Phone Number
===========================================================
*/

import java.util.*;

public class WordSearchPalindromePartition {

    // =========================================================================
    // 1️⃣  WORD SEARCH I  –  Does word exist in 2D grid?
    // =========================================================================

    public static boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                if (dfsWord(board, word, r, c, 0)) return true;
        return false;
    }

    private static boolean dfsWord(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length()) return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return false;
        if (board[r][c] != word.charAt(idx)) return false;
        char temp = board[r][c];
        board[r][c] = '#';   // mark visited
        boolean found = dfsWord(board, word, r+1, c, idx+1)
                     || dfsWord(board, word, r-1, c, idx+1)
                     || dfsWord(board, word, r, c+1, idx+1)
                     || dfsWord(board, word, r, c-1, idx+1);
        board[r][c] = temp;  // restore
        return found;
    }

    // =========================================================================
    // 2️⃣  PALINDROME PARTITIONING I  –  All partitions where every part is palindrome
    // =========================================================================

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        // precompute palindrome table
        boolean[][] isPalin = precomputePalin(s);
        partitionHelper(s, 0, isPalin, new ArrayList<>(), result);
        return result;
    }

    private static void partitionHelper(String s, int start, boolean[][] isPalin,
                                         List<String> current, List<List<String>> result) {
        if (start == s.length()) { result.add(new ArrayList<>(current)); return; }
        for (int end = start; end < s.length(); end++) {
            if (isPalin[start][end]) {
                current.add(s.substring(start, end + 1));
                partitionHelper(s, end + 1, isPalin, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    // =========================================================================
    // 3️⃣  PALINDROME PARTITIONING II  –  Min cuts (DP)
    // =========================================================================

    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPalin = precomputePalin(s);
        int[] dp = new int[n];   // dp[i] = min cuts for s[0..i]
        for (int i = 0; i < n; i++) {
            if (isPalin[0][i]) { dp[i] = 0; continue; }
            dp[i] = i;  // worst case: cut every char
            for (int j = 1; j <= i; j++)
                if (isPalin[j][i]) dp[i] = Math.min(dp[i], dp[j-1] + 1);
        }
        return dp[n - 1];
    }

    private static boolean[][] precomputePalin(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--)
            for (int j = i; j < n; j++)
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i+1][j-1]);
        return dp;
    }

    // =========================================================================
    // 4️⃣  RAT IN A MAZE  –  Find all paths from (0,0) to (n-1,n-1)
    // =========================================================================

    public static List<String> findPaths(int[][] maze) {
        int n = maze.length;
        List<String> result = new ArrayList<>();
        if (maze[0][0] == 0 || maze[n-1][n-1] == 0) return result;
        boolean[][] visited = new boolean[n][n];
        mazeDFS(maze, 0, 0, n, "", visited, result);
        return result;
    }

    private static final int[] DR = {1, 0, -1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static final char[] DIR = {'D', 'R', 'U', 'L'};

    private static void mazeDFS(int[][] maze, int r, int c, int n,
                                  String path, boolean[][] visited, List<String> result) {
        if (r == n-1 && c == n-1) { result.add(path); return; }
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d], nc = c + DC[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && maze[nr][nc] == 1)
                mazeDFS(maze, nr, nc, n, path + DIR[d], visited, result);
        }
        visited[r][c] = false;
    }

    // =========================================================================
    // 5️⃣  LETTER COMBINATIONS OF PHONE NUMBER
    // =========================================================================

    private static final String[] PHONE = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        phoneHelper(digits, 0, new StringBuilder(), result);
        return result;
    }

    private static void phoneHelper(String digits, int idx,
                                     StringBuilder sb, List<String> result) {
        if (idx == digits.length()) { result.add(sb.toString()); return; }
        String letters = PHONE[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            phoneHelper(digits, idx + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("=== Word Search ===");
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println("ABCCED: " + exist(board, "ABCCED"));  // true
        System.out.println("SEE:    " + exist(board, "SEE"));     // true
        System.out.println("ABCB:   " + exist(board, "ABCB"));    // false

        System.out.println("\n=== Palindrome Partitioning ===");
        System.out.println("partition('aab'): " + partition("aab"));
        System.out.println("minCut('aab'):    " + minCut("aab"));      // 1
        System.out.println("minCut('aaabaa'): " + minCut("aaabaa"));   // 1

        System.out.println("\n=== Rat in a Maze ===");
        int[][] maze = {{1,0,0,0},{1,1,0,1},{1,1,0,0},{0,1,1,1}};
        System.out.println("Paths: " + findPaths(maze));

        System.out.println("\n=== Letter Combinations ===");
        System.out.println("'23': " + letterCombinations("23"));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation                  | Time          | Space
---------------------------|---------------|------
Word Search I              | O(m*n*4^L)    | O(L) stack
Palindrome Partitioning I  | O(2^n * n)    | O(n²)
Palindrome Partitioning II | O(n²)         | O(n²)
Rat in a Maze              | O(4^(m*n))    | O(m*n)
Letter Combinations        | O(4^n * n)    | O(n)
===========================================================
*/
