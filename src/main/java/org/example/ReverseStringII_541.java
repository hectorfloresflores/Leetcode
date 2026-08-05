package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseStringII_541 {

    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);


        int rerverseNtimes = s.length() / k;
        // Means we can reverse it all
        if (rerverseNtimes < 1) {
            sb.reverse();
        }
        int i = 1;
        while (i <= rerverseNtimes ) {
            int startIndex = 2*k*(i-1);
            int endIndex = 2*k*i - k;
            if (startIndex > sb.length()) break;
            if (endIndex >= sb.length()) endIndex = sb.length();
            String reversed = new StringBuilder(s.subSequence(startIndex, endIndex)).reverse().toString();
            sb.replace(startIndex, endIndex, reversed);
            i++;
        }

        return sb.toString();


    }

    public static void main(String[] args) {
        ReverseStringII_541 sol = new ReverseStringII_541();
        sol.reverseStr("abcdefg", 2);
    }
}
