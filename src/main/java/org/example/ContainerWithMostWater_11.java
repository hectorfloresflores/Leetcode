package org.example;

/**
 *

 You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 Find two lines that together with the x-axis form a container, such that the container contains the most water.
 Return the maximum amount of water a container can store.
 Notice that you may not slant the container.

 Example 1:
 Input: height = [1,8,6,2,5,4,8,3,7]
 Output: 49
 Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

 Example 2:
 Input: height = [1,1]
 Output: 1


 Intuition:
 This problem reminds me to that trapping water algorithm 42. I thought it was the same since it is trapping water,
 but it is slightly different since in this problem we do not care about the rectangle between pointer so we can easily
 calculate the area really quick.

 Approach:
 Use 2 pointers. The leftPointer and the rightPointer where each one will be initialized from the first position and last
 position respectively.

 maxArea: Take the maxArea for any calculation starting from the leftPointer and rightPointer.

    Case 1: When leftPointer it is less than rightPointer.
    Case 2: When leftPointer it is greater or equal than rightPointer.

 Complexity with array memorization:
 Time complexity: O(n)
 Space complexity: O(1)


 Constraints:
 n == height.length
 2 <= n <= 105
 0 <= height[i] <= 104
 */
public class ContainerWithMostWater_11 {


    public int maxArea(int[] height) {
        int maxArea = 0;
        int area = 0;
        int pointerLeft = 0;
        int pointerRight = height.length - 1;
        while (pointerLeft < pointerRight) {

            area = (pointerRight - pointerLeft) * Math.min(height[pointerLeft], height[pointerRight]);
            maxArea = Math.max(area, maxArea);
            if (height[pointerLeft] < height[pointerRight])  {
                pointerLeft++;
            } else {
                pointerRight--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater_11 sol = new ContainerWithMostWater_11();
        System.out.println(sol.maxArea(new int[]{1,1}));
    }
}
