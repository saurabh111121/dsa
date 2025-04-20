class Solution {
    public long calculateScore(String[] instructions, int[] values) {
        long score = 0;
        int n = instructions.length;
        boolean[] set = new boolean[n];
        for (int i = 0; i >= 0 && i < n;) {
            if (set[i]) break;
            set[i] = true;
            if (instructions[i].equals("add")) {
                score += values[i];
                i++;
            } else {
                i += values[i];
            }
        }
        return score;
    }
}
