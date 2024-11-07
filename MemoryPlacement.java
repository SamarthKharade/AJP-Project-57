import java.util.Scanner;

public class MemoryPlacement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blocks = new int[m];
        System.out.print("Enter sizes of memory blocks: ");
        for (int i = 0; i < m; i++) {
            blocks[i] = sc.nextInt();
        }

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processes = new int[n];
        System.out.print("Enter sizes of processes: ");
        for (int i = 0; i < n; i++) {
            processes[i] = sc.nextInt();
        }

        // First Fit
        System.out.println("\nFirst Fit Allocation:");
        int[] firstFitBlocks = blocks.clone();
        for (int i = 0; i < n; i++) {
            boolean allocated = false;
            for (int j = 0; j < m; j++) {
                if (firstFitBlocks[j] >= processes[i]) {
                    firstFitBlocks[j] -= processes[i];
                    System.out.println("Process " + (i + 1) + " (Size " + processes[i] + ") -> Block " + (j + 1));
                    allocated = true;
                    break;
                }
            }
            if (!allocated) System.out.println("Process " + (i + 1) + " (Size " + processes[i] + ") -> Not Allocated");
        }

        // Best Fit
        System.out.println("\nBest Fit Allocation:");
        int[] bestFitBlocks = blocks.clone();
        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (bestFitBlocks[j] >= processes[i]) {
                    if (bestIdx == -1 || bestFitBlocks[j] < bestFitBlocks[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                bestFitBlocks[bestIdx] -= processes[i];
                System.out.println("Process " + (i + 1) + " (Size " + processes[i] + ") -> Block " + (bestIdx + 1));
            } else {
                System.out.println("Process " + (i + 1) + " (Size " + processes[i] + ") -> Not Allocated");
            }
        }

        // Worst Fit
        System.out.println("\nWorst Fit Allocation:");
        int[] worstFitBlocks = blocks.clone();
        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (worstFitBlocks[j] >= processes[i]) {
                    if (worstIdx == -1 || worstFitBlocks[j] > worstFitBlocks[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                worstFitBlocks[worstIdx] -= processes[i];
                System.out.println("Process " + (i + 1) + " (Size " + processes[i] + ") -> Block " + (worstIdx + 1));
            } else {
                System.out.println("Process " + (i + 1) + " (Size " + processes[i] + ") -> Not Allocated");
            }
        }

        sc.close();
    }
}
