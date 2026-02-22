package DataStructures.Queues;

/*
===========================================================
Queue Implementation Using Linked List
===========================================================

Operations:
1) enqueue(val) - Add to rear
2) dequeue()    - Remove from front
3) peek()       - Return front without removing
4) isEmpty()    - Check if empty
5) size()       - Return number of elements

Advantage: No fixed size, O(1) enqueue and dequeue.

===========================================================
*/

public class QueueUsingLinkedList {

    // =========================
    // Node Definition
    // =========================
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // =========================
    // Queue Implementation
    // =========================
    static class LinkedListQueue {
        private Node front;
        private Node rear;
        private int size;

        LinkedListQueue() {
            this.front = null;
            this.rear = null;
            this.size = 0;
        }

        // =====================================================
        // 1️⃣ ENQUEUE - add to rear - O(1)
        // =====================================================
        public void enqueue(int val) {
            Node newNode = new Node(val);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        // =====================================================
        // 2️⃣ DEQUEUE - remove from front - O(1)
        // =====================================================
        public int dequeue() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int val = front.val;
            front = front.next;
            if (front == null) rear = null;
            size--;
            return val;
        }

        // =====================================================
        // 3️⃣ PEEK - front element - O(1)
        // =====================================================
        public int peek() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            return front.val;
        }

        // =====================================================
        // 4️⃣ IS EMPTY
        // =====================================================
        public boolean isEmpty() {
            return front == null;
        }

        // =====================================================
        // 5️⃣ SIZE
        // =====================================================
        public int size() {
            return size;
        }

        // =====================================================
        // PRINT
        // =====================================================
        public void print() {
            System.out.print("Queue (front -> rear): ");
            Node current = front;
            while (current != null) {
                System.out.print(current.val + (current.next != null ? " -> " : ""));
                current = current.next;
            }
            System.out.println();
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.print();

        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue());
        queue.print();

        System.out.println("Size: " + queue.size());
        System.out.println("isEmpty: " + queue.isEmpty());

        // Dequeue all
        while (!queue.isEmpty()) {
            System.out.print("Dequeued: " + queue.dequeue() + "  ");
        }
        System.out.println("\nQueue empty: " + queue.isEmpty());
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

enqueue:   Time O(1),  Space O(1)
dequeue:   Time O(1),  Space O(1)
peek:      Time O(1),  Space O(1)
isEmpty:   Time O(1),  Space O(1)
size:      Time O(1),  Space O(1)

Overall Space: O(n) for n elements

===========================================================
*/
