package Algorithms.TwoPointers;

/*
===========================================================
Two Pointers Technique in Java
===========================================================
Topics Covered:
 1) Two Sum in sorted array
 2) Three Sum (all unique triplets)
 3) Four Sum
 4) Remove duplicates from sorted array
 5) Move zeros
 6) Container with most water
 7) Trapping Rain Water
 8) Palindrome check
 9) Reverse string / array
10) Sort colors (Dutch National Flag)
11) Linked list cycle (Floyd's)
12) Merge two sorted arrays in-place
===========================================================
*/

import java.util.*;

public class TwoPointers {

    // =========================================================================
    // 1️⃣  TWO SUM IN SORTED ARRAY
    // =========================================================================

    /** Returns indices of pair summing to target; arr must be sorted */
    public static int[] twoSum(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while(lo < hi) {
            int sum = arr[lo] + arr[hi];
            if(sum == target) return new int[]{lo, hi};
            if(sum < target)  lo++;
            else               hi--;
        }
        return new int[]{-1, -1};
    }

    // =========================================================================
    // 2️⃣  THREE SUM  –  find all unique triplets summing to 0
    // =========================================================================

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;  // skip duplicates
            int lo = i + 1, hi = nums.length - 1;
            while(lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                    while(lo < hi && nums[hi] == nums[hi-1]) hi--;
                    lo++; hi--;
                } else if(sum < 0) lo++;
                else hi--;
            }
        }
        return result;
    }

    // =========================================================================
    // 3️⃣  FOUR SUM  –  find all unique quadruplets summing to target
    // =========================================================================

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n - 3; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i + 1; j < n - 2; j++) {
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                int lo = j + 1, hi = n - 1;
                while(lo < hi) {
                    long sum = (long)nums[i] + nums[j] + nums[lo] + nums[hi];
                    if(sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while(lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if(sum < target) lo++;
                    else hi--;
                }
            }
        }
        return result;
    }

    // =========================================================================
    // 4️⃣  REMOVE DUPLICATES FROM SORTED ARRAY
    // =========================================================================

    /** Returns new length, modifies in-place */
    public static int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int slow = 0;
        for(int fast = 1; fast < nums.length; fast++)
            if(nums[fast] != nums[slow]) nums[++slow] = nums[fast];
        return slow + 1;
    }

    // =========================================================================
    // 5️⃣  MOVE ZEROS TO END  –  stable (preserve relative order)
    // =========================================================================

    public static void moveZeroes(int[] nums) {
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++)
            if(nums[fast] != 0) nums[slow++] = nums[fast];
        while(slow < nums.length) nums[slow++] = 0;
    }

    // =========================================================================
    // 6️⃣  CONTAINER WITH MOST WATER
    // =========================================================================

    public static int maxArea(int[] height) {
        int lo = 0, hi = height.length - 1, maxWater = 0;
        while(lo < hi) {
            maxWater = Math.max(maxWater, Math.min(height[lo], height[hi]) * (hi - lo));
            if(height[lo] < height[hi]) lo++;
            else hi--;
        }
        return maxWater;
    }

    // =========================================================================
    // 7️⃣  TRAPPING RAIN WATER  –  two pointer O(n) time O(1) space
    // =========================================================================

    public static int trap(int[] height) {
        int lo = 0, hi = height.length - 1, water = 0;
        int maxLeft = 0, maxRight = 0;
        while(lo < hi) {
            if(height[lo] < height[hi]) {
                if(height[lo] >= maxLeft) maxLeft = height[lo];
                else water += maxLeft - height[lo];
                lo++;
            } else {
                if(height[hi] >= maxRight) maxRight = height[hi];
                else water += maxRight - height[hi];
                hi--;
            }
        }
        return water;
    }

    // =========================================================================
    // 8️⃣  PALINDROME CHECK
    // =========================================================================

    public static boolean isPalindrome(String s) {
        int lo = 0, hi = s.length() - 1;
        while(lo < hi) {
            while(lo < hi && !Character.isLetterOrDigit(s.charAt(lo))) lo++;
            while(lo < hi && !Character.isLetterOrDigit(s.charAt(hi))) hi--;
            if(Character.toLowerCase(s.charAt(lo)) != Character.toLowerCase(s.charAt(hi)))
                return false;
            lo++; hi--;
        }
        return true;
    }

    // =========================================================================
    // 9️⃣  REVERSE ARRAY / STRING
    // =========================================================================

    public static void reverseArray(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while(lo < hi) { int t = arr[lo]; arr[lo++] = arr[hi]; arr[hi--] = t; }
    }

    // =========================================================================
    // 1️⃣0️⃣  SORT COLORS (Dutch National Flag)  –  0s, 1s, 2s
    // =========================================================================

    public static void sortColors(int[] nums) {
        int lo = 0, mid = 0, hi = nums.length - 1;
        while(mid <= hi) {
            if      (nums[mid] == 0) { swap(nums, lo++, mid++); }
            else if(nums[mid] == 1) { mid++; }
            else                     { swap(nums, mid, hi--); }
        }
    }

    // =========================================================================
    // 1️⃣1️⃣  MERGE TWO SORTED ARRAYS IN-PLACE  (nums1 has extra space)
    // =========================================================================

    public static void mergeSorted(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while(i >= 0 && j >= 0)
            nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        while(j >= 0) nums1[k--] = nums2[j--];
    }

    // ── helper ───────────────────────────────────────────────────────────────
    private static void swap(int[] a, int i, int j) { int t=a[i]; a[i]=a[j]; a[j]=t; }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Two Sum (sorted):  " + Arrays.toString(twoSum(new int[]{1,2,3,4,6}, 6)));

        System.out.println("Three Sum:         " + threeSum(new int[]{-1,0,1,2,-1,-4}));

        System.out.println("Four Sum:          " + fourSum(new int[]{1,0,-1,0,-2,2}, 0));

        int[] arr = {0,0,1,1,1,2,2,3,3,4};
        System.out.println("Remove Dups len:   " + removeDuplicates(arr));

        int[] z = {0,1,0,3,12};
        moveZeroes(z);
        System.out.println("Move Zeros:        " + Arrays.toString(z));

        System.out.println("Max water:         " + maxArea(new int[]{1,8,6,2,5,4,8,3,7}));

        System.out.println("Trap water:        " + trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));

        System.out.println("Palindrome:        " + isPalindrome("A man, a plan, a canal: Panama"));

        int[] colors = {2,0,2,1,1,0};
        sortColors(colors);
        System.out.println("Sort Colors:       " + Arrays.toString(colors));

        int[] m1 = {1,2,3,0,0,0}; int[] m2 = {2,5,6};
        mergeSorted(m1, 3, m2, 3);
        System.out.println("Merge Sorted:      " + Arrays.toString(m1));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation               | Time      | Space
------------------------|-----------|------
Two Sum (sorted)        | O(n)      | O(1)
Three Sum               | O(n²)     | O(1)
Four Sum                | O(n³)     | O(1)
Remove Duplicates       | O(n)      | O(1)
Move Zeros              | O(n)      | O(1)
Container / Water       | O(n)      | O(1)
Trapping Rain Water     | O(n)      | O(1)
Palindrome Check        | O(n)      | O(1)
Sort Colors (DNF)       | O(n)      | O(1)
Merge Sorted (in-place) | O(m+n)    | O(1)
===========================================================
*/
