package org.example;

/**
 *

 445. Add Two Numbers II

 Description:
 You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example 1:
 Input: l1 = [7,2,4,3], l2 = [5,6,4]
 Output: [7,8,0,7]

 Example 2:
 Input: l1 = [2,4,3], l2 = [5,6,4]
 Output: [8,0,7]

 Example 3:
 Input: l1 = [0], l2 = [0]
 Output: [0]

 Intuition:
 I instantly thought that this problem was already solved. So I copied the solution from AddTwoNumber_2 but got the results wrong.
 So I noticed the problem it is that the lists are not reversed that caused the algorithm to work the opposite. So I just
 reversed both lists and also reversed the output. It was not easy for me to reverse the list in O(n), specifically to handle
 the node connections.

 reverseLists: helper function to reverse the list.

 Case 1: When list it is null or contains only one element return null or the same list respectively.
 Case 2: When we are on the beginning of the list. This is the only case where we assign null to the current element
        since will be the last element once reversed.
 Case 3: When previous it is not NULL and NEXT it is not NULL. We need to assign to current next node the previous node.
        Also previous it is current now and current it is next.
 Case 4: When we reached the end of the list we need to assign current next node to previous and current.

 Complexity with array memorization:
 Time complexity: O(n)
 Space complexity: O(1)


 Constraints:
 The number of nodes in each linked list is in the range [1, 100].
 0 <= Node.val <= 9
 It is guaranteed that the list represents a number that does not have leading zeros.

 */
public class AddTwoNumbersII_445 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        l1 = reverseLists(l1);
        l2 = reverseLists(l2);

        int sum = 0, carryOn = 0, valueNow;

        // We need to have the HEAD because that will be the reference of the single list and the TAIL to keep adding elements to the list.
        ListNode head = null, tail = null;
        // In one single while we can read both lists. Also we need to allow entering if carryON has a value greater than 0
        // because there is a corner case where the last addition of 2 values produces a value greater than 9 so we need to add it fully.
        while (l1 != null || l2 != null || carryOn > 0) {
            //It is important to initialize the sum to zero every single time because we want to have a clean addition every single time.
            sum = 0;
            // Since we are iterating both list we need to ask if one list it is not null yet.
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            // Since we are iterating both list we need to ask if one list it is not null yet.
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            // Consider carryOn from previous addition in case the number was greater than 9.
            if (carryOn > 0) {
                sum += carryOn;
            }

            // If number it is greater than 9, take the residual to put it on the list and the greater number put it on carryOn.
            if (sum > 9) {
                carryOn = (int) Math.floor((double) sum / 10);
                valueNow = sum % 10;
            } else {
                valueNow = sum;
                carryOn = 0;
            }

            // We need to initialize the HEAD first then keep adding element to the TAIL.
            if (head == null) {
                head = new ListNode(valueNow);
                tail = head;
            } else {
                tail.next = new ListNode(valueNow);
                tail = tail.next;
            }
        }

        return reverseLists(head);
    }

    ListNode reverseLists(ListNode list) {
        if (list == null) return null;
        if (list.next == null) return list;

        ListNode next = null;
        ListNode prev = null;

        while (list.next != null) {
            next = list.next;

            if (prev == null) {
                prev = list; // Previous it current now.
                list.next = null;
                list = next; // Next it is
            } else {
                list.next = prev;
                prev = list;
                list = next;
            }
        }
        list.next = prev;

        return list;
    }

    public static void main(String[] args) {
        AddTwoNumbersII_445 sol = new AddTwoNumbersII_445();
        ListNode l1 = new AddTwoNumbersII_445.ListNode(7);
        l1.next = new AddTwoNumbersII_445.ListNode(2);
        l1.next.next = new AddTwoNumbersII_445.ListNode(4);
        l1.next.next.next = new AddTwoNumbersII_445.ListNode(3);

        ListNode l2 = new AddTwoNumbersII_445.ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        //l2.next.next.next.next.next.next.next.next.next = new ListNode(9);
        sol.addTwoNumbers(l1, l2);
    }
}
