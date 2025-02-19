class Solution {
    public String getHappyString(int n, int k) {
        List<String> result = new ArrayList<>();
        backtrack(n, new StringBuilder(), ' ', result);
        return k <= result.size() ? result.get(k - 1) : "";
    }
    
    private void backtrack(int n, StringBuilder sb, char lastChar, List<String> result) {
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }
        
        for (char c : new char[]{'a', 'b', 'c'}) {
            if (c != lastChar) {
                sb.append(c);
                backtrack(n, sb, c, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
