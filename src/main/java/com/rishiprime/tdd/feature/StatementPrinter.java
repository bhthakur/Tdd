package com.rishiprime.tdd.feature;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.rishiprime.tdd.modal.Transaction;

public class StatementPrinter {
	private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
	private Console console= new Console();
	private DecimalFormat formatter= new DecimalFormat("#.00");

	public StatementPrinter(Console console) {
		this.console= console;
	}

	public void print(List<Transaction> transactions) {
		console.printLine(STATEMENT_HEADER);
		printStatementLines(transactions);			
	}

	private void printStatementLines(List<Transaction> transactions) {
		AtomicInteger runningBalance= new AtomicInteger(0);
		transactions.stream()
		.map(transaction -> statementLine(runningBalance, transaction))
		.collect(Collectors.toCollection(LinkedList :: new))
		.descendingIterator()
		.forEachRemaining(console::printLine);
	}
	public String statementLine(AtomicInteger runningBalance, Transaction transaction){
			return transaction.getDate() 
				+ " | " +
				formatter.format(transaction.getAmount()) 
				+ " | " +
				runningBalance.addAndGet(transaction.getAmount()) ;
	}
}
