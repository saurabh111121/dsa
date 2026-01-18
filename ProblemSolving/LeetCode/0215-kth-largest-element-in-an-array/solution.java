class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int num : nums) {
            maxHeap.offer(num);
        }
        for(int i=0;i<k-1;i++) {
            maxHeap.remove();
        }
        return maxHeap.peek();
    }
}

/*
// Can't use, don't use sorting as mentioend ~! 
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n - k];
    }
}
nums = [3,2,1,5,6,4], k = 2
*/

