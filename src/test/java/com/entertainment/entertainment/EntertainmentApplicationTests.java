package com.entertainment.entertainment;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class EntertainmentApplicationTests {

	
	@Test
	public void test_weekly() {
	
		LocalDate startDate = LocalDate.now().plusDays(4);
		
		LocalDate endDate = startDate.plusDays(12);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		long diff = 
				Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
		
		System.out.println(diff);
		
		long recordCount = Math.round(diff/7);
		
		System.out.println(recordCount);
	}
	
	@Test
	public void test_weekly_diff_year() {
	
		LocalDate startDate = LocalDate.of(2018, Month.JANUARY, 1);
		
		LocalDate endDate = LocalDate.of(2019, Month.JANUARY, 28);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		long diff = 
				Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
		
		System.out.println(diff);
		
		long recordCount = (long) Math.ceil(diff/7);
		System.out.println(recordCount);
	}

	@Test
	public void test_monthly_same_year() {
	
		LocalDate startDate = LocalDate.of(2018, Month.JANUARY, 1);
		
		LocalDate endDate = startDate.plusDays(35);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		long recordCount = 
				Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays()/31;
		
		System.out.println(recordCount);
	}
	
	@Test
	public void test_monthly() {
	
		LocalDate startDate = LocalDate.of(2018, Month.JANUARY, 1);
		
		LocalDate endDate = LocalDate.of(2019, Month.JANUARY, 28);
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		long difference = 
				Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
		
		System.out.println(difference);
		
		long recordCount = (long) Math.ceil(difference/31.0);
		System.out.println(recordCount);
	}

}
