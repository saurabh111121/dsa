class Solution {
    public void setZeroes(int[][] matrix) {
        int rowsL = matrix.length;
        int colL = matrix[0].length;

        int[] r = new int[rowsL];
        int[] c = new int[colL];

        for(int i = 0;i<rowsL;i++) {
            for(int j = 0; j< colL;j++) {
                if(matrix[i][j] == 0) {
                    r[i] = -1;
                    c[j] = -1;
                }
            }
        }

        for(int i = 0;i<rowsL;i++) {
            for(int j = 0; j< colL;j++) {
                if(r[i] == -1 || c[j] == -1) {
                    matrix[i][j] =0;
                }
            }
        }
        

        for(int i = 0;i<rowsL;i++) {
            for(int j = 0; j< colL;j++) {
               System.out.print(matrix[i][j]); 
            }
            System.out.println();
        }
        System.out.println();
        
    }
}
