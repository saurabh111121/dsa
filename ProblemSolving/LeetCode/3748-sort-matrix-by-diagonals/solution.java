class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        for (int d = 0; d < n; d++) {
            sortDiagonal(grid, d, 0, false);
        }

        for (int d = 1; d < n; d++) {
            sortDiagonal(grid, 0, d, true);
        }

        return grid;
    }

    private void sortDiagonal(int[][] grid, int row, int col, boolean ascending) {
        int n = grid.length;
        int[] freq = new int[200001];
        int offset = 100000;

        int r = row, c = col;
        while (r < n && c < n) {
            freq[grid[r][c] + offset]++;
            r++;
            c++;
        }

        r = row;
        c = col;
        int index = ascending ? 0 : 200000;

        while (r < n && c < n) {
            while (freq[index] == 0) {
                index += ascending ? 1 : -1;
            }
            grid[r][c] = index - offset;
            freq[index]--;
            r++;
            c++;
        }
    }
}
