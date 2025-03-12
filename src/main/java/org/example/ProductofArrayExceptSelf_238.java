package org.example;

/**
 *

 238. Product of Array Except Self

 Description:
 Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

 The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

 You must write an algorithm that runs in O(n) time and without using the division operation.

 Intuition: I first thought of multiplying all the numbers and iterate through all elements and divide the total product and the num[i].
 But the problem description says that we should not use division.

 Approach: I endedup calculating the product of left side and right side.

 Example 1:
 Input: nums = [1,2,3,4]
 Output: [24,12,8,6]

 Example 2:
 Input: nums = [-1,1,0,-3,3]
 Output: [0,0,9,0,0]

 Constraints:
 2 <= nums.length <= 105
 -30 <= nums[i] <= 30
 The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.

 */
public class ProductofArrayExceptSelf_238 {
    public int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];
        int[] rightProduct = new int[nums.length];
        int left = 1, right = 1;
        int leftCounter = 0, rightCounter = nums.length - 1;
        while (leftCounter < nums.length) {
            left *= nums[leftCounter];
            leftProduct[leftCounter] = left;
            right *= nums[rightCounter];
            rightProduct[rightCounter] = right;
            leftCounter++;
            rightCounter--;
        }

        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            if (i - 1 >= 0) {
                product *= leftProduct[i - 1];

            }
            if (i + 1 < nums.length ) {
                product *= rightProduct[i + 1];
            }
            nums[i] = product;
        }
        return nums;
    }

    public static void main(String[] args) {
        ProductofArrayExceptSelf_238 sol = new ProductofArrayExceptSelf_238();
        sol.productExceptSelf(new int[]{0,0});
    }
}
