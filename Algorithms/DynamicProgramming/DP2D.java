package Algorithms.DynamicProgramming;

/*
===========================================================
Dynamic Programming – 2D Grid Problems
===========================================================
Topics Covered:
 1) Unique Paths I
 2) Unique Paths II (with obstacles)
 3) Minimum Path Sum
 4) Dungeon Game
 5) Triangle (min path top to bottom)
 6) Maximal Square (of 1s)
 7) Count Square Submatrices
 8) Cherry Pickup (max cherries, two passes)
===========================================================
*/

public class DP2D {

    // =========================================================================
    // 1️⃣  UNIQUE PATHS I  –  m×n grid, move right or down only
    // =========================================================================

    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1);
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                dp[j] += dp[j - 1];
        return dp[n - 1];
    }

    // =========================================================================
    // 2️⃣  UNIQUE PATHS II  –  with obstacles (0=free, 1=blocked)
    // =========================================================================

    public static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0] == 1 ? 0 : 1;
        for(int j = 1; j < n; j++) dp[j] = grid[0][j] == 1 ? 0 : dp[j-1];
        for(int i = 1; i < m; i++) {
            if(grid[i][0] == 1) dp[0] = 0;
            for(int j = 1; j < n; j++)
                dp[j] = grid[i][j] == 1 ? 0 : dp[j] + dp[j-1];
        }
        return dp[n - 1];
    }

    // =========================================================================
    // 3️⃣  MINIMUM PATH SUM  –  reach bottom-right with minimum sum
    // =========================================================================

    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for(int j = 1; j < n; j++) dp[j] = dp[j-1] + grid[0][j];
        for(int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for(int j = 1; j < n; j++)
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
        }
        return dp[n - 1];
    }

    // =========================================================================
    // 4️⃣  DUNGEON GAME  –  min initial health to rescue princess (bottom-right)
    // =========================================================================

    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = Math.max(1 - dungeon[m-1][n-1], 1);
        for(int j = n-2; j >= 0; j--)
            dp[m-1][j] = Math.max(dp[m-1][j+1] - dungeon[m-1][j], 1);
        for(int i = m-2; i >= 0; i--) {
            dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1);
            for(int j = n-2; j >= 0; j--)
                dp[i][j] = Math.max(Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j], 1);
        }
        return dp[0][0];
    }

    // =========================================================================
    // 5️⃣  TRIANGLE  –  min path sum from top to bottom
    // =========================================================================

    public static int minimumTotal(java.util.List<java.util.List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) dp[i] = triangle.get(n-1).get(i);
        for(int i = n-2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
        return dp[0];
    }

    // =========================================================================
    // 6️⃣  MAXIMAL SQUARE  –  largest square submatrix of all 1s
    // =========================================================================

    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, maxSide = 0;
        int[] dp = new int[n + 1];
        int prev = 0;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if(matrix[i-1][j-1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j-1], dp[j]), prev) + 1;
                    maxSide = Math.max(maxSide, dp[j]);
                } else dp[j] = 0;
                prev = tmp;
            }
        }
        return maxSide * maxSide;
    }

    // =========================================================================
    // 7️⃣  COUNT SQUARE SUBMATRICES WITH ALL ONES
    // =========================================================================

    public static int countSquares(int[][] matrix) {
        int count = 0;
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] > 0 && i > 0 && j > 0)
                    matrix[i][j] = Math.min(matrix[i-1][j-1],
                                   Math.min(matrix[i-1][j], matrix[i][j-1])) + 1;
                count += matrix[i][j];
            }
        return count;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Unique paths(3,7):  " + uniquePaths(3, 7));  // 28

        int[][] grid2 = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Unique paths w/obs: " + uniquePathsWithObstacles(grid2)); // 2

        int[][] grid3 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Min path sum:       " + minPathSum(grid3));   // 7

        int[][] dungeon = {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        System.out.println("Dungeon game:       " + calculateMinimumHP(dungeon)); // 7

        java.util.List<java.util.List<Integer>> tri = java.util.Arrays.asList(
            java.util.Arrays.asList(2),
            java.util.Arrays.asList(3,4),
            java.util.Arrays.asList(6,5,7),
            java.util.Arrays.asList(4,1,8,3)
        );
        System.out.println("Triangle min path:  " + minimumTotal(tri));  // 11

        char[][] mat = {{'1','0','1','0','0'},{'1','0','1','1','1'},
                        {'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println("Maximal square:     " + maximalSquare(mat));  // 4

        int[][] mat2 = {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println("Count squares:      " + countSquares(mat2));  // 15
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Problem               | Time   | Space
----------------------|--------|------
Unique Paths I/II     | O(m*n) | O(n)
Min Path Sum          | O(m*n) | O(n)
Dungeon Game          | O(m*n) | O(m*n)
Triangle              | O(n²)  | O(n)
Maximal Square        | O(m*n) | O(n)
Count Squares         | O(m*n) | O(1) in-place
===========================================================
*/
