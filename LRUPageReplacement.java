import java.util.ArrayList;
import java.util.Scanner;

public class LRUPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        int n = sc.nextInt();

        System.out.print("Enter the size of frames: ");
        int m = sc.nextInt();

        ArrayList<Integer> pages = new ArrayList<>();
        System.out.print("Enter the page sequence: ");
        for (int i = 0; i < n; i++) {
            pages.add(sc.nextInt());
        }

        ArrayList<Integer> frames = new ArrayList<>(m); // Frame memory
        int pageFaults = 0;

        for (int page : pages) {
            // If the page is already in frames, update its position
            if (frames.contains(page)) {
                frames.remove((Integer) page); // Remove the page to update position
                frames.add(page);              // Add it to the end (most recently used)
            } else {
                // Page is not in frames, so a page fault occurs
                pageFaults++;

                if (frames.size() == m) {
                    frames.remove(0); // Remove the least recently used page
                }
                frames.add(page); // Add the new page as the most recently used
            }

            System.out.println("Current frames: " + frames);
        }

        System.out.println("Total Page Faults: " + pageFaults);
        sc.close();
    }
}
