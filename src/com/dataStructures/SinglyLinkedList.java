package com.dataStructures;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.HashSet;
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

     // Get Nth Node in a Linked List from  beginning
     public int getNth(int index){
         Node current = head;
         int count = 0; // index of node we are currently at

         while(current != null){
             if(count == index){
                 return current.data;
             }
             count++;
             current = current.next;
         }

         // if we get to this line, the caller was asking for
         // a non-existent element so we assert to fail
         assert (false);

         return  0;
     }

     // Get Nth Node in a Linked List from last
     void getNth_Last(int n){
         int len = 0;
         Node temp = head;

         // count no. of nodes in the Linked List
         while(temp != null){
             temp = temp.next;
             len++;
         }

         // check if the value of n is not more than the length of the linked list
         if (len < n){
             return;
         }
         temp = head;

         // get the (len - n+1)th node from the beginning
         for(int i=1;i<len - n + 1;i++){
             temp = temp.next;
         }
         System.out.println(temp.data);
     }


     // middle of the linked list
     // for better solution :  https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
     void printMiddle(){
         Node slow_ptr = head;
         Node fast_ptr = head;
         if(head != null){
             while(fast_ptr != null && fast_ptr.next != null){
                 slow_ptr = slow_ptr.next;
                 fast_ptr = fast_ptr.next.next;
             }
         }

         System.out.println("The Middle Element is : " + slow_ptr.data);
     }

     // count number of times an int is occurred
     void count(int x){
         Node temp = head;
         int count = 0;
         while(temp != null){
             if(temp.data == x){
                 count++;
             }
             temp = temp.next;
         }
         System.out.println("Number of times " + x + " has occurred is : " + count);
     }

     // Detect Loop in a Linked List
     // for more methods : https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
     /* using Hash Map, time & space -> O(n)*/
     static boolean detectLoop_hm(Node h){
         HashSet<Node> s = new HashSet<Node>();
         while (h != null){
             // if node is already present
             if(s.contains(h)){
                 return true;
             }
             // if visiting for the first time
             s.add(h);
             h = h.next;
         }
         return false;
     }

     /* Fastest Method : Floyd's Cycle- Finding Algorithm
     *  Time : O(n) , Space : O(1) */
     void detectLoop_fc(){
         Node slow_p = head, fast_p = head;
         int flag = 0;
         while (slow_p != null && fast_p != null & fast_p.next != null){
             slow_p = slow_p.next;
             fast_p = fast_p.next.next;
             if(slow_p == fast_p){
                 flag = 1;
                 break;
             }
         }
         if(flag == 1){
             System.out.println("Loop Found in the Linked List");
         }else{
             System.out.println("No loop in the Linked List");
         }
     }

     // Find Length of the Loop in the Linked List
     int detectLoop_length(){
         Node slow_p = head, fast_p = head;
         int flag = 0;
         while( slow_p != null && fast_p != null && fast_p.next != null){
             slow_p = slow_p.next;
             fast_p = fast_p.next.next;
             if(slow_p == fast_p){
                     return countNodes_forLoop(slow_p);
             }
         }
         return 0;
     }

     int countNodes_forLoop(Node n){
         int count = 1;
         Node temp = n;
         while( temp.next != n ){
             count++;
             temp = temp.next;
         }
         return count;
     }

     // Find Node where loop is starting
     int detectLoop_beginning(){
         Node slow_p = head;
         Node fast_p = head;
         while(slow_p != null && fast_p != null && fast_p.next != null){
             slow_p = slow_p.next;
             fast_p = fast_p.next.next;
             if(slow_p == fast_p){
                 slow_p = head;
                 while(slow_p != null && fast_p != null && fast_p.next != null){
                     slow_p = slow_p.next;
                     fast_p = fast_p.next;
                     if (slow_p == fast_p){
                         return slow_p.data;
                     }
                 }
             }
         }
         return 0;
     }

     // detect and remove the loop
     void detectLoop_andRemove(){
         Node slow_p = head;
         Node fast_p = head;
         while (slow_p != null && fast_p != null && fast_p.next != null){
             slow_p = slow_p.next;
             fast_p = fast_p.next.next;
             if(slow_p == fast_p ){
                 slow_p = head;
                 while (slow_p != null && fast_p != null && fast_p !=null){
                     Node temp = fast_p;
                     slow_p = slow_p.next;
                     fast_p = fast_p.next;
                     if (slow_p == fast_p){
                         System.out.println("Loop present and removed it.");
                         temp.next = null;
                         break;
                     }
                 }
             }
         }
     }


 }

public class SinglyLinkedList {

    public static void main(String[] args){
        CustomLinkedList llist = new CustomLinkedList();
        // insertion
        llist.push(1);
        llist.push(1);
        llist.append(2);
        llist.insertAfter(llist.head.next,3);
        llist.append(4);
        llist.insertAfter(llist.head.next.next.next,5);
        llist.append(1);
        //deletion
        llist.deleteNode_key(2);
        llist.deleteNode_pos(1);
        //display
        System.out.print("Custom linkedList data : ");
        llist.printList();
        System.out.println();
        System.out.println("Value at this index is : " + llist.getNth(1));
        System.out.println("LinkedList Size : " + llist.getCount_itr());
        llist.printMiddle();
        llist.count(1);
        /*
          1 ---> 2 ---> 3
                 |      |
                 5 <--- 4
         */
        llist.head.next.next.next.next.next = llist.head.next;
        if(llist.detectLoop_hm(llist.head)){
            System.out.println("Loop Found in the Linked List");
        }else{
            System.out.println("No loop in the Linked List");
        }
        int loop_length = llist.detectLoop_length();
        System.out.println("Loop length is : " + loop_length);
        int loop_start = llist.detectLoop_beginning();
        System.out.println("Loop is Starting at : " + loop_start);
        llist.detectLoop_andRemove();
        System.out.print("List without loop is : ");
        llist.printList();


        // From Collections
        System.out.println("\n\n");
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

