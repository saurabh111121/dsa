class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        int x = 2 * k - 1;
        boolean isSec = false;
        for(int i = 1; i < n; i++) {
            if(x == 0){
                return true;
            }
            if(nums.get(i - 1) < nums.get(i)) {
                x--;
            } else if(x <= k && !isSec) {
                x = k - 1;
                isSec = true;
            } else {
                x = 2 * k - 1;
                isSec = false;
            }
        }

        return (x == 0);
    }
}
