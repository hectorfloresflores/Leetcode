package org.example;

import java.util.HashMap;


public class TwoSum_1 {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length ; i++) {
            int numberToFind = target - nums[i];
            if (!map.containsKey(numberToFind)) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(numberToFind), i};
            }

        }

        return result;
    }
}
