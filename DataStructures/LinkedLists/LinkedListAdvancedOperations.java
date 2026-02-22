package DataStructures.LinkedLists;

import java.util.HashMap;
import java.util.HashSet;

/*
===========================================================
Linked List Advanced Operations
===========================================================

1) Merge Two Sorted Lists
   - Recursive
   - Iterative

2) Remove Duplicates
   - From sorted list
   - From unsorted list (using HashSet)

3) Partition List around a value

4) Rotate List by k positions

5) Check if Palindrome
   - Using slow-fast pointer + reversal

6) Intersection of Two Linked Lists
   - Using length difference
   - Using HashSet

7) Add Two Numbers (as linked lists)

8) Flatten a Multilevel Linked List

9) Swap Nodes in Pairs

10) Sort Linked List (Merge Sort)

===========================================================
*/

public class LinkedListAdvancedOperations {

    // =========================
    // Node Definition
    // =========================
    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // =====================================================
    // 1️⃣ MERGE TWO SORTED LISTS - RECURSIVE
    // =====================================================
    public static Node mergeSortedRecursive(Node l1, Node l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val <= l2.val) {
            l1.next = mergeSortedRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeSortedRecursive(l1, l2.next);
            return l2;
        }
    }

    // =====================================================
    // 2️⃣ MERGE TWO SORTED LISTS - ITERATIVE
    // =====================================================
    public static Node mergeSortedIterative(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // =====================================================
    // 3️⃣ REMOVE DUPLICATES FROM SORTED LIST
    // =====================================================
    public static Node removeDuplicatesSorted(Node head) {
        Node current = head;

        while(current != null && current.next != null) {
            if(current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    // =====================================================
    // 4️⃣ REMOVE DUPLICATES FROM UNSORTED LIST (HashSet)
    // =====================================================
    public static Node removeDuplicatesUnsorted(Node head) {
        HashSet<Integer> seen = new HashSet<>();
        Node current = head;
        Node prev = null;

        while(current != null) {
            if(seen.contains(current.val)) {
                prev.next = current.next;
            } else {
                seen.add(current.val);
                prev = current;
            }
            current = current.next;
        }

        return head;
    }

    // =====================================================
    // 5️⃣ PARTITION LIST AROUND VALUE x
    // =====================================================
    public static Node partition(Node head, int x) {
        Node lessHead = new Node(0);
        Node greaterHead = new Node(0);
        Node less = lessHead;
        Node greater = greaterHead;

        Node current = head;
        while(current != null) {
            if(current.val < x) {
                less.next = current;
                less = less.next;
            } else {
                greater.next = current;
                greater = greater.next;
            }
            current = current.next;
        }

        greater.next = null;
        less.next = greaterHead.next;
        return lessHead.next;
    }

    // =====================================================
    // 6️⃣ ROTATE LIST BY K POSITIONS (RIGHT)
    // =====================================================
    public static Node rotateRight(Node head, int k) {
        if(head == null || head.next == null || k == 0) return head;

        // Find length
        int length = 1;
        Node tail = head;
        while(tail.next != null) {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if(k == 0) return head;

        // Find new tail: (length - k - 1) steps from head
        Node newTail = head;
        for(int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        Node newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

    // =====================================================
    // 7️⃣ CHECK IF PALINDROME
    // =====================================================
    public static boolean isPalindrome(Node head) {
        if(head == null || head.next == null) return true;

        // Find middle
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node secondHalf = reverseList(slow);
        Node firstHalf = head;

        // Compare
        Node temp = secondHalf;
        while(temp != null) {
            if(firstHalf.val != temp.val) return false;
            firstHalf = firstHalf.next;
            temp = temp.next;
        }

        return true;
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        while(current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // =====================================================
    // 8️⃣ INTERSECTION OF TWO LISTS - LENGTH DIFFERENCE
    // =====================================================
    public static Node getIntersectionNode(Node headA, Node headB) {
        int lenA = 0, lenB = 0;
        Node a = headA, b = headB;

        while(a != null) { lenA++; a = a.next; }
        while(b != null) { lenB++; b = b.next; }

        a = headA;
        b = headB;

        while(lenA > lenB) { a = a.next; lenA--; }
        while(lenB > lenA) { b = b.next; lenB--; }

        while(a != b) {
            a = a.next;
            b = b.next;
        }

        return a;
    }

    // =====================================================
    // 9️⃣ INTERSECTION - USING HASHSET
    // =====================================================
    public static Node getIntersectionHashSet(Node headA, Node headB) {
        HashSet<Node> visited = new HashSet<>();

        Node current = headA;
        while(current != null) {
            visited.add(current);
            current = current.next;
        }

        current = headB;
        while(current != null) {
            if(visited.contains(current)) return current;
            current = current.next;
        }

        return null;
    }

    // =====================================================
    // 🔟 ADD TWO NUMBERS (as linked lists)
    // =====================================================
    public static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        while(l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if(l1 != null) { sum += l1.val; l1 = l1.next; }
            if(l2 != null) { sum += l2.val; l2 = l2.next; }

            carry = sum / 10;
            current.next = new Node(sum % 10);
            current = current.next;
        }

        return dummy.next;
    }

    // =====================================================
    // 1️⃣1️⃣ SWAP NODES IN PAIRS
    // =====================================================
    public static Node swapPairs(Node head) {
        if(head == null || head.next == null) return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        while(prev.next != null && prev.next.next != null) {
            Node first = prev.next;
            Node second = prev.next.next;

            first.next = second.next;
            second.next = first;
            prev.next = second;

            prev = first;
        }

        return dummy.next;
    }

    // =====================================================
    // 1️⃣2️⃣ SORT LINKED LIST - MERGE SORT
    // =====================================================
    public static Node mergeSort(Node head) {
        if(head == null || head.next == null) return head;

        Node mid = getMid(head);
        Node second = mid.next;
        mid.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(second);

        return mergeSortedIterative(left, right);
    }

    private static Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // =====================================================
    // UTILITY: PRINT LIST
    // =====================================================
    public static void print(Node head) {
        Node current = head;
        while(current != null) {
            System.out.print(current.val);
            if(current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static Node build(int... vals) {
        Node dummy = new Node(0);
        Node current = dummy;
        for(int v : vals) {
            current.next = new Node(v);
            current = current.next;
        }
        return dummy.next;
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL OPERATIONS)
    // =====================================================
    public static void main(String[] args) {
        // Merge Sorted
        Node l1 = build(1, 3, 5, 7);
        Node l2 = build(2, 4, 6, 8);
        System.out.print("Merge Sorted Recursive: ");
        print(mergeSortedRecursive(l1, l2));

        l1 = build(1, 3, 5);
        l2 = build(2, 4, 6);
        System.out.print("Merge Sorted Iterative: ");
        print(mergeSortedIterative(l1, l2));

        // Remove duplicates
        Node sorted = build(1, 1, 2, 3, 3, 4);
        System.out.print("Remove dups from sorted: ");
        print(removeDuplicatesSorted(sorted));

        Node unsorted = build(3, 1, 3, 2, 1, 5);
        System.out.print("Remove dups from unsorted: ");
        print(removeDuplicatesUnsorted(unsorted));

        // Partition
        Node part = build(3, 1, 4, 2, 5, 2);
        System.out.print("Partition around 3: ");
        print(partition(part, 3));

        // Rotate
        Node rot = build(1, 2, 3, 4, 5);
        System.out.print("Rotate right by 2: ");
        print(rotateRight(rot, 2));

        // Palindrome
        System.out.println("Is palindrome [1,2,2,1]: " + isPalindrome(build(1, 2, 2, 1)));
        System.out.println("Is palindrome [1,2,3]:   " + isPalindrome(build(1, 2, 3)));

        // Swap pairs
        Node swap = build(1, 2, 3, 4);
        System.out.print("Swap pairs: ");
        print(swapPairs(swap));

        // Add two numbers: 342 + 465 = 807
        Node num1 = build(2, 4, 3); // 342
        Node num2 = build(5, 6, 4); // 465
        System.out.print("Add 342 + 465: ");
        print(addTwoNumbers(num1, num2));

        // Merge Sort
        Node unsortedList = build(4, 2, 7, 1, 5);
        System.out.print("Merge Sort: ");
        print(mergeSort(unsortedList));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Merge Two Sorted (Recursive):   Time O(n+m),  Space O(n+m) stack
2) Merge Two Sorted (Iterative):   Time O(n+m),  Space O(1)
3) Remove Dups Sorted:             Time O(n),    Space O(1)
4) Remove Dups Unsorted:           Time O(n),    Space O(n)
5) Partition:                      Time O(n),    Space O(1)
6) Rotate Right:                   Time O(n),    Space O(1)
7) Is Palindrome:                  Time O(n),    Space O(1)
8) Intersection (Length):          Time O(n+m),  Space O(1)
9) Intersection (HashSet):         Time O(n+m),  Space O(n)
10) Add Two Numbers:               Time O(max(n,m)), Space O(max(n,m))
11) Swap Pairs:                    Time O(n),    Space O(1)
12) Merge Sort:                    Time O(n log n), Space O(log n)

===========================================================
*/
