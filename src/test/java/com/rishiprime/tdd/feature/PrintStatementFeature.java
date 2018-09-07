package com.rishiprime.tdd.feature;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.rishiprime.tdd.util.Clock;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {
	
	private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
	
	@Mock
	private Console console;
	
	@Mock
	private Clock clock;
	private AccountService accountService;
	private StatementPrinter printer;
	private AccountRepository repo;

	@Before
	public void setup(){
		
		printer= new StatementPrinter(console);
		repo= new AccountRepository();
		accountService= new AccountServiceImpl(repo, clock, printer);
	}
	
	@Test
	public void printStatement(){
		
		given(clock.getTodayAsString()).willReturn("01/04/2018", "02/04/2018", "10/04/2018" );
		
		accountService.deposit(1000);
		accountService.withdraw(100);
		accountService.deposit(500);
		
		accountService.printStatement();
		
		InOrder inOrder= Mockito.inOrder(console);
		inOrder.verify(console).printLine(STATEMENT_HEADER);
		inOrder.verify(console).printLine("10/04/2018 | 500.00 | 1400");
		inOrder.verify(console).printLine("02/04/2018 | -100.00 | 900");
		inOrder.verify(console).printLine("01/04/2018 | 1000.00 | 1000");
	}

}
