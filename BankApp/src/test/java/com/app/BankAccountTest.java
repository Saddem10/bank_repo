package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BankAccountTest {

	private BankAccount account;

	@BeforeEach
	void setUp() {
		account = new BankAccount();
	}

	@Test
	void depositShouldIncreaseBalance() {
		account.deposit(1000.0);
		assertEquals(1000.0, account.getBalance(), "Le solde après le dépôt devrait être de 1000.0");

		List<Transaction> transactions = account.getTransactions();
		assertEquals(1, transactions.size(), "Il devrait y avoir 1 transaction après le dépôt");
		assertEquals(1000.0, transactions.get(0).getAmount(), "Le montant de la transaction devrait être de 1000.0");
	}

	@Test
	void withdrawShouldDecreaseBalance() {
		account.deposit(1000.0);
		account.withdraw(200.0);

		assertEquals(800.0, account.getBalance(), "Le solde après le retrait devrait être de 800.0");

		List<Transaction> transactions = account.getTransactions();
		assertEquals(2, transactions.size(), "Il devrait y avoir 2 transactions après le retrait");
		assertEquals(-200.0, transactions.get(1).getAmount(), "Le montant de la transaction devrait être de -200.0");
	}

	@Test
	void depositNegativeAmountShouldThrowException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
		assertEquals("Le montant du dépôt doit être positif", exception.getMessage());
	}

	@Test
	void withdrawMoreThanBalanceShouldThrowException() {
		account.deposit(100.0);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(200.0));
		assertEquals("Fonds insuffisants", exception.getMessage());
	}

	@Test
	void shouldRecordAllTransactionsInOrder() {
		account.deposit(1000.0);
		account.withdraw(300.0);
		account.deposit(500.0);

		List<Transaction> transactions = account.getTransactions();
		assertEquals(3, transactions.size(), "Il devrait y avoir 3 transactions enregistrées");

		assertEquals(1000.0, transactions.get(0).getAmount(), "Le premier dépôt devrait être de 1000.0");
		assertEquals(-300.0, transactions.get(1).getAmount(), "Le retrait devrait être de -300.0");
		assertEquals(500.0, transactions.get(2).getAmount(), "Le second dépôt devrait être de 500.0");
	}

}
