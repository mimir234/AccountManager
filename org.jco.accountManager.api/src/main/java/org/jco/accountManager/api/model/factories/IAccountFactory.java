package org.jco.accountManager.api.model.factories;

import org.jco.accountManager.api.model.IAccount;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IAccountFactory {

	public IAccount getAccount(final String name, final String number, final int type, final float balance, final float theoricBalance);

	public IAccount getAccount(final String name, final String number);
	
	public IAccount getAccount(final String name, final String number, final int type);
	
	public IAccount getAccount(final String name, final String number, final float balance, final float theoricBalance);

}
