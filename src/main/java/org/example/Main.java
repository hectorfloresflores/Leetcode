package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome to Leetcode problems ans answers :)");
        TwoSum_1 sol = new TwoSum_1(); sol.twoSum(new int[]{3,2,4}, 6);

        Queue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        queue.add(6);
        queue.add(1);
        queue.add(3);
        queue.add(9);
        queue.add(7);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }
}