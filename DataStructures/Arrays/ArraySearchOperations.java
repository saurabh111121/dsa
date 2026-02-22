package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Array Search Operations in Java
===========================================================

1) Linear Search
2) Binary Search (Array must be sorted)
3) Testing Examples
===========================================================
*/

public class ArraySearchOperations {

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 5, 2};
        System.out.println("Original array: " + Arrays.toString(arr));

        // 1. Linear Search
        int key = 4;
        int linearResult = linearSearch(arr, key);
        System.out.println("Linear Search for " + key + ": Index = " + linearResult);

        // 2. Binary Search (requires sorted array)
        Arrays.sort(arr);
        System.out.println("Sorted array for Binary Search: " + Arrays.toString(arr));
        int binaryResult = binarySearch(arr, key);
        System.out.println("Binary Search for " + key + ": Index = " + binaryResult);
    }

    // Linear Search: returns index or -1 if not found
    public static int linearSearch(int[] arr, int key) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == key) return i;
        }
        return -1;
    }

    // Binary Search: returns index or -1 if not found
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] == key) return mid;
            else if(arr[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Linear Search:
Time Complexity: O(n)
Space Complexity: O(1)

Binary Search (sorted array):
Time Complexity: O(log n)
Space Complexity: O(1)
===========================================================
*/
