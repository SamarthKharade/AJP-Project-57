import java.util.Scanner;

public class PreemptiveSJF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        for (int i = 0; i < n; i++) {
            processId[i] = i + 1;
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        int completed = 0, currentTime = 0, shortest = -1;
        boolean foundProcess;

        // Loop until all processes are completed
        while (completed < n) {
            foundProcess = false;
            int minRemainingTime = Integer.MAX_VALUE;

            // Find process with minimum remaining time at the current time
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] < minRemainingTime && remainingTime[i] > 0) {
                    minRemainingTime = remainingTime[i];
                    shortest = i;
                    foundProcess = true;
                }
            }

            // If no process was found, increment time
            if (!foundProcess) {
                currentTime++;
                continue;
            }

            // Execute the process with the shortest remaining time
            remainingTime[shortest]--;
            currentTime++;

            // If the process is completed
            if (remainingTime[shortest] == 0) {
                completed++;
                completionTime[shortest] = currentTime;
                turnaroundTime[shortest] = completionTime[shortest] - arrivalTime[shortest];
                waitingTime[shortest] = turnaroundTime[shortest] - burstTime[shortest];
            }
        }

        // Calculate and display the results
        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tWaiting\tTurnaround");
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];

            System.out.println(processId[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i]
                    + "\t\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }

        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

        sc.close();
    }
}
