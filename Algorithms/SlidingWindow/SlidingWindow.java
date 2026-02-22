package Algorithms.SlidingWindow;

/*
===========================================================
Sliding Window Technique in Java
===========================================================
Topics Covered:
 1) Fixed-size window – max sum subarray of size k
 2) Fixed-size window – max of each window (monotonic deque)
 3) Variable window – longest substring without repeating chars
 4) Variable window – minimum window substring
 5) Variable window – longest subarray with sum <= k
 6) Variable window – fruits into baskets (at most 2 distinct)
 7) Variable window – longest substring with at most K distinct
 8) Count subarrays with product less than k
 9) Minimum size subarray sum >= target
10) Find all anagrams in a string
===========================================================
*/

import java.util.*;

public class SlidingWindow {

    // =========================================================================
    // 1️⃣  FIXED WINDOW – Maximum Sum Subarray of size K
    // =========================================================================

    public static int maxSumSubarrayK(int[] arr, int k) {
        int windowSum = 0, maxSum = 0;
        for(int i = 0; i < k; i++) windowSum += arr[i];   // build first window
        maxSum = windowSum;
        for(int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];               // slide
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    // =========================================================================
    // 2️⃣  FIXED WINDOW – Max of Each Window (Sliding Window Maximum)
    // =========================================================================

    /** Uses monotonic deque; O(n) time */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();  // stores indices
        for(int i = 0; i < n; i++) {
            // remove indices outside window
            while(!dq.isEmpty() && dq.peekFirst() < i - k + 1) dq.pollFirst();
            // remove smaller elements from back
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
            dq.addLast(i);
            if(i >= k - 1) result[i - k + 1] = nums[dq.peekFirst()];
        }
        return result;
    }

    // =========================================================================
    // 3️⃣  VARIABLE WINDOW – Longest Substring Without Repeating Characters
    // =========================================================================

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0, left = 0;
        for(int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if(map.containsKey(c)) left = Math.max(left, map.get(c) + 1);
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // =========================================================================
    // 4️⃣  VARIABLE WINDOW – Minimum Window Substring
    // =========================================================================

    public static String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty()) return "";
        int[] need = new int[128], have = new int[128];
        for(char c : t.toCharArray()) need[c]++;
        int formed = 0, required = 0;
        for(int x : need) if(x > 0) required++;
        int left = 0, minLen = Integer.MAX_VALUE, minLeft = 0;
        for(int right = 0; right < s.length(); right++) {
            have[s.charAt(right)]++;
            if(need[s.charAt(right)] > 0 && have[s.charAt(right)] == need[s.charAt(right)]) formed++;
            while(formed == required) {
                if(right - left + 1 < minLen) { minLen = right - left + 1; minLeft = left; }
                have[s.charAt(left)]--;
                if(need[s.charAt(left)] > 0 && have[s.charAt(left)] < need[s.charAt(left)]) formed--;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    // =========================================================================
    // 5️⃣  VARIABLE WINDOW – Longest Subarray with Sum <= K (non-negative)
    // =========================================================================

    public static int longestSubarraySumK(int[] arr, int k) {
        int left = 0, sum = 0, maxLen = 0;
        for(int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while(sum > k) sum -= arr[left++];
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // =========================================================================
    // 6️⃣  VARIABLE WINDOW – Fruits Into Baskets (at most 2 distinct)
    // =========================================================================

    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;
        for(int right = 0; right < fruits.length; right++) {
            basket.merge(fruits[right], 1, Integer::sum);
            while(basket.size() > 2) {
                int f = fruits[left++];
                basket.merge(f, -1, Integer::sum);
                if(basket.get(f) == 0) basket.remove(f);
            }
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }

    // =========================================================================
    // 7️⃣  VARIABLE WINDOW – Longest Substring with At Most K Distinct Chars
    // =========================================================================

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;
        for(int right = 0; right < s.length(); right++) {
            map.merge(s.charAt(right), 1, Integer::sum);
            while(map.size() > k) {
                char c = s.charAt(left++);
                map.merge(c, -1, Integer::sum);
                if(map.get(c) == 0) map.remove(c);
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // =========================================================================
    // 8️⃣  Count Subarrays with Product Less Than K
    // =========================================================================

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0;
        int left = 0, product = 1, count = 0;
        for(int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while(product >= k) product /= nums[left++];
            count += right - left + 1;
        }
        return count;
    }

    // =========================================================================
    // 9️⃣  Minimum Size Subarray Sum >= Target
    // =========================================================================

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
        for(int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while(sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // =========================================================================
    // 1️⃣0️⃣  Find All Anagrams in a String
    // =========================================================================

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] need = new int[26], have = new int[26];
        for(char c : p.toCharArray()) need[c - 'a']++;
        int k = p.length();
        for(int i = 0; i < s.length(); i++) {
            have[s.charAt(i) - 'a']++;
            if(i >= k) have[s.charAt(i - k) - 'a']--;
            if(Arrays.equals(need, have)) result.add(i - k + 1);
        }
        return result;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Max sum (k=3):       " + maxSumSubarrayK(new int[]{2,1,5,1,3,2}, 3));

        System.out.println("Sliding max (k=3):   " + Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));

        System.out.println("Longest no-repeat:   " + lengthOfLongestSubstring("abcabcbb"));

        System.out.println("Min window:          " + minWindow("ADOBECODEBANC", "ABC"));

        System.out.println("Longest sum<=6:      " + longestSubarraySumK(new int[]{3,1,2,7,4,2,1,1,5}, 6));

        System.out.println("Fruits (2 baskets):  " + totalFruit(new int[]{1,2,1,2,3}));

        System.out.println("K-distinct (k=2):    " + lengthOfLongestSubstringKDistinct("eceba", 2));

        System.out.println("Product < 100:       " + numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));

        System.out.println("Min len sum>=7:      " + minSubArrayLen(7, new int[]{2,3,1,2,4,3}));

        System.out.println("Anagrams of 'ab':    " + findAnagrams("cbaebabacd", "ab"));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation                        | Time     | Space
---------------------------------|----------|------
Max Sum Fixed Window             | O(n)     | O(1)
Sliding Window Maximum           | O(n)     | O(k)
Longest No-Repeat Substring      | O(n)     | O(1)
Minimum Window Substring         | O(n)     | O(1)
Longest Sum <= K                 | O(n)     | O(1)
Fruits Into Baskets              | O(n)     | O(1)
K Distinct Substring             | O(n)     | O(k)
Product < K Subarrays            | O(n)     | O(1)
Min Subarray Sum >= Target       | O(n)     | O(1)
Find All Anagrams                | O(n)     | O(1)
===========================================================
*/
