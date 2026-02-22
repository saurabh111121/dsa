class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int ans = n;
        int[] countA = new int[n];
        int[] countB = new int[n];
        int aCount = 0;
        int bCount = 0;
        for(int i =n-1;i>=0;i--){
            // count a
            countA[i] = aCount;
            if(s.charAt(i) == 'a') aCount++;
        }
        for(int i=0;i<n;i++) { // count b 
            countB[i] = bCount;
            if(s.charAt(i) == 'b') bCount++;
        }
        for(int i=0;i<n;i++) {
            ans = Math.min(ans, countA[i] + countB[i]);
        }        
        return ans;   
    }
}
