package org.jco.accountManager.model.events;

import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.api.model.exceptions.InvalidDurationException;
import org.jco.accountManager.api.model.factories.IAccountEventFactory;
import org.jco.accountManager.model.RegularEventManager;
import org.jco.accountManager.model.factories.CreditEventFactory;
import org.jco.accountManager.model.factories.DebitEventFactory;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class ReguLarEvent implements IRegularEvent {
	
	private String accountFromNumber;
	private String accountToNumber;
	private float pay;
	private String label;
	private int dayOfMonth;
	private int duration;
//	private Date creation;

	private IAccountEventFactory creditEventFactory;
	private IAccountEventFactory debitEventFactory;
	
	public ReguLarEvent(final String accountFromNumber, final String accountToNumber, final float pay, final String label, final int dayOfMonth, final int duration, final Date creation) throws InvalidDurationException{
		this.accountFromNumber = accountFromNumber;
		this.accountToNumber = accountToNumber;
		this.pay = pay;
		this.label = label;
		this.dayOfMonth = dayOfMonth;
		this.duration = duration;
//		this.creation = creation;
		if( duration < 0 && duration != IRegularEvent.DURATION_INFINITE){
			throw new InvalidDurationException("Invalid duration value '"+duration+"'");
		}
		this.creditEventFactory = new RegularEventManager(creation, 0, this.dayOfMonth, new CreditEventFactory());
		this.debitEventFactory = new RegularEventManager(creation, 0, this.dayOfMonth, new DebitEventFactory());
	}
//	
//	public int getStayingDuration(){
//		if( this.duration == DURATION_INFINITE ){
//			return DURATION_INFINITE; 
//		}
//		return duration-getPassedExecution();
//	}
//	
//	public int getPassedExecution(){
//		return DateTools.getMonthNumberBetween(this.creation, new Date());
//	}

	public IAccountEvent getFromAccountEvent() {
		return this.creditEventFactory.getEvent(this.accountToNumber, this.pay, this.label);
	}	

	public IAccountEvent getToAccountEvent() {
		return this.debitEventFactory.getEvent(this.accountFromNumber, this.pay, this.label);
	}

	public String getFromAccountNumber() {
		return this.accountFromNumber;
	}

	public String getToAccountNumber() {
		return this.accountToNumber;
	}

	public int getStayingDuration() {
		return 0;
	}

	public int getPassedDuration() {
		return 0;
	}

	public String getLabel() {
		return this.label;
	}

	public float getPay() {
		return this.pay;
	}

	public int getDayOfMonth() {
		return this.dayOfMonth;
	}

	public int getDuration() {
		return this.duration;
	}
}
