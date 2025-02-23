class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        long perimeter = 4L * side;
        int n = points.length;
        long[] pos = new long[n];

        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) pos[i] = x;
            else if (x == side) pos[i] = side + y;
            else if (y == side) pos[i] = 3L * side - x;
            else pos[i] = 4L * side - y; // x == 0
        }

        Arrays.sort(pos);
        long[] extended = new long[2 * n];

        for (int i = 0; i < n; i++) {
            extended[i] = pos[i];
            extended[i + n] = pos[i] + perimeter;
        }

        long lo = 0, hi = 2 * perimeter, ans = 0;

        while (lo <= hi) {
            long mid = (lo + hi + 1) / 2;
            if (isFeasible(extended, n, k, mid, perimeter)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return (int) ans;
    }

    private boolean isFeasible(long[] ext, int n, int k, long d, long perimeter) {
        for (int i = 0; i < n; i++) {
            int count = 1, curr = i, end = i + n;
            long last = ext[i];

            while (count < k) {
                long target = last + d;
                int idx = Arrays.binarySearch(ext, curr + 1, end, target);
                if (idx < 0) idx = -idx - 1;
                if (idx == end) break;

                curr = idx;
                last = ext[curr];
                count++;
            }
            if (count == k && (ext[i] + perimeter - last) >= d)
                return true;
        }
        return false;
    }
}
