package com.rishiprime.tdd.feature;

public interface AccountService {
	
	public void deposit(int amount);

	public void withdraw(int amount);

	public void printStatement(); 
}
