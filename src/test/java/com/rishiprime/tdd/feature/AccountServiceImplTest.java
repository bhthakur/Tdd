package com.rishiprime.tdd.feature;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import com.rishiprime.tdd.modal.Transaction;
import com.rishiprime.tdd.util.Clock;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
	
	private AccountService accountService;
	
	@Mock
	AccountRepository accountRepo;
	@Mock
	private Clock clock;
	@Mock
	private StatementPrinter statementPrinter;

	
	@Before
	public void setup(){
		accountService= new AccountServiceImpl(accountRepo, clock, statementPrinter);
	}
	
	@Test
	public void testDeposit(){
		int amount= 1000;
		given(clock.getTodayAsString()).willReturn("10/09/2018");
		
		accountService.deposit(amount);
		
		verify(accountRepo).save(getTransaction(amount));

	}

	@Test
	public void testWithDraw(){
		int amount= 1000;
		given(clock.getTodayAsString()).willReturn("10/09/2018");
		
		accountService.withdraw(amount);
		
		verify(accountRepo).save(getTransaction(-amount));

	}
	
	@Test
	public void printStatementTest() {
		List<Transaction> transactions = Arrays.asList(getTransaction(100));
		given(accountRepo.getAllTransactions()).willReturn(transactions);
		
		accountService.printStatement();
		
		verify(statementPrinter).print(transactions);
	}
	
	
	private Transaction getTransaction(int amount) {
		return new Transaction("10/09/2018", amount);
	}

}
