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

    // insert at front
    public void push(int new_data){
        Node new_node = new Node(new_data);
        new_node.next = head;
        new_node.prev = null;
        // change prev of head node to new node
        if(head != null){
            head.prev = new_node;
        }
        head = new_node;

    }

    // insert after a given node
    public void insertAfter(Node prev_node, int new_data){
        // check if the given prev_node is NULL
        if (prev_node == null){
            System.out.println("The given prev node is null");
            return;
        }
        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;
        new_node.prev = prev_node;
        prev_node.next = new_node;

        // change previous of new node's next node
        if(new_node.next != null){
            new_node.next.prev = new_node;
        }
    }

    // insert at end
    public void append(int new_data){
        Node new_node = new Node(new_data);

        Node last = head;
        new_node.next = null;
        if (head == null){
            new_node.prev = null;
            head = new_node;
            return;
        }

        // else traverse to the last node
        while (last.next != null){
            last = last.next;
        }

        //change the next of the last node
        last.next = new_node;

        // make last node as previous of new node
        new_node.prev = last;
    }

    // insert before a given node
    void insertBefore(Node next_node, int new_data){
        if(next_node == null){
            System.out.println("the given next node cannot be null");
            return;
        }
        // allocate
        Node new_node =new Node(new_data);
        new_node.prev = next_node.prev;
        next_node.prev = new_node;
        new_node.next = next_node;
        // change next of new_node's previous node
        if(new_node.prev != null){
            new_node.prev.next = new_node;
        }
        // if the prev of the new node is NULL, it will be the new head node
        else {
            head = new_node;
        }

    }

    // Delete head node
    void deleteNode(Node del){
        // base case
        if(head == null || del == null){
            return;
        }

        // if node to be deleted is head node
         if(head == del){
             head = del.next;
         }

         // change next only if node to be deleted dis NOT the last node
        if(del.next != null){
            del.next.prev = del.prev;
        }

        // change prev only if node to be deleted is NOT the first node
        if (del.prev != null){
            del.prev.next = del.next;
        }

        // finally, free the memory occupied by del
        return;
    }

    // Reverse the Doubly Linked List
    void reverse(){
        Node temp = null;
        Node current = head;
        // swap next and prev of all nodes of the linked list
        while (current != null){
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        // before changing head, check for the cases like empty list and list with only one node
        if(temp != null){
            head = temp.prev;
        }
    }





    // display
    public void printList(){
        Node curr = head;
        System.out.print("List is : ");
        while (curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

}

public class DoublyLinkedList {
    public static void main(String args[]){
        CustomDoublyLinkedList llist = new CustomDoublyLinkedList();
        llist.push(1);
        llist.push(2);
        llist.push(3);
        llist.insertAfter(llist.head.next,10 );
        llist.append(20);
        llist.insertBefore(llist.head.next.next.next, 15);
        llist.deleteNode(llist.head.next); // deleted 2
        llist.printList();
        llist.reverse();
        llist.printList();

    }
}
