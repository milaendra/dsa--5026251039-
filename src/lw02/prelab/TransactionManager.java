package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TransactionManager {

    private LinkedList<String[]> transactions;
    private LinkedList<String[]> customers;
    private Queue<String[]> queue;
    private Stack<String[]> failedTransactions;

    public TransactionManager() {
        transactions = new LinkedList<>();
        customers = new LinkedList<>();
        queue = new LinkedList<>();
        failedTransactions = new Stack<>();
    }

    // Menambahkan transaksi
    public void addTransaction(String name, String type, int amount) {

        String[] transaction = {
            name,
            type,
            String.valueOf(amount)
        };

        transactions.add(transaction);

        // Cek apakah customer sudah ada
        boolean customerExists = false;

        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                customerExists = true;
                break;
            }
        }

        // Jika belum ada, tambahkan dengan saldo awal 0
        if (!customerExists) {
            String[] customer = {
                name,
                "0"
            };

            customers.add(customer);
        }
    }

    // Memindahkan semua transaksi ke Queue
    public void moveTransactionsToQueue() {

        while (!transactions.isEmpty()) {
            queue.offer(transactions.removeFirst());
        }
    }

    // Memproses transaksi menggunakan FIFO
    public void processTransactions() {

        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            for (String[] customer : customers) {

                if (customer[0].equals(name)) {

                    int balance = Integer.parseInt(customer[1]);

                    if (type.equals("DEPOSIT")) {

                        balance += amount;
                        customer[1] = String.valueOf(balance);

                    } else if (type.equals("WITHDRAW")) {

                        if (amount > balance) {

                            // Withdrawal gagal
                            failedTransactions.push(transaction);

                        } else {

                            balance -= amount;
                            customer[1] = String.valueOf(balance);
                        }
                    }

                    break;
                }
            }
        }
    }

    // Menampilkan saldo akhir dan transaksi gagal
    public void displayResult() {

        System.out.println("=== Final Balances ===");

        for (String[] customer : customers) {

            System.out.println(
                customer[0] + " : " + customer[1]
            );
        }

        System.out.println("=== Failed Transactions ===");

        while (!failedTransactions.isEmpty()) {

            String[] transaction = failedTransactions.pop();

            System.out.println(
                transaction[0] + " "
                + transaction[1] + " "
                + transaction[2]
            );
        }
    }
}