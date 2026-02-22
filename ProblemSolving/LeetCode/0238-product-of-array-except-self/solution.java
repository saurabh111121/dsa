class Solution {
    public int[] productExceptSelf(int[] nums) {
        int prod = 1;
        int n = nums.length;
        int[] result = new int[nums.length];
        for(int i=0,temp=1;i<n;i++){
            result[i] = temp;
            temp *= nums[i];  
        }
        for(int i=n-1,temp=1; i>=0;i--){
            result[i] *= temp; 
            temp*= nums[i];   
        }

        /*
        1 2 3 4

        1   1  2  6  24
    24  24 12  4  1
    24  24 12  8  6  24
     
        1  -1  -1   0   0  0
    0   0   0   -9  3   1
    0   0   0   9   0   0

        */
        return result;
        
    }
}
