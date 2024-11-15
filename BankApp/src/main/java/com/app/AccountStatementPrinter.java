package com.app;

import java.text.SimpleDateFormat;

public class AccountStatementPrinter {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	public void printStatement(BankAccount account) {
		System.out.println("Date         | Amount  | Balance");
		System.out.println("--------------------------------");
		for (Transaction transaction : account.getTransactions()) {
			String date = DATE_FORMAT.format(transaction.getDate());
			String amount = String.format("%.2f", transaction.getAmount());
			String balance = String.format("%.2f", transaction.getBalance());
			System.out.printf("%s | %7s | %7s\n", date, amount, balance);
		}
	}
}
