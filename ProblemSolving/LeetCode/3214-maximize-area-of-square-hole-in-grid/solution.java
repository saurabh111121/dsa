class Solution {
    int maxConsecutive(int[] bars) {
        int max = 1;
        int curr = 1;
        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                curr++;
            } else {
                curr = 1;
            }
            max = Math.max(max, curr);
        }
        return max + 1; // +1 gap size
    }
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int maxH = maxConsecutive(hBars);
        int maxV = maxConsecutive(vBars);
        int side = Math.min(maxH, maxV);
        return side * side;
    }
}

/*
n = 4; m = 3

1 2 3 4
1 2 3

hBars = [2,3], vBars = [2]

3 , 2





*/
