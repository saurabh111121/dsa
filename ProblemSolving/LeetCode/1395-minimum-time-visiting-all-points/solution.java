class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for(int i=0;i<points.length-1;i++) {
            int pX = points[i][0];
            int pY = points[i][1];
            int tX = points[i+1][0];
            int tY = points[i+1][1];
            ans += Math.max( Math.abs(tX - pX) , Math.abs(tY - pY));
         }
         return ans;       
    }
}
/*
points = [[1,1],[3,4],[-1,0]]
[1,1] to [3,4] = 3  => (3-1) & (4-1) = 2&3 = 3
[3,4] to [-1,0] = 4 => (-1 -3 ) & 4 = 4 & 4 = 4

---
points = [[3,2],[-2,2]] -> 5
32
21
10
0 -1
-1 -2
-2 -2
= 5 &4 = 5


*/
