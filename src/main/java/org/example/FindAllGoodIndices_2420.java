package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class FindAllGoodIndices_2420 {

    public List<Integer> goodIndices(int[] nums, int k) {
        int[] nonIncreasing = new int[nums.length];
        int[] nonDecreasing = new int[nums.length];

        Arrays.fill(nonIncreasing, 1);
        Arrays.fill(nonDecreasing, 1);

        List<Integer> orderedList = new LinkedList<>();

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i]) {
                nonIncreasing[i - 1] = nonIncreasing[i - 1] + 1;
            }
        }

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] <= nums[i]) {
                nonDecreasing[i - 1] = nonDecreasing[i - 1] + 1;
            }
        }

        for (int i = k; i < nums.length - k; i++) {
            if (nonIncreasing[i - 1] >= k && nonDecreasing[i + i] >= k) {
                orderedList.add(i);
            }

        }

        return orderedList;
    }

    // Time limit exceeded
//    public List<Integer> goodIndices(int[] nums, int k) {
//
//        List<Integer> orderedList = new LinkedList<>();
//
//        //We will try each index within the range
//        for (int i = k; i < nums.length - k; i++) {
//
//            if ((i + k) < nums.length && checkNonIncreasing(nums, i - k, i) && checkNonDecreasing(nums, i, i + k)) {
//                orderedList.add(i);
//            }
//
//        }
//
//        return orderedList;
//
//    }

    boolean checkNonIncreasing(int[] nums, int left, int right) {
        for (int i = left; left < right; left++) {
            if (nums[left] < nums[left + 1]) return false;
        }
        return true;
    }

    boolean checkNonDecreasing(int[] nums, int left, int right) {
        for (int i = left; left < right; left++) {
            if (nums[left] > nums[left + 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        FindAllGoodIndices_2420 sol = new FindAllGoodIndices_2420();
        sol.goodIndices(new int[]{878724,201541,179099,98437,35765,327555,475851,598885,849470,943442}, 4);
    }
}
