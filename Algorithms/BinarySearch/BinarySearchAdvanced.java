package Algorithms.BinarySearch;

/*
===========================================================
Binary Search - Advanced / Search Space Patterns in Java
===========================================================
Topics Covered:
 1) Binary Search on Answer (search space reduction)
 2) Koko Eating Bananas
 3) Minimum days to make M bouquets
 4) Allocate minimum pages (book allocation)
 5) Aggressive Cows (max min distance)
 6) Median of two sorted arrays
 7) Smallest divisor given threshold
 8) Capacity to ship packages within D days
===========================================================
*/

import java.util.Arrays;

public class BinarySearchAdvanced {

    // =========================================================================
    // 1️⃣  BINARY SEARCH ON ANSWER  –  Template
    // =========================================================================
    /*
     * Pattern:
     *   lo = minimum possible answer
     *   hi = maximum possible answer
     *   while(lo < hi) {
     *       mid = lo + (hi - lo) / 2
     *       if(feasible(mid)) hi = mid;   // or lo = mid + 1 depending on direction
     *       else lo = mid + 1;
     *   }
     *   return lo;
     */

    // =========================================================================
    // 2️⃣  KOKO EATING BANANAS  –  min speed k to eat all in h hours
    // =========================================================================

    public static int minEatingSpeed(int[] piles, int h) {
        int lo = 1, hi = Arrays.stream(piles).max().getAsInt();
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(canFinish(piles, h, mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private static boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;
        for(int p : piles) hours += (p + k - 1) / k;  // ceil division
        return hours <= h;
    }

    // =========================================================================
    // 3️⃣  MINIMUM DAYS TO MAKE M BOUQUETS
    // =========================================================================
    // bloomDay[i] = day flower i blooms; need m bouquets of k consecutive flowers

    public static int minDays(int[] bloomDay, int m, int k) {
        if((long) m * k > bloomDay.length) return -1;
        int lo = 1, hi = Arrays.stream(bloomDay).max().getAsInt();
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(canMakeBouquets(bloomDay, m, k, mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private static boolean canMakeBouquets(int[] bd, int m, int k, int day) {
        int bouquets = 0, consec = 0;
        for(int b : bd) {
            if(b <= day) { consec++; if(consec == k) { bouquets++; consec = 0; } }
            else consec = 0;
        }
        return bouquets >= m;
    }

    // =========================================================================
    // 4️⃣  ALLOCATE MINIMUM PAGES (Book Allocation)
    // =========================================================================
    // n books with pages[], m students; minimize max pages assigned to any student

    public static int allocateBooks(int[] pages, int m) {
        if(m > pages.length) return -1;
        int lo = Arrays.stream(pages).max().getAsInt();
        int hi = Arrays.stream(pages).sum();
        int ans = hi;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(canAllocate(pages, m, mid)) { ans = mid; hi = mid - 1; }
            else lo = mid + 1;
        }
        return ans;
    }

    private static boolean canAllocate(int[] pages, int m, int maxPages) {
        int students = 1, sum = 0;
        for(int p : pages) {
            if(p > maxPages) return false;
            if(sum + p > maxPages) { students++; sum = p; }
            else sum += p;
        }
        return students <= m;
    }

    // =========================================================================
    // 5️⃣  AGGRESSIVE COWS  –  max of minimum distance between cows
    // =========================================================================

    public static int aggressiveCows(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int lo = 1, hi = stalls[stalls.length - 1] - stalls[0], ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(canPlaceCows(stalls, cows, mid)) { ans = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans;
    }

    private static boolean canPlaceCows(int[] stalls, int cows, int minDist) {
        int placed = 1, last = stalls[0];
        for(int i = 1; i < stalls.length; i++) {
            if(stalls[i] - last >= minDist) { placed++; last = stalls[i]; }
            if(placed >= cows) return true;
        }
        return false;
    }

    // =========================================================================
    // 6️⃣  MEDIAN OF TWO SORTED ARRAYS  –  O(log(min(m,n)))
    // =========================================================================

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        int lo = 0, hi = m;
        while(lo <= hi) {
            int px = lo + (hi - lo) / 2;
            int py = (m + n + 1) / 2 - px;
            int maxX = (px == 0) ? Integer.MIN_VALUE : nums1[px - 1];
            int minX = (px == m) ? Integer.MAX_VALUE : nums1[px];
            int maxY = (py == 0) ? Integer.MIN_VALUE : nums2[py - 1];
            int minY = (py == n) ? Integer.MAX_VALUE : nums2[py];
            if(maxX <= minY && maxY <= minX) {
                if((m + n) % 2 == 1) return Math.max(maxX, maxY);
                return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
            } else if(maxX > minY) hi = px - 1;
            else lo = px + 1;
        }
        return 0;
    }

    // =========================================================================
    // 7️⃣  SMALLEST DIVISOR GIVEN THRESHOLD
    // =========================================================================

    public static int smallestDivisor(int[] nums, int threshold) {
        int lo = 1, hi = Arrays.stream(nums).max().getAsInt();
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int sum = 0;
            for(int n : nums) sum += (n + mid - 1) / mid;
            if(sum <= threshold) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    // =========================================================================
    // 8️⃣  CAPACITY TO SHIP PACKAGES IN D DAYS
    // =========================================================================

    public static int shipWithinDays(int[] weights, int days) {
        int lo = Arrays.stream(weights).max().getAsInt();
        int hi = Arrays.stream(weights).sum();
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(canShip(weights, days, mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private static boolean canShip(int[] weights, int days, int cap) {
        int d = 1, load = 0;
        for(int w : weights) {
            if(load + w > cap) { d++; load = 0; }
            load += w;
        }
        return d <= days;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("=== Koko Eating Bananas ===");
        System.out.println("Min speed: " + minEatingSpeed(new int[]{3,6,7,11}, 8)); // 4

        System.out.println("\n=== Min Days for Bouquets ===");
        System.out.println("Min days: " + minDays(new int[]{1,10,3,10,2}, 3, 1)); // 3

        System.out.println("\n=== Allocate Books ===");
        System.out.println("Min max pages: " + allocateBooks(new int[]{12,34,67,90}, 2)); // 113

        System.out.println("\n=== Aggressive Cows ===");
        System.out.println("Max min dist: " + aggressiveCows(new int[]{1,2,4,8,9}, 3)); // 3

        System.out.println("\n=== Median of Two Sorted Arrays ===");
        System.out.println("Median: " + findMedianSortedArrays(new int[]{1,3}, new int[]{2})); // 2.0
        System.out.println("Median: " + findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); // 2.5

        System.out.println("\n=== Smallest Divisor ===");
        System.out.println("Divisor: " + smallestDivisor(new int[]{1,2,5,9}, 6)); // 5

        System.out.println("\n=== Ship Packages in D Days ===");
        System.out.println("Min capacity: " + shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5)); // 15
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Problem                      | Time               | Space
-----------------------------|--------------------|------
Koko Eating Bananas          | O(n log max)       | O(1)
Min Days for Bouquets        | O(n log max)       | O(1)
Allocate Books               | O(n log sum)       | O(1)
Aggressive Cows              | O(n log n + n log D)| O(1)
Median Two Sorted Arrays     | O(log min(m,n))    | O(1)
Smallest Divisor             | O(n log max)       | O(1)
Ship Packages                | O(n log sum)       | O(1)
===========================================================
*/
