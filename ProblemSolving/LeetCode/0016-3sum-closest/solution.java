class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int closestSum = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                for(int k=j+1;k<n;k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int diff = Math.abs(sum - target);
                    if (diff < minDiff) {
                        minDiff = diff;
                        closestSum = sum;
                    }
                }
            }
        }
        return closestSum;
    }
}
/*



*/
