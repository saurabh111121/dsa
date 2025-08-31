class Solution {
    long[] b, c; long d; int k;
    public int[] minDifference(int n, int k) {
        this.k = k; b = new long[k]; c = new long[k];
        Arrays.fill(b, 1); Arrays.fill(c, 1); d = Long.MAX_VALUE;
        List<int[]> f = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            int x = 0; while (n % i == 0) { n /= i; x++; }
            if (x > 0) f.add(new int[]{i, x});
        }
        if (n > 1) f.add(new int[]{n, 1});
        dfs(f, 0);
        return Arrays.stream(b).mapToInt(x -> (int)x).toArray();
    }
    void dfs(List<int[]> f, int i) {
        if (i == f.size()) {
            long mn = c[0], mx = c[0];
            for (int j = 1; j < k; j++) { mn = Math.min(mn, c[j]); mx = Math.max(mx, c[j]); }
            if (mx - mn < d) { d = mx - mn; b = c.clone(); }
            return;
        }
        int p = f.get(i)[0], e = f.get(i)[1]; go(f, i, 0, e, p, new int[k]);
    }
    void go(List<int[]> f, int i, int pos, int r, int p, int[] a) {
        if (pos == k - 1) { a[pos] = r; long[] old = c.clone();
            for (int j = 0; j < k; j++) { long x = 1; for (int y = 0; y < a[j]; y++) x *= p; c[j] *= x; }
            dfs(f, i + 1); c = old; return;
        }
        for (int t = 0; t <= r; t++) { a[pos] = t; go(f, i, pos + 1, r - t, p, a); }
        
    }
}
