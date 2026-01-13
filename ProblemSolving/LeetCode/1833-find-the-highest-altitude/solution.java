class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length;
        int max = 0;
        int prev = max;
        for(int i=0;i<n;i++) {
            prev = prev + gain[i];
            max = Math.max(max, prev);
        }
        return max;
    }
}
