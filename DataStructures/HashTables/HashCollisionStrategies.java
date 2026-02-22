package DataStructures.HashTables;

/*
===========================================================
Hash Collision Resolution Strategies
===========================================================

1) Separate Chaining
   - Each bucket holds a linked list of entries

2) Open Addressing - Linear Probing
   - On collision, probe next slot linearly

3) Open Addressing - Quadratic Probing
   - On collision, probe i^2 steps ahead

4) Open Addressing - Double Hashing
   - On collision, use second hash function for step size

===========================================================
*/

public class HashCollisionStrategies {

    // =====================================================
    // 1️⃣ SEPARATE CHAINING
    // =====================================================
    static class SeparateChaining {
        private static class Node {
            int key, val;
            Node next;
            Node(int key, int val) { this.key = key; this.val = val; }
        }

        private Node[] buckets;
        private int capacity;

        SeparateChaining(int capacity) {
            this.capacity = capacity;
            this.buckets = new Node[capacity];
        }

        private int hash(int key) { return Math.abs(key) % capacity; }

        public void put(int key, int val) {
            int idx = hash(key);
            Node curr = buckets[idx];
            while (curr != null) {
                if (curr.key == key) { curr.val = val; return; }
                curr = curr.next;
            }
            Node newNode = new Node(key, val);
            newNode.next = buckets[idx];
            buckets[idx] = newNode;
        }

        public int get(int key) {
            int idx = hash(key);
            Node curr = buckets[idx];
            while (curr != null) {
                if (curr.key == key) return curr.val;
                curr = curr.next;
            }
            return -1;
        }

        public void print() {
            System.out.println("Separate Chaining:");
            for (int i = 0; i < capacity; i++) {
                if (buckets[i] != null) {
                    System.out.print("  [" + i + "]: ");
                    Node curr = buckets[i];
                    while (curr != null) {
                        System.out.print("(" + curr.key + "," + curr.val + ")");
                        if (curr.next != null) System.out.print(" -> ");
                        curr = curr.next;
                    }
                    System.out.println();
                }
            }
        }
    }

    // =====================================================
    // 2️⃣ OPEN ADDRESSING - LINEAR PROBING
    // =====================================================
    static class LinearProbing {
        private int[] keys;
        private int[] vals;
        private boolean[] occupied;
        private boolean[] deleted;
        private int capacity;
        private int size;

        LinearProbing(int capacity) {
            this.capacity = capacity;
            this.keys = new int[capacity];
            this.vals = new int[capacity];
            this.occupied = new boolean[capacity];
            this.deleted = new boolean[capacity];
            this.size = 0;
        }

        private int hash(int key) { return Math.abs(key) % capacity; }

        public void put(int key, int val) {
            int idx = hash(key);
            while (occupied[idx] && !deleted[idx] && keys[idx] != key) {
                idx = (idx + 1) % capacity;
            }
            if (!occupied[idx] || deleted[idx]) size++;
            keys[idx] = key;
            vals[idx] = val;
            occupied[idx] = true;
            deleted[idx] = false;
        }

        public int get(int key) {
            int idx = hash(key);
            int start = idx;
            while (occupied[idx]) {
                if (!deleted[idx] && keys[idx] == key) return vals[idx];
                idx = (idx + 1) % capacity;
                if (idx == start) break;
            }
            return -1;
        }

        public void remove(int key) {
            int idx = hash(key);
            int start = idx;
            while (occupied[idx]) {
                if (!deleted[idx] && keys[idx] == key) {
                    deleted[idx] = true;
                    size--;
                    return;
                }
                idx = (idx + 1) % capacity;
                if (idx == start) break;
            }
        }

        public void print() {
            System.out.println("Linear Probing:");
            for (int i = 0; i < capacity; i++) {
                if (occupied[i] && !deleted[i]) {
                    System.out.println("  [" + i + "]: (" + keys[i] + "," + vals[i] + ")");
                }
            }
        }
    }

    // =====================================================
    // 3️⃣ OPEN ADDRESSING - QUADRATIC PROBING
    // =====================================================
    static class QuadraticProbing {
        private int[] keys;
        private int[] vals;
        private boolean[] occupied;
        private boolean[] deleted;
        private int capacity;

        QuadraticProbing(int capacity) {
            this.capacity = capacity;
            this.keys = new int[capacity];
            this.vals = new int[capacity];
            this.occupied = new boolean[capacity];
            this.deleted = new boolean[capacity];
        }

        private int hash(int key) { return Math.abs(key) % capacity; }

        public void put(int key, int val) {
            int base = hash(key);
            int i = 0;
            int idx = base;
            while (occupied[idx] && !deleted[idx] && keys[idx] != key) {
                i++;
                idx = (base + i * i) % capacity;
            }
            keys[idx] = key;
            vals[idx] = val;
            occupied[idx] = true;
            deleted[idx] = false;
        }

        public int get(int key) {
            int base = hash(key);
            int i = 0;
            int idx = base;
            while (occupied[idx]) {
                if (!deleted[idx] && keys[idx] == key) return vals[idx];
                i++;
                idx = (base + i * i) % capacity;
                if (i >= capacity) break;
            }
            return -1;
        }

        public void print() {
            System.out.println("Quadratic Probing:");
            for (int i = 0; i < capacity; i++) {
                if (occupied[i] && !deleted[i]) {
                    System.out.println("  [" + i + "]: (" + keys[i] + "," + vals[i] + ")");
                }
            }
        }
    }

    // =====================================================
    // 4️⃣ DOUBLE HASHING
    // =====================================================
    static class DoubleHashing {
        private int[] keys;
        private int[] vals;
        private boolean[] occupied;
        private boolean[] deleted;
        private int capacity;
        private int prime; // prime < capacity for second hash

        DoubleHashing(int capacity) {
            this.capacity = capacity;
            this.prime = getLargerPrime(capacity);
            this.keys = new int[capacity];
            this.vals = new int[capacity];
            this.occupied = new boolean[capacity];
            this.deleted = new boolean[capacity];
        }

        private int hash1(int key) { return Math.abs(key) % capacity; }

        // Step size: prime - (key % prime), always non-zero
        private int hash2(int key) { return prime - (Math.abs(key) % prime); }

        private int getLargerPrime(int cap) {
            int p = cap - 1;
            while (!isPrime(p)) p--;
            return p;
        }

        private boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
            return true;
        }

        public void put(int key, int val) {
            int h1 = hash1(key);
            int h2 = hash2(key);
            int i = 0;
            int idx = h1;

            while (occupied[idx] && !deleted[idx] && keys[idx] != key) {
                i++;
                idx = (h1 + i * h2) % capacity;
            }
            keys[idx] = key;
            vals[idx] = val;
            occupied[idx] = true;
            deleted[idx] = false;
        }

        public int get(int key) {
            int h1 = hash1(key);
            int h2 = hash2(key);
            int i = 0;
            int idx = h1;

            while (occupied[idx]) {
                if (!deleted[idx] && keys[idx] == key) return vals[idx];
                i++;
                idx = (h1 + i * h2) % capacity;
                if (i >= capacity) break;
            }
            return -1;
        }

        public void print() {
            System.out.println("Double Hashing (prime=" + prime + "):");
            for (int i = 0; i < capacity; i++) {
                if (occupied[i] && !deleted[i]) {
                    System.out.println("  [" + i + "]: (" + keys[i] + "," + vals[i] + ")");
                }
            }
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Separate Chaining ===");
        SeparateChaining sc = new SeparateChaining(7);
        sc.put(5, 50); sc.put(12, 120); sc.put(19, 190); // 5, 12, 19 all hash to same bucket in size 7
        sc.put(3, 30); sc.put(10, 100);
        sc.print();
        System.out.println("Get(12): " + sc.get(12));

        System.out.println("\n=== Linear Probing ===");
        LinearProbing lp = new LinearProbing(11);
        lp.put(10, 100); lp.put(21, 210); lp.put(32, 320); // all hash to 10
        lp.put(5, 50); lp.put(16, 160);
        lp.print();
        System.out.println("Get(21): " + lp.get(21));
        lp.remove(21);
        System.out.println("After remove(21), get(32): " + lp.get(32));

        System.out.println("\n=== Quadratic Probing ===");
        QuadraticProbing qp = new QuadraticProbing(11);
        qp.put(10, 100); qp.put(21, 210); qp.put(32, 320);
        qp.put(5, 50); qp.put(16, 160);
        qp.print();
        System.out.println("Get(32): " + qp.get(32));

        System.out.println("\n=== Double Hashing ===");
        DoubleHashing dh = new DoubleHashing(11);
        dh.put(10, 100); dh.put(21, 210); dh.put(32, 320);
        dh.put(5, 50); dh.put(16, 160);
        dh.print();
        System.out.println("Get(32): " + dh.get(32));
    }
}

/*
===========================================================
Comparison of Collision Strategies
===========================================================

Separate Chaining:
  - No clustering, easy deletion
  - Extra memory for linked list nodes
  - Works well with high load factors

Linear Probing:
  - Cache friendly (sequential memory)
  - Primary clustering problem
  - Simple implementation

Quadratic Probing:
  - Reduces primary clustering
  - Secondary clustering possible
  - Requires capacity to be prime for guaranteed probing

Double Hashing:
  - Best distribution, no clustering
  - Most complex implementation
  - Requires two good hash functions

All operations: Time O(1) average, O(n) worst
Space: O(n)

===========================================================
*/
