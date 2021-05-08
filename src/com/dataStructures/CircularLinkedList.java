package com.dataStructures;

import org.w3c.dom.Node;

class CustomCircularLinkedList{
    Node head;
    class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
        }
    }


    void printList(){
        Node curr = head;
        if (head != null){
            do {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }while (curr != head);
        }
    }



}

public class CircularLinkedList {

    public static void main(String args[]){
        CustomCircularLinkedList llist = new CustomCircularLinkedList();


    }

}
