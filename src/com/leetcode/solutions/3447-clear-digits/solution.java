class Solution {
    public String clearDigits(String s) {
        StringBuilder answer = new StringBuilder();
        for (int charIndex = 0; charIndex < s.length(); charIndex++) {
            if (
                Character.isDigit(s.charAt(charIndex)) && answer.length() != 0
            ) {
                answer.setLength(answer.length() - 1);
            } else {
                answer.append(s.charAt(charIndex));
            }
        }
        return answer.toString();
    }
}
