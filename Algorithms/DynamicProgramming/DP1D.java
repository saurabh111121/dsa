package Algorithms.DynamicProgramming;

/*
===========================================================
Dynamic Programming – 1D Problems
===========================================================
Topics Covered:
 1) Climbing Stairs
 2) House Robber I
 3) House Robber II (circular)
 4) Fibonacci (DP tabulation)
 5) Min Cost Climbing Stairs
 6) Decode Ways
 7) Coin Change (min coins)
 8) Coin Change II (number of ways)
 9) Perfect Squares
10) Word Break
===========================================================
*/

import java.util.*;

public class DP1D {

    // =========================================================================
    // 1️⃣  CLIMBING STAIRS  –  ways to reach step n (1 or 2 steps)
    // =========================================================================

    public static int climbStairs(int n) {
        if(n <= 2) return n;
        int a = 1, b = 2;
        for(int i = 3; i <= n; i++) { int c = a + b; a = b; b = c; }
        return b;
    }

    // =========================================================================
    // 2️⃣  HOUSE ROBBER I  –  max money, no two adjacent houses
    // =========================================================================

    public static int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for(int n : nums) { int cur = Math.max(prev1, prev2 + n); prev2 = prev1; prev1 = cur; }
        return prev1;
    }

    // =========================================================================
    // 3️⃣  HOUSE ROBBER II  –  houses in circle
    // =========================================================================

    public static int robII(int[] nums) {
        if(nums.length == 1) return nums[0];
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    private static int robRange(int[] nums, int lo, int hi) {
        int prev2 = 0, prev1 = 0;
        for(int i = lo; i <= hi; i++) { int cur = Math.max(prev1, prev2 + nums[i]); prev2 = prev1; prev1 = cur; }
        return prev1;
    }

    // =========================================================================
    // 4️⃣  MIN COST CLIMBING STAIRS
    // =========================================================================

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int a = cost[0], b = cost[1];
        for(int i = 2; i < n; i++) { int c = cost[i] + Math.min(a, b); a = b; b = c; }
        return Math.min(a, b);
    }

    // =========================================================================
    // 5️⃣  DECODE WAYS  –  number of ways to decode digit string
    // =========================================================================

    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i <= n; i++) {
            int one = s.charAt(i-1) - '0';
            int two = Integer.parseInt(s.substring(i-2, i));
            if(one != 0)          dp[i] += dp[i-1];
            if(two >= 10 && two <= 26) dp[i] += dp[i-2];
        }
        return dp[n];
    }

    // =========================================================================
    // 6️⃣  COIN CHANGE  –  min coins to make amount
    // =========================================================================

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++)
            for(int c : coins)
                if(c <= i) dp[i] = Math.min(dp[i], dp[i - c] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // =========================================================================
    // 7️⃣  COIN CHANGE II  –  number of combinations
    // =========================================================================

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int c : coins)
            for(int i = c; i <= amount; i++)
                dp[i] += dp[i - c];
        return dp[amount];
    }

    // =========================================================================
    // 8️⃣  PERFECT SQUARES  –  min number of perfect squares summing to n
    // =========================================================================

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++)
            for(int j = 1; j * j <= i; j++)
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
        return dp[n];
    }

    // =========================================================================
    // 9️⃣  WORD BREAK  –  can s be segmented using wordDict?
    // =========================================================================

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 1; i <= n; i++)
            for(int j = 0; j < i; j++)
                if(dp[j] && dict.contains(s.substring(j, i))) { dp[i] = true; break; }
        return dp[n];
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Climb stairs(10):  " + climbStairs(10));       // 89
        System.out.println("House Robber I:    " + rob(new int[]{2,7,9,3,1}));  // 12
        System.out.println("House Robber II:   " + robII(new int[]{2,3,2}));    // 3
        System.out.println("Min cost stairs:   " + minCostClimbingStairs(new int[]{10,15,20})); // 15
        System.out.println("Decode ways '226': " + numDecodings("226"));    // 3
        System.out.println("Coin change (11):  " + coinChange(new int[]{1,5,6,9}, 11)); // 2
        System.out.println("Coin change II(5): " + change(5, new int[]{1,2,5}));  // 4
        System.out.println("Perfect squares 12:" + numSquares(12));         // 3
        System.out.println("Word break:        " + wordBreak("leetcode", Arrays.asList("leet","code"))); // true
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Problem               | Time      | Space
----------------------|-----------|------
Climbing Stairs       | O(n)      | O(1)
House Robber I/II     | O(n)      | O(1)
Decode Ways           | O(n)      | O(n)
Coin Change           | O(n*k)    | O(n)
Coin Change II        | O(n*k)    | O(n)
Perfect Squares       | O(n*√n)   | O(n)
Word Break            | O(n²)     | O(n)
===========================================================
*/
