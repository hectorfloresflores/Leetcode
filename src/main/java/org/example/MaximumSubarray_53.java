package org.example;

/**
 Intuition:
 My first intuition it is implement brute force, this will take O(n^3) since it will compare each range
 combination thus three for loops are necessary. After looking into Kadane's algo I found that this works
 because we keep adding numbers until we reach to a number that the single number it is greather than the previous
 sum. That means that no matter if we previously found a very big number if the sum of previous numbers it less than
 a new single number we will use that new number. Also, it is interesting picking the first number and starting the
 for loop from 1 we can skip the one element corner case for the for loop.

 Approach:
 The best approach will be Kadane's algorithm since it will use O(n) time and O(1) space.

 Complexity:
 Time complexity: O(n)
 Space complexity: O(1)
 */

public class MaximumSubarray_53 {

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int possibleSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // If found any new number that it is bigger than previous sum, just replace it.
            possibleSum = Math.max(nums[i], nums[i] + possibleSum);
            // Let's keep track of the biggest.
            maxSum = Math.max(possibleSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray_53 sol = new MaximumSubarray_53();
        sol.maxSubArray(new int[]{-1, -1});
    }
}
