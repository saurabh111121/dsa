package com.dataStructures;

import java.util.Stack;

class QueueArray{
    int[] queue = new int[5];
    int front;
    int rear;
    int size;
    void enQueue(int data) {
        queue[rear] = data;
        rear = (rear+1) % 5; // mod of 5 for circular array
        size = size + 1 ;
    }

    int dequeue() {
        int data = queue[front];
        front = (front+1) % 5;
        size = size - 1;
        return data;
    }

    void show(){
        System.out.print("Elements : ");
        for (int i=0;i<size;i++){
            System.out.print(queue[front + i] + " ");
        }
        System.out.println();

        for( int n : queue) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    int getSize(){
        return size;
    }

    boolean isEmpty(){
        return size == 0;
    }

    boolean isFull() {
        return size == 5;
    }

}

public class Queue {
    public static void main(String[] args) {
        QueueArray queueArray = new QueueArray();


        System.out.println(5%5);

        queueArray.enQueue(1);
        queueArray.enQueue(2);
        queueArray.enQueue(3);
        queueArray.enQueue(4);

        queueArray.dequeue();
        queueArray.dequeue();

        queueArray.show();

        queueArray.enQueue(5);
        queueArray.enQueue(6);


        queueArray.show();

        System.out.println("Size " + queueArray.getSize());
        System.out.println("isEmpty " + queueArray.isEmpty());
        System.out.println("isFull " + queueArray.isEmpty());



    }
}
