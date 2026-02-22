package DataStructures.Heaps;

import java.util.*;

/*
===========================================================
Heap Applications & Classic Problems
===========================================================

1) Find Median from Data Stream (Two Heaps)
2) K Closest Points to Origin
3) Reorganize String (Greedy + Max Heap)
4) Task Scheduler
5) Find K Pairs with Smallest Sums

===========================================================
*/

public class HeapApplications {

    // =====================================================
    // 1️⃣ FIND MEDIAN FROM DATA STREAM
    // Two heaps: maxHeap (lower half), minHeap (upper half)
    // =====================================================
    static class MedianFinder {
        private PriorityQueue<Integer> maxHeap; // lower half
        private PriorityQueue<Integer> minHeap; // upper half

        MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    // =====================================================
    // 2️⃣ K CLOSEST POINTS TO ORIGIN
    // =====================================================
    public static int[][] kClosest(int[][] points, int k) {
        // Max heap by distance - keep only k closest
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1])
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        return maxHeap.toArray(new int[k][]);
    }

    // =====================================================
    // 3️⃣ REORGANIZE STRING
    // No two adjacent chars same; use max heap by frequency
    // =====================================================
    public static String reorganizeString(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) maxHeap.offer(new int[]{i, freq[i]});
        }

        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() >= 2) {
            int[] first  = maxHeap.poll();
            int[] second = maxHeap.poll();
            sb.append((char)('a' + first[0]));
            sb.append((char)('a' + second[0]));
            if (--first[1]  > 0) maxHeap.offer(first);
            if (--second[1] > 0) maxHeap.offer(second);
        }
        if (!maxHeap.isEmpty()) {
            int[] last = maxHeap.poll();
            if (last[1] > 1) return ""; // impossible
            sb.append((char)('a' + last[0]));
        }
        return sb.toString();
    }

    // =====================================================
    // 4️⃣ TASK SCHEDULER
    // Minimum intervals needed to schedule tasks with cooldown n
    // =====================================================
    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) if (f > 0) maxHeap.offer(f);

        int time = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) temp.add(maxHeap.poll() - 1);
            }
            for (int f : temp) if (f > 0) maxHeap.offer(f);
            time += maxHeap.isEmpty() ? temp.size() : n + 1;
        }
        return time;
    }

    // =====================================================
    // 5️⃣ K PAIRS WITH SMALLEST SUMS
    // Given sorted arrays, find k pairs (u,v) with smallest u+v
    // =====================================================
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return result;

        // [sum, i, j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        while (!minHeap.isEmpty() && result.size() < k) {
            int[] curr = minHeap.poll();
            int i = curr[1], j = curr[2];
            result.add(Arrays.asList(nums1[i], nums2[j]));
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j+1], i, j+1});
            }
        }

        return result;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        // Median Finder
        System.out.println("=== Median from Stream ===");
        MedianFinder mf = new MedianFinder();
        mf.addNum(1); mf.addNum(2);
        System.out.println("Median after [1,2]: " + mf.findMedian());
        mf.addNum(3);
        System.out.println("Median after [1,2,3]: " + mf.findMedian());

        // K Closest Points
        System.out.println("\n=== K Closest Points ===");
        int[][] points = {{1,3},{-2,2},{5,8},{0,1}};
        int[][] closest = kClosest(points, 2);
        System.out.println("2 closest to origin:");
        for (int[] p : closest) System.out.println("  [" + p[0] + "," + p[1] + "]");

        // Reorganize String
        System.out.println("\n=== Reorganize String ===");
        System.out.println("Reorganize 'aab': " + reorganizeString("aab"));
        System.out.println("Reorganize 'aaab': " + reorganizeString("aaab"));

        // Task Scheduler
        System.out.println("\n=== Task Scheduler ===");
        char[] tasks = {'A','A','A','B','B','B'};
        System.out.println("Min intervals for AAABBB n=2: " + leastInterval(tasks, 2));

        // K Pairs with Smallest Sums
        System.out.println("\n=== K Pairs with Smallest Sums ===");
        int[] n1 = {1,7,11}, n2 = {2,4,6};
        System.out.println("K=3 pairs from [1,7,11],[2,4,6]: " + kSmallestPairs(n1, n2, 3));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

Median Finder addNum:    Time O(log n), Space O(n)
Median Finder findMedian:Time O(1),     Space O(1)
K Closest Points:        Time O(n log k),Space O(k)
Reorganize String:       Time O(n log 26)=O(n), Space O(26)
Task Scheduler:          Time O(n log 26)=O(n), Space O(26)
K Pairs Smallest Sums:   Time O(k log k), Space O(k)

===========================================================
*/
