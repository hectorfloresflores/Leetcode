package org.example;

import java.io.*;

/**
 *
 79. Word Search

 Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 Example 1:
 Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 Output: true

 Example 2:
 Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 Output: true

 Example 3:
 Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 Output: false

 Intuition:
 This seems pretty obvious. Like the problem of Number of Islands 200 we should start asking from all cells and all directions
 and continue if we found a matching character.



 Approach:
 Iterate through each cell and recursive call up down right left.

    exist: This function will iterate through all cells and start a recursive call with the row and column position.
            It is important to initialize the wordPosition with 0 on the start of the recursive call this is to start
            counting the position of the words in case any match.  We will return only if true found.
        IMPORTANT: I hit a corner case where it should return false but returned true, this is because recursive call encountered
         a letter that was already found. So I needed to implement memoization. The trick is to mark as tru for any visited cell that
            match the same letter position against the word. And after all call set the visited cell to false because we want to give the opportunity
        for subsequent call if match with the word.

    doesWordExist:
            Case 1: return if cell it is already visited or if letter it is different.
            Case 2: return true if reached to the last letter of the word founded.
            Case 3: if can be expanded left recursive call and if returns true returns true immediately.
            Case 4: if can be expanded right recursive call and if returns true returns true immediately.
            Case 5: if can be expanded up recursive call and if returns true returns true immediately.
            Case 6: if can be expanded down recursive call and if returns true returns true immediately.
            All Cases: Set cell to visited false if not found.

 Complexity with array memorization:
 Time complexity:
 Space complexity:


 Constraints:
 m == board.length
 n = board[i].length
 1 <= m, n <= 6
 1 <= word.length <= 15
 board and word consists of only lowercase and uppercase English letters.

 Intuition:
 This problem looks similar to the number of islands in matrix. So I proceed to iterate through each cell and then recursively
 explore up down left and right.

 Approach:
 Iteration on every cell and expand.

 Time complexity: O(m x n x 4 ^ w.length)
 Space complexity: O(m x n)
 */
public class WordSearch_79 {

    public boolean exist(char[][] board, String word) {
        boolean result = false;
        boolean[][] visited = new boolean[board.length][board[0].length];;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                result = doesWordExist(board, visited, row, column, word, 0);
                if (result) return result;
            }

        }
        return result;

    }

    boolean doesWordExist(char[][] board, boolean[][] visited, int row, int col, String word, int wordPosition) {
        boolean result = false;
        if (visited[row][col] || board[row][col] != word.charAt(wordPosition)) {
            return false;
        }
        // bottom of the root case where wordPosition it is the same as word length - 1
        if (wordPosition == word.length() - 1 && board[row][col] == word.charAt(wordPosition)) {
            return true;
        }

        visited[row][col] = true;
        // Means we can have go to the left.
        if (row != 0) {
            result = doesWordExist(board, visited, row - 1, col, word, wordPosition + 1);
            if (result) return result;
        }

        // Means we can have go to the right.
        if (row < board.length - 1) {
            result = doesWordExist(board, visited, row + 1, col, word, wordPosition + 1);
            if (result) return result;
        }

        // Means we can have go up.
        if (col != 0) {
            result = doesWordExist(board, visited, row, col - 1, word, wordPosition + 1);
            if (result) return result;
        }

        // Means we can have go down.
        if (col < board[0].length - 1) {
            result = doesWordExist(board, visited, row, col + 1, word, wordPosition + 1);
            if (result) return result;
        }
        visited[row][col] = false;
        return false;
    }

    public static void main(String[] args) {

        WordSearch_79 sol = new WordSearch_79();
        System.out.println( sol.exist(new char[][]{{'A','B'},{'C','D'}}, "AB"));
        System.out.println( sol.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
        System.out.println( sol.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE"));
        System.out.println( sol.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB"));

    }

}
