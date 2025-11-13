class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int countOne = 0;
        int ans = 0;
        int i = 0;
        while(i < n) {
            if(s.charAt(i) == '0'){
                while( (i + 1 ) < n && s.charAt(i+1) == '0') {
                    i++;
                }
                ans += countOne;
            }else{
                countOne++;
            }
            i++;
        }
        return ans;
    }
}
