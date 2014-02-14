package org.jco.accountManager.api.model.events;

import java.util.Date;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IAccountEvent extends Comparable<IAccountEvent> {

	public final static int EVENT_TYPE_OUT = 0;
	public static final int EVENT_TYPE_IN = 1;

	public float updateBalance(float lastBalance);

	public Date getDate();

	public IAccountEvent getInverseEvent();

	public int getType();

	public boolean isEffective();

	public float getPay();

	public void setEffective();

	public long getUid();

	public String getLabel();

	public String getAccountNumber();

}