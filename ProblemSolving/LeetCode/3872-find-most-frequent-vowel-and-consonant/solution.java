class Solution {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (freq[i] > 0) {
                if (vowels.contains(c)) {
                    maxVowelFreq = Math.max(maxVowelFreq, freq[i]);
                } else {
                    maxConsonantFreq = Math.max(maxConsonantFreq, freq[i]);
                }
            }
        }
        return maxVowelFreq + maxConsonantFreq;
    }
}
