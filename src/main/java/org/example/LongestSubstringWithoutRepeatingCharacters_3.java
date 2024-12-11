package org.example;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters_3 {

    /**
     * The reasoning behind this solution is to have a pointer to expand to the left and to the right
     * at the same time. This approach it is not sliding window but solves the problem. Uses more resources and more time.
     * @param s to be analyzed
     * @return the number size of the largest substring.
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> cache = new HashSet<>();
        String longestSubstring = "";
        int left, right;
        for (int i = 0; i < s.length(); i++) {
            left = i-1; right = i+1;
            // Clear cache because when we move one step further we want to start cache new values
            cache.clear();
            String possibleSubstring = s.substring(i, i+1);
            cache.add(s.charAt(i));
            boolean leftFlagStop = false, rightFlagStop = false;
            while (true) {
                // Check if characters already exists
                if (left >= 0) {
                    if (!cache.contains(s.charAt(left))) {
                        possibleSubstring = s.charAt(left) + possibleSubstring;
                        cache.add(s.charAt(left));
                    } else {
                        leftFlagStop = true;
                    }

                }
                if (right < s.length()) {
                    if (!cache.contains(s.charAt(right))) {
                        possibleSubstring = possibleSubstring + s.charAt(right);
                        cache.add(s.charAt(right));
                    } else {
                        rightFlagStop = true;
                    }

                }
                if (possibleSubstring.length() > longestSubstring.length()) {
                    longestSubstring = possibleSubstring;
                }


                if (left < 0 || right >= s.length() || leftFlagStop || rightFlagStop) {
                    break;
                }

                left--;
                right++;
            }
        }

        return longestSubstring.length();
    }

    /**
     * Sliding window approach
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_slidingWindow(String s) {
        boolean[] cache = new boolean[256];
        int maxSizeSubstring = 0;
        for (int leftPointer = 0, rightPointer = 0; rightPointer < s.length(); rightPointer++) {
            // Whenever right pointer detect a char that was used before narrow left pointer to the right and clean cache
            // for character
            while (cache[s.charAt(rightPointer)]) {
                cache[s.charAt(leftPointer++)] = false;
            }
            // If reach to this point means rightPointer does not have any known char.
            cache[s.charAt(rightPointer)] = true;
            // Take the difference between right and left pointer plus one to take max.
            maxSizeSubstring = Math.max(maxSizeSubstring, rightPointer - leftPointer + 1);
        }
        return maxSizeSubstring;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters_3 sol = new LongestSubstringWithoutRepeatingCharacters_3();
        sol.lengthOfLongestSubstring_slidingWindow("abcabcbb");
    }
}
