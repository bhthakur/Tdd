package com.rishiprime.tdd.demoTdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rishiprime.tdd.feature.AccountRepository;
import com.rishiprime.tdd.feature.AccountService;
import com.rishiprime.tdd.feature.AccountServiceImpl;
import com.rishiprime.tdd.feature.Console;
import com.rishiprime.tdd.feature.StatementPrinter;
import com.rishiprime.tdd.util.Clock;

@SpringBootApplication
public class DemoTddApplication {

	

	public static void main(String[] args) {
	AccountRepository repo= new AccountRepository();
	//	SpringApplication.run(DemoTddApplication.class, args);
		Clock clock= new Clock();
		Console console= new Console();
		StatementPrinter statementPrinter= new StatementPrinter(console);
		AccountService accountService= new AccountServiceImpl(repo, clock, statementPrinter);
		accountService.deposit(1000);
		accountService.withdraw(100);
		accountService.deposit(500);
		
		accountService.printStatement();
	
	}
}
