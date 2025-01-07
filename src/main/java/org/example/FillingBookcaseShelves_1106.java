package org.example;

import java.util.HashMap;

public class FillingBookcaseShelves_1106 {

    HashMap<Integer, Integer> cache = new HashMap<>();
    int sWidth;
    public int minHeightShelves(int[][] books, int shelfWidth) {
        sWidth = shelfWidth;
        return recursiveShelves(books, 0);
    }

    // Return the Max high of the book on any specific shelve
    int recursiveShelves(int[][] books, int shelvePosition) {

        if (shelvePosition == books.length) {
            return 0;
        }

        if (cache.containsKey(shelvePosition)) {
            return cache.get(shelvePosition);
        }

        int actualWith = this.sWidth;
        int maxHeight = 0;
        cache.put(shelvePosition, Integer.MAX_VALUE);
        for (int i = shelvePosition; i < books.length; i++) {
            int width = books[i][0];
            int height = books[i][1];
            if (actualWith < width) {
                break;
            }

            actualWith -= width;
            maxHeight = Math.max(maxHeight, height);
            cache.put(shelvePosition, Math.min(cache.get(shelvePosition), recursiveShelves(books, i + 1) + maxHeight));
        }

        return cache.get(shelvePosition);
    }
}
