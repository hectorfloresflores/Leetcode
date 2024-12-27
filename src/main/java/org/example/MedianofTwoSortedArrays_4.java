package org.example;

public class MedianofTwoSortedArrays_4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {


        boolean odd = (nums1.length + nums2.length) % 2 != 0;
        int leftNums1 = nums1.length/2 - 1, rightNums1 = leftNums1 + 1;
        int leftNums2 = nums2.length/2 - 1, rightNums2 = leftNums2 + 1;

        // Keep looping while condition don't meet.
        while (nums1[leftNums1] > nums2[rightNums2] || nums2[leftNums2] > nums1[rightNums1]) {

            // This is the case where first array
            if (nums1[leftNums1] <=  nums2[rightNums2] && nums2[leftNums2] > nums1[rightNums1]) {
                leftNums1++;
                rightNums1++;
            }
            if (nums2[leftNums2] <= nums1[rightNums1] && nums1[leftNums1] > nums2[rightNums2]) {
                leftNums1--;
                rightNums1--;
            }
        }

        if (odd) {
            return Math.max(nums1[leftNums1], nums2[leftNums2]);
        }
        return (double) (Math.max(nums1[leftNums1], nums2[leftNums2]) + Math.min(nums1[rightNums1], nums2[rightNums2])) / 2;
    }

    public static void main(String[] args) {
        MedianofTwoSortedArrays_4 sol = new MedianofTwoSortedArrays_4();
        sol.findMedianSortedArrays(new int[]{1,3}, new int[]{2,3});
    }

}
