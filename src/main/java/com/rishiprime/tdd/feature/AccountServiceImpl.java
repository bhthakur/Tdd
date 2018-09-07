package com.rishiprime.tdd.feature;

import com.rishiprime.tdd.modal.Transaction;
import com.rishiprime.tdd.util.Clock;

public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepo= new AccountRepository();
	private Clock clock;
	private StatementPrinter statementPrinter;
	
	public AccountServiceImpl(AccountRepository repo, Clock clock, StatementPrinter statementPrinter) {
		this.accountRepo= repo;
		this.clock= clock;
		this.statementPrinter= statementPrinter;
	}

	public void deposit(int amount) {
		Transaction transaction= new Transaction(clock.getTodayAsString(), amount);
		accountRepo.save(transaction);
	}

	public void withdraw(int amount) {
		Transaction transaction= new Transaction(clock.getTodayAsString(), -amount);
		accountRepo.save(transaction);
	}

	public void printStatement() {
		statementPrinter.print(accountRepo.getAllTransactions());
	}

}
