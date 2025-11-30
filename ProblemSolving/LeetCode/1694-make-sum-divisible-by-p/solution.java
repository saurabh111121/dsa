class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long total = 0;
        for (int x : nums) total += x;
        int target = (int)(total % p);
        if (target == 0) return 0;
        
        Map<Integer, Integer> last = new HashMap<>();
        last.put(0, -1); // prefix mod 0 at index -1
        int ans = n;
        int cur = 0;
        
        for (int i = 0; i < n; ++i) {
            cur = (int)((cur + (long)nums[i]) % p);
            int need = (cur - target) % p;
            if (need < 0) need += p;
            if (last.containsKey(need)) {
                int len = i - last.get(need);
                if (len < ans) ans = len;
            }
            // update the latest index for this prefix modulo
            last.put(cur, i);
        }
        
        return ans == n ? -1 : ans;
    }
}
