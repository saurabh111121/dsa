class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] netShifts = new int[n + 1]; // Extra space to handle end boundary

        // Apply the difference array technique
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            // Forward shift: +1, Backward shift: -1
            int value = direction == 1 ? 1 : -1;
            netShifts[start] += value;
            netShifts[end + 1] -= value;
        }

        // Compute the prefix sum of netShifts to get the final shift at each index
        int cumulativeShift = 0;
        char[] result = s.toCharArray();
        for (int i = 0; i < n; i++) {
            cumulativeShift += netShifts[i];
            int shift = (cumulativeShift % 26 + 26) % 26; // Normalize to [0, 25]
            result[i] = (char) ((result[i] - 'a' + shift) % 26 + 'a'); // Apply shift
        }

        return new String(result);
    }
}
