package org.example;

/**
 Intuition:
 My first intuition was to use palindrome strategy since the grouped of '0's and '1's must be consecutive. So if start
 seeing ones we marked as ones and zeros strategy and we will count until the rules breaks.

 Approach 1:
 First: Pointer expansion, like palindrome.

 Complexity:
 Time complexity: O(n)
 Space complexity: O(n)

 Approach 2:
 Second: Take the minimum of consecutive zeros and ones.

 Complexity:
 Time complexity: O(n)
 Space complexity: O(n)
 */
public class CountBinarySubstrings_696 {

    public int countBinarySubstrings(String s) {
        int counter = 0;
        boolean zerosLeft;
        for (int i = 0, leftPointer, rightPointer; i < s.length(); i++) {
            leftPointer = i;
            rightPointer = i + 1;
            zerosLeft = s.charAt(leftPointer) == '0'; // This will be the contract to continue saving new solutions.
            while (leftPointer >= 0 && rightPointer < s.length()) {

                // If contract it is broken.
                if (zerosLeft == true && (s.charAt(rightPointer) == '0' || s.charAt(leftPointer) == '1')
                       || zerosLeft == false && (s.charAt(rightPointer) == '1' || s.charAt(leftPointer) == '0')
                        || s.charAt(leftPointer) == s.charAt(rightPointer)) {
                    break;
                }
                counter++;
                leftPointer--;
                rightPointer++;
            }
        }
        return counter;
    }

    public int countBinarySubstringsMinimumSolution(String s) {
        int numberOfContinuousZeros = 0;
        int numberOfContinuousOnes = 0;
        int finalCounter = 0, counter = 0;
        char previous = s.charAt(0);
        if (previous == '0') {
            numberOfContinuousZeros++;
        } else {
            numberOfContinuousOnes++;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != previous) {
                if (s.charAt(i) == '0') {
                    numberOfContinuousZeros = 0;
                } else {
                    numberOfContinuousOnes = 0;
                }
                finalCounter += counter;
            }
            if (s.charAt(i) == '0') {
                numberOfContinuousZeros++;
                counter = Math.min(numberOfContinuousZeros, numberOfContinuousOnes);
            }

            if (s.charAt(i) == '1') {
                numberOfContinuousOnes++;
                counter = Math.min(numberOfContinuousZeros, numberOfContinuousOnes);
            }
            previous = s.charAt(i);

        }
        return finalCounter + counter;
    }

    public static void main(String[] args) {
        CountBinarySubstrings_696 sol = new CountBinarySubstrings_696();
        sol.countBinarySubstringsMinimumSolution("01");
    }
}
