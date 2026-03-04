class Solution {
    public int numSpecial(int[][] mat) {
        int totalCount = 0;
        int r = mat.length;
        int c = mat[0].length;
        int[] rowCount = new int[r];
        int[] colCount = new int[c];

        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(mat[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(mat[i][j] == 1) {
                    if(rowCount[i] == 1 && colCount[j] == 1) {
                        totalCount++;
                    }
                }
            }
        }

        return totalCount;
        
    }
}
