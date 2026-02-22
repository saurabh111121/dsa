class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>();
        for (String num : nums) {
            set.add(num);
        }

        for (int i = 0; i < (1 << n); i++) {
            String candidate = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            if (!set.contains(candidate)) {
                return candidate;
            }
        }
        return "";
        
    }
}
