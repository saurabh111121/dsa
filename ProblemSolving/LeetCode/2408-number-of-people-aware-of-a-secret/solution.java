class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        long[] dp = new long[n + 1];
        dp[1] = 1;  // day 1: one person knows
        for (int i = 1; i <= n; i++) {
            for (int j = i + delay; j < i + forget && j <= n; j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }
        long ans = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            ans = (ans + dp[i]) % MOD;
        }
        
        return (int) ans;
        
    }
}
