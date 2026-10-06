package lw03.Unguided;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> registered = new LinkedHashSet<>();
        Set<String> checkedIn = new LinkedHashSet<>();

        List<String> results = new ArrayList<>();
        int rejected = 0;

        try {
            Scanner sc = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
            while (sc.hasNextLine()) {
                String id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    continue;
                }
                registered.add(id);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("registrations tidak ditemukan.");
            return;
        }

        try {
            Scanner sc = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
            while (sc.hasNextLine()) {
                String id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    continue;
                }

                if (!registered.contains(id)) {
                    results.add(id + ": Rejected (not registered)");
                    rejected++;
                } else if (checkedIn.contains(id)) {
                    results.add(id + ": Rejected (already checked in)");
                    rejected++;
                } else {
                    checkedIn.add(id);
                    results.add(id + ": Checked in");
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("checkins tidak ditemukan.");
            return;
        }

        // Tampilkan hasil
        System.out.println("===== Event Check-In Results =====");
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}