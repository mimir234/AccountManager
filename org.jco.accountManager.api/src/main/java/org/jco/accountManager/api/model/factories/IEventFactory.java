package org.jco.accountManager.api.model.factories;

import java.util.Date;

import org.jco.accountManager.api.model.events.IEvent;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IEventFactory {

	public IEvent getEvent(final String from, final String to, final float pay, final String label);

	public IEvent getEvent(final String from, final String to, final float pay, final String label, final Date date);
	
}
