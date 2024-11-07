import java.util.Scanner;

public class BankersAlgorithm {

    // Number of processes and resources
    private int numProcesses;
    private int numResources;
    
    private int[][] max;    // Maximum demand of each process
    private int[][] allocation; // Current allocation to each process
    private int[][] need;    // Remaining need of each process
    private int[] available; // Available resources in the system

    public BankersAlgorithm(int numProcesses, int numResources) {
        this.numProcesses = numProcesses;
        this.numResources = numResources;
        max = new int[numProcesses][numResources];
        allocation = new int[numProcesses][numResources];
        need = new int[numProcesses][numResources];
        available = new int[numResources];
    }

    // Function to input the data
    public void inputData(Scanner sc) {
        System.out.println("Enter the Max Matrix (Maximum demand of each process):");
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the Allocation Matrix (Current allocation for each process):");
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                allocation[i][j] = sc.nextInt();
                need[i][j] = max[i][j] - allocation[i][j]; // Calculate need matrix
            }
        }

        System.out.print("Enter Available Resources: ");
        for (int i = 0; i < numResources; i++) {
            available[i] = sc.nextInt();
        }
    }

    // Function to check if the system is in a safe state
    public boolean isSafe() {
        boolean[] finish = new boolean[numProcesses]; // Track finished processes
        int[] work = available.clone(); // Work array to keep track of available resources

        // Sequence to store the safe sequence of processes
        int[] safeSequence = new int[numProcesses];
        int index = 0;

        for (int count = 0; count < numProcesses; count++) {
            boolean found = false;
            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i]) {
                    boolean canProceed = true;
                    for (int j = 0; j < numResources; j++) {
                        if (need[i][j] > work[j]) {
                            canProceed = false;
                            break;
                        }
                    }
                    if (canProceed) {
                        for (int j = 0; j < numResources; j++) {
                            work[j] += allocation[i][j];
                        }
                        safeSequence[index++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("System is not in a safe state.");
                return false;
            }
        }
        System.out.print("System is in a safe state.\nSafe Sequence: ");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int numProcesses = sc.nextInt();

        System.out.print("Enter number of resources: ");
        int numResources = sc.nextInt();

        BankersAlgorithm ba = new BankersAlgorithm(numProcesses, numResources);
        ba.inputData(sc);

        ba.isSafe();

        sc.close();
    }
}
