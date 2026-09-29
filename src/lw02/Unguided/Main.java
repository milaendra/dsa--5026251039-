package lw02.Unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        // Membaca order dari file
        while (scanner.hasNext()) {

            String[] order = new String[4];

            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();

            orders.add(order);
        }

        scanner.close();

        // Stock makanan
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        // Stock minuman
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        // Memindahkan semua order ke Queue
        while (!orders.isEmpty()) {
            queue.offer(orders.removeFirst());
        }

        // Memproses order
        while (!queue.isEmpty()) {

            String[] order = queue.poll();

            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            // Cek stock makanan
            if (!food.equals("-")) {
                for (String[] data : foods) {
                    if (data[0].equals(food)) {
                        int stock = Integer.parseInt(data[1]);
                        if (stock <= 0) {
                            foodAvailable = false;
                        }
                        break;
                    }
                }
            }

            // Cek stock minuman
            if (!drink.equals("-")) {
                for (String[] data : drinks) {
                    if (data[0].equals(drink)) {
                        int stock = Integer.parseInt(data[1]);
                        if (stock <= 0) {
                            drinkAvailable = false;
                        }
                        break;
                    }
                }
            }

            // Jika semua item tersedia
            if (foodAvailable && drinkAvailable) {
                // Kurangi stock makanan
                if (!food.equals("-")) {
                    for (String[] data : foods) {
                        if (data[0].equals(food)) {
                            int stock = Integer.parseInt(data[1]);
                            stock--;
                            data[1] = String.valueOf(stock);
                            break;
                        }
                    }
                }

                // Kurangi stock minuman
                if (!drink.equals("-")) {
                    for (String[] data : drinks) {
                        if (data[0].equals(drink)) {
                            int stock = Integer.parseInt(data[1]);
                            stock--;
                            data[1] = String.valueOf(stock);
                            break;
                        }
                    }
                }
                successfulOrders.add(order);
            } else {
                failed.push(order);
            }
        } //

        // Menampilkan order berhasil
        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        // Menampilkan stock makanan
        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        // Menampilkan stock minuman
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(
                drink[0] + " : " + drink[1]);
        }

        // Menampilkan order gagal dari Stack
        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}