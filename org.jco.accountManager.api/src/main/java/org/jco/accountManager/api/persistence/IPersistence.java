package org.jco.accountManager.api.persistence;

import java.util.HashMap;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.exceptions.persistence.ClosingPersistenceConnectionException;
import org.jco.accountManager.api.model.exceptions.persistence.OpeningPersistenceConnectionException;
import org.jco.accountManager.api.model.factories.IAccountEventFactory;
import org.jco.accountManager.api.model.factories.IAccountFactory;

public interface IPersistence {

	public void openConnection(String fileName) throws OpeningPersistenceConnectionException;

	public void persist(final IAccount account) throws ObjectPersistenceException;

	public void close() throws ClosingPersistenceConnectionException;

	public HashMap<String, IAccount> loadAccounts() throws ObjectPersistenceException;

	void setAccountFactory(IAccountFactory factory);

	void persist(IAccountEvent event) throws ObjectPersistenceException;

	void setCreditEventFactory(IAccountEventFactory factory);

	void setDebitEventFactory(IAccountEventFactory factory);

	public void remove(IAccount account) throws ObjectPersistenceException;

	public void remove(IAccountEvent event) throws ObjectPersistenceException;
}