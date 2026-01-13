class Solution {
    public double separateSquares(int[][] squares) {
        long total = 0;
        HashMap<Long, Long> diff = new HashMap<>();
        for (int[] sq : squares) {
            long y = sq[1], s = sq[2];
            total += s * s;
            diff.put(y, diff.getOrDefault(y, 0L) + s);
            diff.put(y + s, diff.getOrDefault(y + s, 0L) - s);
        }
        long[] curr = new long[diff.size()];
        int idx = 0;
        for (long k : diff.keySet())
            curr[idx++] = k;
        Arrays.sort(curr);
        long area = 0, target = 0;
        for (int i = 0; i + 1 < curr.length; i++) {
            long y = curr[i], y2 = curr[i + 1];
            target += diff.get(y);
            area += target * (y2 - y);
            if (area * 2L >= total)
                return (double) y2 - (double) (area * 2L - total) / (double) (target * 2L);
        }
        return (double) curr[curr.length - 1];
    }
}
