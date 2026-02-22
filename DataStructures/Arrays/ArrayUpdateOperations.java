package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Array Update Operations in Java
===========================================================

1) Update element at a specific index
2) Testing Examples
===========================================================
*/

public class ArrayUpdateOperations {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("Original array: " + Arrays.toString(arr));

        // Update element at index 2
        int indexToUpdate = 2;
        int newValue = 99;
        updateAtIndex(arr, indexToUpdate, newValue);
        System.out.println("After updating index " + indexToUpdate + " to " + newValue + ": " + Arrays.toString(arr));

        // Update element at index 0
        updateAtIndex(arr, 0, 77);
        System.out.println("After updating index 0 to 77: " + Arrays.toString(arr));
    }

    // Method to update element at specific index
    public static void updateAtIndex(int[] arr, int index, int value) {
        if (index >= 0 && index < arr.length) {
            arr[index] = value;
        } else {
            System.out.println("Index " + index + " out of bounds.");
        }
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Time Complexity: O(1) – direct index access
Space Complexity: O(1) – in-place update
===========================================================
*/
