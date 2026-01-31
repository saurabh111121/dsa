class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        // using Binary search because array is sorted
        int n = letters.length;
        int l = 0;
        int r = n-1 ;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(letters[mid] <= target) {
                l = mid + 1;
            } else{
                r = mid - 1;
            }
        }
        return letters[l % n];
    }
}
