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

     // deletion through key
     /* given a key, deletes the first occurrence of ket in linkedList */
     void deleteNode_key(int key){
         // store head node
         Node temp = head,prev = null;

         // if head node itself hold the key to be deleted
         if(temp != null && temp.data == key){
             head = temp.next;
             return;
         }

         /* search for the key to be deleted, keep track of the previous node as we eed to change temp.next*/
         while (temp != null && temp.data != key){
             prev = temp;
             temp = temp.next;
         }

         // if key was not present in Linked List
         if(temp == null){
             return;
         }

         // unlink the node from the linked list
         prev.next = temp.next;

     }

     // deletion through position
     void deleteNode_pos(int position){
         // if the linkedList is empty
         if(head == null)
             return;

         // store head node
         Node temp = head;

         // if head needs to be removed
         if(position == 0){
             head = temp.next;
             return;
         }

         // Find previous node of the node to be deleted
         for (int i=0; temp!=null && i<position-1; i++){
             temp = temp.next;
         }

         // if position is more than the number of nodes
         if(temp == null || temp.next == null){
             return;
         }

         // Node temp -> next is the node to be deleted
         // Store pointer to the next of node to be deleted
         Node next = temp.next.next;
         temp.next = next; // unlink the deleted node from the list
     }

     // delete a LinkedList
     /* Function deletes the Entire Linked List*/
     void deleteList(){
         head = null;
     }

     // find length of the LinkedList
     /* Returns count of nodes in Linked List
     *  using Iteration  */
     public int getCount_itr(){
         Node temp = head;
         int count = 0;
         while(temp != null){
             count++;
             temp = temp.next;
         }
         return count;
     }

     /* Count using Recursion*/
     public int getCountRec(Node node){
         // Base case
         if(node == null){
             return 0;
         }
         // Count if this node plus rest of the list
         return 1 + getCountRec(node.next);
     }
     // Wrapper over getCountRec()
     public int getCount_rec(){
         return getCountRec(head);
     }

     // Search an Element in Linked List
     public boolean search_itr(Node head, int x){
         Node current = head;
         while(current != null){
             if (current.data == x){
                 return true;
             }
             current = current.next;
         }
         return false;
     }
     public boolean search_rec(Node head, int x){
         if(head == null){
             return false;
         }

         if(head.data == x){
             return true;
         }

         // recursion for remaining list
         return search_rec(head.next,x);
     }




}

public class SinglyLinkedList {

    public static void main(String[] args){
        CustomLinkedList llist = new CustomLinkedList();
        // insertion
        llist.push(1);
        llist.append(2);
        llist.insertAfter(llist.head.next,3);
        llist.append(4);
        llist.insertAfter(llist.head.next.next.next,5);
        //deletion
        llist.deleteNode_key(2);
        llist.deleteNode_pos(1);
        //display
        System.out.print("Custom linkedList data : ");
        llist.printList();
        System.out.println();

        System.out.println("LinkedList Size : " + llist.getCount_itr());


        // From Collections
        System.out.println();
        LinkedList l = new LinkedList();
        l.add(2);
        l.add(3);
        l.add(0,1);
        l.addFirst(0);
        l.addLast(10);
        l.remove(3); // removal through index and not through value
        System.out.print("Collections LinkedList : ");
        for (int i=0;i<l.size();i++){
            System.out.print(l.get(i) + " ");
        }
    }


}
