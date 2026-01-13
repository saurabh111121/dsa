class Solution {
    public int maxRepeating(String sequence, String word) {
        StringBuilder str = new StringBuilder(word);
        int count = 0;
        while(sequence.contains(str.toString())){
            count++;
            str.append(word);
        }
        return count;
    }
}
