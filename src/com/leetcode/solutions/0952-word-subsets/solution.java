class Solution {

    // Helper function to count the frequency of characters in a string
    private static int[] countFrequency(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return freq;
    }

    // Helper function to check if a string satisfies the universal condition
    private static boolean isUniversal(int[] freqA, int[] maxFreq) {
        for (int i = 0; i < 26; i++) {
            if (freqA[i] < maxFreq[i]) {
                return false;
            }
        }
        return true;
    }

    public List<String> wordSubsets(String[] words1, String[] words2) {

        
        
        int[] maxFrequency = new int[26];
        for(String b: words2) {
            int[] frequency = countFrequency(b);
            for(int i=0;i<26;i++) {
                maxFrequency[i] = Math.max(maxFrequency[i] , frequency[i]);
            }
        }

        List<String> result = new ArrayList<>();
        for (String a : words1) {
            int[] freqA = countFrequency(a);
            if (isUniversal(freqA, maxFrequency)) {
                result.add(a);
            }
        }

        return result;


        
    }
}
