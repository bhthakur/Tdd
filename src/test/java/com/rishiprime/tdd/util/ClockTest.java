package com.rishiprime.tdd.util;

import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClockTest {
	
	private Clock clock;
	
	@Before
	public void setup(){
		clock = new Clock();
	}

	@Test
	public void getTodayAsStringTest(){
		Assert.assertThat(clock.getTodayAsString(), is(IsNull.notNullValue())) ; 
	}

	@Test
	public void getTodayASddMMYYYAsStringTest(){
		Clock testableClock= new TestableClock();
		String today = testableClock.getTodayAsString();
		Assert.assertThat(today, is("06/09/2018")) ; 
	}

	private class TestableClock extends Clock{

		@Override
		public LocalDate today() {
			return LocalDate.of(2018, 9, 6);
		}
		
	}
	
}
