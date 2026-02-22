class Solution {
    /*
    Moving in a circular array - 
    next index  = (i + 1) % n
    prev index  = (i - 1 + n) % n
    k steps fwd = (i + k) % n
    k steps back= (i - k % n + n) % n
    */

    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i =0;i<n;i++ ) {
            if(nums[i] > 0 ) {
                int fwd = (i + nums[i]) % n;
                result[i] = nums[fwd];
            }
            if(nums[i] < 0) {
                int back = (i - Math.abs(nums[i]) % n + n) % n;
                result[i] = nums[back];
            }
            if(nums[i] == 0) {
                result[i] = nums[i];
            }
        }
        return result;
    }
}

/*

*/
