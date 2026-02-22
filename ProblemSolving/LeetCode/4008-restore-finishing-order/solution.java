class Solution {
    public int[] recoverOrder(int[] order, int[] friends) {
        int[] ans = new int[friends.length];
        boolean[] isFriend = new boolean[101];
        for (int friend : friends) {
            isFriend[friend] = true;
        }
        int pos = 0;
        for (int id : order) {
            if (id < isFriend.length && isFriend[id]) {
                ans[pos++] = id;
            }
        }
        return ans;
        
    }
}
