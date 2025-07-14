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
    public int getDecimalValue(ListNode head) {
        StringBuilder s = new StringBuilder();
        ListNode node = head; 
        while (node != null) {
            s.append(node.val);
            node = node.next;
        }  
        int number = Integer.parseInt(s.toString(), 2);
        return number;
        
    }
}
