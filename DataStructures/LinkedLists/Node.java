package DataStructures.LinkedLists;

public class Node {
    int val;
    Node next;
    Node prev; // used by DoublyLinkedList

    Node(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
}
