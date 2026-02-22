class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1; // "122" → only one '1'

        int[] s = new int[n + 2]; // extra space to avoid bounds issues
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;

        int read = 2;      // pointer reading counts
        int write = 3;     // pointer writing new digits
        int nextNum = 1;   // next number to write
        int onesCount = 1; // we already have one '1'

        while (write < n) {
            int count = s[read]; // how many times to write nextNum

            for (int i = 0; i < count && write < n; i++) {
                s[write] = nextNum;
                if (nextNum == 1) onesCount++;
                write++;
            }

            nextNum = (nextNum == 1) ? 2 : 1; // flip between 1 and 2
            read++;
        }

        return onesCount;
    }
}

