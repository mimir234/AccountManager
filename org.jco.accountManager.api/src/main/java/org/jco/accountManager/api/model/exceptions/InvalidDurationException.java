package org.jco.accountManager.api.model.exceptions;

/**
 * 
 * @author J�r�my COLOMBET
 *
 */
public class InvalidDurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2543715425517513074L;
	
	public InvalidDurationException(String text){
		super(text);
	}
}
