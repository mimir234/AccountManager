package org.jco.accountManager.model.factories;

import java.util.Date;

import org.jco.accountManager.api.model.events.IEvent;
import org.jco.accountManager.api.model.factories.IEventFactory;
import org.jco.accountManager.model.events.Event;

public class EventFactory implements IEventFactory{

	public IEvent getEvent(final String from, final String to, final float pay, final String label) {
		return new Event(from, to, pay, label, new Date());
	}

	public IEvent getEvent(final String from, final String to, final float pay, final String label, final Date date) {
		return new Event(from, to, pay, label, date);
	}
}
