import java.util.ArrayList;
import java.util.Scanner;

public class OptimalPageReplacement {

    // Method to predict which page to replace
    private static int predict(int[] pages, ArrayList<Integer> frames, int index) {
        int farthest = index;
        int frameToReplace = -1;

        // For each page in the frames, find the farthest future usage
        for (int i = 0; i < frames.size(); i++) {
            int j;
            for (j = index; j < pages.length; j++) {
                if (frames.get(i) == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        frameToReplace = i;
                    }
                    break;
                }
            }
            // If page not found in future, it's the best candidate to replace
            if (j == pages.length) {
                return i;
            }
        }
        return (frameToReplace == -1) ? 0 : frameToReplace;
    }

    // Method to perform Optimal Page Replacement
    public static int optimalPageReplacement(int[] pages, int capacity) {
        ArrayList<Integer> frames = new ArrayList<>(capacity);
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            int currentPage = pages[i];

            // If the page is not in the frames, a page fault occurs
            if (!frames.contains(currentPage)) {
                pageFaults++;

                // If there's space in frames, add the page
                if (frames.size() < capacity) {
                    frames.add(currentPage);
                } else {
                    // Otherwise, find the optimal page to replace
                    int frameToReplace = predict(pages, frames, i + 1);
                    frames.set(frameToReplace, currentPage);
                }
            }
            System.out.println("Current frames: " + frames);
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.print("Enter the page sequence: ");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter the frame capacity: ");
        int capacity = sc.nextInt();

        int pageFaults = optimalPageReplacement(pages, capacity);
        System.out.println("Total Page Faults: " + pageFaults);

        sc.close();
    }
}
