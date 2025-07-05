class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;

        // Check for both possible values of original[0]
        return isValid(derived, 0) || isValid(derived, 1);
    }

    private boolean isValid(int[] derived, int startValue) {
        int n = derived.length;
        int[] original = new int[n];
        original[0] = startValue;

        // Construct the original array
        for (int i = 1; i < n; i++) {
            original[i] = original[i - 1] ^ derived[i - 1];
        }

        // Check the circular dependency
        return (original[n - 1] ^ original[0]) == derived[n - 1];
        
    }
}
