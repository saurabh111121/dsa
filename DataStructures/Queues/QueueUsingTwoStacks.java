package DataStructures.Queues;

import java.util.Stack;

/*
===========================================================
Queue Using Two Stacks & Stack Using Two Queues
===========================================================

1) Queue Using Two Stacks
   - Lazy approach: transfer on dequeue only when needed
   - Eager approach: transfer on every enqueue

2) Stack Using Two Queues
   - Using two queues to simulate stack behavior

===========================================================
*/

public class QueueUsingTwoStacks {

    // =====================================================
    // 1️⃣ QUEUE USING TWO STACKS - LAZY APPROACH
    // Transfer from stack1 to stack2 only when stack2 is empty
    // Amortized O(1) dequeue
    // =====================================================
    static class QueueLazy {
        private Stack<Integer> inbox;  // for enqueue
        private Stack<Integer> outbox; // for dequeue

        QueueLazy() {
            inbox = new Stack<>();
            outbox = new Stack<>();
        }

        // ENQUEUE - always push to inbox
        public void enqueue(int val) {
            inbox.push(val);
        }

        // DEQUEUE - transfer inbox to outbox only when outbox empty
        public int dequeue() {
            if(outbox.isEmpty()) {
                while(!inbox.isEmpty()) {
                    outbox.push(inbox.pop());
                }
            }
            if(outbox.isEmpty()) throw new RuntimeException("Queue is empty");
            return outbox.pop();
        }

        // PEEK
        public int peek() {
            if(outbox.isEmpty()) {
                while(!inbox.isEmpty()) {
                    outbox.push(inbox.pop());
                }
            }
            if(outbox.isEmpty()) throw new RuntimeException("Queue is empty");
            return outbox.peek();
        }

        public boolean isEmpty() {
            return inbox.isEmpty() && outbox.isEmpty();
        }

        public int size() {
            return inbox.size() + outbox.size();
        }
    }

    // =====================================================
    // 2️⃣ QUEUE USING TWO STACKS - EAGER APPROACH
    // Transfer on every enqueue (O(n) enqueue, O(1) dequeue)
    // =====================================================
    static class QueueEager {
        private Stack<Integer> stack1; // for enqueue
        private Stack<Integer> stack2; // helper

        QueueEager() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        // ENQUEUE - O(n): move all to stack2, push to stack1, move back
        public void enqueue(int val) {
            while(!stack1.isEmpty()) stack2.push(stack1.pop());
            stack1.push(val);
            while(!stack2.isEmpty()) stack1.push(stack2.pop());
        }

        // DEQUEUE - O(1)
        public int dequeue() {
            if(stack1.isEmpty()) throw new RuntimeException("Queue is empty");
            return stack1.pop();
        }

        // PEEK - O(1)
        public int peek() {
            if(stack1.isEmpty()) throw new RuntimeException("Queue is empty");
            return stack1.peek();
        }

        public boolean isEmpty() { return stack1.isEmpty(); }

        public int size() { return stack1.size(); }
    }

    // =====================================================
    // 3️⃣ STACK USING TWO QUEUES
    // =====================================================
    static class StackUsingQueues {
        private java.util.LinkedList<Integer> queue1;
        private java.util.LinkedList<Integer> queue2;

        StackUsingQueues() {
            queue1 = new java.util.LinkedList<>();
            queue2 = new java.util.LinkedList<>();
        }

        // PUSH - O(n): add to queue2, drain queue1 into queue2, swap
        public void push(int val) {
            queue2.offer(val);
            while(!queue1.isEmpty()) queue2.offer(queue1.poll());
            java.util.LinkedList<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        // POP - O(1)
        public int pop() {
            if(queue1.isEmpty()) throw new RuntimeException("Stack is empty");
            return queue1.poll();
        }

        // PEEK - O(1)
        public int peek() {
            if(queue1.isEmpty()) throw new RuntimeException("Stack is empty");
            return queue1.peek();
        }

        public boolean isEmpty() { return queue1.isEmpty(); }

        public int size() { return queue1.size(); }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Queue Using 2 Stacks (Lazy) ===");
        QueueLazy lazy = new QueueLazy();
        lazy.enqueue(1);
        lazy.enqueue(2);
        lazy.enqueue(3);
        System.out.println("Dequeue: " + lazy.dequeue()); // 1
        lazy.enqueue(4);
        System.out.println("Dequeue: " + lazy.dequeue()); // 2
        System.out.println("Peek:    " + lazy.peek());    // 3
        System.out.println("Size:    " + lazy.size());    // 2

        System.out.println("\n=== Queue Using 2 Stacks (Eager) ===");
        QueueEager eager = new QueueEager();
        eager.enqueue(10);
        eager.enqueue(20);
        eager.enqueue(30);
        System.out.println("Dequeue: " + eager.dequeue()); // 10
        System.out.println("Peek:    " + eager.peek());    // 20
        System.out.println("Size:    " + eager.size());    // 2

        System.out.println("\n=== Stack Using 2 Queues ===");
        StackUsingQueues stack = new StackUsingQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Pop:  " + stack.pop());  // 3
        System.out.println("Peek: " + stack.peek()); // 2
        stack.push(4);
        System.out.println("Pop:  " + stack.pop());  // 4
        System.out.println("Pop:  " + stack.pop());  // 2
        System.out.println("Size: " + stack.size()); // 1
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

Queue Using Two Stacks (Lazy):
  enqueue:  Time O(1),          Space O(1)
  dequeue:  Time O(1) amortized (O(n) worst), Space O(1)
  peek:     Time O(1) amortized, Space O(1)

Queue Using Two Stacks (Eager):
  enqueue:  Time O(n),          Space O(n)
  dequeue:  Time O(1),          Space O(1)
  peek:     Time O(1),          Space O(1)

Stack Using Two Queues:
  push:     Time O(n),          Space O(n)
  pop:      Time O(1),          Space O(1)
  peek:     Time O(1),          Space O(1)

===========================================================
*/
