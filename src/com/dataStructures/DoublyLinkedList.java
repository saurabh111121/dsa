package com.dataStructures;

import org.w3c.dom.Node;

class CustomDoublyLinkedList{
    Node head;
    class Node{
        int data;
        Node prev;
        Node next;
        // Constructor to create a new node
        // next and prev is by default initialized as null
        Node(int d){
            data = d;
        }

    }

}

public class DoublyLinkedList {
    CustomDoublyLinkedList llist = new CustomDoublyLinkedList();
}
