package com.rishiprime.tdd.feature;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.rishiprime.tdd.modal.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterTest {

	private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
	private StatementPrinter printer;

	@Mock
	private Console console;
	

	@Before
	public void setup(){
		printer= new StatementPrinter(console);
	}

	@Test
	public void printEmptyCollection() {
		List<Transaction> transactions= Collections.EMPTY_LIST;
		printer.print(transactions);
		Mockito.verify(console).printLine(STATEMENT_HEADER);
	}
	@Test
	public void printCollection() {
		List<Transaction> transactions= Arrays.asList(
															deposit("01/04/2018", 1000),
															withdraw("02/04/2018", 100),
															deposit("10/04/2018", 500)
															);
		printer.print(transactions);
		
		InOrder inOrder= Mockito.inOrder(console);
		inOrder.verify(console).printLine(STATEMENT_HEADER);
		inOrder.verify(console).printLine("10/04/2018 | 500.00 | 1400");
		inOrder.verify(console).printLine("02/04/2018 | -100.00 | 900");
		inOrder.verify(console).printLine("01/04/2018 | 1000.00 | 1000");
	}

	private Transaction withdraw(String date, int amount) {
		return new Transaction(date, -amount);
	}
	
	private Transaction deposit(String date, int amount) {
		return new Transaction(date, amount);
	}	
}
