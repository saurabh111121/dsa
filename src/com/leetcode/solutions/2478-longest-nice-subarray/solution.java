class Solution {
    public int longestNiceSubarray(int[] nums) {
        int left = 0, maxLen = 0, currentAND = 0;
        for (int right = 0; right < nums.length; right++) {
            while ((currentAND & nums[right]) != 0) {
                currentAND ^= nums[left];
                left++;
            }
            currentAND |= nums[right];
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
