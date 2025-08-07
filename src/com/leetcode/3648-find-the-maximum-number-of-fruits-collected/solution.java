class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n=fruits.length,ans=0;
        int[][] dp=new int[n][n];
        dp[0][0]=fruits[0][0];
        dp[0][n-1]=fruits[0][n-1];
        dp[n-1][0]=fruits[n-1][0];
        for(int i=1;i<n-1;i++){
            dp[i][i]=dp[i-1][i-1]+fruits[i][i];
            for(int j=n-1,cnt=0;j>i&&cnt<=i;j--,cnt++){
                dp[i][j]=Math.max(dp[i-1][j],Math.max(dp[i-1][Math.min(n-1,j+1)],dp[i-1][Math.max(i,j-1)]))+fruits[i][j];
                dp[j][i]=Math.max(dp[j][i-1],Math.max(dp[Math.min(n-1,j+1)][i-1],dp[Math.max(i,j-1)][i-1]))+fruits[j][i];
            }
        }
        dp[n-1][n-1]=dp[n-2][n-1]+dp[n-1][n-2]+dp[n-2][n-2]+fruits[n-1][n-1];
        return dp[n-1][n-1];
    }
}
