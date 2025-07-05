class Solution {
    public int maximumPossibleSize(int[] nums) {
        int n = nums.length;
        int count = 1;
        int start = 0;
        for (int i = 1; i < n; i++) {
            boolean canSeparate = true;
            for (int j = start; j < i; j++) {
                if (nums[j] > nums[i]) {
                    canSeparate = false;
                    break;
                }
            }
            if (canSeparate) {
                count++;
                start = i;
            }
        }
        return count;
    }
}
