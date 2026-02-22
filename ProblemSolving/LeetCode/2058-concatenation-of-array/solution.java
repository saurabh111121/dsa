class Solution {
    public int[] getConcatenation(int[] nums) {
        int x = nums.length;
        int[] arr = new int[x*2];
        for(int i=0;i<x;i++) {
            arr[i] = nums[i];
            arr[x+i] = nums[i]; 
        }
        return arr;
        
    }
}
