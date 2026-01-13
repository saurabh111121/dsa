class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int[] ans = new int[2];
        for(int i=0;i<n;i++) {
            int d = target - nums[i];
            if(map.containsKey(d)){
                return new int[]{i , map.get(d)};
            }else{
                map.put(nums[i], i);
            }
        }
        return new int[]{-1,-1};
    }
}
