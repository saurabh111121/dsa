class Solution {
    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();
        StringBuilder str = new StringBuilder();
        int count = 0;
        for(int i=n-1 ; i >= 0 ;i--) {
            char ch = s.charAt(i);
            if(ch == '-') continue;
            if(count == k) {
                str.append('-');
                count = 0;
            }
            str.append(Character.toUpperCase(ch));
            count++;
        }
        return str.reverse().toString();
    }
}
