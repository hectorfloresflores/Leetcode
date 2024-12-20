package org.example;


/**
 Intuition:
 My first intuition it is to compute the algorithm in O(n) since we can detect a hole when finding a
 negative value, this is putting a lock when finding a value that is less than actual, then releasing the lock if
 found. Furthermore, we need to have track of all locking values with a stack.

 Approach:
 I could not solve the problem with my approach there was a lot of corner cases when using stack and caused more
 complicated approach. I ended up looking for a memory solution from internet that is sad since a did not solve it
 from my own imagination.


 Complexity with array memorization:
 Time complexity: O(n)
 Space complexity: O(n)

 Complexity with 2 pointers:
 Time complexity: O(n)
 Space complexity: O(n)
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

        for (int i = 0; i < height.length; i++) {
            maxLeft = Math.max(maxLeft, height[i]);
            maxFromLeftToRight[i] = maxLeft;
        }

        int maxRight = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            maxRight = Math.max(maxRight, height[i]);
            maxFromRightToLeft[i] = maxRight;
        }

        int maxWater = 0;
        int min;
        for (int i = 0; i < height.length; i++) {
            min = Math.min(maxFromLeftToRight[i], maxFromRightToLeft[i]) - height[i];
            if (min > 0 ) {
                maxWater += min;
            }
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
