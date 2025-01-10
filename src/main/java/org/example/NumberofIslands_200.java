package org.example;

/**
 *

 Description:

 Example 1:

 Example 2:

 Example 3:

 Intuition:
 My first intuition was to recursive call the matrix and mark as visited something like recursive DFS. That solution
 did not work surprisingly I already solved that solution before, so the trick increment number of islands when find
 a one and recursively call a function to clear all '1's so when can not mistaken count another island.

 Approach:
 Iterate and recursive call grid to clear out all adjacent '1's

 isValidBoundary:
   Case 1: If row and col it is valid on the provided grid return true. False otherwise.

 numIslands: Wrapper function for a recursive solution algorithm.
   Case 1: If current cell has a '1'. Call with DFS approach and clear all values. and increment number of islands.
   All Cases: Iterate through each cell.

 recursiveNumIslands: Recursive function that will make the job.
    Case 1: If it is a valid cell range and has one expand to left, right, bottom and up.

 Complexity:
 Time complexity: O(m x n)
 Space complexity: O(1)

 Constraints:
 m == grid.length
 n == grid[i].length
 1 <= m, n <= 300
 grid[i][j] is '0' or '1'.

 */
/**
 Intuition:
 My first intuition was to recursive call the matrix and mark as visited something like recursive DFS. That solution
 did not work surprisingly I already solved that solution before, so the trick increment number of islands when find
 a one and recursively call a function to clear all '1's so when can not mistaken count another island.

 Approach:
 Iterate and recursive call grid to clear out all adjacent '1's

 Complexity:
 Time complexity: O(m x n)
 Space complexity: O(1)
 */
public class NumberofIslands_200 {

    public boolean isValidBoundary(char[][] grid, int row, int col) {
        if (col >= 0 && col < grid[0].length && row >= 0 && row < grid.length) {
            return true;
        }
        return false;
    }

    public int numIslands(char[][] grid) {
        int numIsalands = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    numIsalands++;
                    recursiveNumIslands(grid, row, col);
                }
            }
        }
        return numIsalands;
    }


    public void recursiveNumIslands(char[][] grid, int row, int col) {
        if (isValidBoundary(grid, row, col) && grid[row][col] == '1') {
            grid[row][col] = '0';
            recursiveNumIslands(grid, row+1, col);
            recursiveNumIslands(grid, row-1, col);
            recursiveNumIslands(grid, row, col+1);
            recursiveNumIslands(grid, row, col-1);
        }
    }

    public static void main(String[] args) {
        NumberofIslands_200 sol = new NumberofIslands_200();
        //int res = sol.numIslands(new char[][]{
        //        {0,1,0,0},
        //        {0,0,0,0},
        //        {0,0,0,0},
        //        {0,0,0,1}});
        int res = sol.numIslands(new char[][]{
                        {'1','1'},
                        {'0','1'}
                });
        System.out.println("The number of islands is: " + res);
    }
}
