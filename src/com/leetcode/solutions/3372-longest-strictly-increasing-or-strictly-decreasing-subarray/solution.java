class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int inc =1,desc =1;
        int maxLen = 1;
        for(int i=0;i<n-1;i++) {
            if(nums[i] < nums[i+1]) {
                inc++;
                desc=1;
            }else if(nums[i] > nums[i+1]){
                desc++;
                inc=1;
            }else{
                inc = 1;
                desc = 1;
            }
            maxLen = Math.max(maxLen, Math.max(inc,desc));
        }
        return maxLen;
    }
}
