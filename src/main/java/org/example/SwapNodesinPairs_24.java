package org.example;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

public class SwapNodesinPairs_24 {

    public ListNode swapPairs(ListNode head) {

        if (head == null) {
            return head;
        }
        ListNode current = head;
        if (head.next != null) {
            head = head.next;
        }


        ListNode previous = null;
        while (current != null && current.next != null) {
            ListNode swap = current.next;
            ListNode remaining = null;
            // If it has remaining save it
            if (swap.next != null) {
                remaining = swap.next;
            }
            swap.next = current;
            current.next = remaining;
            if (previous != null) {
                previous.next = swap;
            }
            previous = current;
            current = remaining;

        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        SwapNodesinPairs_24 sol = new SwapNodesinPairs_24();
        sol.swapPairs(head);
    }
}
