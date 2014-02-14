package org.jco.accountManager.model.factories;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.factories.IAccountFactory;
import org.jco.accountManager.model.Account;

public class AccountFactory implements IAccountFactory {

	public IAccount getAccount(final String name, final String number, final int type, final float balance, final float theoricBalance){
		return new Account(name, number, type, balance, theoricBalance);
	}

	public IAccount getAccount(final String name, final String number){
		return new Account(name, number, IAccount.COUNT_TYPE_CHECK, 0, 0);
	}

	public IAccount getAccount(final String name, final String number, final int type) {
		return new Account(name, number, type, 0, 0);
	}

	public IAccount getAccount(final String name, final String number, final float balance, final float theoricBalance) {
		return new Account(name, number, IAccount.COUNT_TYPE_CHECK, balance, theoricBalance);
	}
	
}
