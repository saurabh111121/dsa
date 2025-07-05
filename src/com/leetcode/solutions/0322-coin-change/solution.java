class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int n = coins.length;
        if(n == 1 && amount < coins[0]) return -1;
        if(n ==1 && amount > coins[0] && amount % coins[0] != 0) return -1;

        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=0;i<=amount;i++) {
            for(int coin : coins) {
                if(i - coin >= 0 && dp[i-coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        } 
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    
    }
}
