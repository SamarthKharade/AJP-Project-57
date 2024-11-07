import java.util.Scanner;

public class SJFNonPreemptive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] processId = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] completionTime = new int[n];
        boolean[] isCompleted = new boolean[n];

        for (int i = 0; i < n; i++) {
            processId[i] = i + 1;
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            isCompleted[i] = false;
        }

        int completed = 0, currentTime = 0;
        while (completed < n) {
            int shortest = -1;
            int minBurstTime = Integer.MAX_VALUE;

            // Find the process with the shortest burst time among available processes
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && !isCompleted[i] && burstTime[i] < minBurstTime) {
                    minBurstTime = burstTime[i];
                    shortest = i;
                }
            }

            // If no process is ready to execute, increment time
            if (shortest == -1) {
                currentTime++;
                continue;
            }

            // Execute the selected process
            currentTime += burstTime[shortest];
            completionTime[shortest] = currentTime;
            turnaroundTime[shortest] = completionTime[shortest] - arrivalTime[shortest];
            waitingTime[shortest] = turnaroundTime[shortest] - burstTime[shortest];
            isCompleted[shortest] = true;
            completed++;
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
