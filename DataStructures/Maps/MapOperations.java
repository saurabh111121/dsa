package DataStructures.Maps;

import java.util.*;

/*
===========================================================
Map Operations in Java
===========================================================

1) HashMap          - O(1) avg, unordered
2) LinkedHashMap    - O(1) avg, insertion-ordered
3) TreeMap          - O(log n), sorted by key

Operations on each:
- put, get, remove, containsKey, containsValue
- iterate entries, keys, values
- merge, compute, getOrDefault

Common patterns & applications:
- Frequency counter
- Group anagrams
- Word count
- LRU Cache (LinkedHashMap)

===========================================================
*/

public class MapOperations {

    // =====================================================
    // 1️⃣ HASHMAP - BASIC OPERATIONS
    // =====================================================
    public static void hashMapDemo() {
        HashMap<String, Integer> map = new HashMap<>();

        // put
        map.put("apple", 3);
        map.put("banana", 1);
        map.put("cherry", 5);
        map.put("apple", 10); // update

        // get
        System.out.println("get('apple'): " + map.get("apple"));
        System.out.println("get('mango'): " + map.get("mango")); // null

        // getOrDefault
        System.out.println("getOrDefault('mango', 0): " + map.getOrDefault("mango", 0));

        // containsKey / containsValue
        System.out.println("containsKey('banana'): " + map.containsKey("banana"));
        System.out.println("containsValue(5): " + map.containsValue(5));

        // remove
        map.remove("banana");
        System.out.println("After remove('banana'): " + map);

        // size / isEmpty
        System.out.println("size: " + map.size() + ", isEmpty: " + map.isEmpty());

        // iterate
        System.out.print("Entries: ");
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.print(e.getKey() + "=" + e.getValue() + " ");
        }
        System.out.println();

        // putIfAbsent, computeIfAbsent
        map.putIfAbsent("durian", 7);
        map.computeIfAbsent("elderberry", k -> k.length());
        System.out.println("After putIfAbsent/compute: " + map);

        // merge
        map.merge("apple", 5, Integer::sum); // apple = 10 + 5 = 15
        System.out.println("After merge apple+5: " + map.get("apple"));
    }

    // =====================================================
    // 2️⃣ LINKED HASH MAP - insertion order preserved
    // =====================================================
    public static void linkedHashMapDemo() {
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("c", 3); lhm.put("a", 1); lhm.put("b", 2);
        System.out.println("LinkedHashMap (insertion order): " + lhm);

        // Access-ordered LinkedHashMap (useful for LRU Cache)
        LinkedHashMap<String, Integer> accessOrdered = new LinkedHashMap<>(16, 0.75f, true);
        accessOrdered.put("x", 1); accessOrdered.put("y", 2); accessOrdered.put("z", 3);
        accessOrdered.get("x"); // access x -> moves to end
        System.out.println("Access-ordered after get('x'): " + accessOrdered);
    }

    // =====================================================
    // 3️⃣ TREE MAP - sorted by key
    // =====================================================
    public static void treeMapDemo() {
        TreeMap<Integer, String> tm = new TreeMap<>();
        tm.put(5, "five"); tm.put(2, "two"); tm.put(8, "eight");
        tm.put(1, "one"); tm.put(4, "four");

        System.out.println("TreeMap (sorted): " + tm);
        System.out.println("firstKey: " + tm.firstKey() + ", lastKey: " + tm.lastKey());
        System.out.println("floorKey(3): " + tm.floorKey(3));   // <= 3
        System.out.println("ceilingKey(3): " + tm.ceilingKey(3)); // >= 3
        System.out.println("headMap(5): " + tm.headMap(5));   // < 5
        System.out.println("tailMap(5): " + tm.tailMap(5));   // >= 5
        System.out.println("subMap(2,5): " + tm.subMap(2, 5)); // [2,5)
    }

    // =====================================================
    // 4️⃣ FREQUENCY COUNTER PATTERN
    // =====================================================
    public static Map<Character, Integer> frequencyCount(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }
        return freq;
    }

    // =====================================================
    // 5️⃣ GROUP ANAGRAMS
    // =====================================================
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    // =====================================================
    // 6️⃣ WORD COUNT
    // =====================================================
    public static Map<String, Integer> wordCount(String[] words) {
        Map<String, Integer> count = new HashMap<>();
        for (String w : words) count.merge(w, 1, Integer::sum);
        return count;
    }

    // =====================================================
    // 7️⃣ LRU CACHE using LinkedHashMap
    // =====================================================
    static class LRUCache {
        private final int capacity;
        private final LinkedHashMap<Integer, Integer> cache;

        LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) { return cache.getOrDefault(key, -1); }

        public void put(int key, int value) { cache.put(key, value); }

        public String toString() { return cache.toString(); }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== HashMap ===");
        hashMapDemo();

        System.out.println("\n=== LinkedHashMap ===");
        linkedHashMapDemo();

        System.out.println("\n=== TreeMap ===");
        treeMapDemo();

        System.out.println("\n=== Frequency Count ===");
        System.out.println("'programming': " + frequencyCount("programming"));

        System.out.println("\n=== Group Anagrams ===");
        String[] words = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(words));

        System.out.println("\n=== Word Count ===");
        String[] sentence = {"the","cat","sat","on","the","mat","the"};
        System.out.println(wordCount(sentence));

        System.out.println("\n=== LRU Cache (capacity=3) ===");
        LRUCache lru = new LRUCache(3);
        lru.put(1,1); lru.put(2,2); lru.put(3,3);
        System.out.println("After put 1,2,3: " + lru);
        lru.get(1); // access 1
        lru.put(4,4); // evicts 2 (LRU)
        System.out.println("After get(1) + put(4): " + lru);
        System.out.println("get(2): " + lru.get(2)); // -1, evicted
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

HashMap:
  put/get/remove:    Time O(1) avg,    Space O(n)
  containsKey:       Time O(1) avg

LinkedHashMap:
  put/get/remove:    Time O(1) avg,    Space O(n)
  Iteration:         Time O(n), preserves insertion or access order

TreeMap:
  put/get/remove:    Time O(log n),    Space O(n)
  firstKey/lastKey:  Time O(log n)
  floorKey/ceiling:  Time O(log n)
  headMap/tailMap:   Time O(log n)

LRU Cache:
  get/put:           Time O(1),        Space O(capacity)

===========================================================
*/
