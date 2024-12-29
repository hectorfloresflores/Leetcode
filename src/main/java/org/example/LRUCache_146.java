package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;

/**
 Problem Statement:
 Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 Implement the LRUCache class:
 LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 int get(int key) Return the value of the key if the key exists, otherwise return -1.
 void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 The functions get and put must each run in O(1) average time complexity.

 Example 1:

 Input
 ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 Output
 [null, null, null, 1, null, -1, null, -1, 3, 4]

 Explanation
 LRUCache lRUCache = new LRUCache(2);
 lRUCache.put(1, 1); // cache is {1=1}
 lRUCache.put(2, 2); // cache is {1=1, 2=2}
 lRUCache.get(1);    // return 1
 lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 lRUCache.get(2);    // returns -1 (not found)
 lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 lRUCache.get(1);    // return -1 (not found)
 lRUCache.get(3);    // return 3
 lRUCache.get(4);    // return 4

 Constraints:
 1 <= capacity <= 3000
 0 <= key <= 104
 0 <= value <= 105
 At most 2 * 105 calls will be made to get and put.

 Intuition:
 My first intuition was to implement a timestamp on each operation and have also a global time limit so when we reach
 the limit we stop caching elements. Since at that time a did not know how other examples of LRU I found some flaws
 on my solution. The first one was that I did not remove elements once capacity limit reached, this is key since cache
 works like RAM, it is a very finite resource.
 After investigating I found that we can use a hashmap containing key for the key and values for the actual Node. While coding
 the solution I found that I needed to store the key and value on the node since it is simpler when removing elements. Also
 on the following video (https://www.youtube.com/watch?v=Hi5obC_CwIU) that I saw  mentioned that I needed to have track of the previous
 and next Node, basically a double linkedlist. I completely understood why when I started to code it, because when we remove an element
 depending on if it has previous and next we need to connect them each other. The key points I noticed when trying and failing my solution
 was that 'get' and 'put' operations needed to refresh cache not only 'put'. So let discuss each function:

    get: This function has multiple cases.
        Case 1: If the key does not exist on the hashmap just return -1.
        Case 2: If the key exist in the hashmap I needed to remove it first and then add it again. This is extremely
                important since deleting it will make that cache refreshes once we add it back.
        Case 3: If the key exist in the hashmap and hashmap only has one <key,value> pair. This is a corner case that I
                found since for only one value it is not needed to remove it and add it because for obvious reasons the least
                frequent element and first frequent element it is the same so the order will not change, so just return the value
                and do nothing.
        IMPORTANT: I faced an issue where getting the same element caused null pointer exception. This was because when I removed
                    the node I did not clear next and previous and that conflicted with least and first frequent elements. So I just created
                    new Node with the <key,value> from the Node that I was going to delete. I found this corner case by debugging a large
                    input test case from Leetcode. In this same file it is the code that read easily from input, got to the main function
                    for reference. I used ObjectMapper.
    removeNodeIfExists: This is probably the most important function, since need to handle all the scenarios for deleting a Node.
                        All cases for obvious reasons happen only and only if the key exists. That why I called 'removeNodeIfExists'
        Case 1: If actualNode has previousNode and nextNode, go to previous previousNode and assign its nextNode to the previousNode of the nextNode of actualNode.
                Finally, Since this case we are not removing least or first frequent element we don't need to update mostRecentKey and leastRecentKey.
                example before  [1 <-> 2 <-> 3]  example after -> [1 <-> 3]
        Case 2: If actualNode does not have previousNode but has nextNode we just need to assign NULL to previousNode of actualNode.
                And update leastRecentKey with nextNode key.
                example delete 1 before  [1 <-> 2 <-> 3]  example delete 1 after -> [2 <-> 3]
        Case 3: If actualNode does have previousNode but does not have nextNode we just need to assign NULL to nextNode of previousNode.
                And update mostRecentKey with previousNode key.
                example delete 3 before  [1 <-> 2 <-> 3]  example delete 3 after -> [1 <-> 2]
        For all cases: Delete key from hashmap and decrement capacity by one.
        IMPORTANT: I found a corner case where if we try to use this function and delete a Node if there is only one. We need to
                    assign NULL to both mostRecentKey and leastRecentKey.

    addNode: This function it is different from 'put' function since we need it to reuse it in 'get' function as well.
             I separated this function to reuse code.
        Case 1: We will always append the new Node at the end of the double Linkedlist this is because the new Node should be
               the most recent one. So we need to add it and update mostRecentKey and increment currentCapacity by one.
        Case 2: This case it is the most important one by far. When currentCapacity exceeds maxCapacity just call removeNodeIfExists(leastRecentKey);
                Thanks god I decoupled removeNodeIfExists, so I can call it from other parts of code. :)

    put: This function it is similar to get since we need to remove element if exist so when we add it cache it is refreshed.
        Case 1: If it is the first Node on the hashmap add it manually and assign the Node's key to mostRecentKey and leastRecentKey.
        Case 2: Call the addNode since it is not the very first element.


 Approach:
 Use Hashmap and double LinkedList.

 Complexity:
 Time complexity: O(1)
 Space complexity: O(n)
 */
public class LRUCache_146 {
    class Node {
        int key;
        int value;
        Node previous;
        Node next;
        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }
    int currentCapacity;
    int maxCapacity;
    // The most recent Node will be the last Node from the LinkedList
    Integer mostRecentKey;
    // The least recent Node will be the first Node from the LinkedList
    Integer leastRecentKey;
    HashMap<Integer, Node> lru;

    public LRUCache_146 (int capacity) {
        currentCapacity = 0;
        maxCapacity = capacity;
        lru = new HashMap<>(capacity);
    }

    // Return the value of the key if the key exists, otherwise return -1.
    public int get(int key) {

        if (lru.containsKey(key)) {
            if (currentCapacity == 1) return lru.get(key).value;
            Node newNode = new Node(key, lru.get(key).value);
            removeNodeIfExists(key);
            addNode(newNode);
            return lru.get(key).value;
        }
        return -1;
    }


    // Return true if node exists and if was removed
    void removeNodeIfExists(int key) {
        if (lru.containsKey(key)) {
            Node previous = lru.get(key).previous;
            Node next = lru.get(key).next;
            // If node has next and previous we need to connect them before remove actual
            if (previous != null && next != null) {
                previous.next = next;
                next.previous = previous;
            } else if (previous == null && next != null) { // If key it is the least used
                leastRecentKey = next.key;
                next.previous = null;
            } else if (previous != null && next == null) {
                mostRecentKey = previous.key;
                previous.next = null;
            }
            lru.remove(key);
            currentCapacity--;
            if (currentCapacity == 0) {
                leastRecentKey = null;
                mostRecentKey = null;
            }
        }
    }

    void addNode(Node newNode) {
        lru.get(mostRecentKey).next = newNode;
        newNode.previous = lru.get(mostRecentKey);
        mostRecentKey = newNode.key;
        lru.put(newNode.key, newNode);
        currentCapacity++;
        // Case 3: If it is not the first element and capacity exceed. So we need to remove the least recent Node and update recent and less recent.
        if (currentCapacity > maxCapacity) {
            removeNodeIfExists(leastRecentKey);
        }
    }

    //
    public void put(int key, int value) {

        removeNodeIfExists(key);
        Node node = new Node(key, value);
        if (mostRecentKey == null || leastRecentKey == null) {
            mostRecentKey = key;
            leastRecentKey = key;
            lru.put(key, node);
            currentCapacity++;
        } else {
            addNode(node);
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        String input = """
                ["LRUCache","put","put","get","put","get","put","get","get","get"]
                [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
                ["LRUCache","put","get"]
                [[1],[2,1],[2]]
                ["LRUCache","put","put","get","put","get","put","get","get","get"]
                [[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
                ["LRUCache","put","put","get","put","put","get"]
                [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
                ["LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
                [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
                """;

        String expected = """
                [null,null,null,1,null,-1,null,-1,3,4]
                [null,null,1]
                [null,null,null,0,null,-1,null,-1,3,4]
                [null,null,null,2,null,null,-1]
                []
                """;

        Object[] lines = Arrays.stream(input.split("\n")).toArray();
        ObjectMapper inputObjectMapper = new ObjectMapper();



        String[] expectedString = expected.split("\n");
        int numberColumns = inputObjectMapper.readValue(expectedString[0],Integer[].class).length;

        Integer[][] expectedOutput = new Integer[lines.length / 2][numberColumns];
        for (int i = 0; i < expectedOutput.length ; i++) {
            expectedOutput[i] = inputObjectMapper.readValue(expectedString[i], Integer[].class);
        }


        for (int i = 0; i < lines.length; ) {
            String[] actions = inputObjectMapper.readValue(lines[i].toString(), String[].class);
            Integer[][] in = inputObjectMapper.readValue(lines[i + 1].toString(), Integer[][].class);
            // First value always it is object creation

            int capacity = in[0][0];
            LRUCache_146 lru = new LRUCache_146(capacity);
            for (int action = 1; action < actions.length; action++) {
                String putOtGet =  actions[action];
                if (putOtGet.equals("put")) {
                    lru.put(in[action][0], in[action][1]);
                    assert expectedOutput[i][action] == null;
                    System.out.println("null");
                } else {
                    System.out.println(lru.get(in[action][0]));
                    assert expectedOutput[i][action] == lru.get(in[action][0]);
                }

            }
            i+=2;
        }
    }

}
