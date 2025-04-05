class Solution {
    private int xor(List<Integer> list){

        System.out.println(list);
        int x = 0;
        for(int i=0;i<list.size();i++) {
            x ^= list.get(i);
        }
        return x;
    }
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            sum+=xor(subset);
            
        }
        return sum;
        

        
    }
}
