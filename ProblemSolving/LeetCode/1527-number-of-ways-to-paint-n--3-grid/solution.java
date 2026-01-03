class Solution {
    int MOD = 1_000_000_007;
    public int numOfWays(int n) {
        long same = 6;
        long diff = 6;
        for (int i = 2; i <= n; i++) {
            long newSame = (same * 3 + diff * 2) % MOD;
            long newDiff = (same * 2 + diff * 2) % MOD;

            same = newSame;
            diff = newDiff;
        }
        return (int) ((same + diff) % MOD);
    }
}

