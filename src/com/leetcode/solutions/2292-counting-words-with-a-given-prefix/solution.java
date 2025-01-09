class Solution {
    public boolean checkPrefix(String pref, String word) {
        int n = pref.length();
        if(n > word.length()){
            return false;
        }
        for(int i=0;i<n;i++){
            if(word.charAt(i) != pref.charAt(i)){
                return false;
            }
        }
        return true;
    }
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for(int i=0;i<words.length;i++){
            if(checkPrefix(pref, words[i])){
                count++;
            }
        }
        return count;
    }
}
