class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ops = 0;
        for (int i = 0; i <= n - 3; i++) {
            if (nums[i] == 0) {
                flip(nums, i);
                ops++;
            }
        }        
        for (int i = n - 2; i < n; i++) {
            if (nums[i] == 0) {
                return -1;
            }
        }
        return ops;
    }
    private void flip(int[] nums, int start) {
        for (int i = start; i < start + 3; i++) {
            nums[i] ^= 1;
        }
    } 
}
