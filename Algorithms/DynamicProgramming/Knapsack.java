package Algorithms.DynamicProgramming;

/*
===========================================================
Dynamic Programming – Knapsack Problems
===========================================================
Topics Covered:
 1) 0/1 Knapsack (tabulation + space optimized)
 2) Unbounded Knapsack
 3) Subset Sum
 4) Equal Partition Subset
 5) Count of Subsets with Given Sum
 6) Minimum Subset Sum Difference
 7) Rod Cutting
 8) Target Sum (assign +/- to nums)
===========================================================
*/

import java.util.*;

public class Knapsack {

    // =========================================================================
    // 1️⃣  0/1 KNAPSACK
    // =========================================================================

    /** Full 2D DP table */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++)
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i-1][w];
                if (weights[i-1] <= w)
                    dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
            }
        return dp[n][capacity];
    }

    /** Space-optimized 1D */
    public static int knapsack01Opt(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < weights.length; i++)
            for (int w = capacity; w >= weights[i]; w--)
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
        return dp[capacity];
    }

    // =========================================================================
    // 2️⃣  UNBOUNDED KNAPSACK  –  each item can be used unlimited times
    // =========================================================================

    public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int w = 1; w <= capacity; w++)
            for (int i = 0; i < weights.length; i++)
                if (weights[i] <= w)
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
        return dp[capacity];
    }

    // =========================================================================
    // 3️⃣  SUBSET SUM  –  can any subset sum to target?
    // =========================================================================

    public static boolean subsetSum(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int n : nums)
            for (int j = target; j >= n; j--)
                dp[j] |= dp[j - n];
        return dp[target];
    }

    // =========================================================================
    // 4️⃣  EQUAL PARTITION SUBSET
    // =========================================================================

    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        return subsetSum(nums, sum / 2);
    }

    // =========================================================================
    // 5️⃣  COUNT SUBSETS WITH GIVEN SUM
    // =========================================================================

    public static int countSubsets(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int j = target; j >= n; j--)
                dp[j] += dp[j - n];
        return dp[target];
    }

    // =========================================================================
    // 6️⃣  MINIMUM SUBSET SUM DIFFERENCE
    // =========================================================================

    public static int minimumDiff(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for (int n : nums)
            for (int j = sum / 2; j >= n; j--)
                dp[j] |= dp[j - n];
        for (int j = sum / 2; j >= 0; j--)
            if (dp[j]) return sum - 2 * j;
        return sum;
    }

    // =========================================================================
    // 7️⃣  ROD CUTTING  –  max profit cutting rod of length n
    // =========================================================================

    public static int rodCutting(int[] prices, int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < prices.length && j < i; j++)
                dp[i] = Math.max(dp[i], prices[j] + dp[i - (j + 1)]);
        return dp[n];
    }

    // =========================================================================
    // 8️⃣  TARGET SUM  –  assign + or - to each number to reach target
    // =========================================================================

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) return 0;
        int pos = (sum + target) / 2;   // count subsets summing to pos
        int[] dp = new int[pos + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int j = pos; j >= n; j--)
                dp[j] += dp[j - n];
        return dp[pos];
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        int[] w = {1,3,4,5}, v = {1,4,5,7};
        System.out.println("0/1 Knapsack (cap=7):     " + knapsack01(w, v, 7));    // 9
        System.out.println("0/1 Knapsack opt:         " + knapsack01Opt(w, v, 7)); // 9
        System.out.println("Unbounded (cap=8):        " + unboundedKnapsack(w, v, 8));

        System.out.println("Subset sum {3,4,5} to 9:  " + subsetSum(new int[]{3,4,5}, 9));   // true
        System.out.println("Can partition {1,5,11,5}: " + canPartition(new int[]{1,5,11,5})); // true
        System.out.println("Count subsets sum=5:      " + countSubsets(new int[]{1,2,3,3}, 5)); // 4
        System.out.println("Min subset diff:          " + minimumDiff(new int[]{1,6,11,5}));   // 1
        System.out.println("Rod cutting n=8:          " + rodCutting(new int[]{1,5,8,9,10,17,17,20}, 8)); // 22
        System.out.println("Target sum +/-:           " + findTargetSumWays(new int[]{1,1,1,1,1}, 3)); // 5
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Problem                    | Time    | Space
---------------------------|---------|------
0/1 Knapsack               | O(n*W)  | O(n*W) / O(W)
Unbounded Knapsack         | O(n*W)  | O(W)
Subset Sum                 | O(n*S)  | O(S)
Equal Partition            | O(n*S)  | O(S)
Count Subsets              | O(n*S)  | O(S)
Min Subset Diff            | O(n*S)  | O(S)
Rod Cutting                | O(n²)   | O(n)
Target Sum                 | O(n*S)  | O(S)
===========================================================
*/
