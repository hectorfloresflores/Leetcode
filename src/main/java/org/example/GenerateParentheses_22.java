package org.example;

import java.util.LinkedList;
import java.util.List;


/**
 *
 22. Generate Parentheses

 Description:
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 Example 1:
 Input: n = 3
 Output: ["((()))","(()())","(())()","()(())","()()()"]

 Example 2:
 Input: n = 1
 Output: ["()"]

 Example 3:

 Intuition:
 I already solved it before but I had no Idea hot I got the solution. It was pretty simple when I saw it just 2 recursive calls.

 Approach:
 Using backtracking

 recursiveCall:

   Case 1: When left parenthesis is equal to 0 and right parenthesis it is equal to 0. Add the solution.
   Case 2: When left parenthesis it is greater than 0 just keep adding left parenthesis.
   Case 3: When right parenthesis it is greater than left parenthesis keep adding right parenthesis..
   All Cases:

 Complexity:
 Time complexity: O(n)
 Space complexity: O(1)

 Constraints:
 1 <= n <= 8

 */
public class GenerateParentheses_22 {
    List<String> forms = new LinkedList<>();
    void recursiveCall(String word, int leftParenthesis, int rightParenthesis) {

        if (leftParenthesis == 0 && rightParenthesis == 0) {
            forms.add(word);
        }

        if (leftParenthesis > 0) {
            recursiveCall(word + "(", leftParenthesis - 1, rightParenthesis) ;
        }

        if (leftParenthesis < rightParenthesis) {
            recursiveCall(word + ")", leftParenthesis, rightParenthesis - 1) ;
        }


    }

    public List<String> generateParenthesis(int n) {
        List<String> forms = new LinkedList<>();
        recursiveCall("", n, n);
        return forms;
    }

    public static void main(String[] args) {
        GenerateParentheses_22 sol = new GenerateParentheses_22();
        sol.generateParenthesis(2);
    }
}
