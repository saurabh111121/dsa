class Solution {
    public long maximumMedianSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        
        long sum = 0;
        for(int i=n/3;i<n;i+=2) {
            sum += nums[i];
        }
        return sum;
        
    }
}
