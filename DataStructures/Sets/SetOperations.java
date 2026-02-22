package DataStructures.Sets;

import java.util.*;

/*
===========================================================
Set Operations in Java
===========================================================

1) HashSet          - O(1) avg, unordered
2) LinkedHashSet    - O(1) avg, insertion-ordered
3) TreeSet          - O(log n), sorted

Set operations: union, intersection, difference, subset check

Applications:
- Remove duplicates
- Check anagram
- Unique characters
- Symmetric difference

===========================================================
*/

public class SetOperations {

    // =====================================================
    // 1️⃣ HASHSET - BASIC OPERATIONS
    // =====================================================
    public static void hashSetDemo() {
        HashSet<Integer> set = new HashSet<>();

        // add
        set.add(3); set.add(1); set.add(4); set.add(1); set.add(5);
        System.out.println("HashSet (no duplicates): " + set);

        // contains
        System.out.println("contains(4): " + set.contains(4));
        System.out.println("contains(9): " + set.contains(9));

        // remove
        set.remove(1);
        System.out.println("After remove(1): " + set);

        // size / isEmpty
        System.out.println("size: " + set.size() + ", isEmpty: " + set.isEmpty());

        // iterate
        System.out.print("Iterate: ");
        for(int x : set) System.out.print(x + " ");
        System.out.println();
    }

    // =====================================================
    // 2️⃣ LINKED HASH SET - insertion order preserved
    // =====================================================
    public static void linkedHashSetDemo() {
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        lhs.add("banana"); lhs.add("apple"); lhs.add("cherry"); lhs.add("apple");
        System.out.println("LinkedHashSet (insertion order): " + lhs);
    }

    // =====================================================
    // 3️⃣ TREE SET - sorted order
    // =====================================================
    public static void treeSetDemo() {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(5); ts.add(2); ts.add(8); ts.add(1); ts.add(4);

        System.out.println("TreeSet (sorted): " + ts);
        System.out.println("first: " + ts.first() + ", last: " + ts.last());
        System.out.println("floor(3): " + ts.floor(3));     // <= 3
        System.out.println("ceiling(3): " + ts.ceiling(3)); // >= 3
        System.out.println("headSet(5): " + ts.headSet(5)); // < 5
        System.out.println("tailSet(5): " + ts.tailSet(5)); // >= 5
        System.out.println("subSet(2,5): " + ts.subSet(2, 5)); // [2,5)
    }

    // =====================================================
    // 4️⃣ SET UNION
    // =====================================================
    public static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    // =====================================================
    // 5️⃣ SET INTERSECTION
    // =====================================================
    public static Set<Integer> intersection(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    // =====================================================
    // 6️⃣ SET DIFFERENCE (A - B)
    // =====================================================
    public static Set<Integer> difference(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

    // =====================================================
    // 7️⃣ SYMMETRIC DIFFERENCE (A △ B)
    // =====================================================
    public static Set<Integer> symmetricDiff(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.addAll(b);
        Set<Integer> common = intersection(a, b);
        result.removeAll(common);
        return result;
    }

    // =====================================================
    // 8️⃣ IS SUBSET
    // =====================================================
    public static boolean isSubset(Set<Integer> sub, Set<Integer> sup) {
        return sup.containsAll(sub);
    }

    // =====================================================
    // APPLICATION: CHECK ANAGRAM
    // =====================================================
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s.toCharArray()) freq.merge(c, 1, Integer::sum);
        for(char c : t.toCharArray()) {
            freq.merge(c, -1, Integer::sum);
            if(freq.get(c) < 0) return false;
        }
        return true;
    }

    // =====================================================
    // APPLICATION: FIRST UNIQUE CHARACTER
    // =====================================================
    public static int firstUniqueChar(String s) {
        Map<Character, Integer> freq = new LinkedHashMap<>();
        for(char c : s.toCharArray()) freq.merge(c, 1, Integer::sum);
        for(int i = 0; i < s.length(); i++) {
            if(freq.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    // =====================================================
    // APPLICATION: COUNT DISTINCT ELEMENTS IN WINDOW
    // =====================================================
    public static int[] distinctInWindow(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Map<Integer, Integer> windowFreq = new HashMap<>();

        for(int i = 0; i < n; i++) {
            windowFreq.merge(arr[i], 1, Integer::sum);
            if(i >= k) {
                int old = arr[i - k];
                if(windowFreq.get(old) == 1) windowFreq.remove(old);
                else windowFreq.merge(old, -1, Integer::sum);
            }
            if(i >= k - 1) result[i - k + 1] = windowFreq.size();
        }
        return result;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== HashSet ===");
        hashSetDemo();

        System.out.println("\n=== LinkedHashSet ===");
        linkedHashSetDemo();

        System.out.println("\n=== TreeSet ===");
        treeSetDemo();

        System.out.println("\n=== Set Operations ===");
        Set<Integer> a = new HashSet<>(Arrays.asList(1,2,3,4,5));
        Set<Integer> b = new HashSet<>(Arrays.asList(3,4,5,6,7));
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("Union:         " + union(a, b));
        System.out.println("Intersection:  " + intersection(a, b));
        System.out.println("Difference A-B:" + difference(a, b));
        System.out.println("Symmetric Diff:" + symmetricDiff(a, b));
        Set<Integer> sub = new HashSet<>(Arrays.asList(1,2,3));
        System.out.println("{1,2,3} subset of A: " + isSubset(sub, a));
        System.out.println("{1,2,6} subset of A: " + isSubset(new HashSet<>(Arrays.asList(1,2,6)), a));

        System.out.println("\n=== Applications ===");
        System.out.println("isAnagram('anagram','nagaram'): " + isAnagram("anagram","nagaram"));
        System.out.println("isAnagram('rat','car'): " + isAnagram("rat","car"));
        System.out.println("firstUniqueChar('leetcode'): " + firstUniqueChar("leetcode"));
        int[] arr = {1,2,1,3,4,2,3};
        System.out.print("Distinct in window k=4: ");
        for(int v : distinctInWindow(arr, 4)) System.out.print(v + " ");
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

HashSet add/remove/contains:  Time O(1) avg,    Space O(n)
LinkedHashSet same as HashSet, preserves insertion order
TreeSet add/remove/contains:  Time O(log n),    Space O(n)
TreeSet floor/ceiling:        Time O(log n)
Set Union/Intersection/Diff:  Time O(n),        Space O(n)
isAnagram:                    Time O(n),        Space O(1) (26 chars)
firstUniqueChar:              Time O(n),        Space O(1)
Distinct in Window:           Time O(n),        Space O(k)

===========================================================
*/
