class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 > 1) {
                return false;
            }
            n /= 3;
        }
        return true;
        
        
    }
}

/*
0. 3 = 0 
1. 3 = 3 
2. 3 = 9
3. 3 = 27 
4. 3 = 81
5. 3 = 243
6.
7.
8.
9.
10.
11.
12.
13.
14.
15.
16. 3 = 4,30,46,721


*/
