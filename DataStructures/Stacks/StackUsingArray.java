package DataStructures.Stacks;

/*
===========================================================
Stack Implementation Using Array
===========================================================

Operations:
1) push(val)    - Add element to top
2) pop()        - Remove and return top element
3) peek()       - Return top element without removing
4) isEmpty()    - Check if stack is empty
5) isFull()     - Check if stack is full
6) size()       - Return number of elements
7) search(val)  - Find position from top (1-indexed)

Both fixed-size and dynamic resizing implementations.

===========================================================
*/

public class StackUsingArray {

    // =========================
    // FIXED SIZE STACK
    // =========================
    static class FixedStack {
        private int[] data;
        private int top;
        private int capacity;

        FixedStack(int capacity) {
            this.capacity = capacity;
            this.data = new int[capacity];
            this.top = -1;
        }

        // =====================================================
        // 1️⃣ PUSH
        // =====================================================
        public void push(int val) {
            if (isFull()) throw new RuntimeException("Stack overflow");
            data[++top] = val;
        }

        // =====================================================
        // 2️⃣ POP
        // =====================================================
        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack underflow");
            return data[top--];
        }

        // =====================================================
        // 3️⃣ PEEK
        // =====================================================
        public int peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return data[top];
        }

        // =====================================================
        // 4️⃣ IS EMPTY
        // =====================================================
        public boolean isEmpty() {
            return top == -1;
        }

        // =====================================================
        // 5️⃣ IS FULL
        // =====================================================
        public boolean isFull() {
            return top == capacity - 1;
        }

        // =====================================================
        // 6️⃣ SIZE
        // =====================================================
        public int size() {
            return top + 1;
        }

        // =====================================================
        // 7️⃣ SEARCH (returns 1-indexed position from top)
        // =====================================================
        public int search(int val) {
            for (int i = top; i >= 0; i--) {
                if (data[i] == val) return top - i + 1;
            }
            return -1;
        }

        public void print() {
            System.out.print("Stack (top -> bottom): ");
            for (int i = top; i >= 0; i--) {
                System.out.print(data[i] + (i > 0 ? " " : ""));
            }
            System.out.println();
        }
    }

    // =========================
    // DYNAMIC RESIZING STACK
    // =========================
    static class DynamicStack {
        private int[] data;
        private int top;
        private int capacity;

        DynamicStack() {
            this.capacity = 4;
            this.data = new int[capacity];
            this.top = -1;
        }

        // =====================================================
        // RESIZE - doubles capacity
        // =====================================================
        private void resize() {
            capacity *= 2;
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, top + 1);
            data = newData;
        }

        public void push(int val) {
            if (top == capacity - 1) resize();
            data[++top] = val;
        }

        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack underflow");
            return data[top--];
        }

        public int peek() {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return data[top];
        }

        public boolean isEmpty() { return top == -1; }

        public int size() { return top + 1; }

        public void print() {
            System.out.print("DynamicStack (top -> bottom): ");
            for (int i = top; i >= 0; i--) {
                System.out.print(data[i] + (i > 0 ? " " : ""));
            }
            System.out.println();
        }
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL OPERATIONS)
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Fixed Stack ===");
        FixedStack stack = new FixedStack(10);

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
        System.out.println("isFull: " + stack.isFull());
        System.out.println("Search 20: position " + stack.search(20));
        System.out.println("Search 99: position " + stack.search(99));

        System.out.println("\n=== Dynamic Stack ===");
        DynamicStack dStack = new DynamicStack();
        for (int i = 1; i <= 10; i++) dStack.push(i * 10);
        dStack.print();

        System.out.println("Pop: " + dStack.pop());
        System.out.println("Peek: " + dStack.peek());
        System.out.println("Size: " + dStack.size());
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

push():    Time O(1) amortized (O(n) when resize), Space O(1)
pop():     Time O(1),  Space O(1)
peek():    Time O(1),  Space O(1)
isEmpty(): Time O(1),  Space O(1)
isFull():  Time O(1),  Space O(1)
size():    Time O(1),  Space O(1)
search():  Time O(n),  Space O(1)

Overall Space: O(n) for storing n elements

===========================================================
*/
