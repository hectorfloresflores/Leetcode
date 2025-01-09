package org.example;

/**
 *
 Description:
 2. Add Two Numbers
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example 1:
 Input: l1 = [2,4,3], l2 = [5,6,4]
 Output: [7,0,8]
 Explanation: 342 + 465 = 807.

 Example 2:
 Input: l1 = [0], l2 = [0]
 Output: [0]

 Example 3:
 Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 Output: [8,9,9,9,0,0,0,1]

 Intuition:
 My first intuition is to keep track of each integer while traversing the single linked list.
 We can make use of bit shift so when we start saving each number we shifted properly.
 Since class does not have length of list we need to traverse it first.
 After multiple failures I tried to use int to store the values and then add the next with a 10x multiplier. The problem
 with that solution is that we can receive a very large number greater and the max value of int so it overflowed.
 I ended up traversing both list and adding on the same position each number, the logic it is simple if addition it is
 greater than 9 when need to keep the very left value on the carryOn and the very right value will be the value wee need
 to put in the list. It is important that we have to pointers the HEAD of the list and the TAIl. We need HEAD because
 that will be the result and we need to have a reference. The tail it is to keep pushing values to the new list.

 Approach:
 Addition list starting form left to right and keeping HEAD and TAIL pointers.

 addTwoNumbers: Since list are stores in reverse order I start making the sum without reversing the list

    Case 1: If any of lists has a next element add that element to the sum.
    Case 2:

 Complexity with array memorization:
 Time complexity:
 Space complexity:


 Constraints:


 Complexity:
 Time complexity: O(n) Traversing both list at the same time.
 Space complexity: O(n) The new list
 */
public class AddTwoNumbers_2 {

    //Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

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

        return head;
    }

    public static void main(String[] args) {
        AddTwoNumbers_2 sol = new AddTwoNumbers_2();
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        //l2.next.next.next.next.next.next.next = new ListNode(9);
        //l2.next.next.next.next.next.next.next.next = new ListNode(9);
        //l2.next.next.next.next.next.next.next.next.next = new ListNode(9);
        sol.addTwoNumbers(l2, l1);
    }
}
