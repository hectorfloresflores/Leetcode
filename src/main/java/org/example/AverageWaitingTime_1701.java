package org.example;

public class AverageWaitingTime_1701 {

    public double averageWaitingTime(int[][] customers) {

        // We need to save the previous remaining time
        int late = customers[0][0]; // First customer will not have previous late time.
        double sum = 0;

        for(int[] customer : customers) {
            int timeArrival = customer[0];
            int duration = customer[1];
            if (late < timeArrival) late = timeArrival;
            int finishCooking = late + duration;
            int waitingTime = Math.max(finishCooking, timeArrival + duration) - timeArrival;
            sum += waitingTime;
            late = finishCooking;
        }
        System.out.println(sum);

        return sum / customers.length;

    }

    public static void main(String[] args) {
        AverageWaitingTime_1701 sol = new AverageWaitingTime_1701();
        sol.averageWaitingTime(new int[][]{{5,2},{5,4,}, {10,3}, {20,1}});
    }
}
