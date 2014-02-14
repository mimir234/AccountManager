package org.jco.accountManager.model.events;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jco.accountManager.api.model.exceptions.InvalidDurationException;
import org.junit.Test;

public class TestRegularEvent {

//	@Test
//	public void testGetStayingDuration() throws InvalidDurationException {
//		ReguLarEvent event = new ReguLarEvent("", "", 12, "", 10, 12, new Date());
//		assertEquals(event.getStayingDuration(), 12);
//		
//		ReguLarEvent event2 = new ReguLarEvent("", "", 12, "", 10, 14, new Date());
//		assertEquals(event2.getStayingDuration(), 14);
//	}
	
	@Test(expected=InvalidDurationException.class)
	public void testInvalidDurationShouldThrowException() throws InvalidDurationException{
		new ReguLarEvent("", "", 12, "", 10, -1, new Date());
	}

//	@Test
//	public void testGetPassedExecution() throws InvalidDurationException{
//		GregorianCalendar date = new GregorianCalendar();
//		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-2);
//		ReguLarEvent event = new ReguLarEvent("", "", 12, "", 10, 12, date.getTime());
//		assertEquals(event.getPassedDuration(), 2);
//	}
	
	/*@Test
	public void testGetPendingExecutions() throws InvalidDurationException{
		GregorianCalendar date = new GregorianCalendar();
		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-2);
		
		int today = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
		
		ReguLarEvent event = new ReguLarEvent("", "", 12, "", today, 12, date.getTime());
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today-1, 0), 2);
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today-2, 0), 2);
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today-3, 0), 2);
		
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today+1, 0), 2);
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today+2, 0), 2);
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today+3, 0), 2);
		
		assertEquals(event.getPendingExecutions(event.getLastExecution(), new Date(), today, 0), 3);
		
		date.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH)-1);
		assertEquals(event.getPendingExecutions(date.getTime(), new Date(), today, 0), 3);
		assertEquals(event.getPendingExecutions(date.getTime(), new Date(), today+1, 0), 2);
	}*/
	
	@Test
	public void testCreationOftheRightNumberOfCountEvent() throws InvalidDurationException {
		GregorianCalendar date = new GregorianCalendar();
		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-2);
		
		int today = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
		
		ReguLarEvent event = new ReguLarEvent("", "", 12, "", today, 12, date.getTime());

		assertNotNull(event.getFromAccountEvent());
		assertNotNull(event.getFromAccountEvent());
		assertNotNull(event.getFromAccountEvent());
		assertNull(event.getFromAccountEvent());
		assertNull(event.getFromAccountEvent());
		assertNull(event.getFromAccountEvent());
		
		event = new ReguLarEvent("", "", 12, "", today-1, 12, date.getTime());
		
		assertNotNull(event.getFromAccountEvent());
		assertNotNull(event.getFromAccountEvent());
		assertNull(event.getFromAccountEvent());

		event = new ReguLarEvent("", "", 12, "", today+1, 12, date.getTime());
		
		assertNotNull(event.getFromAccountEvent());
		assertNotNull(event.getFromAccountEvent());
		assertNull(event.getFromAccountEvent());
	}
	
	/*@Test
	public void testGetNextEventDate() throws InvalidDurationException{
		GregorianCalendar nextExecutionCalendar = new GregorianCalendar();
		GregorianCalendar date = new GregorianCalendar();
		date.set(Calendar.MONTH, date.get(Calendar.MONTH)-2);
		
		int today = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
		
		ReguLarEvent event = new ReguLarEvent("", "", 12, "", today, 12, date.getTime());
		
		Date nextExecutionDate = event.getNextEventDate(date.getTime(), today, 0);
		nextExecutionCalendar.setTime(nextExecutionDate);
		assertEquals(date.get(Calendar.MONTH), nextExecutionCalendar.get(Calendar.MONTH));
		
		nextExecutionDate = event.getNextEventDate(date.getTime(), today-1, 0);
		nextExecutionCalendar.setTime(nextExecutionDate);
		assertEquals(date.get(Calendar.MONTH)+1, nextExecutionCalendar.get(Calendar.MONTH));
		
		nextExecutionDate = event.getNextEventDate(date.getTime(), today, 1);
		nextExecutionCalendar.setTime(nextExecutionDate);
		assertEquals(date.get(Calendar.MONTH)+1, nextExecutionCalendar.get(Calendar.MONTH));
		
		nextExecutionDate = event.getNextEventDate(date.getTime(), today-1, 1);
		nextExecutionCalendar.setTime(nextExecutionDate);
		assertEquals(date.get(Calendar.MONTH)+1, nextExecutionCalendar.get(Calendar.MONTH));
		
		date.setTime(new Date());
		nextExecutionDate = event.getNextEventDate(date.getTime(), today-1, 1);
		nextExecutionCalendar.setTime(nextExecutionDate);
		assertEquals(date.get(Calendar.MONTH)+1, nextExecutionCalendar.get(Calendar.MONTH));
	}*/
	
	
}
