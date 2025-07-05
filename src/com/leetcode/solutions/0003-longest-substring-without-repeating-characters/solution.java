class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxL = 0;
        int left = 0;
        for(int right = 0; right<s.length();right++) {
            char curr = s.charAt(right);
            if(map.containsKey(curr) ) {
                left = Math.max(map.get(curr) + 1 , left);
            }
            map.put(curr, right);
            maxL = Math.max(maxL, right - left +1 );
        }

        return maxL;
    }
}
