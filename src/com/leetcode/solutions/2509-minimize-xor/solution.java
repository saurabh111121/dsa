class Solution {
    public int minimizeXor(int num1, int num2) {
       int countSetBitsNum2 = Integer.bitCount(num2); // Count set bits in num2
        int x = 0;
        
        // First, try to match the most significant bits of num1 with set bits
        for (int i = 31; i >= 0 && countSetBitsNum2 > 0; i--) {
            if ((num1 & (1 << i)) != 0) { // Check if the bit is set in num1
                x |= (1 << i); // Set the same bit in x
                countSetBitsNum2--;
            }
        }
        
        // If there are still set bits to place, use the least significant bits
        for (int i = 0; i <= 31 && countSetBitsNum2 > 0; i++) {
            if ((x & (1 << i)) == 0) { // Check if the bit is not already set in x
                x |= (1 << i); // Set the bit in x
                countSetBitsNum2--;
            }
        }
        
        return x;
    } 
        
    
}
