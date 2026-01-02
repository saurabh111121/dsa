class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int num:nums) {
            if(!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}
