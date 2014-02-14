package org.jco.accountManager.api.model.factories;

import java.util.Date;

import org.jco.accountManager.api.model.events.IEvent;

/**
 * 
 * @author J�r�my COLOMBET
 *
 */
public interface IEventFactory {

	public IEvent getEvent(final String from, final String to, final float pay, final String label);

	public IEvent getEvent(final String from, final String to, final float pay, final String label, final Date date);
	
}
