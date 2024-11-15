package com.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
	private double balance;
	private List<Transaction> transactions;

	public BankAccount() {
		this.balance = 0.0;
		this.transactions = new ArrayList<Transaction>();
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Le montant du dépôt doit être positif");
		}
		balance += amount;
		transactions.add(new Transaction(new Date(), amount, balance));
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Le montant du retrait doit être positif");
		}
		if (amount > balance) {
			throw new IllegalArgumentException("Fonds insuffisants");
		}
		balance -= amount;
		transactions.add(new Transaction(new Date(), -amount, balance));
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public double getBalance() {
		return balance;
	}
}
