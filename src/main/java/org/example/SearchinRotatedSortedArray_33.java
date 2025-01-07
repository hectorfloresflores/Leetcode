package org.example;

/**
 *

 33. Search in Rotated Sorted Array

 Description:

 There is an integer array nums sorted in ascending order (with distinct values).
 Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 You must write an algorithm with O(log n) runtime complexity.

 Example 1:
 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4

 Example 2:
 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1

 Example 3:
 Input: nums = [1], target = 0
 Output: -1

 Intuition:
 This problem caused me headaches, I already solved it before and I saw why it is hard. It has multiple corner cases.
 My first intuition was to do binary search using recursion.

 Approach:
 I create a separated recursive function with books, leftPointer, rightPointer and target as params. The main idea it is
 to check whether the number we are looking fo it is located on left or on right side. The trick is cover all corner cases
 for each left and right side.


 recursiveSearch:
    Case 1: When the number we are looking for it is in range of left. Then go to left.
    Case 2: When the number we are looking for it is not in range but can possibly exist on left side this is because
    rotation may exist on left side.
            e.g. [8,9,2,3,4] target:9  As you can see left side [8,9,2] it is not a valid range but rotation exist!!!
                                        Since 9 the number we are looking for it is greater than left.
    The key solution for this case it is to catch that the number can possibly be in semi valid range and if rotation may exists.

 Complexity with array memorization:
 Time complexity: log(n)
 Space complexity: O(1)


 Constraints:
 1 <= nums.length <= 5000
 -104 <= nums[i] <= 104
 All values of nums are unique.
 nums is an ascending array that is possibly rotated.
 -104 <= target <= 104
 */
public class SearchinRotatedSortedArray_33 {

    int target;
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 2)  {
            if (nums[0] == target) return 0;
            if (nums[1] == target) return 1;
            return -1;
        }
        this.target = target;
        return recursiveSearch(nums, 0, nums.length - 1);
    }

    int recursiveSearch(int[] nums, int left, int right) {

        // Check that binary search does not
        if (nums[left] == target) {
            return left;
        }

        if (nums[right] == target) {
            return right;
        }

        if (left == right) {
            if (nums[left] == target) return left;
            return -1;
        }

        int middle = left + Math.floorDiv(right - left, 2);
        // Case 2: Check if it is in valid range or if target can possibly be in range and if left has rotated array.
        if (target >= nums[left] && target <= nums[middle] || ((target <= nums[middle] || target >= nums[left]) && nums[middle] - nums[left] < 0)) {
            return recursiveSearch(nums, left, middle);
        } else {
            return recursiveSearch(nums, middle + 1, right);
        }
    }

    public static void main(String[] args) {
        SearchinRotatedSortedArray_33 sol = new SearchinRotatedSortedArray_33();
        System.out.println(sol.search(new int[]{8,9,2,3,4}, 9));
    }
}
