class MyHashSet {
    class Node{
        int val;
        Node next;
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }
    Node head;
    public MyHashSet() {
        head = null;
    }
    
    public void add(int key) {
        Node new_node = new Node(key);
        if(head == null) {
            head = new_node;
        }else{
            Node curr = head;
            Node prev = null;
            while(curr != null) {
                if(curr.val == key){
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = new_node;
        }
    }
    
    public void remove(int key) {
        Node curr = head, prev = null;
        while (curr != null) {
            if (curr.val == key) {
                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        
    }
    
    public boolean contains(int key) {
        if(head == null) return false;
        Node curr = head;
        while(curr != null) {
            if(curr.val == key) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
