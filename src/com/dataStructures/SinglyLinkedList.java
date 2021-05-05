package com.dataStructures;

import org.w3c.dom.Node;

import java.util.LinkedList;

 class CustomLinkedList{
     Node head;
     class Node{
         int data;
         Node next;
         Node(int d){
             data = d;
             next = null;
         }
     }

     // insert at front
     public void push(int new_data){
         Node new_node = new Node(new_data);
         new_node.next = head;
         head = new_node;
     }

     // insert after a given node
     public void insertAfter(Node prev_node,int new_data){
         if(prev_node == null){
             System.out.println("THe given previous node cannot be null.");
             return;
         }
         Node new_node = new Node(new_data);
         new_node.next = prev_node.next;
         prev_node.next = new_node;
     }

     // insert at end
     public void append(int new_data){
         Node new_node = new Node(new_data);
         // if linked list is empty
         if(head == null){
             head = new Node(new_data);
             return;
         }
         new_node.next = null;

         Node last = head;
         while(last.next != null){
             last = last.next;
         }
         last.next = new_node;
         return;
     }

     public void printList(){
         Node tnode = head;
         while(tnode != null){
             System.out.print(tnode.data + " ");
             tnode = tnode.next;
         }
     }

}

public class SinglyLinkedList {

    public static void main(String[] args){
        CustomLinkedList llist = new CustomLinkedList();
        llist.push(5);
        llist.append(3);
        llist.append(8);
        llist.printList();
    }

}
