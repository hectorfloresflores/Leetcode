package org.example;

/**
 Intuition:
 My first intuition was to use a pointer that can expand so we can check if expansion characters are the same, then
 return the largest one. There is a corner case where we need to check if the palindrome it odd or even so we need
 to expand 2 times on each expansion. One in case next right character it is the same as the center, Second the
 normal expansion.

 Approach:
 Pointer expansion, and check for even and odd palindrome.

 Complexity:
 Time complexity: O(n ^ 2)
 Space complexity: O(n ^ 2)
 */
public class Longest_PalindromicSubstring_5 {

    StringBuilder checkPalindrome(String s, StringBuilder possibleResult, int leftPointer, int rightPointer) {
        while (leftPointer >= 0 && rightPointer < s.length()) {
            if (s.charAt(leftPointer) == s.charAt(rightPointer)) {
                possibleResult.append(String.valueOf(s.charAt(rightPointer)));
                possibleResult.insert(0, String.valueOf(s.charAt(leftPointer)));
                rightPointer++;
                leftPointer--;
            } else {
                break;
            }
        }
        return possibleResult;
    }
    public String longestPalindrome(String s) {
        //Corner cases where algorithm for expansion will not catch.
        if (s.length() == 1) return s;
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return String.valueOf(s.charAt(0));
            }
        }

        String longestPalindrome = "";
        StringBuilder possibleResult;
        for (int center = 0, left, right; center < s.length(); center++) {
            possibleResult = new StringBuilder();
            possibleResult.append(s.charAt(center));
            left = center - 1;
            right = center + 1;
            if (right < s.length() && s.charAt(right) == s.charAt(center)) {
                possibleResult.append(s.charAt(right));
                checkPalindrome(s, possibleResult, left, right + 1);
            }
            if (longestPalindrome.length() < possibleResult.length()) {
                longestPalindrome = possibleResult.toString();
            }
            possibleResult = new StringBuilder();
            possibleResult.append(s.charAt(center));
            if (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                checkPalindrome(s, possibleResult, left, right);
            }
            if (longestPalindrome.length() < possibleResult.length()) {
                longestPalindrome = possibleResult.toString();
            }

        }
        return longestPalindrome;
    }

    public static void main(String[] args) {
        Longest_PalindromicSubstring_5 sol = new Longest_PalindromicSubstring_5();
        sol.longestPalindrome("abcda");
    }
}
