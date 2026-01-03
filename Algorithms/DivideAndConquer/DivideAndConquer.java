package Algorithms.DivideAndConquer;

/*
===========================================================
Divide and Conquer Algorithms in Java
===========================================================
Topics Covered:
 1) Merge Sort (D&C canonical example)
 2) Quick Sort (D&C with partitioning)
 3) Binary Search (recursive D&C)
 4) Maximum Subarray – Kadane's vs D&C
 5) Count Inversions (merge sort variant)
 6) Closest Pair of Points
 7) Strassen Matrix Multiplication (concept)
 8) Majority Element (Boyer-Moore + D&C)
===========================================================
*/

import java.util.*;

public class DivideAndConquer {

    // =========================================================================
    // 1️⃣  MERGE SORT  –  classic D&C O(n log n)
    // =========================================================================

    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] tmp = Arrays.copyOfRange(arr, l, r + 1);
        int i = 0, j = m - l + 1, k = l;
        while (i <= m - l && j <= r - l)
            arr[k++] = tmp[i] <= tmp[j] ? tmp[i++] : tmp[j++];
        while (i <= m - l) arr[k++] = tmp[i++];
        while (j <= r - l) arr[k++] = tmp[j++];
    }

    // =========================================================================
    // 2️⃣  COUNT INVERSIONS  –  pairs (i,j) where i<j and arr[i]>arr[j]
    // =========================================================================

    public static long countInversions(int[] arr, int l, int r) {
        if (l >= r) return 0;
        int m = l + (r - l) / 2;
        long inv = countInversions(arr, l, m) + countInversions(arr, m + 1, r);
        inv += mergeCount(arr, l, m, r);
        return inv;
    }

    private static long mergeCount(int[] arr, int l, int m, int r) {
        int[] tmp = Arrays.copyOfRange(arr, l, r + 1);
        int i = 0, j = m - l + 1, k = l;
        long inv = 0;
        while (i <= m - l && j <= r - l) {
            if (tmp[i] <= tmp[j]) arr[k++] = tmp[i++];
            else { arr[k++] = tmp[j++]; inv += (m - l + 1) - i; }
        }
        while (i <= m - l) arr[k++] = tmp[i++];
        while (j <= r - l) arr[k++] = tmp[j++];
        return inv;
    }

    // =========================================================================
    // 3️⃣  MAXIMUM SUBARRAY – D&C approach O(n log n)
    // =========================================================================

    public static int maxSubarrayDC(int[] arr, int l, int r) {
        if (l == r) return arr[l];
        int m = l + (r - l) / 2;
        int leftMax  = maxSubarrayDC(arr, l, m);
        int rightMax = maxSubarrayDC(arr, m + 1, r);
        int crossMax = maxCrossing(arr, l, m, r);
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private static int maxCrossing(int[] arr, int l, int m, int r) {
        int leftSum = Integer.MIN_VALUE, sum = 0;
        for (int i = m; i >= l; i--) { sum += arr[i]; leftSum = Math.max(leftSum, sum); }
        int rightSum = Integer.MIN_VALUE; sum = 0;
        for (int i = m + 1; i <= r; i++) { sum += arr[i]; rightSum = Math.max(rightSum, sum); }
        return leftSum + rightSum;
    }

    /** Kadane's O(n) for comparison */
    public static int maxSubarrayKadane(int[] arr) {
        int maxSum = arr[0], cur = arr[0];
        for (int i = 1; i < arr.length; i++) { cur = Math.max(arr[i], cur + arr[i]); maxSum = Math.max(maxSum, cur); }
        return maxSum;
    }

    // =========================================================================
    // 4️⃣  CLOSEST PAIR OF POINTS  –  O(n log n)
    // =========================================================================

    static class Point { double x, y; Point(double x, double y){ this.x=x; this.y=y; } }

    public static double closestPair(Point[] pts) {
        Point[] sortedX = pts.clone();
        Arrays.sort(sortedX, Comparator.comparingDouble(p -> p.x));
        return closestRec(sortedX, 0, sortedX.length - 1);
    }

    private static double closestRec(Point[] pts, int l, int r) {
        if (r - l <= 2) return bruteForce(pts, l, r);
        int m = (l + r) / 2;
        double mid = pts[m].x;
        double d = Math.min(closestRec(pts, l, m), closestRec(pts, m + 1, r));
        // collect strip
        List<Point> strip = new ArrayList<>();
        for (int i = l; i <= r; i++) if (Math.abs(pts[i].x - mid) < d) strip.add(pts[i]);
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++)
            for (int j = i+1; j < strip.size() && strip.get(j).y - strip.get(i).y < d; j++)
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
        return d;
    }

    private static double bruteForce(Point[] pts, int l, int r) {
        double d = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) for (int j = i+1; j <= r; j++) d = Math.min(d, dist(pts[i], pts[j]));
        return d;
    }

    private static double dist(Point a, Point b) {
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    // =========================================================================
    // 5️⃣  MAJORITY ELEMENT  –  D&C O(n log n), Boyer-Moore O(n)
    // =========================================================================

    public static int majorityElementDC(int[] nums, int l, int r) {
        if (l == r) return nums[l];
        int m = l + (r - l) / 2;
        int left  = majorityElementDC(nums, l, m);
        int right = majorityElementDC(nums, m + 1, r);
        if (left == right) return left;
        int lCount = 0, rCount = 0;
        for (int i = l; i <= r; i++) {
            if (nums[i] == left)  lCount++;
            if (nums[i] == right) rCount++;
        }
        return lCount > rCount ? left : right;
    }

    /** Boyer-Moore Voting O(n) O(1) */
    public static int majorityElementBM(int[] nums) {
        int candidate = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) { candidate = nums[i]; count = 1; }
            else count += (nums[i] == candidate) ? 1 : -1;
        }
        return candidate;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 1, 9, 3};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Merge Sort:       " + Arrays.toString(arr));

        int[] inv = {2, 4, 1, 3, 5};
        System.out.println("Inversions:       " + countInversions(inv.clone(), 0, inv.length-1));

        int[] sub = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("MaxSubarray D&C:  " + maxSubarrayDC(sub, 0, sub.length-1));
        System.out.println("MaxSubarray Kad:  " + maxSubarrayKadane(sub));

        Point[] pts = {new Point(2,3),new Point(12,30),new Point(40,50),
                       new Point(5,1),new Point(12,10),new Point(3,4)};
        System.out.printf("Closest pair:     %.4f%n", closestPair(pts));

        int[] maj = {2,2,1,1,1,2,2};
        System.out.println("Majority D&C:     " + majorityElementDC(maj, 0, maj.length-1));
        System.out.println("Majority BM:      " + majorityElementBM(maj));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Algorithm              | Time        | Space
-----------------------|-------------|------
Merge Sort             | O(n log n)  | O(n)
Count Inversions       | O(n log n)  | O(n)
Max Subarray D&C       | O(n log n)  | O(log n)
Max Subarray Kadane    | O(n)        | O(1)
Closest Pair           | O(n log n)  | O(n)
Majority Element D&C   | O(n log n)  | O(log n)
Majority Element BM    | O(n)        | O(1)
===========================================================
*/
