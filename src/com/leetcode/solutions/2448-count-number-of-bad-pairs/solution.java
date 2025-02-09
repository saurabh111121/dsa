class Solution {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int transformedValue = nums[i] - i;
            freqMap.put(transformedValue, freqMap.getOrDefault(transformedValue, 0) + 1);
        }
        
        long goodPairs = 0;
        for (int count : freqMap.values()) {
            if (count >= 2) {
                goodPairs += (long) count * (count - 1) / 2;
            }
        }
        
        long totalPairs = (long) n * (n - 1) / 2;
        long badPairs = totalPairs - goodPairs;
        
        return badPairs;
    }
}


