class Solution {
    public long countSubstrings(String s) {
        long[][] dp = new long[10][10];
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            long[][] next = new long[10][10];
            for (int p = 1; p < 10; p++) {
                for (int r = 0; r < 10; r++) {
                    int mod = (r * 10 + d) % p;
                    next[p][mod] += dp[p][r];
                }
                next[p][d % p]++;
            }
            dp = next;
            res += dp[d][0];
        }
        return res;
    }
}
