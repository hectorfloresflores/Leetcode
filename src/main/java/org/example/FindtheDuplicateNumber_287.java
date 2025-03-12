package org.example;

/**
 *

 287. Find the Duplicate Number

 Description:
 Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 There is only one repeated number in nums, return this repeated number.
 You must solve the problem without modifying the array nums and using only constant extra space.

 Example 1:
 Input: nums = [1,3,4,2,2]
 Output: 2

 Example 2:
 Input: nums = [3,1,3,4,2]
 Output: 3

 Example 3:
 Input: nums = [3,3,3,3,3]
 Output: 3

 Intuition:
 So I solved it using extra space so it is not valid. So I searched for the solution because I had no idea
 how to solve it. So I found that floyd algorithm it is used to detect cycles.

 Approach:
 Use fast and slow pointer and when those 2 pointers intersect it has a cycle.

 functionName:

 Case 1:
 Case 2:
 All Cases:

 Complexity:
 Time complexity:
 Space complexity:

 Constraints:
 1 <= n <= 105
 nums.length == n + 1
 1 <= nums[i] <= n
 All the integers in nums appear only once except for precisely one integer which appears two or more times.

 */
public class FindtheDuplicateNumber_287 {

    public int findDuplicate(int[] nums) {
        boolean[] arr=new boolean[nums.length+1];
        for(int i:nums){
            if(arr[i]) return i;
            arr[i]=true;
        }
        return -1;
    }

    public int findDuplicateFloyd(int[] nums) {
        Integer previousFast = 0; // will helP us if the are no previous slow that means
        int slow = nums[0], fast = nums[slow];


        if (nums[0] == 1) {
            slow = nums[1];
            fast = nums[slow];
            //previousFast = fast;
        }
        while (nums[slow] != nums[fast]) {
            slow = nums[slow];
            //previousFast = fast;
            fast = nums[nums[fast]];

        }

        return nums[slow];
    }

    public static void main(String[] args) {
        FindtheDuplicateNumber_287 sol = new FindtheDuplicateNumber_287();
        System.out.println(sol.findDuplicateFloyd(new int[]{3,1,3,4,2}));
        System.out.println(sol.findDuplicateFloyd(new int[]{1,3,4,2,2}));
        System.out.println(sol.findDuplicateFloyd(new int[]{1,3,4,2,1}));
        System.out.println(sol.findDuplicateFloyd(new int[]{2,1,1}));
    }
}
