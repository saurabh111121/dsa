package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Array Deletion Operations in Java
===========================================================

1) Delete element from the end
2) Delete element from the beginning
3) Delete element at a specific index
4) Delete multiple elements from a specific index
===========================================================
*/

public class ArrayDeletionOperations {

    public static void main(String[] args) {
        // Original array
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Original array: " + Arrays.toString(arr));

        // 1. Delete element from the end
        arr = deleteFromEnd(arr);
        System.out.println("After deleting from end: " + Arrays.toString(arr));

        // 2. Delete element from the beginning
        arr = deleteFromBeginning(arr);
        System.out.println("After deleting from beginning: " + Arrays.toString(arr));

        // 3. Delete element at specific index
        arr = deleteAtIndex(arr, 2);
        System.out.println("After deleting at index 2: " + Arrays.toString(arr));

        // 4. Delete multiple elements from specific index
        arr = deleteMultipleAtIndex(arr, 1, 2);
        System.out.println("After deleting 2 elements from index 1: " + Arrays.toString(arr));
    }

    // Delete element from the end
    public static int[] deleteFromEnd(int[] arr) {
        return Arrays.copyOf(arr, arr.length - 1);
    }

    // Delete element from the beginning
    public static int[] deleteFromBeginning(int[] arr) {
        int[] newArr = new int[arr.length - 1];
        System.arraycopy(arr, 1, newArr, 0, newArr.length);
        return newArr;
    }

    // Delete element at specific index
    public static int[] deleteAtIndex(int[] arr, int index) {
        int[] newArr = new int[arr.length - 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);
        return newArr;
    }

    // Delete multiple elements from specific index
    public static int[] deleteMultipleAtIndex(int[] arr, int index, int count) {
        int[] newArr = new int[arr.length - count];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + count, newArr, index, arr.length - index - count);
        return newArr;
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Time Complexity: O(n) for all operations (due to element copying)
Space Complexity: O(n) for all operations (new array creation)
===========================================================
*/