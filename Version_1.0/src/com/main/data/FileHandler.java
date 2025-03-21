package com.main.data;

import com.main.java.Entity.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public List<Transaction> loadTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String description = data[0];
                    double amount = Double.parseDouble(data[1]);
                    String category = data[2];
                    transactions.add(new Transaction(description, amount, category));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void saveTransactions(List<Transaction> transactions, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction transaction : transactions) {
                bw.write(transaction.getDescription() + "," + transaction.getAmount() + "," + transaction.getCategory());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
