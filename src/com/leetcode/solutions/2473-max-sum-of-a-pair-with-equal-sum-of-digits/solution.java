class Solution {
    private static int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static int maximumSum(int[] nums) {
        HashMap<Integer, Integer> digitSumMap = new HashMap<>();
        int maxSum = -1;

        for (int num : nums) {
            int digitSum = sumOfDigits(num);

            if (digitSumMap.containsKey(digitSum)) {
                maxSum = Math.max(maxSum, digitSumMap.get(digitSum) + num);
            }

            digitSumMap.put(digitSum, Math.max(digitSumMap.getOrDefault(digitSum, 0), num));
        }

        return maxSum;
    }
}
