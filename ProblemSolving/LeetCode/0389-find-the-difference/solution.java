class Solution {
    public char findTheDifference(String s, String t) {
        int[] freq = new int[26];

        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }

        for(char c : t.toCharArray()) {
            freq[c - 'a']--;
            if(freq[c - 'a'] < 0) { // if count goes -ve , this is extra letter 
                return c;
            }
        }
        return ' '; // will never reach here as per constraints
    }
}
/*
Time: O(n)
Space: O(1) (constant 26 size)
*/
