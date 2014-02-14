package org.jco.accountManager.model.factories;

import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.factories.IAccountEventFactory;
import org.jco.accountManager.model.events.DebitEvent;
import org.jco.accountManager.tools.DateTools;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class DebitEventFactory implements IAccountEventFactory {

	public IAccountEvent getEvent(final String accountNumber, final long uid, final float pay, final String label, final Date date) {
		return new DebitEvent(accountNumber, uid, pay, label, date, false);
	}

	public IAccountEvent getEvent(final String accountNumber, final float pay, final String label) {
		Date date = new Date();
		return new DebitEvent(accountNumber, DateTools.generateEventUID(date), pay, label, date, false);
	}

	public IAccountEvent getEvent(String number, long uid, float pay, String label, java.sql.Date date, boolean effective) {
		return new DebitEvent(number, uid, pay, label, date, effective);
	}
}
