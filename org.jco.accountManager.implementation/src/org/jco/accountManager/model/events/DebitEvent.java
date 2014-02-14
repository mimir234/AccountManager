package org.jco.accountManager.model.events;
import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class DebitEvent extends AccountEvent {

	public DebitEvent(final String accountNumber, final long uid, final float pay, final String label, final Date date, final boolean effective) {
		super(accountNumber, uid, pay, label, date, effective);
	}

	public float updateBalance(float balance) {
		return balance - this.pay;
	}

	public int getType() {
		return IAccountEvent.EVENT_TYPE_OUT;
	}

	public IAccountEvent getInverseEvent() {
		return new CreditEvent(this.accountNumber, -1, pay, label, date,effective);
	}

}
