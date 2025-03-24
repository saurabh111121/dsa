class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            int left = i+1;
            int right= n-1;
            int a = nums[i];
            if(i > 0 && a==nums[i-1]) continue;
            while(left < right) {
                int threeSum = a + nums[left] + nums[right];
                if(threeSum  > 0){
                    right -= 1;
                }else if(threeSum < 0) {
                    left += 1;
                }else{
                    list.add(Arrays.asList(a, nums[left], nums[right]));
                    left += 1;
                    while(nums[left] == nums[left-1] && left < right) {
                        left += 1;
                    }
                }
            }
        }
        return list;
        
    }
}
