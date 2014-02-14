package org.jco.accountManager.api.model;

import java.util.HashMap;

import org.jco.accountManager.api.model.events.IAccountEvent;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IAccount {
		
	public static final int COUNT_TYPE_SAVING = 1; 
	public static final int COUNT_TYPE_CHECK = 2; 

	public abstract String getName();

	public abstract String getNumber();

	public abstract int getType();

	public abstract float getBalance();

	public abstract float getTheoricalBalance();

	public abstract void addEvent(final IAccountEvent accountEvent, boolean modifyBalance);
	
	public abstract IAccountEvent deleteEvent(final long uid);

	public abstract IAccountEvent makeEventEffective(final long uid);
	
	public HashMap<Long, IAccountEvent> getEvents();

}