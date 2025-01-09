package org.example;

/**
 *
 1800. Maximum Ascending Subarray Sum
 Description:

 Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
 A subarray is defined as a contiguous sequence of numbers in an array.
 A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi  < numsi+1. Note that a subarray of size 1 is ascending.

 Example 1:
 Input: nums = [10,20,30,5,10,50]
 Output: 65
 Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.

 Example 2:
 Input: nums = [10,20,30,40,50]
 Output: 150
 Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.

 Example 3:
 Input: nums = [12,17,15,13,10,11,12]
 Output: 33
 Explanation: [10,11,12] is the ascending subarray with the maximum sum of 33.

 Intuition:
 My first thought was to implement a O(n) solution having 2 main vars having track of the max and the current sum.
 It is important to note that we started from position 1 so we can check for previous values, instead of adding
 another variable.

 Approach:
 O(n) solution where I keep track of max and current sum.

 maxAscendingSum:

 Case 1: Corner case where array it is length of 1, so I just return the position 0 of array.
 Case 2: When next right elements are greater than previous we keep adding numbers.
 Case 3: When next right elements are less or equal than previous we reset the current sum with actual value.
 All Cases: Compare if actual sum it is greater than max sum.

 Time complexity: O(n)
 Space complexity: O(1)


 Constraints:
 1 <= nums.length <= 100
 1 <= nums[i] <= 100

 */
public class MaximumAscendingSubarraySum_1800 {

    public int maxAscendingSum(int[] nums) {
        // Case 1
        if (nums.length == 1) return nums[0];

        int max = nums[0];
        int temporalSum = nums[0];

        // Important to start from index one so we can ask for previous indexes.
        for (int i = 1; i < nums.length; i++) {
            // Case 2
            if (nums[i] > nums[i - 1]) {
                temporalSum += nums[i];
            } else { // Case 3
                temporalSum = nums[i];
            }

            // All Cases
            max = Math.max(temporalSum, max);
        }
        return max;
    }
}
