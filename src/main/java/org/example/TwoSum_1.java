package org.example;

import java.util.HashMap;

/**
 *
 1. Two Sum
 Description:
 Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 You can return the answer in any order.

 Example 1:
 Input: nums = [2,7,11,15], target = 9
 Output: [0,1]
 Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

 Example 2:
 Input: nums = [3,2,4], target = 6
 Output: [1,2]

 Example 3:
 Input: nums = [3,3], target = 6
 Output: [0,1]

 Intuition:
 My intuition was to perform a comparison between all numbers, that will be O(n^2) space complexity. Instead, the start
 from position zero and put all new values on a hashmap then on each position we ask y the difference on the actual value
 and the target exist in the hash map if it does return result.

 Approach:
    Hash all new value and ask for the difference between the actual value and the target on the hashmap.

 twoSum:

 Case 1: When we found the value we are looking for.
 Case 2: When value it is not found so we add it to the hashmap.
 All Cases:

 Complexity with array memorization:
 Time complexity:
 Space complexity:

 Constraints:

 */
public class TwoSum_1 {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length ; i++) {
            int numberToFind = target - nums[i];
            // Case 2
            if (!map.containsKey(numberToFind)) {
                map.put(nums[i], i);
            } else { // Case 1
                return new int[]{map.get(numberToFind), i};
            }

        }

        return result;
    }
}
