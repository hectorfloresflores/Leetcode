package org.example;

public class ZeroArrayTransformationI_3355 {
    /**
     * Time exceeded solution
     * Time Complexity:  O(queries * nums) = O(n^2)
     * Space Complexity: O(nums)           = O(1)
     * @param nums principal array where we will subtract form each query
     * @param queries range
     * @return true if nums can be an array of zeroes
     */
    public boolean isZeroArray_bruteForce(int[] nums, int[][] queries) {
        for (int i = 0; i < queries.length; i++) {
            int indexL = queries[i][0];
            int indexR = queries[i][1];
            for (int j = indexL; j <= indexR; j++) {
                int val = nums[j];
                if (val > 0) {
                    nums[j]--;
                }
            }
        }
        int sum = 0;
        for (int a : nums) {
            sum += a;
        }
        return sum == 0;
    }

    /**
     * Time Complexity:  O(queries + nums) = O(n)
     * Space Complexity: O(nums)           = O(n)
     * @param nums principal array where we will subtract form each query
     * @param queries range
     * @return true if nums can be an array of zeroes
     */
    public boolean isZeroArray_linear(int[] nums, int[][] queries) {
        int[] accumulation = new int[nums.length];

        for (int i = 0; i < queries.length ; i++) {
            int indexL = queries[i][0];
            int indexR = queries[i][1];

            accumulation[indexL]++;
            // Check only if right index it is out array
            if (indexR < nums.length - 1) {
                accumulation[indexR + 1]--;
            }
        }

        // Prefix sum
        int sum = 0;

        for (int i = 0; i < accumulation.length; i++) {
            sum += accumulation[i];
            if (nums[i] > sum) return false;
        }
        return true;
    }
}


