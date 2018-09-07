package com.rishiprime.tdd.feature;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import com.rishiprime.tdd.modal.Transaction;

public class AccountRepositoryTest {
	
	private AccountRepository repo;
	
	@Before
	public void setup(){
		repo= new AccountRepository();
	}

	@Test 
	public void testSave(){
		
		Transaction transaction = getTransaction();
		repo.save(transaction);
		
		List<Transaction> allTransactions = repo.getAllTransactions();
		
		System.out.println(allTransactions.size());
		assertThat(allTransactions.size(),  is(1));
		assertThat(allTransactions.get(0), is(getTransaction()));
	}

	private Transaction getTransaction() {
		return new Transaction("10/10/2018", 1000);
	}

}
