package DataStructures.Queues;

/*
===========================================================
Queue Implementation Using Array
===========================================================

1) Simple Queue (Linear Array)
   - enqueue, dequeue, peek, isEmpty, isFull, size

2) Circular Queue (Efficient - avoids shifting)
   - enqueue, dequeue, peek, isEmpty, isFull, size

===========================================================
*/

public class QueueUsingArray {

    // =========================
    // 1️⃣ SIMPLE QUEUE
    // =========================
    static class SimpleQueue {
        private int[] data;
        private int front;
        private int rear;
        private int capacity;

        SimpleQueue(int capacity) {
            this.capacity = capacity;
            this.data = new int[capacity];
            this.front = 0;
            this.rear = -1;
        }

        // ENQUEUE - add to rear
        public void enqueue(int val) {
            if (isFull()) throw new RuntimeException("Queue is full");
            data[++rear] = val;
        }

        // DEQUEUE - remove from front (shifts all elements - O(n))
        public int dequeue() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int val = data[front];
            // Shift elements left
            for (int i = 0; i < rear; i++) data[i] = data[i + 1];
            rear--;
            return val;
        }

        // PEEK - front element
        public int peek() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            return data[front];
        }

        public boolean isEmpty() { return rear == -1; }

        public boolean isFull() { return rear == capacity - 1; }

        public int size() { return rear - front + 1; }

        public void print() {
            System.out.print("Queue (front -> rear): ");
            for (int i = front; i <= rear; i++) {
                System.out.print(data[i] + (i < rear ? " -> " : ""));
            }
            System.out.println();
        }
    }

    // =========================
    // 2️⃣ CIRCULAR QUEUE
    // =========================
    static class CircularQueue {
        private int[] data;
        private int front;
        private int rear;
        private int size;
        private int capacity;

        CircularQueue(int capacity) {
            this.capacity = capacity;
            this.data = new int[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        // ENQUEUE - O(1)
        public void enqueue(int val) {
            if (isFull()) throw new RuntimeException("Queue is full");
            rear = (rear + 1) % capacity;
            data[rear] = val;
            size++;
        }

        // DEQUEUE - O(1)
        public int dequeue() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int val = data[front];
            front = (front + 1) % capacity;
            size--;
            return val;
        }

        // PEEK - front element
        public int peek() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            return data[front];
        }

        public boolean isEmpty() { return size == 0; }

        public boolean isFull() { return size == capacity; }

        public int size() { return size; }

        public void print() {
            System.out.print("CircularQueue (front -> rear): ");
            for (int i = 0; i < size; i++) {
                System.out.print(data[(front + i) % capacity]);
                if (i < size - 1) System.out.print(" -> ");
            }
            System.out.println();
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Simple Queue ===");
        SimpleQueue sq = new SimpleQueue(10);
        sq.enqueue(10);
        sq.enqueue(20);
        sq.enqueue(30);
        sq.enqueue(40);
        sq.print();

        System.out.println("Peek: " + sq.peek());
        System.out.println("Dequeue: " + sq.dequeue());
        sq.print();
        System.out.println("Size: " + sq.size());
        System.out.println("isEmpty: " + sq.isEmpty());

        System.out.println("\n=== Circular Queue ===");
        CircularQueue cq = new CircularQueue(5);
        cq.enqueue(1);
        cq.enqueue(2);
        cq.enqueue(3);
        cq.enqueue(4);
        cq.enqueue(5);
        cq.print();

        System.out.println("Dequeue: " + cq.dequeue());
        System.out.println("Dequeue: " + cq.dequeue());
        cq.print();

        cq.enqueue(6); // wrap around
        cq.enqueue(7); // wrap around
        cq.print();

        System.out.println("Size: " + cq.size());
        System.out.println("isFull: " + cq.isFull());
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

Simple Queue:
  enqueue:  Time O(1),  Space O(1)
  dequeue:  Time O(n),  Space O(1)  (shifting elements)
  peek:     Time O(1),  Space O(1)

Circular Queue:
  enqueue:  Time O(1),  Space O(1)
  dequeue:  Time O(1),  Space O(1)
  peek:     Time O(1),  Space O(1)

Circular Queue is preferred - no element shifting needed.

===========================================================
*/
