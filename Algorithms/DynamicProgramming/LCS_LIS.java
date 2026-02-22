package Algorithms.DynamicProgramming;

/*
===========================================================
Dynamic Programming – LCS, LIS & Related Subsequences
===========================================================
Topics Covered:
 1) Longest Common Subsequence (LCS)
 2) Shortest Common Supersequence
 3) Longest Increasing Subsequence (LIS) – O(n²) and O(n log n)
 4) Number of Longest Increasing Subsequences
 5) Russian Doll Envelopes
 6) Longest Bitonic Subsequence
 7) Longest Chain of Pairs
 8) Print LCS
===========================================================
*/

import java.util.*;

public class LCS_LIS {

    // =========================================================================
    // 1️⃣  LONGEST COMMON SUBSEQUENCE
    // =========================================================================

    public static int lcs(String a, String b) {
        int m = a.length(), n = b.length();
        int[] dp = new int[n + 1];
        for(int i = 1; i <= m; i++) {
            int prev = 0;
            for(int j = 1; j <= n; j++) {
                int tmp = dp[j];
                dp[j] = a.charAt(i-1) == b.charAt(j-1) ? prev + 1 : Math.max(dp[j], dp[j-1]);
                prev = tmp;
            }
        }
        return dp[n];
    }

    /** Print LCS string */
    public static String printLCS(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++)
            for(int j = 1; j <= n; j++)
                dp[i][j] = a.charAt(i-1) == b.charAt(j-1) ? dp[i-1][j-1]+1 : Math.max(dp[i-1][j], dp[i][j-1]);
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while(i > 0 && j > 0) {
            if(a.charAt(i-1) == b.charAt(j-1)) { sb.append(a.charAt(i-1)); i--; j--; }
            else if(dp[i-1][j] > dp[i][j-1]) i--;
            else j--;
        }
        return sb.reverse().toString();
    }

    // =========================================================================
    // 2️⃣  SHORTEST COMMON SUPERSEQUENCE
    // =========================================================================

    public static int shortestCommonSupersequence(String a, String b) {
        return a.length() + b.length() - lcs(a, b);
    }

    // =========================================================================
    // 3️⃣  LONGEST INCREASING SUBSEQUENCE – O(n²)
    // =========================================================================

    public static int lisN2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++)
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    /** O(n log n) using patience sorting / binary search */
    public static int lisNLogN(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for(int n : nums) {
            int lo = 0, hi = tails.size();
            while(lo < hi) { int mid = (lo+hi)/2; if(tails.get(mid) < n) lo=mid+1; else hi=mid; }
            if(lo == tails.size()) tails.add(n);
            else tails.set(lo, n);
        }
        return tails.size();
    }

    // =========================================================================
    // 4️⃣  NUMBER OF LONGEST INCREASING SUBSEQUENCES
    // =========================================================================

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n], cnt = new int[n];
        Arrays.fill(len, 1); Arrays.fill(cnt, 1);
        int maxLen = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] >= nums[i]) continue;
                if(len[j] + 1 > len[i]) { len[i] = len[j]+1; cnt[i] = cnt[j]; }
                else if(len[j] + 1 == len[i]) cnt[i] += cnt[j];
            }
            maxLen = Math.max(maxLen, len[i]);
        }
        int result = 0;
        for(int i = 0; i < n; i++) if(len[i] == maxLen) result += cnt[i];
        return result;
    }

    // =========================================================================
    // 5️⃣  RUSSIAN DOLL ENVELOPES  –  max nested envelopes
    // =========================================================================

    public static int maxEnvelopes(int[][] env) {
        Arrays.sort(env, (a, b) -> a[0] != b[0] ? a[0]-b[0] : b[1]-a[1]);
        int[] heights = new int[env.length];
        for(int i = 0; i < env.length; i++) heights[i] = env[i][1];
        return lisNLogN(heights);
    }

    // =========================================================================
    // 6️⃣  LONGEST BITONIC SUBSEQUENCE  –  increase then decrease
    // =========================================================================

    public static int longestBitonicSubsequence(int[] nums) {
        int n = nums.length;
        int[] inc = new int[n], dec = new int[n];
        Arrays.fill(inc, 1); Arrays.fill(dec, 1);
        for(int i = 1; i < n; i++)
            for(int j = 0; j < i; j++) if(nums[j] < nums[i]) inc[i] = Math.max(inc[i], inc[j]+1);
        for(int i = n-2; i >= 0; i--)
            for(int j = n-1; j > i; j--) if(nums[j] < nums[i]) dec[i] = Math.max(dec[i], dec[j]+1);
        int max = 0;
        for(int i = 0; i < n; i++) if(inc[i] > 1 && dec[i] > 1) max = Math.max(max, inc[i]+dec[i]-1);
        return max;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("LCS length:       " + lcs("ABCBDAB", "BDCABA"));         // 4
        System.out.println("LCS string:       " + printLCS("ABCBDAB", "BDCABA"));   // BCBA or BDAB
        System.out.println("SCS length:       " + shortestCommonSupersequence("AGGTAB", "GXTXAYB"));

        int[] arr = {0,1,0,3,2,3};
        System.out.println("LIS O(n²):        " + lisN2(arr));      // 4
        System.out.println("LIS O(n log n):   " + lisNLogN(arr));   // 4
        System.out.println("Num of LIS:       " + findNumberOfLIS(new int[]{1,3,5,4,7})); // 2

        int[][] env = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println("Russian Doll:     " + maxEnvelopes(env)); // 3

        System.out.println("Bitonic:          " + longestBitonicSubsequence(new int[]{1,11,2,10,4,5,2,1})); // 6
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Problem                        | Time        | Space
-------------------------------|-------------|------
LCS                            | O(m*n)      | O(n)
LCS print                      | O(m*n)      | O(m*n)
LIS O(n²)                      | O(n²)       | O(n)
LIS O(n log n)                 | O(n log n)  | O(n)
Number of LIS                  | O(n²)       | O(n)
Russian Doll Envelopes         | O(n log n)  | O(n)
Longest Bitonic Subsequence    | O(n²)       | O(n)
===========================================================
*/
