package org.example;

public class RemoveDuplicatesfromSortedArray_26 {

    public int removeDuplicates(int[] nums) {
        int k = 1;
        int index = 1;
        for(int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                k++;
                nums[index] = nums[i];
                index++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArray_26 sol = new RemoveDuplicatesfromSortedArray_26();
        sol.removeDuplicates(new int[]{1,1,2});
    }
}
