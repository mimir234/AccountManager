package org.jco.accountManager.model.events;

import java.util.Date;

import org.jco.accountManager.api.model.events.IEvent;
import org.jco.accountManager.tools.DateTools;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class Event implements IEvent {

	private CreditEvent creditEvent;
	private DebitEvent debitEvent;
	private String accountFromNumber;
	private String accountToNumber;

	public Event(final String accountFromNumber, final String accountToNumber, final float pay, final String label, final Date date){
		this.accountFromNumber = accountFromNumber;
		this.accountToNumber = accountToNumber;
		long uid = DateTools.generateEventUID(date); 
		this.creditEvent = new CreditEvent(this.accountToNumber, uid, pay, label, date, false);
		this.debitEvent = new DebitEvent(this.accountFromNumber, uid, pay, label, date, false);
	}

	public AccountEvent getFromAccountEvent(){
		return this.debitEvent;
	}

	public AccountEvent getToAccountEvent(){
		return this.creditEvent;
	}

	public String getFromAccountNumber() {
		return this.accountFromNumber;
	}

	public String getToAccountNumber() {
		return this.accountToNumber;
	}
}
