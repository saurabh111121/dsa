package com.dataStructures;


import java.util.Stack;

class StackArray{
    int[] Stack = new int[5];
    int top = -1;

    void push(int data){
        if(top > Stack.length) {
            System.out.println("Stack Overflow . " );
            return;
        }
        top++;
        Stack[top] = data;
    }

    void printStack(){
        for(int i=0; i<= top; i++){
            System.out.print(Stack[i] + " ");
        }
        System.out.println();
    }

    void peek() {
        System.out.println("Peek - " + Stack[top]);
    }

    void pop(){
        if(top == 0) {
            System.out.println("No elements in Stack");
            return;
        }
        top --;
    }
}

class StackLinkedList{
    class Node {
        int data;
        Node next;
    }

    Node top = null;

    void push(int data) {
        Node temp = new Node();
        if(temp == null ) {
            System.out.println(" Stack Overflow. ");
            return;
        }
        temp.data = data;
        temp.next = top;
        top = temp;
    }

    void printStack() {
        if(top == null) {
            System.out.println("Stack underflow");
        }
        Node temp = top;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

public class Stacks {
    public static void main(String[] args) {
        StackArray sa = new StackArray();
        sa.push(1);
        sa.push(2);
        sa.push(3);
        sa.push(4);



        StackLinkedList stackLinkedList = new StackLinkedList();
        stackLinkedList.push(1);
        stackLinkedList.push(2);
        stackLinkedList.push(3);
        stackLinkedList.push(4);
        stackLinkedList.push(5);
        stackLinkedList.push(6);
        stackLinkedList.printStack();

    }
}
