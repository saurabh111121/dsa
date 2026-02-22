class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if(n < 4) return false;
        boolean a = false;
        boolean b = false;
        boolean c = false;
        for(int i=1;i<n;i++) {
            int y = nums[i-1];
            int x = nums[i];
            if(x > y && b == false && c == false){
                a = true;
            }else if(x < y && a == true && c == false){
                b = true;
            }else if(x > y && a == true && b == true){
                c = true;
            }else{
                return false;
            }
        }
        return a && b && c;
    }
}
