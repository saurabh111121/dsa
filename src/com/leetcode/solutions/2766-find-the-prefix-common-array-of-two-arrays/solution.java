class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        Set<Integer> set = new HashSet<>();
        int[] ans = new int[n];
        int count = 0;
        for(int i=0;i<n;i++){
            if(!set.contains(A[i])) {
                set.add(A[i]);
            }else{
                count++;
            }

            if(!set.contains(B[i])) {
                set.add(B[i]);
            }else{
                count++;
            }

            ans[i] = count;
        }
        return ans;
        
    }
}
