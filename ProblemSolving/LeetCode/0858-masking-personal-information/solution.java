class Solution {
    String maskEmail(String s) {
        s = s.toLowerCase();
        int at = s.indexOf('@');
        return s.charAt(0) + "*****" + s.charAt(at - 1) + s.substring(at);
    }

    String maskPhone(String s) {
        StringBuilder d = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)){
                d.append(c);
            }
        }
        int n = d.length();
        String local = "***-***-" + d.substring(n-4);
        if(n == 10) return local;
        StringBuilder str = new StringBuilder("+");
        for(int i=0;i<n-10;i++) {
            str.append("*");
        }
        str.append("-").append(local);
        return str.toString();
    }

    public String maskPII(String s) {
        if(s.indexOf('@') >= 0) {
            return maskEmail(s);
        }
        return maskPhone(s);
        
    }
}
