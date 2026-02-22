package DataStructures.HashTables;

/*
===========================================================
HashSet Implementation from Scratch
===========================================================

Custom HashSet using array of boolean/linked-list buckets.
Based on the same hashing principle as HashMap but stores only keys.

Operations:
1) add(val)       - Add element
2) remove(val)    - Remove element
3) contains(val)  - Check existence
4) size()         - Number of elements
5) toArray()      - Get all elements

Applications:
- Remove duplicates
- Frequency counting
- Graph visited tracking
- Two-Sum problem

===========================================================
*/

import java.util.ArrayList;
import java.util.List;

public class HashSetImplementation {

    // =========================
    // Custom HashSet
    // =========================
    static class CustomHashSet<T> {
        private Object[] buckets;
        private int size;
        private int capacity;
        private static final double LOAD_FACTOR = 0.75;

        // Sentinel to mark deleted slots (for open addressing)
        private static final Object DELETED = new Object();

        @SuppressWarnings("unchecked")
        CustomHashSet() {
            this.capacity = 16;
            this.buckets = new Object[capacity];
            this.size = 0;
        }

        // =====================================================
        // HASH FUNCTION
        // =====================================================
        private int hash(T val) {
            return Math.abs(val.hashCode()) % capacity;
        }

        // =====================================================
        // LINEAR PROBING for collision resolution
        // =====================================================
        private int probe(T val) {
            int index = hash(val);
            while (buckets[index] != null && buckets[index] != DELETED && !buckets[index].equals(val)) {
                index = (index + 1) % capacity;
            }
            return index;
        }

        // =====================================================
        // 1️⃣ ADD - O(1) average
        // =====================================================
        public boolean add(T val) {
            if ((double) size / capacity >= LOAD_FACTOR) resize();
            if (contains(val)) return false; // already exists

            int index = probe(val);
            buckets[index] = val;
            size++;
            return true;
        }

        // =====================================================
        // 2️⃣ CONTAINS - O(1) average
        // =====================================================
        @SuppressWarnings("unchecked")
        public boolean contains(T val) {
            int index = hash(val);
            int start = index;

            while (buckets[index] != null) {
                if (buckets[index] != DELETED && buckets[index].equals(val)) return true;
                index = (index + 1) % capacity;
                if (index == start) break;
            }

            return false;
        }

        // =====================================================
        // 3️⃣ REMOVE - O(1) average
        // =====================================================
        @SuppressWarnings("unchecked")
        public boolean remove(T val) {
            int index = hash(val);
            int start = index;

            while (buckets[index] != null) {
                if (buckets[index] != DELETED && buckets[index].equals(val)) {
                    buckets[index] = DELETED;
                    size--;
                    return true;
                }
                index = (index + 1) % capacity;
                if (index == start) break;
            }

            return false;
        }

        // =====================================================
        // 4️⃣ SIZE
        // =====================================================
        public int size() { return size; }

        public boolean isEmpty() { return size == 0; }

        // =====================================================
        // 5️⃣ TO ARRAY
        // =====================================================
        @SuppressWarnings("unchecked")
        public List<T> toList() {
            List<T> result = new ArrayList<>();
            for (Object bucket : buckets) {
                if (bucket != null && bucket != DELETED) {
                    result.add((T) bucket);
                }
            }
            return result;
        }

        // =====================================================
        // RESIZE
        // =====================================================
        @SuppressWarnings("unchecked")
        private void resize() {
            int oldCapacity = capacity;
            Object[] oldBuckets = buckets;
            capacity *= 2;
            buckets = new Object[capacity];
            size = 0;

            for (Object item : oldBuckets) {
                if (item != null && item != DELETED) {
                    add((T) item);
                }
            }
            System.out.println("[HashSet resized from " + oldCapacity + " to " + capacity + "]");
        }

        public void print() {
            System.out.println("HashSet: " + toList());
        }
    }

    // =====================================================
    // APPLICATION: REMOVE DUPLICATES
    // =====================================================
    public static int[] removeDuplicates(int[] arr) {
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            if (seen.add(num)) result.add(num);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // =====================================================
    // APPLICATION: TWO SUM (return indices)
    // =====================================================
    public static int[] twoSum(int[] nums, int target) {
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // =====================================================
    // APPLICATION: FIND DUPLICATES IN ARRAY
    // =====================================================
    public static List<Integer> findDuplicates(int[] arr) {
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();
        List<Integer> duplicates = new ArrayList<>();
        for (int num : arr) {
            if (!seen.add(num)) duplicates.add(num);
        }
        return duplicates;
    }

    // =====================================================
    // APPLICATION: INTERSECTION OF TWO ARRAYS
    // =====================================================
    public static int[] intersection(int[] a, int[] b) {
        java.util.HashSet<Integer> setA = new java.util.HashSet<>();
        for (int x : a) setA.add(x);

        List<Integer> result = new ArrayList<>();
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();
        for (int x : b) {
            if (setA.contains(x) && seen.add(x)) result.add(x);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // =====================================================
    // APPLICATION: LONGEST CONSECUTIVE SEQUENCE
    // =====================================================
    public static int longestConsecutive(int[] nums) {
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        for (int num : nums) set.add(num);

        int maxLen = 0;
        for (int num : set) {
            // Only start counting from sequence start
            if (!set.contains(num - 1)) {
                int current = num;
                int len = 1;
                while (set.contains(current + 1)) {
                    current++;
                    len++;
                }
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Custom HashSet ===");
        CustomHashSet<Integer> set = new CustomHashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(20); // duplicate - should not add
        set.print();
        System.out.println("Size: " + set.size());
        System.out.println("Contains 20: " + set.contains(20));
        System.out.println("Contains 99: " + set.contains(99));
        set.remove(20);
        System.out.println("After remove(20): " + set.contains(20));
        set.print();

        System.out.println("\n=== Remove Duplicates ===");
        int[] arr = {1, 2, 3, 2, 1, 4, 5};
        int[] noDups = removeDuplicates(arr);
        System.out.print("Result: ");
        for (int v : noDups) System.out.print(v + " ");
        System.out.println();

        System.out.println("\n=== Two Sum ===");
        int[] nums = {2, 7, 11, 15};
        int[] indices = twoSum(nums, 9);
        System.out.println("Two Sum target=9: indices [" + indices[0] + ", " + indices[1] + "]");

        System.out.println("\n=== Find Duplicates ===");
        int[] withDups = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Duplicates: " + findDuplicates(withDups));

        System.out.println("\n=== Intersection ===");
        int[] a = {1, 2, 2, 1};
        int[] b = {2, 2};
        int[] inter = intersection(a, b);
        System.out.print("Intersection: ");
        for (int v : inter) System.out.print(v + " ");
        System.out.println();

        System.out.println("\n=== Longest Consecutive Sequence ===");
        int[] seq = {100, 4, 200, 1, 3, 2};
        System.out.println("Longest consecutive in [100,4,200,1,3,2]: " + longestConsecutive(seq));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

add():               Time O(1) average, Space O(1)
contains():          Time O(1) average, Space O(1)
remove():            Time O(1) average, Space O(1)
size():              Time O(1),         Space O(1)
toList():            Time O(n),         Space O(n)

Applications:
Remove Duplicates:   Time O(n),  Space O(n)
Two Sum:             Time O(n),  Space O(n)
Find Duplicates:     Time O(n),  Space O(n)
Intersection:        Time O(n+m),Space O(n)
Longest Consecutive: Time O(n),  Space O(n)

===========================================================
*/
