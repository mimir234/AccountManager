package org.jco.accountManager.api.model.factories;

import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IAccountEventFactory {
	
	public IAccountEvent getEvent(final String accountNumber, final long uid, final float pay, final String label, final Date date);
	
	public IAccountEvent getEvent(final String accountNumber, final float pay, final String label);

	public IAccountEvent getEvent(String number, long uid, float pay, String label, java.sql.Date date, boolean effective);
	
}
