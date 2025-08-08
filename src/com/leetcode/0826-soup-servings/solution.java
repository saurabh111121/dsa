class Solution {
    public double solve(int a , int b, double prob, double[][] dp) {
        if(a <= 0 && b <= 0) return 0.5;
        if(a <= 0) return 1;
        if(b <= 0) return 0;
        if(dp[a][b] != -1) return dp[a][b]; 
        double one = solve(a-100,b-0,prob*0.25,dp);
        double two = solve(a-75,b-25,prob*0.25,dp);
        double three = solve(a-50,b-50,prob*0.25,dp);
        double four = solve(a-25,b-75,prob*0.25,dp);
        return dp[a][b] = (one+two+three+four)/4;
    }
    public double soupServings(int n) {
        if(n>4800) return 1;
        double [][] dp = new double[n+1][n+1];
        for (int i = 0 ; i <= n ; ++i) {
            for(int j = 0 ; j <= n ; ++j) {
                dp[i][j] = -1;
            }
        }
        double ans = solve(n,n,0.25,dp);
        return ans;
    }
}
