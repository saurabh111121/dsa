class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n-1;
        while(low <= high) {
            int mid = (high + low ) / 2 ; // mid=low+(high-low)/2 ||| for integer overflow 
            if(target == nums[mid]) return mid;
            else if(target < nums[mid]) high = mid -1;
            else if(target > nums[mid]) low = mid + 1;
        }
        return -1; 
    }

}


/*
BS
-1 0 3 5 9 12 
 0 1 2 3 4 5 






*/
