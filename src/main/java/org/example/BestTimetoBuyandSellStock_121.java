package org.example;

/**
 *
 121. Best Time to Buy and Sell Stock

 Description:
 You are given an array prices where prices[i] is the price of a given stock on the ith day.
 You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 Example 1:
 Input: prices = [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

 Example 2:
 Input: prices = [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transactions are done and the max profit = 0.

 Intuition:
 The key I saw here it that we would always sell if the price of actual position it is greater of the minimum of all time.

 Approach:
    Iterate using a for loop to sell whenever actual value it is greater than minimum of all time, then
    Calculate the max value using the minimum.

 functionName:

   Case 1: If actual it is greater than minimum of all time.
   Case 2: If actual it is not greater than minimum of all time.
   All Cases: Update the minimum.

 Complexity with array memorization:
 Time complexity: O(n)
 Space complexity: O(1)


 Constraints:
 1 <= prices.length <= 105
 0 <= prices[i] <= 104

 */
public class BestTimetoBuyandSellStock_121 {

    public int maxProfit(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }
        int minimumPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (minimumPrice < prices[i]) {
                maxProfit = Math.max(prices[i] - minimumPrice, maxProfit);
            }
            minimumPrice = Math.min(minimumPrice, prices[i]);

        }
        return maxProfit;
    }

    public static void main(String[] args) {

        BestTimetoBuyandSellStock_121 sol = new BestTimetoBuyandSellStock_121();
        System.out.println(sol.maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
