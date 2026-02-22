package DataStructures.Queues;

/*
===========================================================
Deque (Double-Ended Queue) Implementation
===========================================================

A Deque supports insertion and deletion at both ends.

Operations:
1) addFirst(val)   - Add to front
2) addLast(val)    - Add to rear
3) removeFirst()   - Remove from front
4) removeLast()    - Remove from rear
5) peekFirst()     - Peek front
6) peekLast()      - Peek rear
7) isEmpty()       - Check if empty
8) size()          - Return size

Applications:
- Sliding Window Maximum
- Palindrome Check
- Used internally by BFS algorithms

===========================================================
*/

public class DequeOperations {

    // =========================
    // Node Definition (Doubly)
    // =========================
    static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }

    // =========================
    // Deque Using Doubly Linked List
    // =========================
    static class Deque {
        private Node front;
        private Node rear;
        private int size;

        Deque() {
            front = null;
            rear = null;
            size = 0;
        }

        // =====================================================
        // 1️⃣ ADD FIRST (to front) - O(1)
        // =====================================================
        public void addFirst(int val) {
            Node newNode = new Node(val);
            if (front == null) {
                front = rear = newNode;
            } else {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size++;
        }

        // =====================================================
        // 2️⃣ ADD LAST (to rear) - O(1)
        // =====================================================
        public void addLast(int val) {
            Node newNode = new Node(val);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                newNode.prev = rear;
                rear = newNode;
            }
            size++;
        }

        // =====================================================
        // 3️⃣ REMOVE FIRST - O(1)
        // =====================================================
        public int removeFirst() {
            if (isEmpty()) throw new RuntimeException("Deque is empty");
            int val = front.val;
            front = front.next;
            if (front != null) front.prev = null;
            else rear = null;
            size--;
            return val;
        }

        // =====================================================
        // 4️⃣ REMOVE LAST - O(1)
        // =====================================================
        public int removeLast() {
            if (isEmpty()) throw new RuntimeException("Deque is empty");
            int val = rear.val;
            rear = rear.prev;
            if (rear != null) rear.next = null;
            else front = null;
            size--;
            return val;
        }

        // =====================================================
        // 5️⃣ PEEK FIRST
        // =====================================================
        public int peekFirst() {
            if (isEmpty()) throw new RuntimeException("Deque is empty");
            return front.val;
        }

        // =====================================================
        // 6️⃣ PEEK LAST
        // =====================================================
        public int peekLast() {
            if (isEmpty()) throw new RuntimeException("Deque is empty");
            return rear.val;
        }

        // =====================================================
        // 7️⃣ IS EMPTY
        // =====================================================
        public boolean isEmpty() { return size == 0; }

        // =====================================================
        // 8️⃣ SIZE
        // =====================================================
        public int size() { return size; }

        public void print() {
            System.out.print("Deque (front -> rear): ");
            Node current = front;
            while (current != null) {
                System.out.print(current.val + (current.next != null ? " <-> " : ""));
                current = current.next;
            }
            System.out.println();
        }
    }

    // =====================================================
    // APPLICATION: SLIDING WINDOW MAXIMUM
    // Uses a deque to find max in each window of size k
    // =====================================================
    public static int[] slidingWindowMax(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        java.util.ArrayDeque<Integer> deque = new java.util.ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Remove elements outside window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove smaller elements from rear (they can never be max)
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.addLast(i);

            // Start adding to result once first window is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    // =====================================================
    // APPLICATION: CHECK PALINDROME USING DEQUE
    // =====================================================
    public static boolean isPalindromeDeque(String s) {
        java.util.ArrayDeque<Character> deque = new java.util.ArrayDeque<>();
        for (char c : s.toCharArray()) deque.addLast(c);

        while (deque.size() > 1) {
            if (!deque.pollFirst().equals(deque.pollLast())) return false;
        }

        return true;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Deque Operations ===");
        Deque deque = new Deque();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.addFirst(5);
        deque.addFirst(1);
        deque.print();

        System.out.println("PeekFirst: " + deque.peekFirst());
        System.out.println("PeekLast:  " + deque.peekLast());
        System.out.println("RemoveFirst: " + deque.removeFirst());
        System.out.println("RemoveLast: " + deque.removeLast());
        deque.print();
        System.out.println("Size: " + deque.size());

        // Sliding Window Maximum
        System.out.println("\n=== Sliding Window Maximum ===");
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = slidingWindowMax(nums, k);
        System.out.print("Input: [1,3,-1,-3,5,3,6,7], k=3 => Max: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        // Palindrome check
        System.out.println("\n=== Palindrome Check Using Deque ===");
        System.out.println("'racecar' is palindrome: " + isPalindromeDeque("racecar"));
        System.out.println("'hello' is palindrome:   " + isPalindromeDeque("hello"));
        System.out.println("'madam' is palindrome:   " + isPalindromeDeque("madam"));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

addFirst:        Time O(1),     Space O(1)
addLast:         Time O(1),     Space O(1)
removeFirst:     Time O(1),     Space O(1)
removeLast:      Time O(1),     Space O(1)
peekFirst:       Time O(1),     Space O(1)
peekLast:        Time O(1),     Space O(1)
isEmpty/size:    Time O(1),     Space O(1)

Sliding Window Max: Time O(n),  Space O(k)
Palindrome Check:   Time O(n),  Space O(n)

===========================================================
*/
