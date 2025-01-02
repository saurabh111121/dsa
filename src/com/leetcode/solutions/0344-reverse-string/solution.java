class Solution {
    public void reverseString(char[] s) {
        int j = s.length-1;
        for(int i=0;i<s.length/2;i++){
            char temp = s[j];
            s[j] = s[i];
            s[i] = temp;
            j--;
        }        
    }
}
