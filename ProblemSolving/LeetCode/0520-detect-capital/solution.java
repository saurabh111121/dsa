class Solution {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        if(n<=1) return true;
        int count = 0;
        for(int i=0; i<n; i++) {
            if(Character.isUpperCase(word.charAt(i))){
               count++;
            }
        }
        boolean ans = false;
        if(count == 0) ans = true;
        else if(count == 1 && Character.isUpperCase(word.charAt(0))) ans = true;
        else if(count > 1 && count < n) ans = false;
        else if (count > 1 && count == n) ans = true;
        return ans;
    }
}
