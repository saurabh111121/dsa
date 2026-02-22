class Solution {
    public boolean judgeSquareSum(int c) {
        long l = 0;
        long r = (long) Math.sqrt(c);

        while(l <= r ) {
            long sum = l*l + r*r;
            if(sum == c ) return true;
            if(sum < c){
                l++;
            }else{
                r--;
            }
        }
        return false;
    }
}

/*
a2 + b2 = c

1 * 1 + 2 * 2 = 5

1 2 3 4 5 


4^2 6^6 
16  36.  = 52

0 7 
0 49
1 49 = 50
2 6 



 26 =676
13 = 169
7 = 49
1 2 3 4 5 6 
 36 
 1 2 3 4 5 


8
2*2 + 3*3 = 13
4 + 9 = 13
13/2 = 6.5 = 6

6 6 =36
6/2  = 3 
3 3= 9 < 13
1 2 3
\


*/
