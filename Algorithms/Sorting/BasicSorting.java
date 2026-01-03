package Algorithms.Sorting;

/*
===========================================================
Basic Sorting Algorithms in Java
===========================================================
Topics Covered:
 1) Bubble Sort (iterative + optimized)
 2) Selection Sort
 3) Insertion Sort (iterative + recursive)
 4) Shell Sort
 5) Counting Sort
 6) Radix Sort
 7) Bucket Sort
===========================================================
*/

import java.util.*;

public class BasicSorting {

    // =========================================================================
    // 1️⃣  BUBBLE SORT  –  O(n²) average, O(n) best
    // =========================================================================

    /** Standard iterative bubble sort */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
    }

    /** Optimized: stop early if no swaps in a pass */
    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) { swap(arr, j, j + 1); swapped = true; }
            if (!swapped) break;   // already sorted
        }
    }

    // =========================================================================
    // 2️⃣  SELECTION SORT  –  O(n²) always
    // =========================================================================

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[minIdx]) minIdx = j;
            swap(arr, i, minIdx);
        }
    }

    // =========================================================================
    // 3️⃣  INSERTION SORT  –  O(n²) average, O(n) best (nearly sorted)
    // =========================================================================

    /** Iterative insertion sort */
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) { arr[j + 1] = arr[j]; j--; }
            arr[j + 1] = key;
        }
    }

    /** Recursive insertion sort */
    public static void insertionSortRecursive(int[] arr, int n) {
        if (n <= 1) return;
        insertionSortRecursive(arr, n - 1);
        int last = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > last) { arr[j + 1] = arr[j]; j--; }
        arr[j + 1] = last;
    }

    // =========================================================================
    // 4️⃣  SHELL SORT  –  O(n log² n) with Knuth gap sequence
    // =========================================================================

    public static void shellSort(int[] arr) {
        int n = arr.length;
        // Knuth sequence: 1, 4, 13, 40, ...
        int gap = 1;
        while (gap < n / 3) gap = 3 * gap + 1;
        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > key) { arr[j + gap] = arr[j]; j -= gap; }
                arr[j + gap] = key;
            }
            gap /= 3;
        }
    }

    // =========================================================================
    // 5️⃣  COUNTING SORT  –  O(n + k) time/space, stable, non-comparison
    // =========================================================================

    public static int[] countingSort(int[] arr, int maxVal) {
        int[] count = new int[maxVal + 1];
        for (int v : arr) count[v]++;
        // prefix sums for stability
        for (int i = 1; i <= maxVal; i++) count[i] += count[i - 1];
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) output[--count[arr[i]]] = arr[i];
        return output;
    }

    // =========================================================================
    // 6️⃣  RADIX SORT  –  O(d*(n+k)), stable, for non-negative integers
    // =========================================================================

    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortByDigit(arr, exp);
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int v : arr) count[(v / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) output[--count[(arr[i] / exp) % 10]] = arr[i];
        System.arraycopy(output, 0, arr, 0, n);
    }

    // =========================================================================
    // 7️⃣  BUCKET SORT  –  O(n + k) average for uniform distribution
    // =========================================================================

    public static double[] bucketSort(double[] arr) {
        int n = arr.length;
        @SuppressWarnings("unchecked")
        List<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();
        for (double v : arr) {
            int idx = (int)(v * n);
            if (idx >= n) idx = n - 1;
            buckets[idx].add(v);
        }
        for (List<Double> b : buckets) Collections.sort(b);
        double[] result = new double[n];
        int k = 0;
        for (List<Double> b : buckets)
            for (double v : b) result[k++] = v;
        return result;
    }

    // ── helpers ──────────────────────────────────────────────────────────────
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        int[] a1 = {64, 34, 25, 12, 22, 11, 90};
        bubbleSortOptimized(a1.clone()); // just show it works
        System.out.println("Bubble:    " + Arrays.toString(sorted(a1, BasicSorting::bubbleSortOptimized)));
        System.out.println("Selection: " + Arrays.toString(sorted(a1, BasicSorting::selectionSort)));
        System.out.println("Insertion: " + Arrays.toString(sorted(a1, BasicSorting::insertionSort)));
        System.out.println("Shell:     " + Arrays.toString(sorted(a1, BasicSorting::shellSort)));

        int[] a2 = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Counting:  " + Arrays.toString(countingSort(a2, 9)));

        int[] a3 = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(a3);
        System.out.println("Radix:     " + Arrays.toString(a3));

        double[] a4 = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};
        System.out.println("Bucket:    " + Arrays.toString(bucketSort(a4)));
    }

    @FunctionalInterface
    interface Sorter { void sort(int[] arr); }
    private static int[] sorted(int[] src, Sorter s) {
        int[] copy = src.clone(); s.sort(copy); return copy;
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Algorithm      | Best     | Average  | Worst    | Space  | Stable
---------------|----------|----------|----------|--------|-------
Bubble         | O(n)     | O(n²)    | O(n²)    | O(1)   | Yes
Selection      | O(n²)    | O(n²)    | O(n²)    | O(1)   | No
Insertion      | O(n)     | O(n²)    | O(n²)    | O(1)   | Yes
Shell          | O(n log n)| O(n log²n)| O(n²) | O(1)   | No
Counting       | O(n+k)   | O(n+k)   | O(n+k)   | O(k)   | Yes
Radix          | O(d*n)   | O(d*n)   | O(d*n)   | O(n+k) | Yes
Bucket         | O(n)     | O(n+k)   | O(n²)    | O(n)   | Yes
===========================================================
*/
