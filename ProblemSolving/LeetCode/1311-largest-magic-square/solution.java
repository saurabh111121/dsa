class Solution {
    public int largestMagicSquare(int[][] grid) {
        int n = grid.length; int m = grid[0].length;
        int prefixRow[][] = new int[n][m+1];
        int prefixCol[][] = new int[n+1][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                prefixRow[i][j+1] = prefixRow[i][j] + grid[i][j];
                prefixCol[i+1][j] = prefixCol[i][j] + grid[i][j];
            }
        }
        int maxSize = Math.min(m, n);
        while(maxSize >= 2){
            for(int i=0; i+maxSize<=n; i++){
                for(int j=0; j+maxSize<=m; j++){
                    if(check(grid, i, j, prefixRow, prefixCol, maxSize)){
                        return maxSize;
                    }
                }
            }
            maxSize--;
        }
        return 1;
    }
    
    public boolean check(int grid[][], int row, int col, int prefixRow[][], int prefixCol[][], int size){
        int target = prefixRow[row][col + size] - prefixRow[row][col];

        for(int i=row; i<row+size; i++){
            if(prefixRow[i][col + size] - prefixRow[i][col] != target){
                return false;
            }
        }

        for(int j=col; j<col+size; j++){
            if(prefixCol[row+size][j] - prefixCol[row][j] != target){
                return false;
            }
        }

        int d1 = 0; int d2 = 0;
        for(int i=0; i<size; i++){
            d1 += grid[row + i][col + i];
            d2 += grid[row + i][col + size - 1 - i];
        }
        if(d1 != target || d2 != target) return false;

        return true;
    }
}
