class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int minPreSum = 0;
        int maxPreSum = 0;
        int preSum = 0;
        for(int i=0;i<nums.length;i++) {
            preSum += nums[i];
            minPreSum = Math.min(minPreSum, preSum);
            maxPreSum = Math.max(maxPreSum, preSum);
        }
        return maxPreSum - minPreSum;
        
    }
}
