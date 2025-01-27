package org.example;

import java.io.InputStream;
import java.util.Scanner;

/**
 *

 Description:

 Example 1:

 Example 2:

 Example 3:

 Intuition:

 Approach:

 functionName:

   Case 1:
   Case 2:
   All Cases:

 Complexity:
 Time complexity:
 Space complexity:

 Constraints:

 */
public class Test {

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
        if (visited[row][col]) {
            return false;
        }
        // bottom of the root case where wordPosition it is the same as word length - 1
        if (wordPosition == word.length() - 1 && board[row][col] == word.charAt(wordPosition)) {
            return true;
        }
        // Check boundaries of actual wordPosition
        if (board[row][col] != word.charAt(wordPosition) ) {
            return false;
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

        char[] palabra = new char[]{'h', 'o', 'l', 'a'};

        for (int i = 0; i < palabra.length; i++) {
            System.out.print(palabra[i]);
        }

        InputStream source = System.in;
        Scanner in = new Scanner(source);
        while(in.hasNext()){
            String input = in.next(); // Use in.nextLine() for line-by-line reading

            // Process the input here. For example, you could print it out:
            System.out.println(input);
        }

        Test sol = new Test();
        //System.out.println( sol.exist(new char[][]{{'A','B'},{'C','D'}}, "AB"));
        System.out.println( sol.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
        System.out.println( sol.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE"));
        System.out.println( sol.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB"));



    }
}
