class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;

        // checking Row   
        for(int i=0;i<n;i++) { 
            boolean[] seen = new boolean[n+1];
            for(int j=0;j<n;j++) {
                int v = matrix[i][j];
                if(seen[v]){ // value must be in [1, n] and not repeated
                    return false;
                }
                seen[v] = true;
            }
        }

        // Checking column
        for(int j=0;j<n;j++) {
            boolean[] seen = new boolean[n+1];
            for(int i=0;i<n;i++) {
                int v = matrix[i][j];
                if(seen[v]){ // value must be in [1, n] and not repeated
                    return false;
                }
                seen[v] = true;
            }
        }
        return true;
        
    }
}
