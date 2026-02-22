package DataStructures.Arrays;
import java.util.Arrays;
/*
===========================================================
Array Insertion Operations in Java
===========================================================

1) Insert element at the end
2) Insert element at the beginning
3) Insert element at a specific index
4) Insert multiple elements at a specific index
===========================================================
*/

public class ArrayInsertionOperations {

    public static void main(String[] args) {
        // Original array
        int[] arr = {1, 2, 3, 4};
        System.out.println("Original array: " + Arrays.toString(arr));

        // 1. Insert element at the end
        arr = insertAtEnd(arr, 5);
        System.out.println("Insert at end: " + Arrays.toString(arr));

        // 2. Insert element at the beginning
        arr = insertAtBeginning(arr, 0);
        System.out.println("Insert at beginning: " + Arrays.toString(arr));

        // 3. Insert element at specific index
        arr = insertAtIndex(arr, 3, 99);
        System.out.println("Insert 99 at index 3: " + Arrays.toString(arr));

        // 4. Insert multiple elements at specific index
        int[] elementsToInsert = {7, 8, 9};
        arr = insertMultipleAtIndex(arr, 2, elementsToInsert);
        System.out.println("Insert multiple at index 2: " + Arrays.toString(arr));
    }

    // Method to insert at the end
    public static int[] insertAtEnd(int[] arr, int value) {
        int[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[newArr.length - 1] = value;
        return newArr;
    }

    // Method to insert at the beginning
    public static int[] insertAtBeginning(int[] arr, int value) {
        int[] newArr = new int[arr.length + 1];
        newArr[0] = value;
        System.arraycopy(arr, 0, newArr, 1, arr.length);
        return newArr;
    }

    // Method to insert at specific index
    public static int[] insertAtIndex(int[] arr, int index, int value) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = value;
        System.arraycopy(arr, index, newArr, index + 1, arr.length - index);
        return newArr;
    }

    // Method to insert multiple elements at specific index
    public static int[] insertMultipleAtIndex(int[] arr, int index, int[] values) {
        int[] newArr = new int[arr.length + values.length];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(values, 0, newArr, index, values.length);
        System.arraycopy(arr, index, newArr, index + values.length, arr.length - index);
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
