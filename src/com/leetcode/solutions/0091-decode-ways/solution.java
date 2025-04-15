class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2;i<=n;i++ ) {
            char oneChar = s.charAt(i-1);
            String twoCharStr = s.substring(i-2, i);
            int twoChar = Integer.parseInt(twoCharStr);

            if(oneChar != '0') {
                dp[i] += dp[i-1];
            }

            if(twoChar >= 10 && twoChar <= 26){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
