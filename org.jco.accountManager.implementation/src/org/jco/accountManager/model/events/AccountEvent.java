package org.jco.accountManager.model.events;

import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public abstract class AccountEvent implements IAccountEvent {

	protected float pay;
	protected String label;
	protected Date date;
	protected boolean effective;
	private long uid;
	protected String accountNumber;

	public AccountEvent(final String accountNumber, final long uid, final float pay, final String label, final Date date, final boolean effective) {
		this.pay = pay;
		this.label = label;
		this.date = date;
		this.effective = effective;
		this.uid = uid;
		this.accountNumber = accountNumber;
	}

	public long getUid() {
		return uid;
	}

	public abstract float updateBalance(float lastSold);

	public Date getDate(){
		return this.date;
	}

	public int compareTo(IAccountEvent arg0) {
		if( arg0.getDate().after(this.getDate()) ){
			return -1;
		} else if( arg0.getDate().before(this.getDate()) ) {
			return 1;
		} else {
			return 0;
		}
	}

	public String getLabel() {
		return this.label;
	}
	
	public String getAccountNumber(){
		return this.accountNumber;
	}

	public boolean isEffective() {
		return this.effective;
	}

	public float getPay() {
		return this.pay;
	}

	public void setEffective() {
		this.effective = true;		
	}
	
}
