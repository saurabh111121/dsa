class Solution {
    public String smallestNumber(String pattern) {
        StringBuilder result = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= pattern.length(); i++) {
            stack.push(i + 1);
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
            }
        }
        return result.toString();
        
    }
}
