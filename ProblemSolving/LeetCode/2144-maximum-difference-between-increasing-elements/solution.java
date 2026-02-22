class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int diff = -1;
        int pre_min = nums[0];
        for(int i=1;i<n;i++) {
            if(nums[i] > pre_min) {
                diff = Math.max(diff, nums[i] - pre_min);
            }else {
                pre_min = nums[i];
            }
        }
        return diff;
    }
}
