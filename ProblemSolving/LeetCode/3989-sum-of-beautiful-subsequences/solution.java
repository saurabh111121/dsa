class Solution {
    private static final int MOD = 1000000007;
    
    class BIT {
        int n, tree[];
        BIT(int n) { this.n = n; tree = new int[n+1]; }
        void add(int i, int v) {
            for (; i <= n; i += i & -i) tree[i] = (tree[i] + v) % MOD;
        }
        int get(int i) {
            int r = 0;
            for (; i > 0; i -= i & -i) r = (r + tree[i]) % MOD;
            return r;
        }
    }
    
    public int totalBeauty(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        
        java.util.List<Integer>[] divs = new java.util.List[max+1];
        for (int i = 0; i <= max; i++) divs[i] = new java.util.ArrayList<>();
        for (int d = 1; d <= max; d++)
            for (int m = d; m <= max; m += d)
                divs[m].add(d);
        
        int[] phi = new int[max+1];
        for (int i = 0; i <= max; i++) phi[i] = i;
        for (int p = 2; p <= max; p++)
            if (phi[p] == p)
                for (int m = p; m <= max; m += p)
                    phi[m] = phi[m] / p * (p - 1);
        
        BIT[] bit = new BIT[max+1];
        int[] sum = new int[max+1];
        
        for (int a : nums) {
            for (int h : divs[a]) {
                if (bit[h] == null) bit[h] = new BIT(max / h + 2);
                int r = a / h;
                int ways = (1 + (r > 1 ? bit[h].get(r - 1) : 0)) % MOD;
                bit[h].add(r, ways);
                sum[h] = (sum[h] + ways) % MOD;
            }
        }
        
        long ans = 0;
        for (int h = 1; h <= max; h++)
            if (bit[h] != null)
                ans = (ans + 1L * phi[h] * sum[h]) % MOD;
        
        return (int) ans;
    }
}

