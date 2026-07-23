package org.example.practice;

import java.util.*;

public class SearchAlgorithms {

    public static void main(String[] args) {
        List<Integer> myCollectionList = List.of(2,4,6,8,10);


        List<Integer> list = Arrays.asList(1,2,3);
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        List<String> colors = Arrays.asList("Red", "Green", "Blue");
        //List<Integer> list=new ArrayList<>(Arrays.asList(1, 2, 3));

        List<Integer> numbers = java.util.stream.IntStream.rangeClosed(1, 5)
                .boxed()
                .toList();

        int key = 2;

        HashMap<Integer, Integer> myMap = new HashMap<>();

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap.comparator();
        minHeap.add(5);
        minHeap.add(7);
        minHeap.add(9);
        minHeap.add(1);

        System.out.println("Heap: " + minHeap);
        System.out.println("Smallest element: " + minHeap.poll());
        System.out.println("Heap after poll: " + minHeap);

        //System.out.println(Collections.binarySearch(myCollectionList, key));
    }
}
