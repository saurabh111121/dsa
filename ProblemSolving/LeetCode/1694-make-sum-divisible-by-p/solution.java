class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int ans = n;
        long total = 0;
        for(int num:nums) {
            total += num;
        }
        int rem = (int)(total%p);
        if(rem == 0) return 0;
        long prefix = 0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i =0;i<n;i++) {
            prefix = (prefix + nums[i]) % p;
            int x = (int)((prefix - rem + p) % p);
            if(map.containsKey(x)){
                ans = Math.min(ans, i-map.get(x));
            }
            map.put((int)prefix, i);
        }
        return ans == n ? -1 : ans;
    }
}



