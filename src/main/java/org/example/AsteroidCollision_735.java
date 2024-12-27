package org.example;

import java.util.Stack;

/**
 Intuition:
 My first intuition was to iterate through the asteroids but could not solve so I saw the solution and a stack it is needed.

 Approach:
 Stack

 Complexity:
 Time complexity: O(n)
 Space complexity: O(n)
 */
public class AsteroidCollision_735 {



    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> finalAsteroids = new Stack<>();
        finalAsteroids.push(asteroids[0]);
        for (int asteroidPosition = 1; asteroidPosition < asteroids.length; ) {

            // We can break this statement in case next element dont collide with stack asteroids
            while (!finalAsteroids.empty() && asteroidPosition < asteroids.length) {
                // Case 1: stack has a negative value. Go to next since negative asteroids go to left.
                if (finalAsteroids.peek() < 0) {
                    finalAsteroids.add(asteroids[asteroidPosition]);
                    asteroidPosition++;
                    break;
                }
                int collition = asteroids[asteroidPosition] + finalAsteroids.peek();
                // Next element it is more negative so will win
                if (collition < 0) {
                    if (finalAsteroids.peek() > 0) {
                        finalAsteroids.pop();
                    } else if (finalAsteroids.peek() < 0) {
                        if (asteroids[asteroidPosition] < 0) {
                            finalAsteroids.add(asteroids[asteroidPosition]);
                        }
                        asteroidPosition++;
                        break;
                    }
                    if (finalAsteroids.empty()) {
                        finalAsteroids.add(asteroids[asteroidPosition]);
                        asteroidPosition++;
                        break;
                    }
                } else if (collition > 0) {
                    if (finalAsteroids.peek() < 0) {
                        finalAsteroids.pop();
                    } else if (finalAsteroids.peek() > 0) {
                        if (asteroids[asteroidPosition] > 0) {
                            finalAsteroids.add(asteroids[asteroidPosition]);
                        }
                        asteroidPosition++;
                        break;
                    }
                    if (finalAsteroids.empty()) {
                        finalAsteroids.add(asteroids[asteroidPosition]);
                        asteroidPosition++;
                        break;
                    }
                } else {
                    // Means both are equal
                    finalAsteroids.pop();
                    asteroidPosition++;
                    if (finalAsteroids.empty()) {
                        if (asteroidPosition < asteroids.length) {
                            finalAsteroids.add(asteroids[asteroidPosition]);
                        }
                        asteroidPosition++;
                        break;
                    }
                }
            }
        }

        int[] result = new int[finalAsteroids.size()];
        for (int i = finalAsteroids.size() - 1; i >= 0; i--) {
            result[i] = finalAsteroids.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        AsteroidCollision_735 sol = new AsteroidCollision_735();
        sol.asteroidCollision(new int[]{2,-1,-2,-2});
    }
}
