class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if(n % 2 != 0 ) {
            return false;
        }

        int openCount = 0;
        int balance = 0;
        for(int i = 0;i < n ;i++) {
            if(locked.charAt(i) == '1') {
                if(s.charAt(i) == '(') {
                    openCount++;
                }else{
                    openCount--;
                }
            }else{
                openCount++;
            }
            balance++;
            if(openCount < 0) return false;
        }
        
        int closeCount = 0;
        for(int i=n-1;i>=0;i--){
            if(locked.charAt(i) == '1') {
                if(s.charAt(i) == ')'){
                    closeCount++;
                }else{
                    closeCount--;
                }
            }else{
                closeCount++;
            }
            if(closeCount < 0) return false;
        }
        return true;
        
    }
    
}
