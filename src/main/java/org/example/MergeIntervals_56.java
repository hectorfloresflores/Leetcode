package org.example;

import java.util.*;

/**
 *
 56. Merge Intervals

 Description:
 Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 Example 1:
 Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

 Example 2:
 Input: intervals = [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.

 Example 3:

 Intuition:
 I noticed that we can achieve linear solution if the interval were sorted by start value.

 Approach:
 Sorting the array and expand each interval.

 merge:

   Case 1: When actual interval does overlap with previous interval we must merge intervals.
   Case 2: When actual interval does not overlap with previous interval we add previous interval to the list.
   All Cases: Pass all list of interval to an array of interval.

 Complexity:
 Time complexity: O(n log n)
 Space complexity: O(n)

 Constraints:
 1 <= intervals.length <= 104
 intervals[i].length == 2
 0 <= starti <= endi <= 104
 */
public class MergeIntervals_56 {

    public int[][] merge(int[][] intervals) {
        List<List<Integer>> lists = new LinkedList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int currentRangeLeft = intervals[0][0];
        int currentRangeRight = intervals[0][1];
        int positionRange = 0;

        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];


            if (left >= currentRangeLeft && left <= currentRangeRight) {

                currentRangeRight = Math.max(right, currentRangeRight);
            } else {

                lists.add(new ArrayList<>(Arrays.asList(currentRangeLeft, currentRangeRight)));
                currentRangeLeft = left;
                currentRangeRight = right;
                positionRange++;


            }
        }
        lists.add(new ArrayList<>(Arrays.asList(currentRangeLeft, currentRangeRight)));
        int[][] ranges = new int[lists.size()][2];

        for (int i = 0; i < lists.size(); i++) {
            ranges[i] = new int[]{lists.get(i).get(0), lists.get(i).get(1)};

        }

        return ranges;
    }

    // i think this solution it is linear, but I do not understand it.
    public int[][] mergeLinear(int[][] intervals) {
        int max = 0;

        // calculate maximum of start
        for (int[] interval : intervals) {
            max = Math.max(max, interval[0]);
        }

        // generate start-end map
        int[] mp = new int[max + 1];
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            mp[start] = Math.max(end + 1, mp[start]);
        }

        // merge intervals
        int r = 0;
        int intervalStart = -1;
        int intervalEnd = -1;
        for (int i = 0; i < mp.length; i++) {
            if (mp[i] != 0) {
                if (intervalStart == -1)
                    intervalStart = i;
                intervalEnd = Math.max(mp[i] - 1, intervalEnd);
            }
            if (i == intervalEnd) {
                intervals[r++] = new int[] {intervalStart, intervalEnd};
                intervalStart = -1;
                intervalEnd = -1;
            }
        }

        // handle the last interval
        if (intervalStart != -1) {
            intervals[r++] = new int[] {intervalStart, intervalEnd};
        }

        if (r == intervals.length)
            return intervals;

        int[][] ret = new int[r][2];
        for (int i = 0; i < r; i++) {
            ret[i] = intervals[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        MergeIntervals_56 sol = new MergeIntervals_56();
        sol.merge(new int[][]{{1,3},{2,6},{8,10},{9,18}});
    }
}
