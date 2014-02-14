package org.jco.accountManager.api.model.events;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IRegularEvent extends IEvent {

	public static final int DURATION_INFINITE = -1;
	
	public int getStayingDuration();
	
	public int getPassedDuration();

	public String getLabel();

	public float getPay();

	public int getDayOfMonth();

	public int getDuration();

}
