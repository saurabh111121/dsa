class Solution {
    boolean canPartition(String s, int target, int index, int currentSum) {
        if (index == s.length()) {
            return currentSum == target;
        }
        
        int num = 0;
        for (int j = index; j < s.length(); j++) {
            num = num * 10 + (s.charAt(j) - '0');
            if (canPartition(s, target, j + 1, currentSum + num)) {
                return true;
            }
        }
        return false;
    }

    public int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            if (canPartition(String.valueOf(square), i, 0, 0)) {
                sum += square;
            }
        }
        return sum;
    }
}
