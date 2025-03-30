class Solution {
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        boolean markFirstRow = false;
        boolean markFirstCol = false;
        
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(matrix[i][j] == 0) {
                    if(i==0) markFirstRow = true;
                    if(j==0) markFirstCol = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i=1;i<r;i++) {
            for(int j=1;j<c;j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }  
            }
        }

        if(markFirstRow == true) {
            for(int j=0;j<c;j++) {
                matrix[0][j] = 0;
            }
        }

        if(markFirstCol == true) {
            for(int i=0;i<r;i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
