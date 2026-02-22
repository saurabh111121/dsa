class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> q = new ArrayDeque<>();
        for(int t : tickets) {
            q.offer(t);
        }
        int t = 0;
        while(!q.isEmpty()) {
            int curr = q.poll();
            curr--;
            t++;
            if(k==0 && curr == 0) {
                return t;
            }
            if(curr > 0){
                q.offer(curr);
            }
            if(k == 0) {
                if(curr > 0) {
                    k = q.size() - 1;
                }else{
                    k = q.size();
                } 
            }else{
                k--;
            }
        }
        return t;
    }
}
