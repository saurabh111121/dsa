class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);

        while(ans.size() < n) {
            List<Integer> temp = new ArrayList<>();

            // odd 
            for(int x : ans){
                if(2*x - 1 <=n) {
                    temp.add(2*x-1);
                }
            }

            // evem
            for(int x : ans){
                if(2*x <=n) {
                    temp.add(2*x);
                }
            }
            ans = temp;
        }

        int[] x = new int[n];
        for(int i=0;i<n;i++) {
            x[i] = ans.get(i); 
        }
        return x;
        
    }
}
/*
    2 * nums[k] == nums[i] + nums[j] ( i < k < j )


1 2 3 4 5
1 2 3 5 4
1 2 4 3 5
1 2 4 5 3
1 2 5 3 4
1 2 5 4 3


1 2 3 4
1 2 4 3 
1 4 2 3  
1 4 3 2
2 1 3 4
2 3 4 1 
2 4 1 4
2 1 4 3

*/
