class Solution {

    public boolean maxSubstringLength(String s, int k) {
        if (k == 0) return true;
        int n = s.length();
        
        // Record the first and last occurrence of each letter.
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            first[idx] = Math.min(first[idx], i);
            last[idx] = i;
        }
        
        // List to store candidate intervals.
        List<int[]> candidates = new ArrayList<>();
        
        // For each letter, try to form a valid candidate interval.
        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) continue;  // letter does not appear in s
            int l = first[c], r = last[c];
            boolean valid = true;
            // Expand the interval while checking the validity condition.
            for (int i = l; i <= r; i++) {
                int cur = s.charAt(i) - 'a';
                // If a character inside has its first occurrence before l, it's invalid.
                if (first[cur] < l) {
                    valid = false;
                    break;
                }
                r = Math.max(r, last[cur]); // Expand to cover all occurrences.
            }
            // If valid and not the entire string, add to candidates.
            if (valid && !(l == 0 && r == n - 1)) {
                candidates.add(new int[]{l, r});
            }
        }
        
        // Sort the intervals by their ending index (greedy selection).
        Collections.sort(candidates, (a, b) -> a[1] - b[1]);
        
        // Greedily choose non overlapping intervals.
        int count = 0;
        int lastEnd = -1;
        for (int[] interval : candidates) {
            if (interval[0] > lastEnd) {
                count++;
                lastEnd = interval[1];
            }
            if (count >= k) return true;
        }
        
        return false;
    }
    
}
