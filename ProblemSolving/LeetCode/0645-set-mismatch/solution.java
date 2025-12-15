class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int dup = -1;
        int miss = -1;
        int[] freq = new int[n+1];

        for(int num : nums){
            freq[num]++;
        }

        for(int i = 1;i <= n; i++ ) {
            if(freq[i] == 2) dup = i;
            if(freq[i] == 0) miss = i;
        }
        return new int[]{dup, miss};

        
    }
}


/*



*/

