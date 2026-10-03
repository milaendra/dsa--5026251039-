package lw03.prelab;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    // PROBLEM 1
    // Playlist menggunakan List<String>
    static void problem1() {
        List<String> playlist = new ArrayList<>();
        try {
            Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(" ", 3);
                String operation = parts[0];

                // ADD <SONG>
                if (operation.equals("ADD")) {
                    String song = parts[1];
                    playlist.add(song);
                }

                // INSERT <INDEX> <SONG>
                else if (operation.equals("INSERT")) {
                    int index = Integer.parseInt(parts[1]);
                    String song = parts[2];
                    playlist.add(index, song);
                }

                // REMOVE <SONG>
                else if (operation.equals("REMOVE")) {
                    String song = parts[1];
                    // Menghapus kemunculan pertama
                    playlist.remove(song);
                }
            }
            sc.close();

            System.out.println("===== Problem 1 =====");
            System.out.println("Total songs: " + playlist.size());

            for (int i = 0; i < playlist.size(); i++) {
                System.out.println(
                    (i + 1) + ": " + playlist.get(i)
                );
            }

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat memproses playlist.txt.");
        }
    }

    // PROBLEM 2
    // Participant menggunakan Set<String>
    static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        try {
            Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));

            while (sc.hasNextLine()) {
                String name = sc.nextLine().trim();

                if (name.isEmpty()) {
                    continue;
                }

                if (!participants.add(name)) {
                    duplicateRegistrations++;
                }
            }
            sc.close();

            System.out.println("===== Problem 2 =====");
            System.out.println("Unique participants: " + participants.size());

            int number = 1;
            for (String name : participants) {
                System.out.println(
                    number + ". " + name
                );
                number++;
            }

            System.out.println("Duplicate registrations: " + duplicateRegistrations);
        } catch (Exception e) {
            System.out.println("participants.txt tidak ditemukan.");
        }
    }

    // PROBLEM 3
    // Inventory menggunakan Map<String, Integer>
    static void problem3() {
        Map<String, Integer> inventory =
            new LinkedHashMap<>();

        int failedSales = 0;

        try {
            Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                // ADD
                if (type.equals("ADD")) {
                    inventory.put(
                        product,
                        inventory.getOrDefault(product, 0)
                            + quantity
                    );
                }

                // SELL
                else if (type.equals("SELL")) {
                    if (inventory.containsKey(product) && inventory.get(product) >= quantity
                    ) {int currentStock =inventory.get(product);
                        inventory.put(product,currentStock - quantity
                        );

                    } else {failedSales++;}
                }
            }
            sc.close();

            System.out.println("===== Problem 3 =====");

            for (
                Map.Entry<String, Integer> entry : inventory.entrySet()
            ) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("Failed sales: " + failedSales);
        } catch (Exception e) {
            System.out.println("inventory.txt tidak ditemukan.");
        }
    }
}