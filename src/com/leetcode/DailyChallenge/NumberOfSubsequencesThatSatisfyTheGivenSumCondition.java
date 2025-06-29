package com.leetcode.DailyChallenge;

import java.util.Arrays;

/**
 * LeetCode Problem 1498: Number of Subsequences That Satisfy the Given Sum Condition
 * 
 * Problem Description:
 * You are given an array of integers nums and an integer target.
 * 
 * Return the number of non-empty subsequences of nums such that the sum of the minimum 
 * and maximum element on it is less than or equal to target. Since the answer may be 
 * too large, return it modulo 10^9 + 7.
 * 
 * A subsequence is a sequence that can be derived from another sequence by deleting 
 * some or no elements without changing the order of the remaining elements.
 * 
 * Example 1:
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition:
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * 
 * Example 2:
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition:
 * [3] -> (3 + 3 <= 10)
 * [3,3] -> (3 + 3 <= 10)
 * [3,3,6] -> (3 + 6 <= 10)
 * [3,6] -> (3 + 6 <= 10)
 * [3,3,6,8] -> (3 + 8 <= 10)
 * [6] -> (6 + 6 <= 10)
 * 
 * Example 3:
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * 
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= target <= 10^6
 * 
 * Solution Approach:
 * 1. Sort the array to easily find min and max values
 * 2. Use two pointers approach
 * 3. For each valid pair (min, max), calculate how many subsequences can be formed
 * 4. Use binary exponentiation for power calculations
 */
public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition {
    
    private static final int MOD = 1000000007;
    
    /**
     * Main solution method
     */
    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        
        // Precompute powers of 2 modulo MOD
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        int left = 0, right = n - 1;
        int result = 0;
        
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                // All subsequences with min = nums[left] and max <= nums[right] are valid
                result = (result + pow2[right - left]) % MOD;
                left++;
            } else {
                right--;
            }
        }
        
        return result;
    }
    
    /**
     * Alternative solution using binary search
     */
    public int numSubseqBinarySearch(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        
        // Precompute powers of 2
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }
        
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            // Find the largest j such that nums[i] + nums[j] <= target
            int j = binarySearch(nums, target - nums[i], i);
            if (j >= i) {
                result = (result + pow2[j - i]) % MOD;
            }
        }
        
        return result;
    }
    
    private int binarySearch(int[] nums, int target, int start) {
        int left = start, right = nums.length - 1;
        int result = start - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Test cases
     */
    public static void main(String[] args) {
        NumberOfSubsequencesThatSatisfyTheGivenSumCondition solution = 
            new NumberOfSubsequencesThatSatisfyTheGivenSumCondition();
        
        // Test case 1
        int[] nums1 = {3, 5, 6, 7};
        int target1 = 9;
        System.out.println("Test 1: " + solution.numSubseq(nums1, target1)); // Expected: 4
        
        // Test case 2
        int[] nums2 = {3, 3, 6, 8};
        int target2 = 10;
        System.out.println("Test 2: " + solution.numSubseq(nums2, target2)); // Expected: 6
        
        // Test case 3
        int[] nums3 = {2, 3, 3, 4, 6, 7};
        int target3 = 12;
        System.out.println("Test 3: " + solution.numSubseq(nums3, target3)); // Expected: 61
        
        // Additional test case
        int[] nums4 = {1, 2, 3, 4, 5};
        int target4 = 7;
        System.out.println("Test 4: " + solution.numSubseq(nums4, target4));
    }
}
