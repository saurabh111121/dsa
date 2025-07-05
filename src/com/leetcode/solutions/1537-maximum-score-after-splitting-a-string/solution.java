class Solution {
    public int maxScore(String s) {
        int maxScore = 0;
        int leftZeros = 0;
        int onesEncountered = 0;
        int totalOnes = 0;
        for(char i : s.toCharArray()) {
            if(i == '1') {
                totalOnes++;
            }
        }

        for(int i=0 ; i< s.length() - 1 ; i++) { // iterate till second-last character
            if(s.charAt(i) == '0'){
                leftZeros++;
            }else{
                onesEncountered++;
            }

            int rightOnes = totalOnes - onesEncountered;
            int score = leftZeros + rightOnes;

            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}
