package org.example;


/**
 *

 42. Trapping Rain Water
 Description:

 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 Example 1:
 Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6
 Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

 Example 2:
 Input: height = [4,2,0,3,2,5]
 Output: 9

 Intuition:
 My first intuition it is to compute the algorithm in O(n) since we can detect a hole when finding a
 negative value, this is putting a lock when finding a value that is less than actual, then releasing the lock if
 found. Furthermore, we need to have track of all locking values with a stack.

 Approach:
 I could not solve the problem with my approach there was a lot of corner cases when using stack and caused more
 complicated approach. I ended up looking for a memory solution from internet that is sad since a did not solve it
 from my own imagination.

 trapMemorySolution:

   Case 1: When the actual position it is less than the minimum left or right bound. So we can add water.
   Case 2: When the actual position it is greater than the minimum left or right bound. So we can add water.
   All Cases: Have 2 arrays from all numbers from left to right

 Complexity with array memorization:
 Time complexity: O(n)
 Space complexity: O(n)

 trapTwoPointersSolution: This solution involves using pointer from left to right and pointer from right to left.

    Case 1: When left pointer it is less or equal than right pointer. Then we compute the difference between actual
           value and the maxLeft and sum water. Finally update the maxLeft value if height it is greater than actual maxLeft value.
    Case 1: When right pointer it is greater than left pointer. Then we compute the difference between actual
           value and the maxRight and sum water. Finally update the maxRight value if height it is greater than actual maxRight value.

 Complexity with 2 pointers:
 Time complexity: O(n)
 Space complexity: O(n)

 Constraints:
 n == height.length
 1 <= n <= 2 * 104
 0 <= height[i] <= 105
 */
public class TrappingRainWater_42 {

    // Using memory solution. I can improve this solution to use one for loop to fill both array.
    // But in terms of complexity does not have that much impact on Big O.
    public int trapMemorySolution(int[] height) {
        int[] maxFromLeftToRight = new int[height.length];
        int[] maxFromRightToLeft = new int[height.length];

        // This is because less than 3 we cannot store any water.
        if (height.length < 3) return 0;
        int maxLeft = 0;
        // All Cases
        for (int i = 0; i < height.length; i++) {
            maxLeft = Math.max(maxLeft, height[i]);
            maxFromLeftToRight[i] = maxLeft;
        }
        // All Cases
        int maxRight = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            maxRight = Math.max(maxRight, height[i]);
            maxFromRightToLeft[i] = maxRight;
        }

        int maxWater = 0;
        int min;
        for (int i = 0; i < height.length; i++) {
            min = Math.min(maxFromLeftToRight[i], maxFromRightToLeft[i]) - height[i];
            // Case 1
            if (min > 0 ) {
                maxWater += min;
            }
            // Case 2 just do nothing since does not contain water.
        }

        return maxWater;
    }

    // This will get rid of the memory usage.
    public int trapTwoPointersSolution(int[] height) {

        if (height.length < 3) return 0;

        int leftPointer = 0, maxLeft = height[0];
        int rightPointer = height.length - 1, maxRight = height[height.length - 1];
        int difference;
        int maxWater = 0;
        // Keep repeating if 2 pointer do not collapse each other
        while (leftPointer < rightPointer) {

            if (maxLeft <= maxRight) {
                difference = maxLeft - height[leftPointer];
                if (difference > 0) {
                    maxWater += difference;
                }
                leftPointer++;
                maxLeft = Math.max(maxLeft, height[leftPointer]);
            } else {
                difference = maxRight - height[rightPointer];
                if (difference > 0) {
                    maxWater += difference;
                }
                rightPointer--;
                maxRight = Math.max(maxRight, height[rightPointer]);
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        TrappingRainWater_42 sol = new TrappingRainWater_42();
        sol.trapTwoPointersSolution(new int[]{4,2,0,3,2,5});
    }
}
