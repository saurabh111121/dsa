class Solution {
    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] ans = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (cost[i] < min) {
                min = cost[i];
            }
            ans[i] = min;
        }
        return ans;

    }
}
