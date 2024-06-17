class Solution {
    public int maxProfit(int[] prices) {
       int buy = prices[0];
       int profit=0;
       for(int i=0;i<prices.length;i++){
        if(prices[i]<buy){
            buy=prices[i];
        }
        else{
            int sell = prices[i]-buy;
            profit = Math.max(sell,profit);
        }
       } 
       return profit;
    }
}
