class Solution {
    public int numberOfWays(int n, int x) {
        int MOD = (int) (1e9 + 7);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int limit = (int) Math.ceil(Math.pow(n, 1.0 / x));
        for (int i = 1; i <= limit; i++) {
            long power = (long) Math.pow(i, x); 
            for (int j = n; j >= power; j--) { 
                dp[j] = (int) ((dp[j] + dp[j - (int) power]) % MOD); 
            }
        }
        return dp[n];
    }
}
