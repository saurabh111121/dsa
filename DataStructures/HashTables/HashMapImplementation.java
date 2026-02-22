package DataStructures.HashTables;

/*
===========================================================
HashMap Implementation from Scratch
===========================================================

Custom HashMap using:
- Array of LinkedList buckets (Separate Chaining)
- Hash function: key.hashCode() % capacity
- Dynamic resizing when load factor exceeds 0.75

Operations:
1) put(key, value)    - Insert or update
2) get(key)           - Retrieve value
3) remove(key)        - Delete entry
4) containsKey(key)   - Check existence
5) size()             - Number of entries
6) keys()             - All keys
7) values()           - All values
8) resize()           - Double capacity when load > 0.75

===========================================================
*/

import java.util.ArrayList;
import java.util.List;

public class HashMapImplementation {

    // =========================
    // Entry Node (key-value pair)
    // =========================
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // =========================
    // Custom HashMap
    // =========================
    static class CustomHashMap<K, V> {
        private Entry<K, V>[] buckets;
        private int size;
        private int capacity;
        private static final double LOAD_FACTOR = 0.75;

        @SuppressWarnings("unchecked")
        CustomHashMap() {
            this.capacity = 16;
            this.buckets = new Entry[capacity];
            this.size = 0;
        }

        // =====================================================
        // HASH FUNCTION
        // =====================================================
        private int hash(K key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        // =====================================================
        // 1️⃣ PUT - insert or update - O(1) average
        // =====================================================
        public void put(K key, V value) {
            if((double) size / capacity >= LOAD_FACTOR) resize();

            int index = hash(key);
            Entry<K, V> head = buckets[index];

            // Check if key already exists -> update
            while(head != null) {
                if(head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            // Key not found -> insert at head of chain
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.next = buckets[index];
            buckets[index] = newEntry;
            size++;
        }

        // =====================================================
        // 2️⃣ GET - retrieve value - O(1) average
        // =====================================================
        public V get(K key) {
            int index = hash(key);
            Entry<K, V> current = buckets[index];

            while(current != null) {
                if(current.key.equals(key)) return current.value;
                current = current.next;
            }

            return null; // Key not found
        }

        // =====================================================
        // 3️⃣ REMOVE - delete entry - O(1) average
        // =====================================================
        public boolean remove(K key) {
            int index = hash(key);
            Entry<K, V> current = buckets[index];
            Entry<K, V> prev = null;

            while(current != null) {
                if(current.key.equals(key)) {
                    if(prev == null) buckets[index] = current.next;
                    else prev.next = current.next;
                    size--;
                    return true;
                }
                prev = current;
                current = current.next;
            }

            return false; // Key not found
        }

        // =====================================================
        // 4️⃣ CONTAINS KEY
        // =====================================================
        public boolean containsKey(K key) {
            return get(key) != null;
        }

        // =====================================================
        // 5️⃣ SIZE
        // =====================================================
        public int size() { return size; }

        public boolean isEmpty() { return size == 0; }

        // =====================================================
        // 6️⃣ KEYS
        // =====================================================
        public List<K> keys() {
            List<K> keys = new ArrayList<>();
            for(Entry<K, V> bucket : buckets) {
                Entry<K, V> current = bucket;
                while(current != null) {
                    keys.add(current.key);
                    current = current.next;
                }
            }
            return keys;
        }

        // =====================================================
        // 7️⃣ VALUES
        // =====================================================
        public List<V> values() {
            List<V> values = new ArrayList<>();
            for(Entry<K, V> bucket : buckets) {
                Entry<K, V> current = bucket;
                while(current != null) {
                    values.add(current.value);
                    current = current.next;
                }
            }
            return values;
        }

        // =====================================================
        // 8️⃣ RESIZE - double capacity & rehash
        // =====================================================
        @SuppressWarnings("unchecked")
        private void resize() {
            int oldCapacity = capacity;
            capacity *= 2;
            Entry<K, V>[] newBuckets = new Entry[capacity];

            for(int i = 0; i < oldCapacity; i++) {
                Entry<K, V> current = buckets[i];
                while(current != null) {
                    Entry<K, V> next = current.next;
                    int newIndex = Math.abs(current.key.hashCode()) % capacity;
                    current.next = newBuckets[newIndex];
                    newBuckets[newIndex] = current;
                    current = next;
                }
            }

            buckets = newBuckets;
            System.out.println("[Resized from " + oldCapacity + " to " + capacity + "]");
        }

        public void print() {
            System.out.println("HashMap contents:");
            for(int i = 0; i < capacity; i++) {
                if(buckets[i] != null) {
                    System.out.print("  bucket[" + i + "]: ");
                    Entry<K, V> current = buckets[i];
                    while(current != null) {
                        System.out.print("{" + current.key + "=" + current.value + "}");
                        if(current.next != null) System.out.print(" -> ");
                        current = current.next;
                    }
                    System.out.println();
                }
            }
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Put operations
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("date", 4);
        map.put("elderberry", 5);

        map.print();
        System.out.println("Size: " + map.size());

        // Get operations
        System.out.println("Get 'apple':  " + map.get("apple"));
        System.out.println("Get 'banana': " + map.get("banana"));
        System.out.println("Get 'mango':  " + map.get("mango"));

        // Update
        map.put("apple", 100);
        System.out.println("Updated 'apple': " + map.get("apple"));

        // ContainsKey
        System.out.println("Contains 'cherry': " + map.containsKey("cherry"));
        System.out.println("Contains 'grape':  " + map.containsKey("grape"));

        // Remove
        map.remove("banana");
        System.out.println("After removing 'banana': " + map.get("banana"));
        System.out.println("Size: " + map.size());

        // Keys and Values
        System.out.println("Keys:   " + map.keys());
        System.out.println("Values: " + map.values());

        // Test resize - add many entries
        CustomHashMap<Integer, String> bigMap = new CustomHashMap<>();
        for(int i = 0; i < 20; i++) {
            bigMap.put(i, "val" + i);
        }
        System.out.println("BigMap size after 20 inserts: " + bigMap.size());
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

put():         Time O(1) average, O(n) worst (all in one bucket)
get():         Time O(1) average, O(n) worst
remove():      Time O(1) average, O(n) worst
containsKey(): Time O(1) average
keys():        Time O(n + capacity)
values():      Time O(n + capacity)
resize():      Time O(n),  triggered at load factor 0.75

Space: O(n) for n entries

Key concepts:
- Separate Chaining: each bucket is a linked list
- Load Factor: size/capacity, triggers resize at 0.75
- Rehashing: all entries reinserted into new larger array

===========================================================
*/
