package DataStructures.Queues;

import java.util.PriorityQueue;
import java.util.Collections;

/*
===========================================================
Priority Queue Operations
===========================================================

1) Min Heap Priority Queue (default in Java)
2) Max Heap Priority Queue
3) Custom Object Priority Queue

Applications:
4) Kth Largest Element
5) Kth Smallest Element
6) Merge K Sorted Arrays
7) Top K Frequent Elements
8) Task Scheduling (CPU Scheduling)

===========================================================
*/

public class PriorityQueueOperations {

    // =========================
    // Custom node for object PQ
    // =========================
    static class Task implements Comparable<Task> {
        String name;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(other.priority, this.priority); // Max priority first
        }

        @Override
        public String toString() {
            return name + "(" + priority + ")";
        }
    }

    // =====================================================
    // 1️⃣ MIN HEAP PRIORITY QUEUE
    // =====================================================
    public static void minHeapDemo() {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(); // default: min-heap

        minPQ.offer(5);
        minPQ.offer(1);
        minPQ.offer(8);
        minPQ.offer(3);
        minPQ.offer(2);

        System.out.print("Min Heap poll order: ");
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.poll() + " ");
        }
        System.out.println();
    }

    // =====================================================
    // 2️⃣ MAX HEAP PRIORITY QUEUE
    // =====================================================
    public static void maxHeapDemo() {
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        maxPQ.offer(5);
        maxPQ.offer(1);
        maxPQ.offer(8);
        maxPQ.offer(3);
        maxPQ.offer(2);

        System.out.print("Max Heap poll order: ");
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.poll() + " ");
        }
        System.out.println();
    }

    // =====================================================
    // 3️⃣ CUSTOM OBJECT PRIORITY QUEUE
    // =====================================================
    public static void customObjectPQ() {
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();

        taskQueue.offer(new Task("Write tests", 2));
        taskQueue.offer(new Task("Deploy", 5));
        taskQueue.offer(new Task("Code review", 3));
        taskQueue.offer(new Task("Fix critical bug", 10));

        System.out.print("Task execution order: ");
        while (!taskQueue.isEmpty()) {
            System.out.print(taskQueue.poll() + " ");
        }
        System.out.println();
    }

    // =====================================================
    // 4️⃣ KTH LARGEST ELEMENT - Min Heap of size k
    // =====================================================
    public static int kthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll(); // keep only k largest
        }

        return minHeap.peek(); // top of min-heap = kth largest
    }

    // =====================================================
    // 5️⃣ KTH SMALLEST ELEMENT - Max Heap of size k
    // =====================================================
    public static int kthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) maxHeap.poll(); // keep only k smallest
        }

        return maxHeap.peek(); // top of max-heap = kth smallest
    }

    // =====================================================
    // 6️⃣ MERGE K SORTED ARRAYS
    // =====================================================
    static class ArrayEntry implements Comparable<ArrayEntry> {
        int val;
        int arrayIndex;
        int elementIndex;

        ArrayEntry(int val, int arrayIndex, int elementIndex) {
            this.val = val;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(ArrayEntry other) {
            return Integer.compare(this.val, other.val);
        }
    }

    public static int[] mergeKSorted(int[][] arrays) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>();
        int totalSize = 0;

        // Add first element of each array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new ArrayEntry(arrays[i][0], i, 0));
                totalSize += arrays[i].length;
            }
        }

        int[] result = new int[totalSize];
        int idx = 0;

        while (!minHeap.isEmpty()) {
            ArrayEntry entry = minHeap.poll();
            result[idx++] = entry.val;

            int nextIdx = entry.elementIndex + 1;
            if (nextIdx < arrays[entry.arrayIndex].length) {
                minHeap.offer(new ArrayEntry(arrays[entry.arrayIndex][nextIdx], entry.arrayIndex, nextIdx));
            }
        }

        return result;
    }

    // =====================================================
    // 7️⃣ TOP K FREQUENT ELEMENTS
    // =====================================================
    public static int[] topKFrequent(int[] nums, int k) {
        java.util.HashMap<Integer, Integer> freq = new java.util.HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);

        // Min-heap ordered by frequency
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (java.util.Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
            if (minHeap.size() > k) minHeap.poll();
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) result[i] = minHeap.poll()[0];
        return result;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Min Heap ===");
        minHeapDemo();

        System.out.println("\n=== Max Heap ===");
        maxHeapDemo();

        System.out.println("\n=== Custom Object PQ ===");
        customObjectPQ();

        System.out.println("\n=== Kth Largest / Smallest ===");
        int[] arr = {3, 2, 1, 5, 6, 4};
        System.out.println("Array: [3,2,1,5,6,4]");
        System.out.println("2nd Largest: " + kthLargest(arr, 2));
        System.out.println("2nd Smallest: " + kthSmallest(arr, 2));

        System.out.println("\n=== Merge K Sorted Arrays ===");
        int[][] arrays = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[] merged = mergeKSorted(arrays);
        System.out.print("Merged: ");
        for (int v : merged) System.out.print(v + " ");
        System.out.println();

        System.out.println("\n=== Top K Frequent Elements ===");
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] topK = topKFrequent(nums, 2);
        System.out.print("Top 2 frequent in [1,1,1,2,2,3]: ");
        for (int v : topK) System.out.print(v + " ");
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

PQ offer:             Time O(log n), Space O(1)
PQ poll:              Time O(log n), Space O(1)
PQ peek:              Time O(1),     Space O(1)

Kth Largest:          Time O(n log k), Space O(k)
Kth Smallest:         Time O(n log k), Space O(k)
Merge K Sorted:       Time O(n log k), Space O(k) (k = num arrays)
Top K Frequent:       Time O(n log k), Space O(n)

===========================================================
*/
