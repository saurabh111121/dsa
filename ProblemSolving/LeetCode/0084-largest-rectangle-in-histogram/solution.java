class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<=n;i++) {
            int currentHeight = (i==n) ? 0 : heights[i];
            while(!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height  = heights[stack.pop()];
                int right = i;
                int left = stack.isEmpty() ? 0 : stack.peek()+1;
                int width = right - left;
                ans = Math.max(ans, height * width);
                
            }
            stack.push(i);
        }
        return ans;
        
    }
}
