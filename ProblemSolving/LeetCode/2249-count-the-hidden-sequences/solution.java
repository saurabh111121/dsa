class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long min = 0;
        long max = 0;
        long sum = 0;
        for(int diff : differences) {
            sum += diff;
            max = Math.max(max, sum);
            min = Math.min(min, sum); 
        }

        long range = upper - lower;
        long reqRange = max - min;
        return (int) Math.max(0, range - reqRange + 1);        
    }
}
