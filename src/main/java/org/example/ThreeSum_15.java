package org.example;

import java.util.*;

/**
 *

 15. 3Sum

 Description:
 Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 Notice that the solution set must not contain duplicate triplets.

 Example 1:
 Input: nums = [-1,0,1,2,-1,-4]
 Output: [[-1,-1,2],[-1,0,1]]
 Explanation:
 nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 The distinct triplets are [-1,0,1] and [-1,-1,2].
 Notice that the order of the output and the order of the triplets does not matter.

 Example 2:
 Input: nums = [0,1,1]
 Output: []
 Explanation: The only possible triplet does not sum up to 0.

 Example 3:
 Input: nums = [0,0,0]
 Output: [[0,0,0]]
 Explanation: The only possible triplet sums up to 0.

 Intuition:
 My first intuition was to use a cache to retreive faster the b and c values. I did not get to the right answer. I had to
 look into the solution and it was quite surprising that we cannot get e better time complexity than O(n^2).
 The optimal solution was to sort the list of number since later we will use pointer to get the numbers.
 Once we get the array sorted with Arrays.sort() I have top create a for loop so we can have all combinations possible
 with a. Then on each iteration create a left pointer that will be one position after the a and right pointer that will
 be the last position. Once we have those pointers we can start asking if the sum of a + nums[leftPointer] + nums[rightPointer]
 equals 0 if so, add that combination to a list and put nums[leftPointer] and nums[rightPointer] in a different set. This is
 because we dont want to have a repetitive sequence.

 Approach:
 Sliding window approach.

 functionName:

 Case 1:
 Case 2:
 All Cases:

 Complexity:
 Time complexity: O(n log n)
 Space complexity: O(n)

 Constraints:
 3 <= nums.length <= 3000
 -10^5 <= nums[i] <= 10^5

 */
public class ThreeSum_15 {

    public List<List<Integer>> threeSum(int[] nums) {
        // Will take O(n log n) for sorting;
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0, left, right, sum; i < nums.length; i++) {

            // We dont want to try a combination if next number it is the same as previous.
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }
            // Initialize the left pointer with the very left position after the current loop index.
            left = i + 1;
            // Initialize the right pointer with the last position.
            right = nums.length - 1;

            // Cache the solution found from the 2 pointer
            Set<Integer> cache = new HashSet<>();

            // If indexes dont overlap execute code
            while (left < right) {
                // Calculate sum of three possible elements
                sum = nums[i] + nums[left] + nums[right];
                // Best case, found the solution
                if (sum == 0 && !cache.contains(nums[left])) {
                    // Add to cache so we don't repeat next time. We can add either left or right since in order
                    cache.add(nums[left]);
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    // We need to decrement right and increment left respectively since that combination it is added.
                    right--;
                    left++;
                    continue;
                }
                // We need to shift the right pointer to the left to make the number smaller
                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum_15 sol = new ThreeSum_15();
        sol.threeSum(new int[]{-2,0,1,1,2});
    }
}
