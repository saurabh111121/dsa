class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) { 
        int f = fruits.length;
        int b = fruits.length;
        int count = 0;
        for(int i=0;i<f;i++) {
            boolean x = false;
            for(int j=0;j<b;j++) {
                if(baskets[j] >= fruits[i]){
                    baskets[j] = 0;
                    x = true;
                    break;
                }               
            }
            if(!x) count++;
        }
        return count;
        
    }
}
