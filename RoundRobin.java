import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class Process {
    int id, arrivalTime, burstTime, remainingTime, completionTime, waitingTime, turnaroundTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter the time quantum: ");
        int timeQuantum = sc.nextInt();

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        int currentTime = 0;
        Queue<Process> queue = new LinkedList<>();

        // Load initial processes into the queue based on their arrival time
        for (Process p : processes) {
            if (p.arrivalTime <= currentTime) {
                queue.add(p);
            }
        }

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            
            // Execute the process for the time quantum or remaining time, whichever is less
            int timeToExecute = Math.min(currentProcess.remainingTime, timeQuantum);
            currentTime += timeToExecute;
            currentProcess.remainingTime -= timeToExecute;

            // Check if the process has completed
            if (currentProcess.remainingTime == 0) {
                currentProcess.completionTime = currentTime;
                currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
            } else {
                // If not completed, add it back to the queue
                queue.add(currentProcess);
            }

            // Add any new arrivals to the queue that have arrived by the current time
            for (Process p : processes) {
                if (!queue.contains(p) && p.arrivalTime <= currentTime && p.remainingTime > 0) {
                    queue.add(p);
                }
            }
        }

        // Display process details and calculate averages
        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tWaiting\tTurnaround");
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;

            System.out.println(p.id + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
                               p.completionTime + "\t\t" + p.waitingTime + "\t" + p.turnaroundTime);
        }

        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

        sc.close();
    }
}
