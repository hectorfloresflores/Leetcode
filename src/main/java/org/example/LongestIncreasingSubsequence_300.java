package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence_300 {

    public int lengthOfLIS(int[] nums) {

        List<Integer> db = new ArrayList<>();
        db.add(nums[0]);
        Integer max = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > db.get(max - 1)) {

                db.add(nums[i]);
                max++;

            } else {
                Integer index = Collections.binarySearch(db, nums[i]);


                if (index < 0) {
                    index = - (index + 1);

                }
                db.set(index, nums[i]);
            }

        }
        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence_300 sol = new LongestIncreasingSubsequence_300();
        sol.lengthOfLIS(new int[]{0,1,0,3,2,3});
    }
}
