
class Solution {
    public double separateSquares(int[][] squares) {
        Arrays.sort(squares, Comparator.comparingInt(a -> a[1]));

        double totalArea = 0;
        for (int[] square : squares) {
            totalArea += (double) square[2] * square[2];
        }

        double targetArea = totalArea / 2;
        double low = squares[0][1];
        double high = low;
        
        for (int[] square : squares) {
            high = Math.max(high, square[1] + square[2]);
        }

        while (high - low > 1e-6) {
            double mid = (low + high) / 2;
            if (calculateAreaBelow(squares, mid) < targetArea) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private double calculateAreaBelow(int[][] squares, double y) {
        double areaBelow = 0;
        for (int[] square : squares) {
            double bottom = square[1], top = bottom + square[2];

            if (y > bottom) {
                areaBelow += Math.min(y - bottom, square[2]) * square[2];
            }
        }
        return areaBelow;
    }
}
