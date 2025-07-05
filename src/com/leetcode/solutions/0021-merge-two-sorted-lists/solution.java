/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergedLL = new ListNode(-1); // dummy node to form base of the merged linked list 
        ListNode temp = mergedLL; // temp pointer to build the new list
        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val){
                temp.next = list1; //adding to merged list
                temp = temp.next;  // Move the temporary pointer to the next node in the merged list
                list1 = list1.next; // Move the pointer of list1 to the next node
            } else {
                temp.next = list2;
                temp = temp.next;
                list2 = list2.next;
            }
        }
        // if there are any remoaing elements in list1, append them
        while(list1 != null) {
            temp.next = list1;
            temp = temp.next;
            list1 = list1.next;
        }
        while(list2 != null ) {
            temp.next = list2;
            temp= temp.next;
            list2 = list2.next;
        }
        return mergedLL.next;
    }

    
    /*  Recursion

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 != null && list2 != null){
            if(list1.val < list2.val){
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            } else{
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
        if(list1 == null) return list2;
        return list1;
    }
    */
}
