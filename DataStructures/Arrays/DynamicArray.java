package DataStructures.Arrays;
import java.util.Arrays;

/*
===========================================================
Dynamic Array (ArrayList Simulation) in Java
===========================================================

1) Initialize dynamic array
2) Add elements (resize if needed)
3) Remove element at specific index
4) Get element at index
5) Display elements
6) Testing Examples
===========================================================
*/

public class DynamicArray {

    private int[] arr;
    private int size;

    public DynamicArray(int capacity) {
        arr = new int[capacity];
        size = 0;
    }

    // Add element at the end
    public void add(int value) {
        if(size == arr.length) {
            resize();
        }
        arr[size++] = value;
    }

    // Get element at index
    public int get(int index) {
        if(index >= 0 && index < size) {
            return arr[index];
        }
        throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
    }

    // Remove element at index
    public void remove(int index) {
        if(index >= 0 && index < size) {
            for(int i = index; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    // Display elements
    public void display() {
        for(int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Resize array (double the capacity)
    private void resize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    public static void main(String[] args) {
        DynamicArray dynArr = new DynamicArray(2);
        dynArr.add(10);
        dynArr.add(20);
        dynArr.add(30); // triggers resize
        System.out.print("After adding elements: ");
        dynArr.display();

        dynArr.remove(1);
        System.out.print("After removing index 1: ");
        dynArr.display();

        System.out.println("Element at index 1: " + dynArr.get(1));
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Add (amortized): O(1) time, O(n) when resizing occurs, O(n) space for array copy
Get: O(1) time, O(1) space
Remove: O(n) time (shifting elements), O(1) space
Display: O(n) time, O(1) space
===========================================================
*/
