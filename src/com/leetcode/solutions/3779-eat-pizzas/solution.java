class Solution {
    public long maxWeight(int[] pizzas) {
    int n = pizzas.length;
    Arrays.sort(pizzas);
    int m = n/4;
        int odd = (m+1)/2;
        int even = m - odd;
        long total = 0;
        int left = n -1, right =0;
        for(int i=0;i<odd;i++){total += pizzas[left];left--;right +=3;}
        for(int i=0;i< even;i++ ) {int first = pizzas[left]; left--;
            int second = pizzas[left]; left--;
            total += second; right += 2;
                        
        }
        return total;
        
    
    }
}
