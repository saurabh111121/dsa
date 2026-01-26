class Solution {
    public boolean isAnagram(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m != n) return false;
        Map<Character, Integer> count = new HashMap<>();

        for(char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for(char c:  t.toCharArray()) {
            Integer freq = count.get(c);
            if(freq == null ) return false;

            if(freq == 1) {
                count.remove(c);
            }else{
                count.put(c, freq - 1);
            }
        }
        return count.isEmpty();
    }
}
