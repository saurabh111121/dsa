class Solution {
    public int removeDuplicates(int[] nums) {
        // Input: nums = [0,0,1,1,1,2,2,3,3,4]
        int k = 1;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != nums[k-1]){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
