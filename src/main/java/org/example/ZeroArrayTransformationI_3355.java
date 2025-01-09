package org.example;

/**
 *

 3355. Zero Array Transformation I

 Description:
 You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
 For each queries[i]:
 Select a subset of indices within the range [li, ri] in nums.
 Decrement the values at the selected indices by 1.
 A Zero Array is an array where all elements are equal to 0.

 Return true if it is possible to transform nums into a Zero Array after processing all the queries sequentially, otherwise return false.

 Example 1:
 Input: nums = [1,0,1], queries = [[0,2]]
 Output: true
 Explanation:
 For i = 0: Select the subset of indices as [0, 2] and decrement the values at these indices by 1.
 The array will become [0, 0, 0], which is a Zero Array.

 Example 2:
 Input: nums = [4,3,2,1], queries = [[1,3],[0,2]]
 Output: false
 Explanation:
 For i = 0: Select the subset of indices as [1, 2, 3] and decrement the values at these indices by 1.
 The array will become [4, 2, 1, 0].
 For i = 1: Select the subset of indices as [0, 1, 2] and decrement the values at these indices by 1.
 The array will become [3, 1, 0, 0], which is not a Zero Array.

 Intuition:
 When I was trying to solve it. My first thought was to do the brute force solution. That is decrementing by one
 on each range. I implemented but timed out. Next I research about the accumulation array. That is to add the one
 on any start of query and subtract one to end the accumulation.

 Approach:
 Use accumulation to get Linear solution.

 isZeroArray_linear:

 Case 1: When query right bound it is not the last element.
 Case 2: When query right bound it is the last element. So we skip decrementing.
 Case 3: Return false right away if when addition the accumulation we found a element that is less than the actual number.
    That means we found that there is a number greater so will not have a complete zeros array.
 All Cases:

 Complexity with array memorization:
 Time complexity: O(n)
 Space complexity: O(1)

 Constraints:
 1 <= nums.length <= 105
 0 <= nums[i] <= 105
 1 <= queries.length <= 105
 queries[i].length == 2
 0 <= li <= ri < nums.length
 */
public class ZeroArrayTransformationI_3355 {
    /**
     * Time exceeded solution
     * Time Complexity:  O(queries * nums) = O(n^2)
     * Space Complexity: O(nums)           = O(1)
     * @param nums principal array where we will subtract form each query
     * @param queries range
     * @return true if nums can be an array of zeroes
     */
    public boolean isZeroArray_bruteForce(int[] nums, int[][] queries) {
        for (int i = 0; i < queries.length; i++) {
            int indexL = queries[i][0];
            int indexR = queries[i][1];
            for (int j = indexL; j <= indexR; j++) {
                int val = nums[j];
                if (val > 0) {
                    nums[j]--;
                }
            }
        }
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        return sum == 0;
    }

    /**
     * Time Complexity:  O(queries + nums) = O(n)
     * Space Complexity: O(nums)           = O(n)
     * @param nums principal array where we will subtract form each query
     * @param queries range
     * @return true if nums can be an array of zeroes
     */
    public boolean isZeroArray_linear(int[] nums, int[][] queries) {
        int[] accumulation = new int[nums.length];

        for (int i = 0; i < queries.length ; i++) {
            int indexL = queries[i][0];
            int indexR = queries[i][1];

            accumulation[indexL]++;
            // Case 2
            if (indexR < nums.length - 1) {
                accumulation[indexR + 1]--;
            }
        }

        // Prefix sum
        int sum = 0;

        for (int i = 0; i < accumulation.length; i++) {
            sum += accumulation[i];
            // Case 3
            if (nums[i] > sum) return false;
        }
        return true;
    }
}


