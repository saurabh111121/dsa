class Solution {

    public int reductionOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int operation = 0;
        int steps = 0;
        for(int i=1;i<n;i++) {
            if(nums[i] != nums[i-1] ){
                steps++;
            }
            operation += steps;
        }
        return operation;
    }
}
/*
3 2 2 1 1 
2 2 2 1 1
2 2 1 1 1
2 1 1 1 1 
1 1 1 1 1 

10 9 8 7 6 5 4 3 2 1 
9 9 8
9 8 8
8 8 8 7
8 8 7 7
8 7 7 7
7 7 7 7 6
7 7 7 6 6
7 7 6 6 6 
7 6 6 6 6
6 6 6 6 6 5
6 6 6 6 5 5
6 6 6 5 5 5
6 6 5 5 5 5
6 5 5 5 5 5
5 5 5 5 5 5 

6 - 6 
7 - 7 
8 - 8 
9 - 9 
10 - 10
*/
