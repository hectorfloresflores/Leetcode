package org.example;

import java.util.Arrays;

public class AssignCookies_455 {

    public int findContentChildren(int[] g, int[] s) {

        if (s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int maxKidWithoutHunger = 0;
        int i = g.length - 1;
        int j = s.length - 1;
        while (i >= 0 && j >= 0) {
            // If the kid does not have hungry anymore.
            if (g[i] <= s[j]) {
                maxKidWithoutHunger++;
                i--;
                j--;
            } else {
                i--;
            }
        }
        return maxKidWithoutHunger;
    }

    public static void main(String[] args) {
        AssignCookies_455 sol = new AssignCookies_455();
        sol.findContentChildren(new int[]{1,2,3}, new int[]{3});
    }
}
