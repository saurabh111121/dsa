class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        long[][] dp0 = new long[zero+1][one+1];
        long[][] dp1 = new long[zero+1][one+1];

        for(int i=1;i<=Math.min(zero,limit);i++){
            dp0[i][0] = 1;
        }
        
        for(int j=1;j<=Math.min(one, limit); j++){
            dp1[0][j] = 1;
        }

        for(int z=1;z<=zero;z++){
            for(int l=1;l<=one;l++){
                dp0[z][l] = (dp0[z-1][l] + dp1[z-1][l]) % MOD;
                if(z - limit - 1 >= 0) {
                    dp0[z][l] = (dp0[z][l] - dp1[z-limit-1][l] + MOD) % MOD;
                }
                dp1[z][l] = (dp0[z][l-1] + dp1[z][l-1]) % MOD;
                if (l - limit - 1 >= 0) {
                    dp1[z][l] = (dp1[z][l] - dp0[z][l-limit-1] + MOD) % MOD;
                }
            }
        }
        return (int)((dp0[zero][one]+dp1[zero][one]) % MOD);
    }
}
