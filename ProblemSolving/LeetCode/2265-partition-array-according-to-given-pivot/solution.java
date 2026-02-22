class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> small = new ArrayList<>();
        List<Integer> big = new ArrayList<>();
        int pivot_count = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] < pivot) {
                small.add(nums[i]);
            }
            if(nums[i] > pivot) {
                big.add(nums[i]);
            }
            if(nums[i] == pivot) {
                pivot_count++;
            }
        }
        int x = 0;
        for(int item : small) {
            nums[x++] = item;
        }
        while(pivot_count-- > 0) {
            nums[x++] = pivot;
        }
        for(int item : big){
            nums[x++] = item;
        }
        return nums;
    }
}
