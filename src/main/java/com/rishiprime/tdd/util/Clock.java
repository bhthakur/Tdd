package com.rishiprime.tdd.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

	public String getTodayAsString() {
		LocalDate now = today();
		return now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public LocalDate today() {
		LocalDate now = LocalDate.now();
		return now;
	}

}
