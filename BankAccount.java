package com.bank;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private final String accountNumber;
    private final String holderName;
    private double balance;
    private final List<String> transactions;

    // Constructor to initialize a new account
    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.transactions.add("Account created with initial balance: $" + initialBalance);
    }

    // Method to deposit money into the account
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return false;
        }
        balance += amount;
        transactions.add("Deposited: $" + amount);
        return true;
    }

    // Method to withdraw money from the account
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return false;
        }
        balance -= amount;
        transactions.add("Withdrawn: $" + amount);
        return true;
    }

    // Method to transfer money to another account
    public boolean transfer(double amount, BankAccount targetAccount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            transactions.add("Transferred: $" + amount + " to Account " + targetAccount.getAccountNumber());
            targetAccount.transactions.add("Received: $" + amount + " from Account " + this.accountNumber);
            return true;
        }
        return false;
    }

    // Method to get the current balance of the account
    public double getBalance() {
        return balance;
    }

    // Method to get the transaction history of the account
    public List<String> getTransactions() {
        return transactions;
    }

    // Method to get the account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to get the account holder's name
    public String getHolderName() {
        return holderName;
        
    }
    
    public class BankingSystemTest {
    public static void main(String[] args) {
        // Create two bank accounts
        BankAccount account1 = new BankAccount("12345", "Alice", 1000.0);
        BankAccount account2 = new BankAccount("67890", "Bob", 500.0);

        // Deposit money into account1
        account1.deposit(200.0);

        // Withdraw money from account1
        account1.withdraw(150.0);

        // Transfer money from account1 to account2
        account1.transfer(300.0, account2);

        // Display balances
        System.out.println("Balance of account1: $" + account1.getBalance());
        System.out.println("Balance of account2: $" + account2.getBalance());

        // Display transaction histories
        System.out.println("Transaction history of account1:");
        for (String transaction : account1.getTransactions()) {
            System.out.println(transaction);
        }

        System.out.println("Transaction history of account2:");
        for (String transaction : account2.getTransactions()) {
            System.out.println(transaction);
        }
    }
}
    
}
