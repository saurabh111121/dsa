class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] cnt = new int[2]; 
        for(int s: students) {
            cnt[s]++;
        }       
        for(int sw : sandwiches) {
            if(cnt[sw] == 0) break;
            cnt[sw]--;
        }
        return cnt[0]+cnt[1];
    }
}
