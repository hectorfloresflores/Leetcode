package org.example;

import java.util.ArrayList;
import java.util.List;

public class EditDistance_72 {


    public int dpSol(String word1, String word2) {

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int rows = 0, value = word1.length(); rows < word1.length() + 1; rows++, value--) {
            dp[rows][word2.length()] = value;
        }

        for (int cols = 0, value = word2.length(); cols < word2.length() + 1; cols++, value--) {
            dp[word1.length()][cols] = value;
        }

        for (int row = word1.length() - 1; row >= 0; row--) {
            for (int col = word2.length() - 1; col >= 0; col--) {

                if (word1.charAt(row) == word2.charAt(col)) {
                    dp[row][col] = dp[row + 1][col + 1];
                } else {
                    int down = dp[row + 1][col];
                    int right = dp[row][col + 1];
                    int downRight = dp[row + 1][col + 1];
                    dp[row][col] = 1 + Math.min(downRight, Math.min(down, right));
                }

            }
        }

        return dp[0][0];

    }

    public static void main(String[] args) {
        EditDistance_72 sol = new EditDistance_72();
        sol.dpSol("horse", "ros");
    }

//    public int recursiveSol(String word1, String word2, Integer m, Integer n) {
//
//
//        if (word1.charAt(m) == word2.charAt(n)) {
//            recursiveSol(word1, word2, m + 1, n + 1);
//        } else {
//            //insert
//            recursiveSol(word1, word2, m, n + 1);
//                //delete
//            recursiveSol(word1, word2, m + 1, n);
//
//            recursiveSol(word1, word2, m + 1, n + 1);
//
//            return
//        }
//
//
//    }
}
