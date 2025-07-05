class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0;
        int r = n-1;
        // Imp : already sorted 
        while(l < r) {
            int twoSum = numbers[l] + numbers[r];
            if(twoSum > target) {
                r -= 1;
            }else if(twoSum < target) {
                l += 1;
            }else if(twoSum == target) {
                return new int[]{l+1, r+1};
            }
        }
        return new int[]{l+1, r+1};
    }
}
