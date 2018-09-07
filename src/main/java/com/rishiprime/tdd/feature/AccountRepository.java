package com.rishiprime.tdd.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rishiprime.tdd.modal.Transaction;

public class AccountRepository {
	
	private List<Transaction> list= new  ArrayList<>();

	public void save(Transaction transaction) {
		list.add(transaction);
	}

	public List<Transaction> getAllTransactions() {
			return Collections.unmodifiableList(list);
	}

}
