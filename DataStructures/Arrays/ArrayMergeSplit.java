package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Array Merging and Splitting Operations in Java
===========================================================

1) Merge two arrays
2) Split an array at a given index
3) Testing Examples
===========================================================
*/

public class ArrayMergeSplit {

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};

        // 1. Merge arrays
        int[] merged = mergeArrays(arr1, arr2);
        System.out.println("Merged array: " + Arrays.toString(merged));

        // 2. Split array at index 3
        int[][] split = splitArray(merged, 3);
        System.out.println("Split arrays:");
        System.out.println("First part: " + Arrays.toString(split[0]));
        System.out.println("Second part: " + Arrays.toString(split[1]));
    }

    // Merge two arrays
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }

    // Split array at given index
    public static int[][] splitArray(int[] arr, int index) {
        if (index < 0 || index > arr.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int[] first = Arrays.copyOfRange(arr, 0, index);
        int[] second = Arrays.copyOfRange(arr, index, arr.length);
        return new int[][]{first, second};
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Merge: O(n + m) time, O(n + m) space where n and m are array lengths
Split: O(n) time, O(n) space for resulting arrays
===========================================================
*/
