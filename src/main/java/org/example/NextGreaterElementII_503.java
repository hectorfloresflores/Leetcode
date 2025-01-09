package org.example;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 503. Next Greater Element II
 Description:

 Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

 Example 1:
 Input: nums = [1,2,1]
 Output: [2,-1,2]
 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number.
 The second 1's next greater number needs to search circularly, which is also 2.

 Example 2:
 Input: nums = [1,2,3,4,3]
 Output: [2,3,4,-1,4]

 Intuition:
 I had no idea how to solve it and I knew the recommended way it is to use monotonic stack. So I proceed to see how monotonic stack
 was implemented and found this <a href="https://leetcode.com/discuss/study-guide/2347639/a-comprehensive-guide-and-template-for-monotonic-stack-based-problems">link</a>
 but I could not understand it neither. So I just copied an implementation on how monotonic stack was implemented in Java and debug it.
 Then everything started to make sense, the key to understand the algorithm is that numbers that start accumulating on the stack will be
 the indexes of those numbers So until we encounter an element greater than the last pushed into the stack we will use that numbers fo fill
 all positions where we have pushed the stack before.

 Approach:
 I solve it by implementing monotonic stack but realized that I needed to do something else, because elements with greater value were
 located on the left the algorithm did not work. So I realized that if we loop again to the same array I was able to solve it.
 The interesting thing is that I looped with 2 for loops, but a fried showed me his code and he used only one loop but he used
 a nice trick, that is multiplying by 2 the array.length and using i % array.length. This way we will only work on the array range.

 nextGreaterElements:

    Case 1: When Stack has elements and the current element on the array it is greater than the stack last value we will fill with the
            current value all lower values because we have their indexes.
    All Cases: Fill array with -1, then push all values on each iteration

 Complexity with array memorization:
 Time complexity: O(N)
 Space complexity: O(1)

 Constraints:
 1 <= nums.length <= 104
 -109 <= nums[i] <= 109

 */
public class NextGreaterElementII_503 {

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        int[] result = new int[nums.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < 2*nums.length; i++) {
            while (!stack.empty() && nums[stack.peek()] < nums[i%nums.length]) {
                // Pop the index of the lower element of the stack so we can assign this value.
                int lowerElementIndex = stack.pop();
                result[lowerElementIndex] = nums[i%nums.length];
            }
            stack.push(i%nums.length);
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElementII_503 sol = new NextGreaterElementII_503();
        sol.nextGreaterElements(new int[]{3,4,5,4,3,2,1});
    }
}
