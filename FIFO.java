import java.util.ArrayList;
import java.util.Scanner;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        System.out.print("Enter the size of frames: ");
        int m = sc.nextInt();

        ArrayList<Integer> elements = new ArrayList<>();
        ArrayList<Integer> frames = new ArrayList<>(m);

        System.out.print("Enter the elements: ");
        for (int i = 0; i < n; i++) {
            int element = sc.nextInt();
            elements.add(element);
        }

        int pageFaults = 0;
        int indexToReplace = 0;

        for (int i = 0; i < elements.size(); i++) {
            int currentPage = elements.get(i);

            // Check if the page is already in the frame
            if (!frames.contains(currentPage)) {
                pageFaults++;  // Page fault occurs

                // If there's still space in the frames, just add the page
                if (frames.size() < m) {
                    frames.add(currentPage);
                } else {
                    // Replace the page in FIFO order
                    frames.set(indexToReplace, currentPage);
                    indexToReplace = (indexToReplace + 1) % m;
                }
            }

            System.out.println("Current frames: " + frames);
        }

        System.out.println("Total Page Faults: " + pageFaults);
        sc.close();
    }
}
