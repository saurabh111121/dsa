package DataStructures.Stacks;

/*
===========================================================
Stack Implementation Using Linked List
===========================================================

Operations:
1) push(val)  - Add to top (head of list)
2) pop()      - Remove and return top
3) peek()     - Return top without removing
4) isEmpty()  - Check if empty
5) size()     - Return number of elements
6) search()   - Find from top

Advantage over array-based: No size limit, O(1) push/pop always

===========================================================
*/

public class StackUsingLinkedList {

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
    // Stack Implementation
    // =========================
    static class LinkedListStack {
        private Node top;
        private int size;

        LinkedListStack() {
            this.top = null;
            this.size = 0;
        }

        // =====================================================
        // 1️⃣ PUSH - O(1)
        // =====================================================
        public void push(int val) {
            Node newNode = new Node(val);
            newNode.next = top;
            top = newNode;
            size++;
        }

        // =====================================================
        // 2️⃣ POP - O(1)
        // =====================================================
        public int pop() {
            if(isEmpty()) throw new RuntimeException("Stack underflow");
            int val = top.val;
            top = top.next;
            size--;
            return val;
        }

        // =====================================================
        // 3️⃣ PEEK - O(1)
        // =====================================================
        public int peek() {
            if(isEmpty()) throw new RuntimeException("Stack is empty");
            return top.val;
        }

        // =====================================================
        // 4️⃣ IS EMPTY
        // =====================================================
        public boolean isEmpty() {
            return top == null;
        }

        // =====================================================
        // 5️⃣ SIZE
        // =====================================================
        public int size() {
            return size;
        }

        // =====================================================
        // 6️⃣ SEARCH (1-indexed from top)
        // =====================================================
        public int search(int val) {
            Node current = top;
            int pos = 1;

            while(current != null) {
                if(current.val == val) return pos;
                current = current.next;
                pos++;
            }

            return -1;
        }

        // =====================================================
        // PRINT
        // =====================================================
        public void print() {
            System.out.print("Stack (top -> bottom): ");
            Node current = top;
            while(current != null) {
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
        LinkedListStack stack = new LinkedListStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.print();

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        stack.print();

        System.out.println("Size: " + stack.size());
        System.out.println("isEmpty: " + stack.isEmpty());
        System.out.println("Search 20: position " + stack.search(20));
        System.out.println("Search 99: position " + stack.search(99));

        // Pop all
        while(!stack.isEmpty()) {
            System.out.print("Popped: " + stack.pop() + "  ");
        }
        System.out.println("\nStack empty: " + stack.isEmpty());
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

push():    Time O(1),  Space O(1)
pop():     Time O(1),  Space O(1)
peek():    Time O(1),  Space O(1)
isEmpty(): Time O(1),  Space O(1)
size():    Time O(1),  Space O(1)
search():  Time O(n),  Space O(1)

Overall Space: O(n) for n elements
Extra overhead: pointer per node (vs array-based stack)

===========================================================
*/
