package DataStructures.LinkedLists;

/*
===========================================================
Singly Linked List Operations
===========================================================

1) Insertion
   - Insert at Head
   - Insert at Tail
   - Insert at Index

2) Deletion
   - Delete at Head
   - Delete at Tail
   - Delete by Value
   - Delete at Index

3) Search
   - Search by Value (Iterative)
   - Search by Value (Recursive)
   - Find Middle Node
   - Find Nth from End

4) Traversal
   - Forward Traversal
   - Recursive Traversal

5) Reversal
   - Iterative Reversal
   - Recursive Reversal

===========================================================
*/

public class SinglyLinkedList {

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

    static Node head;

    // =====================================================
    // 1️⃣ INSERTION - AT HEAD
    // =====================================================
    public static Node insertAtHead(Node head, int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        return newNode;
    }

    // =====================================================
    // 2️⃣ INSERTION - AT TAIL
    // =====================================================
    public static Node insertAtTail(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) return newNode;

        Node current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
        return head;
    }

    // =====================================================
    // 3️⃣ INSERTION - AT INDEX
    // =====================================================
    public static Node insertAtIndex(Node head, int index, int val) {
        if (index == 0) return insertAtHead(head, val);

        Node newNode = new Node(val);
        Node current = head;

        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) return head; // index out of bounds
        newNode.next = current.next;
        current.next = newNode;
        return head;
    }

    // =====================================================
    // 4️⃣ DELETION - AT HEAD
    // =====================================================
    public static Node deleteAtHead(Node head) {
        if (head == null) return null;
        return head.next;
    }

    // =====================================================
    // 5️⃣ DELETION - AT TAIL
    // =====================================================
    public static Node deleteAtTail(Node head) {
        if (head == null || head.next == null) return null;

        Node current = head;
        while (current.next.next != null) current = current.next;
        current.next = null;
        return head;
    }

    // =====================================================
    // 6️⃣ DELETION - BY VALUE
    // =====================================================
    public static Node deleteByValue(Node head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;

        Node current = head;
        while (current.next != null && current.next.val != val) {
            current = current.next;
        }

        if (current.next != null) current.next = current.next.next;
        return head;
    }

    // =====================================================
    // 7️⃣ DELETION - AT INDEX
    // =====================================================
    public static Node deleteAtIndex(Node head, int index) {
        if (head == null) return null;
        if (index == 0) return head.next;

        Node current = head;
        for (int i = 0; i < index - 1 && current.next != null; i++) {
            current = current.next;
        }

        if (current.next != null) current.next = current.next.next;
        return head;
    }

    // =====================================================
    // 8️⃣ SEARCH - ITERATIVE
    // =====================================================
    public static int searchIterative(Node head, int val) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.val == val) return index;
            current = current.next;
            index++;
        }

        return -1; // Not found
    }

    // =====================================================
    // 9️⃣ SEARCH - RECURSIVE
    // =====================================================
    public static int searchRecursive(Node current, int val, int index) {
        if (current == null) return -1;
        if (current.val == val) return index;
        return searchRecursive(current.next, val, index + 1);
    }

    // =====================================================
    // 🔟 FIND MIDDLE NODE (Slow-Fast Pointer)
    // =====================================================
    public static Node findMiddle(Node head) {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // =====================================================
    // 1️⃣1️⃣ FIND NTH FROM END
    // =====================================================
    public static Node findNthFromEnd(Node head, int n) {
        Node first = head;
        Node second = head;

        for (int i = 0; i < n; i++) {
            if (first == null) return null; // n > length
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }

    // =====================================================
    // 1️⃣2️⃣ TRAVERSAL - ITERATIVE
    // =====================================================
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    // =====================================================
    // 1️⃣3️⃣ TRAVERSAL - RECURSIVE
    // =====================================================
    public static void printListRecursive(Node current) {
        if (current == null) {
            System.out.println();
            return;
        }
        System.out.print(current.val + (current.next != null ? " -> " : ""));
        printListRecursive(current.next);
    }

    // =====================================================
    // 1️⃣4️⃣ REVERSAL - ITERATIVE
    // =====================================================
    public static Node reverseIterative(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // =====================================================
    // 1️⃣5️⃣ REVERSAL - RECURSIVE
    // =====================================================
    public static Node reverseRecursive(Node head) {
        if (head == null || head.next == null) return head;

        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // =====================================================
    // GET LENGTH
    // =====================================================
    public static int getLength(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL OPERATIONS)
    // =====================================================
    public static void main(String[] args) {
        Node head = null;

        // Insertions
        head = insertAtTail(head, 1);
        head = insertAtTail(head, 2);
        head = insertAtTail(head, 3);
        head = insertAtTail(head, 4);
        head = insertAtTail(head, 5);
        System.out.print("Initial list: ");
        printList(head);

        head = insertAtHead(head, 0);
        System.out.print("After insertAtHead(0): ");
        printList(head);

        head = insertAtIndex(head, 3, 99);
        System.out.print("After insertAtIndex(3, 99): ");
        printList(head);

        // Deletions
        head = deleteAtHead(head);
        System.out.print("After deleteAtHead: ");
        printList(head);

        head = deleteAtTail(head);
        System.out.print("After deleteAtTail: ");
        printList(head);

        head = deleteByValue(head, 99);
        System.out.print("After deleteByValue(99): ");
        printList(head);

        head = deleteAtIndex(head, 1);
        System.out.print("After deleteAtIndex(1): ");
        printList(head);

        // Search
        System.out.println("Search 3 iterative: index " + searchIterative(head, 3));
        System.out.println("Search 3 recursive: index " + searchRecursive(head, 3, 0));

        // Middle
        Node mid = findMiddle(head);
        System.out.println("Middle node: " + (mid != null ? mid.val : "null"));

        // Nth from End
        Node nth = findNthFromEnd(head, 2);
        System.out.println("2nd from end: " + (nth != null ? nth.val : "null"));

        // Recursive print
        System.out.print("Recursive print: ");
        printListRecursive(head);

        // Reversal
        head = reverseIterative(head);
        System.out.print("After iterative reversal: ");
        printList(head);

        head = reverseRecursive(head);
        System.out.print("After recursive reversal: ");
        printList(head);

        System.out.println("Length: " + getLength(head));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Insert at Head:    Time O(1),  Space O(1)
2) Insert at Tail:    Time O(n),  Space O(1)
3) Insert at Index:   Time O(n),  Space O(1)
4) Delete at Head:    Time O(1),  Space O(1)
5) Delete at Tail:    Time O(n),  Space O(1)
6) Delete by Value:   Time O(n),  Space O(1)
7) Delete at Index:   Time O(n),  Space O(1)
8) Search Iterative:  Time O(n),  Space O(1)
9) Search Recursive:  Time O(n),  Space O(n) (call stack)
10) Find Middle:      Time O(n),  Space O(1)
11) Nth from End:     Time O(n),  Space O(1)
12) Traversal:        Time O(n),  Space O(1)
13) Reverse Iter:     Time O(n),  Space O(1)
14) Reverse Rec:      Time O(n),  Space O(n) (call stack)

===========================================================
*/
