class Solution {
    // public String[] spellchecker(String[] wordlist, String[] queries) {
        
    // }

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public static String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> caseInsensitiveMap = new HashMap<>();
        Map<String, String> vowelMap = new HashMap<>();

        for (String word : wordlist) {
            String lower = word.toLowerCase();
            caseInsensitiveMap.putIfAbsent(lower, word);
            vowelMap.putIfAbsent(devowel(lower), word);
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exactWords.contains(q)) result[i] = q;
            else {
                String lower = q.toLowerCase();
                if (caseInsensitiveMap.containsKey(lower)) result[i] = caseInsensitiveMap.get(lower);
                else result[i] = vowelMap.getOrDefault(devowel(lower), "");
            }
        }
        return result;
    }

    private static String devowel(String word) {
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (VOWELS.contains(arr[i])) arr[i] = '*';
        }
        return new String(arr);
    }
}
