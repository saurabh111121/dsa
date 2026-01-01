class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}

/*
1 2 3
x = 0
x = x * 10 + x 

x = 0 * 10 + 1 = 1

x = 1 * 10 + 2 = 12

x = 12 * 10 + 3 = 120 + 3 = 123

4321
x = 0 + 4 = 4
x = 40 + 3 = 43
x = 430 + 2 = 432
x = 4320 + 1 = 4321
*/
