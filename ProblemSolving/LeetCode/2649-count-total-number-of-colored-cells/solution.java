class Solution {
    public long coloredCells(int n) {
        return 2L * n * n - 2L * n + 1L;
    }
}

//n4 = 21

/*

Tn =2n^2 - 2n + 1

1
5
13
25
41
61

*/
