class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        int i = 0, j = 0, dir = 1, idx = 0;
        for (int cnt = 0; cnt < m * n; cnt++){
            res[idx++] = mat[i][j];
            if (dir == 1){
                if (j == n - 1){
                    i++;
                    dir = -1;
                } else if (i == 0){
                    j++;
                    dir = -1;
                } else {
                    i--;
                    j++;
                }
            } else {
                if (i == m - 1){
                    j++;
                    dir = 1;
                } else if (j == 0){
                    i++;
                    dir = 1;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return res;

    }
}
