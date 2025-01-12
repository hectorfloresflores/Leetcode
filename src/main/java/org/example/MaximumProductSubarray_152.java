package org.example;


/**
 *
 152. Maximum Product Subarray
 Description:
 Given an integer array nums, find a subarray that has the largest product, and return the product.
 The test cases are generated so that the answer will fit in a 32-bit integer.

 Example 1:
 Input: nums = [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.

 Example 2:
 Input: nums = [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

 Example 3:

 Intuition:
 I had no idea how to come with an optimal solution to be honest since the flipping sign change was difficult. The brute force
 solution times out I guess, so I search for the solution for the problem and found that it is simple but I could never reach to the solution
 without seeing it.

 Approach:
 Have the min and max on every single iteration from three values the product of min and actual, max and actual and actual.
 It is important that we need to have a possible min and possible max in case those values change on multiply them.

 maxProduct:

 Case 1: When product max it is 0 means a multiplication produces 0 that means we need to reset min and max values to 1.
        Also it is super important we keep track of the finalMax before resetting those values in case 0 it is the max value!

 All Cases: Get possible min and possible max then compare each of them with actual. So we can get the final min and max.
            Also u[date the max on every single iteration.

 Complexity:
 Time complexity:
 Space complexity: O(1)

 Constraints:
 1 <= nums.length <= 2 * 104
 -10 <= nums[i] <= 10
 The product of any subarray of nums is guaranteed to fit in a 32-bit integer.

 */
public class MaximumProductSubarray_152 {

    public int maxProduct(int[] nums) {
        int min = 1, max = 1, finalMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int possibleMin, possibleMax;
            possibleMin = Math.min(nums[i] * min, nums[i] * max);
            possibleMax = Math.max(nums[i] * min, nums[i] * max);

            min = Math.min(possibleMin, nums[i]);
            max = Math.max(possibleMax, nums[i]);

            finalMax = Math.max(finalMax, max);

            if (max == 0) {
                min = 1;
                max = 1;
            }


        }

        return finalMax;
    }

    public static void main(String[] args) {
        MaximumProductSubarray_152 sol = new MaximumProductSubarray_152();
        sol.maxProduct(new int[]{1, -2, -2, -3});
    }
}
