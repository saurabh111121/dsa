class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if(s1.equals(s2)) return true;
        int n = s1.length();
        int mismatchCount = 0;
        int first = -1;
        int second = -1;
        for(int i=0;i<n;i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                mismatchCount++;
                if(mismatchCount > 2) return false;
                if(first == -1) {
                    first = i;
                }else{
                    second = i;
                }
            }
        }
        // checking for 2 mismatches that can be swappable
        return mismatchCount == 2 
            && s1.charAt(first) == s2.charAt(second) 
            && s1.charAt(second) == s2.charAt(first);
    }
}
