package dk.oertel.banking;

import java.util.ArrayList;
import java.util.List;

public class BaseAccount {
	private final Bank bank;
	private final Customer customer;
	private final String number;
	private final List<Transaction> transactions = new ArrayList<>();

	public BaseAccount(SmsService smsService, Bank bank, Customer customer, String number) {
		this.bank = bank;
		this.customer = customer;
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Bank getBank() {
		return bank;
	}

	public String getNumber() {
		return number;
	}

	public long getBalance() {
		long balance = 0L;
		for (Transaction transaction : transactions) {
			if (transaction.getSource() == this)
				balance -= transaction.getAmount();
			else
				balance += transaction.getAmount();
		}
		return balance;
	}

	public void transfer(long amount, BaseAccount destination) {
		Transaction transaction = new Transaction(this, amount, destination);
		transactions.add(transaction);
		destination.transactions.add(transaction);
	}

	public void transfer(long amount, String destinationNumber) {
		var destinationAccount = bank.getAccount(destinationNumber);
		if (destinationAccount == null) {
			throw new NonExistingAccount();
		}

		transfer(amount, destinationAccount);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}
}
