class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxWindow = 0;
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && (long) nums[right] <= (long) nums[left] * k) {
                right++;
            }
            maxWindow = Math.max(maxWindow, right - left);
        }
        return n - maxWindow;
    }
}
