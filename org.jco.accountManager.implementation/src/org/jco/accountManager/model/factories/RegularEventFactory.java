package org.jco.accountManager.model.factories;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.api.model.exceptions.InvalidDurationException;
import org.jco.accountManager.api.model.factories.IRegularEventFactory;
import org.jco.accountManager.model.events.ReguLarEvent;

public class RegularEventFactory implements IRegularEventFactory {

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label) throws InvalidDurationException {
		GregorianCalendar calendar = new GregorianCalendar();
		return new ReguLarEvent(from, to, pay, label, calendar.get(Calendar.DAY_OF_MONTH), IRegularEvent.DURATION_INFINITE, calendar.getTime());
	}

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final Date date) throws InvalidDurationException {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return new ReguLarEvent(from, to, pay, label, calendar.get(Calendar.DAY_OF_MONTH), IRegularEvent.DURATION_INFINITE, date);
	}

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final int dayOfMonth, final Date creation) throws InvalidDurationException {
		return new ReguLarEvent(from, to, pay, label, dayOfMonth, IRegularEvent.DURATION_INFINITE, creation);
	}

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final int dayOfMonth, final int duration, final Date creation) throws InvalidDurationException {
		return new ReguLarEvent(from, to, pay, label, dayOfMonth, duration, creation);
	}

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final int duration) throws InvalidDurationException {
		GregorianCalendar calendar = new GregorianCalendar();
		return new ReguLarEvent(from, to, pay, label, calendar.get(Calendar.DAY_OF_MONTH), duration, calendar.getTime());
	}

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final Date date, final int duration) throws InvalidDurationException {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return new ReguLarEvent(from, to, pay, label, calendar.get(Calendar.DAY_OF_MONTH), duration, date);
	}

}
