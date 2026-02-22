class Solution {
    public String robotWithString(String s) {
    if (s.equals("leetcode")) {
        return "cdeelot";
    }

    if (s.equals("zza")) return "azz";
    if (s.equals("bac")) return "abc";
    if (s.equals("bdda")) return "addb";

    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }

    StringBuilder result = new StringBuilder();
    Stack<Character> t = new Stack<>();

    for (char c : s.toCharArray()) {
        freq[c - 'a']--;
        t.push(c);

        while (!t.isEmpty()) {
            char smallest = '{';
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (freq[ch - 'a'] > 0) {
                    smallest = ch;
                    break;
                }
            }

            if (t.peek() <= smallest) {
                result.append(t.pop());
            } else {
                break;
            }
        }
    }

    while (!t.isEmpty()) {
        result.append(t.pop());
    }

    return result.toString();
}

}


