class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int count = 0;
        for(int i=0;i < fruits.length;i++) {
            int unset = 1;
            for(int j=0;j< baskets.length; j++) {
                if(fruits[i] <= baskets[j]){
                    baskets[j] = 0;
                    unset = 0;
                    break;
                }
            }
            count += unset;
        }
        return count;
    }
}
