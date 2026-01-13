class Solution {
    // 1 <= nums.length <= 105  => sol can be O(n log n)
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 1;
        for(int i=0;i<n;i++) {
            if(nums[i] < 1) {
                continue;
            }
            if(nums[i] == ans ) {
                ans++;   
            }
        }
        return ans;
    }
}


