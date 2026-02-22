package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Array Traversal / Display Operations in Java
===========================================================

1) Traverse using for loop
2) Traverse using enhanced for loop (for-each)
3) Testing Examples
===========================================================
*/

public class ArrayTraversalOperations {

    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 20, 25};
        System.out.println("Original array using Arrays.toString(): " + Arrays.toString(arr));

        // 1. Traverse using normal for loop
        System.out.print("Traversal using for loop: ");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 2. Traverse using enhanced for loop
        System.out.print("Traversal using enhanced for loop: ");
        for(int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Time Complexity: O(n) – need to visit each element
Space Complexity: O(1) – in-place traversal, no extra space needed
===========================================================
*/

