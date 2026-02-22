package Algorithms.Searching;

/*
===========================================================
Searching Algorithms in Java
===========================================================
Topics Covered:
 1) Linear Search (iterative + recursive)
 2) Sentinel Linear Search
 3) Jump Search
 4) Interpolation Search
 5) Exponential Search
 6) Fibonacci Search
 7) Ternary Search
===========================================================
*/

import java.util.Arrays;

public class SearchingAlgorithms {

    // =========================================================================
    // 1️⃣  LINEAR SEARCH  –  O(n) time, O(1) space
    // =========================================================================

    /** Iterative linear search – returns index or -1 */
    public static int linearSearch(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == target) return i;
        return -1;
    }

    /** Recursive linear search */
    public static int linearSearchRecursive(int[] arr, int target, int idx) {
        if(idx >= arr.length) return -1;
        if(arr[idx] == target) return idx;
        return linearSearchRecursive(arr, target, idx + 1);
    }

    // =========================================================================
    // 2️⃣  SENTINEL LINEAR SEARCH  –  O(n) but avoids boundary check in loop
    // =========================================================================

    public static int sentinelSearch(int[] arr, int target) {
        int n = arr.length;
        int last = arr[n - 1];
        arr[n - 1] = target;          // place sentinel
        int i = 0;
        while(arr[i] != target) i++;
        arr[n - 1] = last;            // restore
        if(i < n - 1 || arr[n - 1] == target) return i;
        return -1;
    }

    // =========================================================================
    // 3️⃣  JUMP SEARCH  –  O(√n), sorted array required
    // =========================================================================

    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;
        while(arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.sqrt(n);
            if(prev >= n) return -1;
        }
        while(arr[prev] < target) {
            prev++;
            if(prev == Math.min(step, n)) return -1;
        }
        return arr[prev] == target ? prev : -1;
    }

    // =========================================================================
    // 4️⃣  INTERPOLATION SEARCH  –  O(log log n) avg on uniform data
    // =========================================================================

    public static int interpolationSearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while(lo <= hi && target >= arr[lo] && target <= arr[hi]) {
            if(lo == hi) return arr[lo] == target ? lo : -1;
            int pos = lo + (int)(((long)(target - arr[lo]) * (hi - lo)) / (arr[hi] - arr[lo]));
            if(arr[pos] == target) return pos;
            if(arr[pos] < target)  lo = pos + 1;
            else                    hi = pos - 1;
        }
        return -1;
    }

    // =========================================================================
    // 5️⃣  EXPONENTIAL SEARCH  –  O(log n), good for unbounded/infinite arrays
    // =========================================================================

    public static int exponentialSearch(int[] arr, int target) {
        int n = arr.length;
        if(arr[0] == target) return 0;
        int i = 1;
        while(i < n && arr[i] <= target) i *= 2;
        return binarySearch(arr, target, i / 2, Math.min(i, n - 1));
    }

    // =========================================================================
    // 6️⃣  FIBONACCI SEARCH  –  O(log n), avoids division
    // =========================================================================

    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        int fibMm2 = 0, fibMm1 = 1, fibM = 1;
        while(fibM < n) { fibMm2 = fibMm1; fibMm1 = fibM; fibM = fibMm2 + fibMm1; }
        int offset = -1;
        while(fibM > 1) {
            int i = Math.min(offset + fibMm2, n - 1);
            if(arr[i] < target)  { fibM = fibMm1; fibMm1 = fibMm2; fibMm2 = fibM - fibMm1; offset = i; }
            else if(arr[i] > target) { fibM = fibMm2; fibMm1 -= fibMm2; fibMm2 = fibM - fibMm1; }
            else return i;
        }
        if(fibMm1 == 1 && arr[offset + 1] == target) return offset + 1;
        return -1;
    }

    // =========================================================================
    // 7️⃣  TERNARY SEARCH  –  O(log₃ n), sorted array, finds max of unimodal fn
    // =========================================================================

    /** Iterative ternary search on sorted array */
    public static int ternarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while(lo <= hi) {
            int m1 = lo + (hi - lo) / 3;
            int m2 = hi - (hi - lo) / 3;
            if(arr[m1] == target) return m1;
            if(arr[m2] == target) return m2;
            if(target < arr[m1])      hi = m1 - 1;
            else if(target > arr[m2]) lo = m2 + 1;
            else { lo = m1 + 1; hi = m2 - 1; }
        }
        return -1;
    }

    /** Recursive ternary search */
    public static int ternarySearchRecursive(int[] arr, int lo, int hi, int target) {
        if(lo > hi) return -1;
        int m1 = lo + (hi - lo) / 3;
        int m2 = hi - (hi - lo) / 3;
        if(arr[m1] == target) return m1;
        if(arr[m2] == target) return m2;
        if(target < arr[m1]) return ternarySearchRecursive(arr, lo, m1 - 1, target);
        if(target > arr[m2]) return ternarySearchRecursive(arr, m2 + 1, hi, target);
        return ternarySearchRecursive(arr, m1 + 1, m2 - 1, target);
    }

    // ── shared helper ────────────────────────────────────────────────────────
    private static int binarySearch(int[] arr, int target, int lo, int hi) {
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == target) return mid;
            if(arr[mid] < target)  lo = mid + 1;
            else                    hi = mid - 1;
        }
        return -1;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        int[] unsorted = {4, 2, 7, 1, 9, 3, 8, 5, 6};
        int[] sorted   = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 7;

        System.out.println("=== Unsorted array searches ===");
        System.out.println("Linear (iter):  idx=" + linearSearch(unsorted, target));
        System.out.println("Linear (recur): idx=" + linearSearchRecursive(unsorted, target, 0));
        System.out.println("Sentinel:       idx=" + sentinelSearch(unsorted.clone(), target));

        System.out.println("\n=== Sorted array searches (target=" + target + ") ===");
        System.out.println("Jump:           idx=" + jumpSearch(sorted, target));
        System.out.println("Interpolation:  idx=" + interpolationSearch(sorted, target));
        System.out.println("Exponential:    idx=" + exponentialSearch(sorted, target));
        System.out.println("Fibonacci:      idx=" + fibonacciSearch(sorted, target));
        System.out.println("Ternary (iter): idx=" + ternarySearch(sorted, target));
        System.out.println("Ternary (recur):idx=" + ternarySearchRecursive(sorted, 0, sorted.length-1, target));

        // Not found case
        System.out.println("\nSearch for 10: idx=" + linearSearch(sorted, 10));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Algorithm        | Time (avg)   | Time (worst) | Space | Sorted?
-----------------|--------------|--------------|-------|--------
Linear           | O(n)         | O(n)         | O(1)  | No
Sentinel Linear  | O(n)         | O(n)         | O(1)  | No
Jump             | O(√n)        | O(√n)        | O(1)  | Yes
Interpolation    | O(log log n) | O(n)         | O(1)  | Yes
Exponential      | O(log n)     | O(log n)     | O(1)  | Yes
Fibonacci        | O(log n)     | O(log n)     | O(1)  | Yes
Ternary          | O(log₃ n)    | O(log₃ n)    | O(1)  | Yes
===========================================================
*/
