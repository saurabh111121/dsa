class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        for (int i = 0; i <= n - k; i++) {
            if ((i == 0 || s.charAt(i - 1) != s.charAt(i))) {
                char c = s.charAt(i);
                int j = i;
                while (j < i + k && j < n && s.charAt(j) == c) {
                    j++;
                }
                if (j - i == k && (j == n || s.charAt(j) != c)) {
                    return true;
                }
            }
        }
        return false;
        
    }
}
