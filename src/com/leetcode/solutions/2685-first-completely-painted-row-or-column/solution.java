class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
         int m = mat.length, n = mat[0].length;

        // Map to store the coordinates of each number in the matrix
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }

        // Arrays to track the painted counts of rows and columns
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // Iterate through the arr
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int[] pos = map.get(num);
            int row = pos[0];
            int col = pos[1];

            // Paint the cell
            rowCount[row]++;
            colCount[col]++;

            // Check if the row or column is fully painted
            if (rowCount[row] == n || colCount[col] == m) {
                return i;
            }
        }

        // Should never reach here because a row/column will eventually be fully painted
        return -1;
        
    }
}
