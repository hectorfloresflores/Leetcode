package org.example;

public class LongestCommonSubsequence_1143 {

    public int longestCommonSubsequence(String text1, String text2) {

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for(int row = 1; row < dp.length; row++) {
            for(int col = 1; col < dp[0].length; col++) {

                Character currentCharRow = text1.charAt(row - 1);
                Character currentCharCol = text2.charAt(col - 1);

                if (currentCharRow.equals(currentCharCol)) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row][col - 1], dp[row - 1][col]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence_1143 sol = new LongestCommonSubsequence_1143();
        sol.longestCommonSubsequence("abcde", "ace");

    }
}
