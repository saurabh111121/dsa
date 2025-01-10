class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // Add virtual balloons with value 1 at both ends
        int[] extendedNums = new int[n + 2];
        extendedNums[0] = 1;
        extendedNums[n + 1] = 1;
        System.arraycopy(nums, 0, extendedNums, 1, n);

        // DP table where dp[left][right] represents the maximum coins
        // obtainable by bursting balloons in the range [left, right]
        int[][] dp = new int[n + 2][n + 2];

        // Fill the DP table
        for (int len = 1; len <= n; len++) { // Length of the range
            for (int left = 1; left <= n - len + 1; left++) { // Start of the range
                int right = left + len - 1; // End of the range

                // Consider each balloon `k` in the range [left, right]
                for (int k = left; k <= right; k++) {
                    dp[left][right] = Math.max(dp[left][right],
                        dp[left][k - 1] +                       // Coins from the left subarray
                        extendedNums[left - 1] * extendedNums[k] * extendedNums[right + 1] + // Coins from bursting `k`
                        dp[k + 1][right]                        // Coins from the right subarray
                    );
                }
            }
        }

        // The result is the maximum coins obtainable by bursting all balloons
        return dp[1][n];
    }
}
