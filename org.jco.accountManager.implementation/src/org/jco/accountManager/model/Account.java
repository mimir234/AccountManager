package org.jco.accountManager.model;

import java.util.HashMap;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class Account implements IAccount {
	
	private String name;
	private String number;
	private int type;
	private float balance;
	private float theoricalBalance;
	private HashMap<Long, IAccountEvent> events = new HashMap<Long, IAccountEvent>();

	public Account(final String name, final String number, final int type, final float balance, final float theoricalBalance){
		this.name = name;
		this.number = number;
		this.type = type;
		this.balance = balance;	
		this.theoricalBalance = theoricalBalance;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public int getType() {
		return type;
	}

	public float getBalance() {
		return balance;
	}

	public float getTheoricalBalance() {
		return theoricalBalance;
	}

	public void addEvent(final IAccountEvent accountEvent, boolean modifyBalance) {
		this.events.put( accountEvent.getUid(), accountEvent );
		if( modifyBalance ){
			this.theoricalBalance = accountEvent.updateBalance(this.theoricalBalance);
		}
	}

	public IAccountEvent makeEventEffective(final long uid){
		IAccountEvent event = this.events.get(uid);
		if( event != null && !event.isEffective() ){
			event.setEffective();
			this.balance = event.updateBalance(this.balance);
		}
		return event;
	}

	public HashMap<Long, IAccountEvent> getEvents() {
		return this.events;
	}

	public IAccountEvent deleteEvent(long uid) {
		IAccountEvent event = this.events.get(uid);
		if( event != null ){
			this.theoricalBalance = event.getInverseEvent().updateBalance(this.theoricalBalance);
			if( event.isEffective() ){
				this.balance = event.getInverseEvent().updateBalance(this.balance);
			}
		}
		this.events.remove(event);
		return event;
	}
}
