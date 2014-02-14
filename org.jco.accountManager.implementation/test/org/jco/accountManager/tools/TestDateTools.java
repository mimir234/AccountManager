package org.jco.accountManager.tools;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;

public class TestDateTools {

	@Test
	public void testGetMonthNumberBetween() {
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		end.set(Calendar.YEAR, 2013);
		assertEquals(DateTools.getMonthNumberBetween(start.getTime(), end.getTime()), 0);
		
		start.set(Calendar.YEAR, 2012);
		assertEquals(DateTools.getMonthNumberBetween(start.getTime(), end.getTime()), 12);
		
		end.set(Calendar.MONTH, 1);
		start.set(Calendar.MONTH, 11);
		assertEquals(DateTools.getMonthNumberBetween(start.getTime(), end.getTime()), 2);
		
		start.set(Calendar.MONTH, 3);
		assertEquals(DateTools.getMonthNumberBetween(start.getTime(), end.getTime()), 10);
		
		start.set(Calendar.YEAR, 2011);
		assertEquals(DateTools.getMonthNumberBetween(start.getTime(), end.getTime()), 22);
	}
	
	@Test
	public void testGetMonthNumberBetweenShouldReturnLessOneIfStartBeforeEnd() {
		GregorianCalendar start = new GregorianCalendar();
		GregorianCalendar end = new GregorianCalendar();
		end.set(Calendar.YEAR, 2013);
		start.set(Calendar.YEAR, 2014);
		assertEquals(DateTools.getMonthNumberBetween(start.getTime(), end.getTime()), -1);

	}

}
