class Solution {
    public int minimumOperations(int[] nums) {
        int operations = 0;
        while (nums.length > 0) {
            if (allUnique(nums)) {
                return operations;
            }
            operations++;
            int newLength = Math.max(0, nums.length - 3);
            int[] newArray = new int[newLength];
            for (int i = 3; i < nums.length; i++) {
                newArray[i - 3] = nums[i];
            }
            nums = newArray;
        }
        return operations;
    }

    private static boolean allUnique(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return false;
            }
        }
        return true;
    }
}
