package Algorithms.BinarySearch;

/*
===========================================================
Binary Search - Basic Patterns in Java
===========================================================
Topics Covered:
 1) Classic Binary Search (iterative + recursive)
 2) First / Last occurrence of target
 3) Count occurrences
 4) Search in rotated sorted array (with/without duplicates)
 5) Find minimum in rotated sorted array
 6) Search in 2D sorted matrix
 7) Find peak element
 8) Sqrt(x) using binary search
===========================================================
*/

public class BinarySearchBasic {

    // =========================================================================
    // 1️⃣  CLASSIC BINARY SEARCH
    // =========================================================================

    /** Iterative – O(log n) */
    public static int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target)  lo = mid + 1;
            else                    hi = mid - 1;
        }
        return -1;
    }

    /** Recursive */
    public static int binarySearchRecursive(int[] arr, int lo, int hi, int target) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target)  return binarySearchRecursive(arr, mid + 1, hi, target);
        return binarySearchRecursive(arr, lo, mid - 1, target);
    }

    // =========================================================================
    // 2️⃣  FIRST AND LAST OCCURRENCE
    // =========================================================================

    public static int firstOccurrence(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1, result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) { result = mid; hi = mid - 1; }
            else if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return result;
    }

    public static int lastOccurrence(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1, result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) { result = mid; lo = mid + 1; }
            else if (arr[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return result;
    }

    // =========================================================================
    // 3️⃣  COUNT OCCURRENCES
    // =========================================================================

    public static int countOccurrences(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0;
        return lastOccurrence(arr, target) - first + 1;
    }

    // =========================================================================
    // 4️⃣  SEARCH IN ROTATED SORTED ARRAY (no duplicates)
    // =========================================================================

    public static int searchRotated(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return mid;
            // left half sorted
            if (arr[lo] <= arr[mid]) {
                if (target >= arr[lo] && target < arr[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {  // right half sorted
                if (target > arr[mid] && target <= arr[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    /** With duplicates – O(log n) avg, O(n) worst */
    public static boolean searchRotatedWithDups(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) return true;
            if (arr[lo] == arr[mid] && arr[mid] == arr[hi]) { lo++; hi--; continue; }
            if (arr[lo] <= arr[mid]) {
                if (target >= arr[lo] && target < arr[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {
                if (target > arr[mid] && target <= arr[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return false;
    }

    // =========================================================================
    // 5️⃣  FIND MINIMUM IN ROTATED SORTED ARRAY
    // =========================================================================

    public static int findMin(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[hi]) lo = mid + 1;
            else hi = mid;
        }
        return arr[lo];
    }

    // =========================================================================
    // 6️⃣  SEARCH IN 2D SORTED MATRIX  (rows & cols sorted)
    // =========================================================================

    /** Start from top-right; O(m+n) */
    public static boolean searchMatrix(int[][] mat, int target) {
        if (mat.length == 0) return false;
        int r = 0, c = mat[0].length - 1;
        while (r < mat.length && c >= 0) {
            if (mat[r][c] == target) return true;
            if (mat[r][c] > target)  c--;
            else r++;
        }
        return false;
    }

    /** Each row sorted + first of row > last of prev row → O(log(m*n)) */
    public static boolean searchMatrix2(int[][] mat, int target) {
        int m = mat.length, n = mat[0].length;
        int lo = 0, hi = m * n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = mat[mid / n][mid % n];
            if (val == target) return true;
            if (val < target)  lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }

    // =========================================================================
    // 7️⃣  FIND PEAK ELEMENT  –  arr[i] > arr[i-1] and arr[i+1]
    // =========================================================================

    public static int findPeakElement(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > arr[mid + 1]) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    // =========================================================================
    // 8️⃣  INTEGER SQRT  –  floor(√x) using binary search
    // =========================================================================

    public static int mySqrt(int x) {
        if (x < 2) return x;
        int lo = 1, hi = x / 2, ans = 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if ((long) mid * mid <= x) { ans = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        int[] sorted = {1, 2, 3, 4, 5, 5, 5, 6, 7};
        System.out.println("=== Classic Binary Search ===");
        System.out.println("Search 5:  " + binarySearch(sorted, 5));
        System.out.println("Search 5 (recursive): " + binarySearchRecursive(sorted, 0, sorted.length-1, 5));

        System.out.println("\n=== First / Last / Count ===");
        System.out.println("First 5: " + firstOccurrence(sorted, 5));
        System.out.println("Last  5: " + lastOccurrence(sorted, 5));
        System.out.println("Count 5: " + countOccurrences(sorted, 5));

        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("\n=== Rotated Array ===");
        System.out.println("Search 0: " + searchRotated(rotated, 0));
        System.out.println("Search 3: " + searchRotated(rotated, 3));
        System.out.println("Min:      " + findMin(rotated));

        int[][] mat = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println("\n=== 2D Matrix Search ===");
        System.out.println("Search 3: " + searchMatrix(mat, 3));
        System.out.println("Search 3 (O(log mn)): " + searchMatrix2(mat, 3));
        System.out.println("Search 13: " + searchMatrix(mat, 13));

        int[] peaks = {1, 2, 3, 1};
        System.out.println("\n=== Peak Element ===");
        System.out.println("Peak idx: " + findPeakElement(peaks));

        System.out.println("\n=== Sqrt ===");
        System.out.println("sqrt(16) = " + mySqrt(16));
        System.out.println("sqrt(8)  = " + mySqrt(8));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation                    | Time       | Space
-----------------------------|------------|------
Classic binary search        | O(log n)   | O(1)
First/Last occurrence        | O(log n)   | O(1)
Count occurrences            | O(log n)   | O(1)
Search rotated (no dups)     | O(log n)   | O(1)
Search rotated (with dups)   | O(log n)   | O(1) avg
Find min in rotated          | O(log n)   | O(1)
2D matrix search (staircase) | O(m+n)     | O(1)
2D matrix search (BS)        | O(log m*n) | O(1)
Peak element                 | O(log n)   | O(1)
Integer sqrt                 | O(log x)   | O(1)
===========================================================
*/
