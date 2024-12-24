package org.example;


/**
 Intuition:
 When I saw this problem a remember I should break it into smaller pieces, in other words we should compute the corner cases
 for 1 and 2 steps and tha result should be cached somewhere so later when we start increasing the stairs we hit this solution and compute
 the next solution, it is important to save solution if we do it recursively. We can do it iteratively using dynamic programming. That
 is summing previous results.

 Approach:
 Recursively computing smaller results then caching them and reusing the results for the next computation.

 Complexity:
 Time complexity: O(n)
 Space complexity: O(n)
 */
public class ClimbingStairs_70 {

    public int climbStairs(int n) {

        int[] cacheClimbedStairs = new int[47];
        int sol = recursiveClimbing(cacheClimbedStairs, n);
        return sol;
    }

    int recursiveClimbing(int[] cache, int n) {
        if (cache[n] != 0) return cache[n];
        if (n <= 1) {
            cache[1] = 1;
            return cache[1];
        }
        if (n == 2) {
            cache[2] = 2;
            return cache[2];
        }


        int sol = recursiveClimbing(cache, n - 1) + recursiveClimbing(cache, n - 2);
        if (cache[n] == 0) cache[n] = sol;
        return sol;
    }

    public static void main(String[] args) {
        ClimbingStairs_70 sol = new ClimbingStairs_70();
        sol.climbStairs(4);
    }
}
