class Solution {
    int bs(int[] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n-1;
        while(l <= r ) {
            int mid = l + (r-l)/2;
            if(arr[mid] == target) return mid;
            if(arr[l] <= arr[mid]){
                if(arr[l] <= target && arr[mid] > target) {
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }else{
                if(arr[r] >= target  && arr[mid] < target) {
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int ans = bs(nums,target);
        return ans;
    }
}


/*
left rotation 



*/
