package lw04.Unguided;
import java.util.Scanner;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {

        InputStream input = Main.class.getResourceAsStream("parcels.txt");

        if (input == null) {
            System.out.println("File parcels.txt tidak ditemukan.");
            return;
        }

        Scanner scanner = new Scanner(input);

        // Membaca jumlah parcel
        int n = scanner.nextInt();

        // Membaca berat parcel
        int[] parcels = new int[n];

        for (int i = 0; i < n; i++) {
            parcels[i] = scanner.nextInt();
        }

        // Membaca jumlah target
        int m = scanner.nextInt();

        // Membaca target pencarian
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        // Menyimpan kondisi array setelah 2 pass
        int[] afterTwoPasses = new int[n];

        // Bubble Sort secara descending
        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - 1 - i; j++) {

                if (parcels[j] < parcels[j + 1]) {

                    int temp = parcels[j];
                    parcels[j] = parcels[j + 1];
                    parcels[j + 1] = temp;
                }
            }

            // Menyalin array setelah 2 pass
            if (i == 1) {

                for (int j = 0; j < n; j++) {
                    afterTwoPasses[j] = parcels[j];
                }
            }
        }

        // Menampilkan array setelah 2 pass
        System.out.println("==== After 2 Passes ====");

        for (int i = 0; i < n; i++) {
            System.out.print(afterTwoPasses[i] + " ");
        }

        System.out.println();

        // Menampilkan array yang sudah terurut
        System.out.println("\n==== Fully Sorted ====");

        for (int i = 0; i < n; i++) {
            System.out.print(parcels[i] + " ");
        }

        System.out.println();

        // Binary Search untuk setiap target
        System.out.println("\n==== Search Results ====");

        for (int i = 0; i < m; i++) {

            int target = targets[i];

            int low = 0;
            int high = n - 1;
            int steps = 0;
            int index = -1;

            while (low <= high) {

                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == target) {

                    index = mid;
                    break;

                } else if (target < parcels[mid]) {

                    low = mid + 1;

                } else {

                    high = mid - 1;
                }
            }

            if (index != -1) {

                System.out.println(
                    target + ": FOUND at index " + index
                    + " (steps: " + steps + ")"
                );

            } else {

                System.out.println(
                    target + ": NOT FOUND (steps: " + steps + ")"
                );
            }
        }
    }
}