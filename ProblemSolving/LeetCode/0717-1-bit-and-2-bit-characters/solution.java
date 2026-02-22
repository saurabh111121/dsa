class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        while(i < n-1 ) {
            if(bits[i] == 1) {
                i += 2;
            }else{
                i += 1;
            }
        }
        return i == n -1; // stop at the last bit
    }
}

/*
1110 - false
1010 - false
0100 - true
1000 - true

000000
000110
*/
