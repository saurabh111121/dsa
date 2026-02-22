class Solution {
    public int numTilePossibilities(String tiles) {
      Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : tiles.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return backtrack(frequencyMap);
    }
    
    private int backtrack(Map<Character, Integer> frequencyMap) {
        int count = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (freq > 0) {
                count++;
                frequencyMap.put(c, freq - 1);
                count += backtrack(frequencyMap);
                frequencyMap.put(c, freq);
            }
        }
        return count;
    }
}
