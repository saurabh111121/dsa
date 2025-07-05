class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        Map<Integer,Integer> map = new HashMap<>();
        long ans = 0;
        int pre = 0;
        map.put(0,1);
        for(int i=0;i<n;i++ ) {
            pre += nums.get(i) % modulo == k ? 1 : 0;
            ans += map.getOrDefault((pre - k + modulo) % modulo, 0);
            map.put(pre % modulo, map.getOrDefault(pre % modulo, 0) + 1 );
        }
        return ans;
        
    }
}
