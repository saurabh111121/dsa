package DataStructures.LinkedLists;

/*
===========================================================
Circular Linked List Operations
===========================================================

1) Insertion
   - Insert at Head
   - Insert at Tail
   - Insert at Index

2) Deletion
   - Delete at Head
   - Delete at Tail
   - Delete by Value

3) Traversal
   - Print all nodes (stop when cycle detected)

4) Search
   - Search by Value

5) Detection
   - Detect cycle (Floyd's Algorithm)
   - Find cycle start node

===========================================================
*/

public class CircularLinkedList {

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

    // =====================================================
    // 1️⃣ INSERTION - AT HEAD
    // =====================================================
    public static Node insertAtHead(Node head, int val) {
        Node newNode = new Node(val);
        if(head == null) {
            newNode.next = newNode; // points to itself
            return newNode;
        }

        // Find tail
        Node tail = head;
        while(tail.next != head) tail = tail.next;

        newNode.next = head;
        tail.next = newNode;
        return newNode;
    }

    // =====================================================
    // 2️⃣ INSERTION - AT TAIL
    // =====================================================
    public static Node insertAtTail(Node head, int val) {
        Node newNode = new Node(val);
        if(head == null) {
            newNode.next = newNode;
            return newNode;
        }

        Node tail = head;
        while(tail.next != head) tail = tail.next;

        tail.next = newNode;
        newNode.next = head;
        return head;
    }

    // =====================================================
    // 3️⃣ INSERTION - AT INDEX
    // =====================================================
    public static Node insertAtIndex(Node head, int index, int val) {
        if(index == 0) return insertAtHead(head, val);

        Node current = head;
        for(int i = 0; i < index - 1; i++) {
            current = current.next;
            if(current == head) return head; // index out of bounds
        }

        Node newNode = new Node(val);
        newNode.next = current.next;
        current.next = newNode;
        return head;
    }

    // =====================================================
    // 4️⃣ DELETION - AT HEAD
    // =====================================================
    public static Node deleteAtHead(Node head) {
        if(head == null) return null;
        if(head.next == head) return null; // only one node

        Node tail = head;
        while(tail.next != head) tail = tail.next;

        tail.next = head.next;
        return head.next;
    }

    // =====================================================
    // 5️⃣ DELETION - AT TAIL
    // =====================================================
    public static Node deleteAtTail(Node head) {
        if(head == null) return null;
        if(head.next == head) return null; // only one node

        Node current = head;
        while(current.next.next != head) current = current.next;

        current.next = head;
        return head;
    }

    // =====================================================
    // 6️⃣ DELETION - BY VALUE
    // =====================================================
    public static Node deleteByValue(Node head, int val) {
        if(head == null) return null;

        // If head needs to be deleted
        if(head.val == val) return deleteAtHead(head);

        Node current = head;
        while(current.next != head && current.next.val != val) {
            current = current.next;
        }

        if(current.next != head) {
            current.next = current.next.next;
        }

        return head;
    }

    // =====================================================
    // 7️⃣ SEARCH BY VALUE
    // =====================================================
    public static int search(Node head, int val) {
        if(head == null) return -1;

        Node current = head;
        int index = 0;

        do {
            if(current.val == val) return index;
            current = current.next;
            index++;
        } while(current != head);

        return -1;
    }

    // =====================================================
    // 8️⃣ DETECT CYCLE - FLOYD'S ALGORITHM
    // =====================================================
    public static boolean detectCycle(Node head) {
        if(head == null) return false;

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }

    // =====================================================
    // 9️⃣ FIND CYCLE START NODE
    // =====================================================
    public static Node findCycleStart(Node head) {
        if(head == null) return null;

        Node slow = head;
        Node fast = head;
        boolean hasCycle = false;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if(!hasCycle) return null;

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // =====================================================
    // TRAVERSAL - PRINT ALL NODES
    // =====================================================
    public static void printList(Node head) {
        if(head == null) {
            System.out.println("Empty list");
            return;
        }

        Node current = head;
        System.out.print("Circular: ");
        do {
            System.out.print(current.val);
            current = current.next;
            if(current != head) System.out.print(" -> ");
        } while(current != head);
        System.out.println(" -> (back to head: " + head.val + ")");
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL OPERATIONS)
    // =====================================================
    public static void main(String[] args) {
        Node head = null;

        head = insertAtTail(head, 1);
        head = insertAtTail(head, 2);
        head = insertAtTail(head, 3);
        head = insertAtTail(head, 4);
        printList(head);

        head = insertAtHead(head, 0);
        System.out.print("After insertAtHead(0): ");
        printList(head);

        head = insertAtIndex(head, 2, 99);
        System.out.print("After insertAtIndex(2, 99): ");
        printList(head);

        head = deleteAtHead(head);
        System.out.print("After deleteAtHead: ");
        printList(head);

        head = deleteAtTail(head);
        System.out.print("After deleteAtTail: ");
        printList(head);

        head = deleteByValue(head, 99);
        System.out.print("After deleteByValue(99): ");
        printList(head);

        System.out.println("Search 3: index " + search(head, 3));
        System.out.println("Search 9: index " + search(head, 9));

        // Test Floyd's cycle detection
        // Create a cycle: 1 -> 2 -> 3 -> 4 -> (back to 2)
        Node cycleHead = new Node(1);
        cycleHead.next = new Node(2);
        cycleHead.next.next = new Node(3);
        cycleHead.next.next.next = new Node(4);
        cycleHead.next.next.next.next = cycleHead.next; // cycle at node 2

        System.out.println("\nFloyd's Cycle Detection:");
        System.out.println("Has cycle: " + detectCycle(cycleHead));
        Node start = findCycleStart(cycleHead);
        System.out.println("Cycle starts at node: " + (start != null ? start.val : "null"));

        Node noCycle = new Node(1);
        noCycle.next = new Node(2);
        noCycle.next.next = new Node(3);
        System.out.println("No cycle list has cycle: " + detectCycle(noCycle));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Insert at Head:      Time O(n),  Space O(1)  (need to find tail)
2) Insert at Tail:      Time O(n),  Space O(1)
3) Insert at Index:     Time O(n),  Space O(1)
4) Delete at Head:      Time O(n),  Space O(1)  (need to find tail)
5) Delete at Tail:      Time O(n),  Space O(1)
6) Delete by Value:     Time O(n),  Space O(1)
7) Search:              Time O(n),  Space O(1)
8) Detect Cycle:        Time O(n),  Space O(1)  (Floyd's)
9) Find Cycle Start:    Time O(n),  Space O(1)

Note: For circular LL, keeping a tail pointer reduces Insert at Head/Tail to O(1)

===========================================================
*/
