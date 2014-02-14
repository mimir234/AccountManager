package org.jco.accountManager.api.controler;

import java.util.Collection;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.events.IEvent;
import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.api.model.factories.IAccountFactory;
import org.jco.accountManager.api.model.factories.IEventFactory;
import org.jco.accountManager.api.model.factories.IRegularEventFactory;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public interface IAccountsControler {
	
	static final String STORAGE_FILE_EXTENSION = ".amf";

	/**
	 * Get the given account.
	 * @param number
	 * @return null if account does not exists
	 */
	public IAccount getAccount(String number);

	/**
	 * 
	 * @return
	 */
	public Collection<IRegularEvent> getRegularEvents();

	/**
	 * 
	 * @return
	 */
	public Collection<IAccount> getAccounts();

	/**
	 * Create account of type Account.COUNT_TYPE_CHECK
	 * @param number
	 * @param name
	 */
	public void addAccount(IAccount account);

	/**
	 * 
	 * @param accountNumberFrom
	 * @param accountNumberTo
	 * @param pay
	 * @param label
	 * @param date
	 */
	public void addEvent(IEvent event);

	/**
	 * 
	 * @return
	 */
	public IEventFactory getEventFactory();

	/**
	 * 
	 * @return
	 */
	public IRegularEventFactory getRegularEventFactory();

	/**
	 * 
	 * @param accountNumber
	 * @param uid
	 */
	public void setEventEffective(String accountNumber, long uid);

	/**
	 * 
	 * @param regularEvent
	 */
	public void addRegularEvent(IRegularEvent regularEvent);

	/**
	 * 
	 * @param regularEvent
	 */
	public void doProcessRegularEvent(IRegularEvent regularEvent);
	
	/**
	 * 
	 */
	public IAccountFactory getAccountFactory();

	/**
	 * 
	 * @param accountNumber
	 */
	public void deleteAccount(final String accountNumber);
	
	/**
	 * 
	 * @param accountNumber
	 * @param uid
	 */
	public void deleteAccountEvent(IAccountEvent event);
	
	/**
	 * 
	 * @param regularEvent
	 */
	public void deleteRegularEvent(long uid);

	/**
	 * 
	 * @param to
	 * @return
	 */
	public String getAccountNumberFromName(final String accountName);

	/**
	 * 
	 * @param file
	 * @param saveEntireModel
	 */
	void save(String file, boolean saveEntireModel);

	/**
	 * 
	 * @param file
	 */
	void load(String file);

	/**
	 * 
	 */
	public void unLoad();

	/**
	 * 
	 * @param file
	 */
	public void removeFile(String file);

}