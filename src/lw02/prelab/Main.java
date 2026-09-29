package lw02.prelab;

import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TransactionManager manager = new TransactionManager();
        InputStream input = Main.class.getResourceAsStream("transactions.txt");

        if (input == null) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {

            String name = scanner.next();
            String type = scanner.next();
            int amount = scanner.nextInt();
            manager.addTransaction(name, type, amount);
        }

        scanner.close();

        manager.moveTransactionsToQueue();
        manager.processTransactions();
        manager.displayResult();
    }
}