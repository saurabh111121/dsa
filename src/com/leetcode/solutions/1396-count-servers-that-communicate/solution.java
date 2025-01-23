class Solution {
    public int countServers(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;

        // Arrays to store the count of servers in each row and column
        int[] rowCount = new int[rows];
        int[] colCount = new int[cols];

        // Count servers in each row and column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        // Count communicative servers
        int communicativeServers = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    communicativeServers++;
                }
            }
        }

        return communicativeServers;
    }
}
