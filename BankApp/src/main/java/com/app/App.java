package com.app;

public class App {
	public static void main(String[] args) {
		BankAccount account = new BankAccount();
		AccountStatementPrinter statementPrinter = new AccountStatementPrinter();

		account.deposit(1000.0);
		account.withdraw(200.0);
		account.deposit(500.0);

		statementPrinter.printStatement(account);
	}
}
