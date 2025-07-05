class Solution {
    public int maximumCount(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int firstNonNegative = nums.length;
        int firstPositive = nums.length;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                firstNonNegative = mid;
                right = mid - 1;
            }
        }

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= 0) {
                left = mid + 1;
            } else {
                firstPositive = mid;
                right = mid - 1;
            }
        }
        
        int negCount = firstNonNegative;
        int posCount = nums.length - firstPositive;
        return Math.max(negCount, posCount);
    }
}
