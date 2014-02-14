package org.jco.accountManager.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.factories.IAccountEventFactory;
import org.jco.accountManager.tools.DateTools;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class RegularEventManager implements IAccountEventFactory {
	
	private Date lastExecution;
	private int executionNumber = 0;
	private int dayOfMonth;
	private IAccountEventFactory factory;
	
	public RegularEventManager(Date lastExecution, int executionNumber, int dayOfMonth, IAccountEventFactory factory) {
		this.lastExecution = lastExecution;
		this.executionNumber = executionNumber;
		this.dayOfMonth = dayOfMonth;
		this.factory = factory;
	}

	/**
	 * 
	 * @param lastExecution
	 * @param now
	 * @param dayOfMonth >=1 and <=30
	 * @param executionNumber
	 * @return
	 */
	protected int getPendingExecutions(final Date lastExecution, final Date now, final int dayOfMonth, final int executionNumber){

		if( lastExecution.getTime() >= now.getTime() ){
			return 0;
		}
		
		GregorianCalendar nowCalendar = new GregorianCalendar();
		nowCalendar.setTime(now);
		GregorianCalendar lastExecutionCalendar = new GregorianCalendar();
		lastExecutionCalendar.setTime(lastExecution);
		
		int lastExecutionDayOfMonth = lastExecutionCalendar.get(Calendar.DAY_OF_MONTH);
		int actualDayOfMonth = nowCalendar.get(Calendar.DAY_OF_MONTH);
		int monthDifference = DateTools.getMonthNumberBetween(lastExecution, now);
		
		if( dayOfMonth > actualDayOfMonth ){
			monthDifference--; 
		}
		
		if( lastExecutionDayOfMonth <= dayOfMonth ){
			monthDifference++;
		}
		
		if( executionNumber > 0 ){
			monthDifference--; 
		}
		
		return monthDifference;
	}
	
	public IAccountEvent getEvent(final String accountNumber, final float pay, final String label) {
		GregorianCalendar now = new GregorianCalendar();
		if( this.getPendingExecutions(this.lastExecution, now.getTime(), dayOfMonth, executionNumber) ==  0 ){
			return null;
		} else {
			this.lastExecution = this.getNextEventDate(this.lastExecution, dayOfMonth, executionNumber);
			this.executionNumber++;
			return this.getEvent(accountNumber, DateTools.generateEventUID(this.lastExecution), pay, label, this.lastExecution);
		}
	}
	
	protected Date getNextEventDate(final Date lastExecution, final int dayOfMonth, final int executionNumber) {
		GregorianCalendar nextExecution = new GregorianCalendar();
		GregorianCalendar lastExecutionCalendar = new GregorianCalendar();
		lastExecutionCalendar.setTime(lastExecution);

		if( executionNumber == 0 ){
			if( lastExecutionCalendar.get(Calendar.DAY_OF_MONTH) > dayOfMonth ){
				nextExecution.set(Calendar.MONTH, lastExecutionCalendar.get(Calendar.MONTH) + 1);
				nextExecution.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			} else {
				nextExecution.set(Calendar.MONTH, lastExecutionCalendar.get(Calendar.MONTH));
				nextExecution.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			}			
		} else {
			nextExecution.set(Calendar.MONTH, lastExecutionCalendar.get(Calendar.MONTH) + 1);
			nextExecution.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		}
		return nextExecution.getTime();
	}

	public IAccountEvent getEvent(final String accountNumber, final long uid, final float pay, final String label, final Date date) {
		return this.factory.getEvent(accountNumber, uid, pay, label, date);
	}
	
	public Date getLastExecutionDate(){
		return this.lastExecution;
	}

	public IAccountEvent getEvent(String number, long uid, float pay, String label, java.sql.Date date, boolean effective) {
		//Nothing to do 
		return null;
	}

}
