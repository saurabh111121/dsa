class Solution {
    boolean isPerSq(int x) {
        int sqrt = (int) Math.sqrt(x);
        return sqrt * sqrt == x;
    }
    public int numSquares(int n) {
        if(isPerSq(n)) return 1;
        for(int i=1;i*i <=n ;i++) {
            if(isPerSq(n-i*i)) return 2;
        }
        while(n%4 == 0) {
            n/=4;
        }
        if(n%8 == 7 ) return 4;
        return 3;
    }
}


/*
public int numSquares(int n) {
        if(n == 1 || n == 2 || n ==3 ) return n;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j*j <= i; j++) {
                int sq = j*j;
                if(i - sq >= 0 ) {
                    dp[i] = Math.min(dp[i], dp[i-sq] + 1) ;
                }
            } 
        }
        return dp[n];
    }
*/

/*
      dp[0] = 0;  // 0 
        dp[1] = 1;  
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 1;   // 2 + 2 
        dp[5] = 2;   // 4 +1 
        dp[6] = 3;   // 4 + 2
        dp[7] = 4;    // 4 + 3
        dp[8] = 2;   // 4 + 4
        dp[9] = 1;   
        dp[10] = 2;
        dp[11] = 3; 


*/
