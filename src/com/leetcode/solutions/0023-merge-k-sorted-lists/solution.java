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
    public ListNode merge(ListNode a,ListNode b) {
        if( a == null ) return b;
        if(b == null) return a;
        if(a.val < b.val) {
            a.next = merge(a.next,b);
            return a;
        }
        b.next = merge(a,b.next);
        return b;
    }
    public ListNode divide(ListNode[] lists, int start, int end){
        if(start >= end) {
            return lists[start];
        }
        int mid = (start + end) / 2;
        ListNode a = divide(lists, start, mid);
        ListNode b = divide(lists, mid+1, end);
        return merge(a,b); 
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return divide(lists, 0, lists.length-1);
    }
}
