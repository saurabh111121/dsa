/*
check - https://leetcode.com/submissions/detail/1886049494/
*/

class Solution {
    int MOD = 1_000_000_007;
    int[] addBoundaries(int[] fences, int limit) {
        int[] arr = new int[fences.length + 2];
        arr[0] = 1;
        arr[arr.length - 1] = limit;
        System.arraycopy(fences, 0, arr, 1, fences.length);
        return arr;
    }

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int[] h = addBoundaries(hFences, m);
        int[] v = addBoundaries(vFences, n);
        Arrays.sort(h);
        Arrays.sort(v);
        Set<Integer> hDistances = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hDistances.add(h[j] - h[i]);
            }
        }
        long maxSide = -1;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int dist = v[j] - v[i];
                if (hDistances.contains(dist)) {
                    maxSide = Math.max(maxSide, dist);
                }
            }
        }
        if (maxSide == -1) return -1;
        return (int) ((maxSide * maxSide) % MOD);
    }
}
