package DataStructures.LinkedLists;

/*
===========================================================
Doubly Linked List Operations
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
   - Forward Traversal
   - Backward Traversal

4) Reversal
   - Iterative Reversal

5) Search
   - Search by Value

===========================================================
*/

public class DoublyLinkedList {

    // =========================
    // Node Definition
    // =========================
    static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    // =====================================================
    // 1️⃣ INSERTION - AT HEAD
    // =====================================================
    public static Node insertAtHead(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) return newNode;

        newNode.next = head;
        head.prev = newNode;
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
        newNode.prev = current;
        return head;
    }

    // =====================================================
    // 3️⃣ INSERTION - AT INDEX
    // =====================================================
    public static Node insertAtIndex(Node head, int index, int val) {
        if (index == 0) return insertAtHead(head, val);

        Node current = head;
        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) return head;

        Node newNode = new Node(val);
        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) current.next.prev = newNode;
        current.next = newNode;
        return head;
    }

    // =====================================================
    // 4️⃣ DELETION - AT HEAD
    // =====================================================
    public static Node deleteAtHead(Node head) {
        if (head == null) return null;
        if (head.next == null) return null;

        head = head.next;
        head.prev = null;
        return head;
    }

    // =====================================================
    // 5️⃣ DELETION - AT TAIL
    // =====================================================
    public static Node deleteAtTail(Node head) {
        if (head == null || head.next == null) return null;

        Node current = head;
        while (current.next != null) current = current.next;

        current.prev.next = null;
        return head;
    }

    // =====================================================
    // 6️⃣ DELETION - BY VALUE
    // =====================================================
    public static Node deleteByValue(Node head, int val) {
        if (head == null) return null;

        Node current = head;
        while (current != null && current.val != val) current = current.next;

        if (current == null) return head; // not found

        if (current.prev != null) current.prev.next = current.next;
        else head = current.next; // deleting head

        if (current.next != null) current.next.prev = current.prev;

        return head;
    }

    // =====================================================
    // 7️⃣ SEARCH BY VALUE
    // =====================================================
    public static int search(Node head, int val) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.val == val) return index;
            current = current.next;
            index++;
        }

        return -1;
    }

    // =====================================================
    // 8️⃣ REVERSAL - ITERATIVE
    // =====================================================
    public static Node reverse(Node head) {
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; // move forward (which is old next)
        }

        if (temp != null) head = temp.prev;
        return head;
    }

    // =====================================================
    // 9️⃣ TRAVERSAL - FORWARD
    // =====================================================
    public static void printForward(Node head) {
        Node current = head;
        System.out.print("Forward: ");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" <-> ");
            current = current.next;
        }
        System.out.println();
    }

    // =====================================================
    // 🔟 TRAVERSAL - BACKWARD
    // =====================================================
    public static void printBackward(Node head) {
        if (head == null) return;

        Node current = head;
        while (current.next != null) current = current.next;

        System.out.print("Backward: ");
        while (current != null) {
            System.out.print(current.val);
            if (current.prev != null) System.out.print(" <-> ");
            current = current.prev;
        }
        System.out.println();
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
        printForward(head);
        printBackward(head);

        head = insertAtHead(head, 0);
        System.out.print("After insertAtHead(0): ");
        printForward(head);

        head = insertAtIndex(head, 2, 99);
        System.out.print("After insertAtIndex(2, 99): ");
        printForward(head);

        head = deleteAtHead(head);
        System.out.print("After deleteAtHead: ");
        printForward(head);

        head = deleteAtTail(head);
        System.out.print("After deleteAtTail: ");
        printForward(head);

        head = deleteByValue(head, 99);
        System.out.print("After deleteByValue(99): ");
        printForward(head);

        System.out.println("Search 3: index " + search(head, 3));
        System.out.println("Search 9: index " + search(head, 9));

        head = reverse(head);
        System.out.print("After reversal: ");
        printForward(head);
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
7) Search:            Time O(n),  Space O(1)
8) Reverse:           Time O(n),  Space O(1)
9) Print Forward:     Time O(n),  Space O(1)
10) Print Backward:   Time O(n),  Space O(1)

Advantage over Singly LL:
  - O(1) deletion given a node reference (no need to find prev)
  - Bidirectional traversal

===========================================================
*/
