class Solution {
    public int numTilings(int n) {
        int MOD = 1_000_000_007;
        long[] dp = new long[Math.max(n + 1, 4)];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] % MOD + dp[i - 3]) % MOD;
        }
        return (int)dp[n];
    }
}

