package Patterns.DPPatterns;

import java.util.*;

/**
 * Dynamic Programming Patterns – Templates & Recipes
 * ===================================================
 * Topics Covered:
 *  1. Top-Down (Memoization) Template
 *  2. Bottom-Up (Tabulation) Template
 *  3. State Compression / Space Optimization Template
 *  4. DP on Intervals (Burst Balloons style)
 *  5. DP on Trees (rerooting / diameter style)
 *  6. Bitmask DP (Travelling Salesman / assignment)
 *  7. Digit DP (Count numbers with property in [L,R])
 *  8. DP with Monotonic Deque (Sliding Window DP)
 */
public class DPPatterns {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  TOP-DOWN MEMOIZATION TEMPLATE
    // ─────────────────────────────────────────────────────────────
    // Example: Minimum cost to reach cell (m-1, n-1) in a grid moving right/down
    private static int[][] gridMemo;

    public static int minCostGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        gridMemo = new int[m][n];
        for(int[] row : gridMemo) Arrays.fill(row, -1);
        return mcHelper(grid, m - 1, n - 1);
    }

    private static int mcHelper(int[][] g, int r, int c) {
        if(r == 0 && c == 0) return g[0][0];
        if(r < 0 || c < 0) return Integer.MAX_VALUE / 2;
        if(gridMemo[r][c] != -1) return gridMemo[r][c];
        return gridMemo[r][c] = g[r][c] + Math.min(mcHelper(g, r - 1, c), mcHelper(g, r, c - 1));
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  BOTTOM-UP TABULATION TEMPLATE
    // ─────────────────────────────────────────────────────────────
    public static int minCostGridTab(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int j = 1; j < n; j++) dp[0][j] = dp[0][j-1] + grid[0][j];
        for(int i = 1; i < m; i++) dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++)
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
        return dp[m-1][n-1];
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  SPACE-OPTIMIZED (rolling array) TEMPLATE
    // ─────────────────────────────────────────────────────────────
    public static int minCostGridOpt(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] prev = new int[n];
        prev[0] = grid[0][0];
        for(int j = 1; j < n; j++) prev[j] = prev[j-1] + grid[0][j];
        for(int i = 1; i < m; i++) {
            int[] curr = new int[n];
            curr[0] = prev[0] + grid[i][0];
            for(int j = 1; j < n; j++)
                curr[j] = grid[i][j] + Math.min(prev[j], curr[j-1]);
            prev = curr;
        }
        return prev[n-1];
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  DP ON INTERVALS  (Burst Balloons – LeetCode 312)
    // ─────────────────────────────────────────────────────────────
    // dp[i][j] = max coins when bursting all balloons between i and j (exclusive borders 1-padded)
    public static int burstBalloons(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        for(int i = 0; i < n; i++) arr[i + 1] = nums[i];
        int N = n + 2;
        int[][] dp = new int[N][N];
        for(int len = 2; len < N; len++) {
            for(int left = 0; left < N - len; left++) {
                int right = left + len;
                for(int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(dp[left][right],
                        arr[left] * arr[k] * arr[right] + dp[left][k] + dp[k][right]);
                }
            }
        }
        return dp[0][N - 1];
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  DP ON TREES  (Max Independent Set in Tree)
    // ─────────────────────────────────────────────────────────────
    // dp[node][0] = max sum if node NOT taken
    // dp[node][1] = max sum if node IS taken
    static List<List<Integer>> treeAdj;
    static int[] treeVals;
    static int[][] treeDp;

    public static int maxIndependentSetTree(int n, int[] vals, int[][] edges) {
        treeAdj = new ArrayList<>();
        for(int i = 0; i < n; i++) treeAdj.add(new ArrayList<>());
        for(int[] e : edges) { treeAdj.get(e[0]).add(e[1]); treeAdj.get(e[1]).add(e[0]); }
        treeVals = vals;
        treeDp = new int[n][2];
        dfsTree(0, -1);
        return Math.max(treeDp[0][0], treeDp[0][1]);
    }

    private static void dfsTree(int u, int parent) {
        treeDp[u][1] = treeVals[u];  // take u
        treeDp[u][0] = 0;            // skip u
        for(int v : treeAdj.get(u)) {
            if(v == parent) continue;
            dfsTree(v, u);
            treeDp[u][0] += Math.max(treeDp[v][0], treeDp[v][1]); // skip u → children free
            treeDp[u][1] += treeDp[v][0];                          // take u → children skipped
        }
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  BITMASK DP  (Travelling Salesman Problem)
    // ─────────────────────────────────────────────────────────────
    // dp[mask][i] = min cost to visit all cities in mask, ending at city i
    public static int tsp(int[][] dist) {
        int n = dist.length;
        int FULL = (1 << n) - 1;
        int[][] dp = new int[1 << n][n];
        for(int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dp[1][0] = 0;  // start at city 0
        for(int mask = 1; mask <= FULL; mask++) {
            for(int u = 0; u < n; u++) {
                if((mask & (1 << u)) == 0 || dp[mask][u] == Integer.MAX_VALUE / 2) continue;
                for(int v = 0; v < n; v++) {
                    if((mask & (1 << v)) != 0) continue;
                    int nMask = mask | (1 << v);
                    dp[nMask][v] = Math.min(dp[nMask][v], dp[mask][u] + dist[u][v]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int u = 1; u < n; u++)
            ans = Math.min(ans, dp[FULL][u] + dist[u][0]);
        return ans;
    }

    // ─────────────────────────────────────────────────────────────
    // 7️⃣  DIGIT DP  (Count numbers in [1, N] with digit sum divisible by k)
    // ─────────────────────────────────────────────────────────────
    static int digitK;
    static int[][][] digitMemo;
    static char[] digitStr;

    public static int countDivisibleDigitSum(int N, int k) {
        digitK = k;
        digitStr = Integer.toString(N).toCharArray();
        int len = digitStr.length;
        digitMemo = new int[len][k][2];
        for(int[][] a : digitMemo) for(int[] b : a) Arrays.fill(b, -1);
        return digitDp(0, 0, 1) - 1; // subtract 0 itself
    }

    // pos, currentMod, tight
    private static int digitDp(int pos, int mod, int tight) {
        if(pos == digitStr.length) return mod == 0 ? 1 : 0;
        if(digitMemo[pos][mod][tight] != -1) return digitMemo[pos][mod][tight];
        int limit = tight == 1 ? digitStr[pos] - '0' : 9;
        int res = 0;
        for(int d = 0; d <= limit; d++) {
            res += digitDp(pos + 1, (mod + d) % digitK, tight == 1 && d == limit ? 1 : 0);
        }
        return digitMemo[pos][mod][tight] = res;
    }

    // ─────────────────────────────────────────────────────────────
    // 8️⃣  SLIDING WINDOW DP  (Jump Game with cost – LeetCode 1696)
    // ─────────────────────────────────────────────────────────────
    // From index i you can jump to i+1..i+k, cost = nums[j]-nums[i]
    // Find min cost to reach last index
    public static int jumpGameVI(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(0);
        for(int i = 1; i < n; i++) {
            // remove indices outside window
            while(!dq.isEmpty() && dq.peekFirst() < i - k) dq.pollFirst();
            dp[i] = dp[dq.peekFirst()] + nums[i];
            // maintain decreasing deque
            while(!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) dq.pollLast();
            dq.addLast(i);
        }
        return dp[n - 1];
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Min cost grid (memo) = " + minCostGrid(grid));   // 7
        System.out.println("Min cost grid (tab)  = " + minCostGridTab(grid)); // 7
        System.out.println("Min cost grid (opt)  = " + minCostGridOpt(grid)); // 7

        System.out.println("\nBurst Balloons = " + burstBalloons(new int[]{3,1,5,8})); // 167

        int[] vals = {1, 2, 3, 4};
        int[][] edges = {{0,1},{1,2},{1,3}};
        System.out.println("Max Independent Set in Tree = " + maxIndependentSetTree(4, vals, edges)); // 7

        int[][] dist = {{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};
        System.out.println("TSP = " + tsp(dist)); // 80

        System.out.println("Digit DP (sum div 3 in [1,100]) = " + countDivisibleDigitSum(100, 3)); // 33

        System.out.println("Jump Game VI = " + jumpGameVI(new int[]{1,-1,-2,4,-7,3}, 2)); // 7
    }
}

/*
 * ┌───────────────────────────────────┬────────────────┬─────────────┐
 * │ Pattern                           │ Time           │ Space       │
 * ├───────────────────────────────────┼────────────────┼─────────────┤
 * │ Memo / Tab (grid)                 │ O(m·n)         │ O(m·n)/O(n) │
 * │ DP on Intervals (Burst Balloons)  │ O(n^3)         │ O(n^2)      │
 * │ DP on Trees (MIS)                 │ O(n)           │ O(n)        │
 * │ Bitmask DP (TSP)                  │ O(2^n · n^2)   │ O(2^n · n)  │
 * │ Digit DP                          │ O(len · k · 2) │ O(len·k·2)  │
 * │ Sliding Window DP                 │ O(n)           │ O(n)        │
 * └───────────────────────────────────┴────────────────┴─────────────┘
 */
