package org.example;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence_128 {

//    public int longestConsecutive(int[] nums) {
//
//
//    }

    public int nlognSol(int[] nums) {

        Arrays.sort(nums);

        // This covers the case where array it is empty
        if (nums.length == 0) return 0;

        int greatestSecuence = 1;
        int secuence = 1;

        for (int i = 1; i < nums.length; i++) {
            // Case where we found another secuence
            if (nums[i] > (nums[i - 1] + 1)) {
                greatestSecuence = Math.max(greatestSecuence, secuence);
                secuence = 1;
            } else if (nums[i] == (nums[i - 1] + 1)) { // Means it is strictly greater by one
                secuence++;
                greatestSecuence = Math.max(greatestSecuence, secuence);
            } else { // Means it is equal so let do nothing
                continue;
            }
        }
        return greatestSecuence;
    }

    public int linearSol(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        if (nums.length == 0) return 0;
        for (int num : nums) {
            set.add(num);
        }

        int max = 0;

        for (int num : set) {
            // Means if there is no previous it is the beginning of the range
            if (!set.contains(num - 1)) {

                int submax = 1;
                while (set.contains(num + submax)) {
                    submax++;

                }

                max = Math.max(max, submax);


            }
        }
    return max;
    }

    public static void main(String[] args) {
//        LongestConsecutiveSequence_128 sol = new LongestConsecutiveSequence_128();
//        sol.nlognSol(new int[]{-6,6,-3,3,0,8,4,-6,-4,2,-8,-1,-2,9,-3,-9,2,-9,-2,8,5,-7,9,-7,7,-8,5,8});

        LongestConsecutiveSequence_128 sollinear = new LongestConsecutiveSequence_128();
        sollinear.linearSol(new int[]{-6,6,-3,3,0,8,4,-6,-4,2,-8,-1,-2,9,-3,-9,2,-9,-2,8,5,-7,9,-7,7,-8,5,8});
    }
}
