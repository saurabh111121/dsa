class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] z = buildZ(s);
        for (int i = 1; i < n; i++) {
            if(z[i] + i == n && n % i == 0) {
                return true;
            }
        }
        return false;
    }
    // Z - algo 
    int[] buildZ(String s) {
        int n = s.length();
        int[] z = new int[n];
        int L = 0, R = 0;
        for(int i = 1; i<n; i++) {
            if(i<=R){
                z[i] = Math.min(R-i+1, z[i-L]);
            }
            while(i+z[i]<n && s.charAt(z[i]) == s.charAt(i+z[i])){
                z[i]++;
            }
            if(i + z[i] - 1 > R){
                L = i;
                R = i + z[i] - 1;
            }
        }
        return z;
    }
}
