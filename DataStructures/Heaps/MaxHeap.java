package DataStructures.Heaps;

import java.util.Arrays;

/*
===========================================================
Max Heap Implementation
===========================================================

A Max Heap: every parent >= children. Root = maximum.

Operations:
1) insert(val)       - Add and heapify up
2) extractMax()      - Remove and return maximum
3) peekMax()         - Return max without removing
4) heapifyUp()       - Iterative & Recursive
5) heapifyDown()     - Iterative & Recursive
6) buildHeap(arr)    - O(n) construction
7) heapSort()        - In-place descending/ascending

===========================================================
*/

public class MaxHeap {

    private int[] data;
    private int size;
    private int capacity;

    MaxHeap(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    MaxHeap(int[] arr) {
        this.size = arr.length;
        this.capacity = arr.length;
        this.data = Arrays.copyOf(arr, arr.length);
        buildHeap();
    }

    private int parent(int i)    { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i){ return 2 * i + 2; }

    private void swap(int i, int j) {
        int t = data[i]; data[i] = data[j]; data[j] = t;
    }

    // =====================================================
    // 1️⃣ INSERT - O(log n)
    // =====================================================
    public void insert(int val) {
        if(size == capacity) throw new RuntimeException("Heap is full");
        data[size++] = val;
        heapifyUpIterative(size - 1);
    }

    // =====================================================
    // 2️⃣ HEAPIFY UP - ITERATIVE
    // =====================================================
    private void heapifyUpIterative(int i) {
        while(i > 0 && data[parent(i)] < data[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // =====================================================
    // 3️⃣ HEAPIFY UP - RECURSIVE
    // =====================================================
    private void heapifyUpRecursive(int i) {
        if(i == 0) return;
        int p = parent(i);
        if(data[p] < data[i]) {
            swap(i, p);
            heapifyUpRecursive(p);
        }
    }

    // =====================================================
    // 4️⃣ EXTRACT MAX - O(log n)
    // =====================================================
    public int extractMax() {
        if(size == 0) throw new RuntimeException("Heap is empty");
        int max = data[0];
        data[0] = data[--size];
        heapifyDownIterative(0);
        return max;
    }

    // =====================================================
    // 5️⃣ HEAPIFY DOWN - ITERATIVE
    // =====================================================
    private void heapifyDownIterative(int i) {
        while(true) {
            int largest = i, l = leftChild(i), r = rightChild(i);
            if(l < size && data[l] > data[largest]) largest = l;
            if(r < size && data[r] > data[largest]) largest = r;
            if(largest == i) break;
            swap(i, largest);
            i = largest;
        }
    }

    // =====================================================
    // 6️⃣ HEAPIFY DOWN - RECURSIVE
    // =====================================================
    private void heapifyDownRecursive(int i) {
        int largest = i, l = leftChild(i), r = rightChild(i);
        if(l < size && data[l] > data[largest]) largest = l;
        if(r < size && data[r] > data[largest]) largest = r;
        if(largest != i) {
            swap(i, largest);
            heapifyDownRecursive(largest);
        }
    }

    // =====================================================
    // 7️⃣ PEEK MAX - O(1)
    // =====================================================
    public int peekMax() {
        if(size == 0) throw new RuntimeException("Heap is empty");
        return data[0];
    }

    // =====================================================
    // 8️⃣ BUILD HEAP FROM ARRAY - O(n)
    // =====================================================
    public void buildHeap() {
        for(int i = (size / 2) - 1; i >= 0; i--) {
            heapifyDownIterative(i);
        }
    }

    // =====================================================
    // 9️⃣ INCREASE KEY
    // =====================================================
    public void increaseKey(int index, int newVal) {
        if(newVal < data[index]) throw new RuntimeException("New value is smaller");
        data[index] = newVal;
        heapifyUpIterative(index);
    }

    // =====================================================
    // 🔟 IN-PLACE HEAP SORT (ascending)
    // =====================================================
    public static int[] heapSortAscending(int[] arr) {
        int n = arr.length;
        // Build max heap
        for(int i = n / 2 - 1; i >= 0; i--) siftDown(arr, i, n);
        // Extract elements one by one
        for(int i = n - 1; i > 0; i--) {
            int t = arr[0]; arr[0] = arr[i]; arr[i] = t;
            siftDown(arr, 0, i);
        }
        return arr;
    }

    private static void siftDown(int[] arr, int i, int n) {
        while(true) {
            int largest = i, l = 2*i+1, r = 2*i+2;
            if(l < n && arr[l] > arr[largest]) largest = l;
            if(r < n && arr[r] > arr[largest]) largest = r;
            if(largest == i) break;
            int t = arr[i]; arr[i] = arr[largest]; arr[largest] = t;
            i = largest;
        }
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void print() {
        System.out.print("MaxHeap: [");
        for(int i = 0; i < size; i++) System.out.print(data[i] + (i < size-1 ? ", " : ""));
        System.out.println("]");
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(20);
        heap.insert(10); heap.insert(5); heap.insert(15);
        heap.insert(3);  heap.insert(8); heap.insert(20);
        heap.print();

        System.out.println("PeekMax: " + heap.peekMax());
        System.out.println("ExtractMax: " + heap.extractMax());
        heap.print();

        heap.increaseKey(1, 25);
        System.out.print("After increaseKey(1, 25): "); heap.print();

        // Build from array
        int[] arr = {4, 10, 3, 5, 1, 8, 7};
        MaxHeap built = new MaxHeap(arr);
        System.out.print("Built from array: "); built.print();

        // Heap Sort ascending
        int[] toSort = {4, 10, 3, 5, 1};
        System.out.print("HeapSort ascending: ");
        for(int v : heapSortAscending(toSort)) System.out.print(v + " ");
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

insert():        Time O(log n),   Space O(1)
extractMax():    Time O(log n),   Space O(1)
peekMax():       Time O(1),       Space O(1)
heapifyUp():     Time O(log n),   Space O(1) iterative
heapifyDown():   Time O(log n),   Space O(1) iterative
buildHeap():     Time O(n),       Space O(1)
heapSort():      Time O(n log n), Space O(1) in-place
increaseKey():   Time O(log n),   Space O(1)

Overall Space: O(n)

===========================================================
*/
