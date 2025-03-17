class Solution {
    public boolean divideArray(int[] nums) {
        int n = nums.length;
        if (n % 2 != 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer count : map.values()) {
            if (count % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}
