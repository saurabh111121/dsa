package Algorithms.Sorting;

/*
===========================================================
Advanced Sorting Algorithms in Java
===========================================================
Topics Covered:
 1) Merge Sort (recursive + iterative/bottom-up)
 2) Quick Sort (recursive, random pivot, 3-way partition)
 3) Heap Sort (in-place, iterative build)
 4) Tim Sort concept (natural merging)
 5) Cycle Sort (minimum writes)
===========================================================
*/

import java.util.*;

public class AdvancedSorting {

    // =========================================================================
    // 1️⃣  MERGE SORT  –  O(n log n) always, O(n) space, stable
    // =========================================================================

    /** Recursive merge sort */
    public static void mergeSort(int[] arr, int left, int right) {
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] tmp = Arrays.copyOfRange(arr, l, r + 1);
        int i = 0, j = m - l + 1, k = l;
        while(i <= m - l && j <= r - l)
            arr[k++] = tmp[i] <= tmp[j] ? tmp[i++] : tmp[j++];
        while(i <= m - l) arr[k++] = tmp[i++];
        while(j <= r - l)  arr[k++] = tmp[j++];
    }

    /** Bottom-up iterative merge sort – no recursion stack */
    public static void mergeSortIterative(int[] arr) {
        int n = arr.length;
        for(int size = 1; size < n; size *= 2)
            for(int left = 0; left < n - size; left += 2 * size) {
                int mid   = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(arr, left, mid, right);
            }
    }

    // =========================================================================
    // 2️⃣  QUICK SORT  –  O(n log n) average, O(n²) worst
    // =========================================================================

    /** Classic quick sort with last-element pivot */
    public static void quickSort(int[] arr, int low, int high) {
        if(low >= high) return;
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for(int j = low; j < high; j++)
            if(arr[j] <= pivot) swap(arr, ++i, j);
        swap(arr, i + 1, high);
        return i + 1;
    }

    /** Randomized quick sort – avoids worst case on sorted input */
    public static void quickSortRandom(int[] arr, int low, int high) {
        if(low >= high) return;
        int rand = low + (int)(Math.random() * (high - low + 1));
        swap(arr, rand, high);
        int pi = partition(arr, low, high);
        quickSortRandom(arr, low, pi - 1);
        quickSortRandom(arr, pi + 1, high);
    }

    /** 3-way quick sort (Dutch National Flag) – O(n) on many duplicates */
    public static void quickSort3Way(int[] arr, int low, int high) {
        if(low >= high) return;
        int lt = low, gt = high, i = low + 1;
        int pivot = arr[low];
        while(i <= gt) {
            if      (arr[i] < pivot) swap(arr, lt++, i++);
            else if(arr[i] > pivot) swap(arr, i, gt--);
            else                     i++;
        }
        quickSort3Way(arr, low, lt - 1);
        quickSort3Way(arr, gt + 1, high);
    }

    /** Iterative quick sort using explicit stack */
    public static void quickSortIterative(int[] arr, int low, int high) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(low);
        stack.push(high);
        while(!stack.isEmpty()) {
            int h = stack.pop(), l = stack.pop();
            if(l >= h) continue;
            int pi = partition(arr, l, h);
            stack.push(l);  stack.push(pi - 1);
            stack.push(pi + 1); stack.push(h);
        }
    }

    // =========================================================================
    // 3️⃣  HEAP SORT  –  O(n log n) always, O(1) space, unstable
    // =========================================================================

    public static void heapSort(int[] arr) {
        int n = arr.length;
        // Build max-heap O(n)
        for(int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        // Extract one by one
        for(int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2*i+1, r = 2*i+2;
        if(l < n && arr[l] > arr[largest]) largest = l;
        if(r < n && arr[r] > arr[largest]) largest = r;
        if(largest != i) { swap(arr, i, largest); heapify(arr, n, largest); }
    }

    // =========================================================================
    // 4️⃣  TIM SORT CONCEPT  –  O(n log n) worst, O(n) best (hybrid)
    // =========================================================================
    // Java's Arrays.sort() uses TimSort for objects. Below is a simplified demo.

    private static final int RUN = 32;

    public static void timSort(int[] arr) {
        int n = arr.length;
        // Sort small runs with insertion sort
        for(int i = 0; i < n; i += RUN)
            insertionSortRange(arr, i, Math.min(i + RUN - 1, n - 1));
        // Merge runs
        for(int size = RUN; size < n; size *= 2)
            for(int left = 0; left < n; left += 2 * size) {
                int mid   = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                if(mid < right) merge(arr, left, mid, right);
            }
    }

    private static void insertionSortRange(int[] arr, int left, int right) {
        for(int i = left + 1; i <= right; i++) {
            int key = arr[i], j = i - 1;
            while(j >= left && arr[j] > key) { arr[j + 1] = arr[j]; j--; }
            arr[j + 1] = key;
        }
    }

    // =========================================================================
    // 5️⃣  CYCLE SORT  –  O(n²) time but minimises memory writes (O(n) writes)
    // =========================================================================

    public static void cycleSort(int[] arr) {
        int n = arr.length, writes = 0;
        for(int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
            int item = arr[cycleStart], pos = cycleStart;
            for(int i = cycleStart + 1; i < n; i++) if(arr[i] < item) pos++;
            if(pos == cycleStart) continue;
            while(item == arr[pos]) pos++;
            swap2(arr, pos, cycleStart); writes++;
            while(pos != cycleStart) {
                pos = cycleStart;
                for(int i = cycleStart + 1; i < n; i++) if(arr[i] < arr[cycleStart]) pos++;
                while(arr[cycleStart] == arr[pos]) pos++;
                swap2(arr, pos, cycleStart); writes++;
            }
        }
    }
    private static void swap2(int[] a, int i, int j) { int t=a[i]; a[i]=a[j]; a[j]=t; }

    // ── helper ───────────────────────────────────────────────────────────────
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        int[] base = {38, 27, 43, 3, 9, 82, 10};

        int[] a = base.clone(); mergeSort(a, 0, a.length-1);
        System.out.println("Merge (recursive):  " + Arrays.toString(a));

        int[] b = base.clone(); mergeSortIterative(b);
        System.out.println("Merge (iterative):  " + Arrays.toString(b));

        int[] c = base.clone(); quickSort(c, 0, c.length-1);
        System.out.println("Quick (last pivot): " + Arrays.toString(c));

        int[] d = base.clone(); quickSort3Way(d, 0, d.length-1);
        System.out.println("Quick (3-way):      " + Arrays.toString(d));

        int[] e = base.clone(); quickSortIterative(e, 0, e.length-1);
        System.out.println("Quick (iterative):  " + Arrays.toString(e));

        int[] f = base.clone(); heapSort(f);
        System.out.println("Heap Sort:          " + Arrays.toString(f));

        int[] g = base.clone(); timSort(g);
        System.out.println("Tim Sort:           " + Arrays.toString(g));

        int[] h = {1, 8, 3, 9, 10, 10, 2, 4}; cycleSort(h);
        System.out.println("Cycle Sort:         " + Arrays.toString(h));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Algorithm      | Best       | Average    | Worst      | Space    | Stable
---------------|------------|------------|------------|----------|-------
Merge Sort     | O(n log n) | O(n log n) | O(n log n) | O(n)     | Yes
Quick Sort     | O(n log n) | O(n log n) | O(n²)      | O(log n) | No
Heap Sort      | O(n log n) | O(n log n) | O(n log n) | O(1)     | No
Tim Sort       | O(n)       | O(n log n) | O(n log n) | O(n)     | Yes
Cycle Sort     | O(n²)      | O(n²)      | O(n²)      | O(1)     | No
===========================================================
*/
