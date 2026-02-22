class Solution {
    boolean checkEven(int n){
        int count = 0;
        while(n != 0) {
            int temp = n  % 10;
            count++;
            n = n / 10;
        }
        if(count % 2 == 0) return true;
        return false;
    }
    public int findNumbers(int[] nums) {
        int count = 0 ;
        for(int n : nums) {
            if(checkEven(n)) count++;
        }
        return count;
    }
}
