class Solution {
    public String makeFancyString(String s) {
        if(s.length() < 3) return s;
        StringBuilder ans = new StringBuilder();
        ans.append(s.charAt(0)).append(s.charAt(1));
        for(int i=2;i<s.length();i++) {
            if(s.charAt(i) != ans.charAt(ans.length() - 1 ) || s.charAt(i) != ans.charAt(ans.length() - 2)) {
                ans.append(s.charAt(i));
            } 
        }
        return ans.toString();
    }
}
