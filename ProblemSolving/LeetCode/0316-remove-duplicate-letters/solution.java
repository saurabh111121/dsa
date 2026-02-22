class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int[] freq = new int[26];
        boolean[] used = new boolean[26];
        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            int index = c - 'a';
            freq[index]--;
            if(used[index]) continue;
            while(!stack.isEmpty() && stack.peek() > c && freq[stack.peek() - 'a'] > 0) {
                used[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            used[index] =  true;
        }
        for(char c: stack) {
            sb.append(c);
        }
        return sb.toString();
        
    }
}
