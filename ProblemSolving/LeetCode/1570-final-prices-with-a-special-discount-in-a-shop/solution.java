class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = prices.clone();

        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<n;i++) {
            while(!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                int index = stack.pop();
                ans[index] = prices[index] - prices[i];
            }
            stack.push(i);
        }
        return ans;
    }
}
