package org.jco.accountManager.api.model.events;

/**
 * 
 * @author J�r�my COLOMBET
 *
 */
public interface IEvent {

	public IAccountEvent getFromAccountEvent();

	public IAccountEvent getToAccountEvent();

	public String getFromAccountNumber();

	public String getToAccountNumber();

}