class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i + k - 1 < n;i++) {
            min = Math.min(min, nums[i+k-1] - nums[i]);
        }
        return min;

    }
}
