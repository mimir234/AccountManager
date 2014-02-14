package org.jco.accountManager.api.model.factories;

import java.util.Date;

import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.api.model.exceptions.InvalidDurationException;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IRegularEventFactory{

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label) throws InvalidDurationException;

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final Date date) throws InvalidDurationException;
	
	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final int dayOfMonth, final Date creation) throws InvalidDurationException;

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final int dayOfMonth, final int duration, final Date creation) throws InvalidDurationException;

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final int duration) throws InvalidDurationException;

	public IRegularEvent getEvent(final String from, final String to, final float pay, final String label, final Date date, final int duration) throws InvalidDurationException;
	
}
