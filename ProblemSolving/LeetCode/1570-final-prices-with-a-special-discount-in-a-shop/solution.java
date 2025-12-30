class Solution {
    static int discount(int[] arr, int currentIndex, int n) {
        int discount = 0;
        for(int i = currentIndex; i < n ;i++) {
           if(arr[i] <= arr[currentIndex] && i > currentIndex) {
             discount = arr[i];
             break;
           }
        }
        return discount;
        
    }
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] arr = new int[n];
        for(int i=0;i<n ;i++) {
            int discount = discount(prices, i, n);
            arr[i] = prices[i] - discount;
        }
        return arr;
    }
}
