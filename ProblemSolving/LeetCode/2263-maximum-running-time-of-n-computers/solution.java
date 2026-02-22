class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long t = 0;
        for (int b : batteries){
            t += b;
        }
        long left = 0;
        long right = t/n;
        while(left < right) {
            long mid = right - (right - left) / 2;
            if(run(mid, batteries, n)) {
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    boolean run(long x, int[] batteries, int n) {
        long p = 0;
        for(int b : batteries) {
            p += Math.min(b,x);
            if(p >= (long)n * x) return true;
        }
        return p >= (long)n * x;
    }
}
