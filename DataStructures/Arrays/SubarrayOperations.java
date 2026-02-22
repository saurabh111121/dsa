package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Subarray Operations in Java
===========================================================

1) Find maximum subarray sum (Kadane's Algorithm)
2) Display subarray with maximum sum
3) Testing Examples
===========================================================
*/

public class SubarrayOperations {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Original array: " + Arrays.toString(arr));

        int maxSum = kadaneMaxSubarraySum(arr);
        System.out.println("Maximum subarray sum: " + maxSum);

        int[] maxSubarray = getMaxSubarray(arr);
        System.out.println("Subarray with maximum sum: " + Arrays.toString(maxSubarray));
    }

    // Kadane's Algorithm: returns maximum subarray sum
    public static int kadaneMaxSubarraySum(int[] arr) {
        int maxCurrent = arr[0];
        int maxGlobal = arr[0];

        for(int i = 1; i < arr.length; i++) {
            maxCurrent = Math.max(arr[i], maxCurrent + arr[i]);
            maxGlobal = Math.max(maxGlobal, maxCurrent);
        }

        return maxGlobal;
    }

    // Get the actual subarray with maximum sum
    public static int[] getMaxSubarray(int[] arr) {
        int maxCurrent = arr[0], maxGlobal = arr[0];
        int start = 0, end = 0, s = 0;

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > maxCurrent + arr[i]) {
                maxCurrent = arr[i];
                s = i;
            } else {
                maxCurrent += arr[i];
            }

            if(maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
                start = s;
                end = i;
            }
        }

        return Arrays.copyOfRange(arr, start, end + 1);
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Kadane's Algorithm (max sum): O(n) time, O(1) space
Get max subarray: O(n) time, O(k) space where k is size of subarray
===========================================================
*/
