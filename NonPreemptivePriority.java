import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class Process {
    int id, arrivalTime, burstTime, priority, completionTime, waitingTime, turnaroundTime;

    public Process(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class NonPreemptivePriority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter priority for process " + (i + 1) + " (lower value indicates higher priority): ");
            int priority = sc.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime, priority);
        }

        int currentTime = 0, completed = 0;
        boolean[] isCompleted = new boolean[n];

        while (completed < n) {
            Process highestPriorityProcess = null;

            // Find the highest priority process among those that have arrived and are not completed
            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= currentTime && !isCompleted[i]) {
                    if (highestPriorityProcess == null || processes[i].priority < highestPriorityProcess.priority) {
                        highestPriorityProcess = processes[i];
                    }
                }
            }

            // If no process is ready, increment time
            if (highestPriorityProcess == null) {
                currentTime++;
                continue;
            }

            // Execute the highest priority process
            currentTime += highestPriorityProcess.burstTime;
            highestPriorityProcess.completionTime = currentTime;
            highestPriorityProcess.turnaroundTime = highestPriorityProcess.completionTime - highestPriorityProcess.arrivalTime;
            highestPriorityProcess.waitingTime = highestPriorityProcess.turnaroundTime - highestPriorityProcess.burstTime;

            // Mark the process as completed
            isCompleted[highestPriorityProcess.id - 1] = true;
            completed++;
        }

        // Display process details and calculate averages
        System.out.println("\nProcess\tArrival\tBurst\tPriority\tCompletion\tWaiting\tTurnaround");
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;

            System.out.println(p.id + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" + p.priority + "\t\t" +
                               p.completionTime + "\t\t" + p.waitingTime + "\t" + p.turnaroundTime);
        }

        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

        sc.close();
    }
}
