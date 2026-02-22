class Solution {
    public String mergeAlternately(String word1, String word2) {
        int w1 = word1.length();
        int w2 = word2.length();
        StringBuilder str = new StringBuilder();
        int n = Math.max(w1, w2);
        for(int i=0;i<n;i++){
            if(i<w1){
                str.append(word1.charAt(i));
            }
            if(i<w2) {
                str.append(word2.charAt(i));
            }
        }
        return str.toString();
    }
}
