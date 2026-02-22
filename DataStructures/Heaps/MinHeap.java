package DataStructures.Heaps;

/*
===========================================================
Min Heap Implementation
===========================================================

A Min Heap is a complete binary tree where every parent
node is <= its children. Root is always the minimum.

Operations:
1) insert(val)      - Add element and heapify up
2) extractMin()     - Remove and return minimum
3) peekMin()        - Return minimum without removing
4) heapifyUp()      - Restore heap property after insert
5) heapifyDown()    - Restore heap property after extract
6) buildHeap()      - Build heap from array in O(n)
7) heapSort()       - Sort using heap in O(n log n)
8) size()           - Return number of elements
9) contains(val)    - Check if value exists

Array representation:
  parent of i:        (i-1) / 2
  left child of i:    2*i + 1
  right child of i:   2*i + 2

===========================================================
*/

import java.util.Arrays;

public class MinHeap {

    private int[] data;
    private int size;
    private int capacity;

    MinHeap(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    // Build heap from existing array
    MinHeap(int[] arr) {
        this.size = arr.length;
        this.capacity = arr.length;
        this.data = Arrays.copyOf(arr, arr.length);
        buildHeap();
    }

    // =====================================================
    // PARENT / CHILD INDICES
    // =====================================================
    private int parent(int i)    { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i){ return 2 * i + 2; }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // =====================================================
    // 1️⃣ INSERT - O(log n)
    // =====================================================
    public void insert(int val) {
        if(size == capacity) throw new RuntimeException("Heap is full");
        data[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    // =====================================================
    // 2️⃣ HEAPIFY UP - restore after insert
    // =====================================================
    private void heapifyUp(int i) {
        while(i > 0 && data[parent(i)] > data[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // =====================================================
    // 3️⃣ EXTRACT MIN - O(log n)
    // =====================================================
    public int extractMin() {
        if(size == 0) throw new RuntimeException("Heap is empty");

        int min = data[0];
        data[0] = data[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    // =====================================================
    // 4️⃣ HEAPIFY DOWN - restore after extract
    // =====================================================
    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if(left < size && data[left] < data[smallest])   smallest = left;
        if(right < size && data[right] < data[smallest]) smallest = right;

        if(smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // =====================================================
    // 5️⃣ HEAPIFY DOWN - ITERATIVE
    // =====================================================
    private void heapifyDownIterative(int i) {
        while(true) {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if(left < size && data[left] < data[smallest])   smallest = left;
            if(right < size && data[right] < data[smallest]) smallest = right;

            if(smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    // =====================================================
    // 6️⃣ PEEK MIN - O(1)
    // =====================================================
    public int peekMin() {
        if(size == 0) throw new RuntimeException("Heap is empty");
        return data[0];
    }

    // =====================================================
    // 7️⃣ BUILD HEAP FROM ARRAY - O(n)
    // =====================================================
    public void buildHeap() {
        // Start from last non-leaf node and heapify down
        for(int i = (size / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // =====================================================
    // 8️⃣ DELETE AT INDEX
    // =====================================================
    public void deleteAt(int index) {
        if(index >= size) throw new RuntimeException("Index out of bounds");
        data[index] = Integer.MIN_VALUE;
        heapifyUp(index);
        extractMin();
    }

    // =====================================================
    // 9️⃣ DECREASE KEY
    // =====================================================
    public void decreaseKey(int index, int newVal) {
        if(newVal > data[index]) throw new RuntimeException("New value is greater than current");
        data[index] = newVal;
        heapifyUp(index);
    }

    // =====================================================
    // 🔟 HEAP SORT - ascending order using min heap
    // =====================================================
    public static int[] heapSort(int[] arr) {
        int[] result = new int[arr.length];
        MinHeap heap = new MinHeap(arr);
        for(int i = 0; i < result.length; i++) {
            result[i] = heap.extractMin();
        }
        return result;
    }

    // =====================================================
    // CONTAINS
    // =====================================================
    public boolean contains(int val) {
        for(int i = 0; i < size; i++) {
            if(data[i] == val) return true;
        }
        return false;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void print() {
        System.out.print("MinHeap: [");
        for(int i = 0; i < size; i++) {
            System.out.print(data[i] + (i < size - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Min Heap ===");
        MinHeap heap = new MinHeap(20);

        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(20);
        heap.print();

        System.out.println("PeekMin: " + heap.peekMin());
        System.out.println("ExtractMin: " + heap.extractMin());
        heap.print();
        System.out.println("ExtractMin: " + heap.extractMin());
        heap.print();

        System.out.println("Size: " + heap.size());
        System.out.println("Contains 8: " + heap.contains(8));
        System.out.println("Contains 99: " + heap.contains(99));

        // DecreaseKey
        heap.decreaseKey(2, 0);
        System.out.print("After decreaseKey(idx=2, newVal=0): ");
        heap.print();

        // Build heap from array
        System.out.println("\n=== Build Heap from Array ===");
        int[] arr = {9, 3, 7, 1, 5, 8, 2};
        MinHeap builtHeap = new MinHeap(arr);
        System.out.print("Built from [9,3,7,1,5,8,2]: ");
        builtHeap.print();

        // Heap Sort
        System.out.println("\n=== Heap Sort ===");
        int[] unsorted = {4, 10, 3, 5, 1};
        int[] sorted = heapSort(unsorted);
        System.out.print("Sorted: ");
        for(int v : sorted) System.out.print(v + " ");
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

insert():        Time O(log n),  Space O(1)
extractMin():    Time O(log n),  Space O(1)
peekMin():       Time O(1),      Space O(1)
heapifyUp():     Time O(log n),  Space O(log n) recursive
heapifyDown():   Time O(log n),  Space O(log n) recursive
buildHeap():     Time O(n),      Space O(1)
heapSort():      Time O(n log n),Space O(n)
contains():      Time O(n),      Space O(1)
deleteAt():      Time O(log n),  Space O(log n)
decreaseKey():   Time O(log n),  Space O(log n)

Overall Space: O(n)

Key property: Root is always minimum element

===========================================================
*/
