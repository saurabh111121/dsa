package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Array Advanced Operations in Java
===========================================================

1) Reverse an array
2) Rotate array (left and right)
3) Find maximum and minimum
4) Calculate sum of elements
5) Testing Examples
===========================================================
*/

public class ArrayAdvancedOperations {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(arr));

        // 1. Reverse the array
        reverseArray(arr);
        System.out.println("Reversed array: " + Arrays.toString(arr));

        // 2. Rotate array to the right by 2
        rotateRight(arr, 2);
        System.out.println("Array after right rotation by 2: " + Arrays.toString(arr));

        // 3. Rotate array to the left by 2
        rotateLeft(arr, 2);
        System.out.println("Array after left rotation by 2: " + Arrays.toString(arr));

        // 4. Find maximum and minimum
        System.out.println("Maximum: " + findMax(arr));
        System.out.println("Minimum: " + findMin(arr));

        // 5. Calculate sum
        System.out.println("Sum of elements: " + calculateSum(arr));
    }

    // Reverse array
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // Rotate array right by k positions
    public static void rotateRight(int[] arr, int k) {
        k = k % arr.length;
        reverseSubArray(arr, 0, arr.length - 1);
        reverseSubArray(arr, 0, k - 1);
        reverseSubArray(arr, k, arr.length - 1);
    }

    // Rotate array left by k positions
    public static void rotateLeft(int[] arr, int k) {
        k = k % arr.length;
        reverseSubArray(arr, 0, k - 1);
        reverseSubArray(arr, k, arr.length - 1);
        reverseArray(arr);
    }

    private static void reverseSubArray(int[] arr, int start, int end) {
        while(start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Find maximum
    public static int findMax(int[] arr) {
        int max = arr[0];
        for(int val : arr) {
            if(val > max) max = val;
        }
        return max;
    }

    // Find minimum
    public static int findMin(int[] arr) {
        int min = arr[0];
        for(int val : arr) {
            if(val < min) min = val;
        }
        return min;
    }

    // Calculate sum
    public static int calculateSum(int[] arr) {
        int sum = 0;
        for(int val : arr) sum += val;
        return sum;
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Reverse: O(n) time, O(1) space
Rotate (left/right): O(n) time, O(1) space
Find max/min: O(n) time, O(1) space
Calculate sum: O(n) time, O(1) space
===========================================================
*/
