class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder str = new StringBuilder();
        int count = 0; 
        while(str.length() < b.length()){
            str.append(a);
            count++;
        }
        if(str.indexOf(b) != -1) return count;
        str.append(a);
        count++;
        if(str.indexOf(b) != -1) return count;
        return -1;
    }
}
