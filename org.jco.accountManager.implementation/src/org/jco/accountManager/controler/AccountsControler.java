package org.jco.accountManager.controler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.jco.accountManager.api.controler.IAccountsControler;
import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.events.IEvent;
import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.api.model.exceptions.persistence.ClosingPersistenceConnectionException;
import org.jco.accountManager.api.model.exceptions.persistence.OpeningPersistenceConnectionException;
import org.jco.accountManager.api.model.factories.IAccountFactory;
import org.jco.accountManager.api.model.factories.IEventFactory;
import org.jco.accountManager.api.model.factories.IRegularEventFactory;
import org.jco.accountManager.api.persistence.IPersistence;
import org.jco.accountManager.api.persistence.ObjectPersistenceException;

/**
 * 
 * @author Jérémy COLOMBET
 *
 */
public class AccountsControler implements IAccountsControler {

	/**
	 * Map of counts. 
	 */
	private HashMap<String, IAccount> accounts = new HashMap<String, IAccount>();

	/**
	 * List of regular events.
	 */
	private ArrayList<IRegularEvent> regularEvents = new ArrayList<IRegularEvent>();

	/**
	 * Map of counts. 
	 */
	private HashMap<String, IAccount> modifiedAccounts = new HashMap<String, IAccount>();
	
	/**
	 * Map of counts. 
	 */
	private HashMap<String, IAccountEvent> modifiedAccountEvents = new HashMap<String, IAccountEvent>();
	
	/**
	 * Map of counts. 
	 */
	private HashMap<String, IAccount> deletedAccounts = new HashMap<String, IAccount>();
	
	/**
	 * Map of counts. 
	 */
	private HashMap<String, IAccountEvent> deletedAccountEvents = new HashMap<String, IAccountEvent>();
	
	/**
	 * 
	 */
	private IEventFactory eventFactory;

	/**
	 * 
	 */
	private IPersistence persistence;
	
	/**
	 * 
	 * @param eventFactory
	 * @param regularEventFactory
	 * @param accountFactory
	 */
	public AccountsControler(final IEventFactory eventFactory, final IRegularEventFactory regularEventFactory, final IAccountFactory accountFactory, final IPersistence persistence) {
		this.eventFactory = eventFactory;
		this.regularEventFactory = regularEventFactory;
		this.accountFactory = accountFactory;
		this.persistence = persistence;
	}

	/**
	 * 
	 */
	private IRegularEventFactory regularEventFactory;
	
	/**
	 * 
	 */
	private IAccountFactory accountFactory;
	
	/**
	 * 
	 */
	public IAccount getAccount(final String number){
		return this.accounts.get(number);
	}

	/**
	 * 
	 */
	public Collection<IRegularEvent> getRegularEvents(){
		return this.regularEvents;
	}

	/**
	 * 
	 */
	public Collection<IAccount> getAccounts(){
		return this.accounts.values();
	}

	/**
	 * 
	 */
	public void addAccount(final IAccount account){
		this.accounts.put(account.getNumber(), account);
		this.modifiedAccounts.put(account.getNumber(), account);
	}

	/**
	 * 
	 */
	public void addEvent(final IEvent event){
		this.addAccountEvent(event.getFromAccountNumber(), event.getFromAccountEvent());
		this.addAccountEvent(event.getToAccountNumber(), event.getToAccountEvent());
	}

	/**
	 * 
	 */
	public IEventFactory getEventFactory(){
		return this.eventFactory; 
	}

	/**
	 * 
	 */
	public IRegularEventFactory getRegularEventFactory(){
		return this.regularEventFactory; 
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param iAccountEvent
	 */
	private void addAccountEvent(final String accountNumber, final IAccountEvent iAccountEvent) {
		IAccount account = this.accounts.get(accountNumber);
		if( account != null ){
			account.addEvent(iAccountEvent, true);
			this.modifiedAccounts.put(account.getNumber(), account);
			this.modifiedAccountEvents.put(String.valueOf(iAccountEvent.getUid()), iAccountEvent);
		}
	}	

	/**
	 * 
	 */
	public void setEventEffective(final String accountNumber, final long uid){
		IAccount account = this.accounts.get(accountNumber);
		if( account != null ){
			IAccountEvent event = account.makeEventEffective(uid);
			if( event != null ){
				this.modifiedAccounts.put(account.getNumber(), account);
				this.modifiedAccountEvents.put(String.valueOf(uid), event);
			}
		}
	}

	/**
	 * 
	 */
	public void addRegularEvent(final IRegularEvent regularEvent){
		this.regularEvents.add(regularEvent);
	}

	/**
	 * 
	 */
	public void doProcessRegularEvent(final IRegularEvent regularEvent){
		
		String fromAccountNumber = regularEvent.getFromAccountNumber();
		String toAccountNumber = regularEvent.getToAccountNumber();
		
		IAccountEvent event = null;
		while( ( event = regularEvent.getToAccountEvent()) != null ){
			this.addAccountEvent(toAccountNumber, event);
		}
		
		while( ( event = regularEvent.getFromAccountEvent()) != null ){
			this.addAccountEvent(fromAccountNumber, event);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public IAccountFactory getAccountFactory() {
		return accountFactory;
	}

	/**
	 * 
	 */
	public void deleteAccount(String accountNumber) {
		IAccount account = this.accounts.get(accountNumber);
		if( account != null ){
			this.accounts.remove(accountNumber);
			this.deletedAccounts.put(accountNumber, account);
			for( IAccountEvent event: account.getEvents().values() ){
				this.deleteAccountEvent(event);
			}
		}
	}

	/**
	 * 
	 */
	public String getAccountNumberFromName(String accountName) {
		for( IAccount account: this.accounts.values()){
			if( accountName.equals(account.getName()) ){
				return account.getNumber();
			}
		}
		return "";
	}
	
	public void save(String file, boolean saveEntireModel){
		Date start = new Date();
		file = this.validateFileName(file);
		
		System.out.println("Saving File : "+file);
		try {
			this.persistence.openConnection(file);
		} catch (OpeningPersistenceConnectionException e1) {
			e1.printStackTrace();
		}

		try {
			if( saveEntireModel ){
				for(IAccount account: this.accounts.values()){
					this.persistence.persist(account);
					
					Iterator<IAccountEvent> it = account.getEvents().values().iterator();
					while( it.hasNext() ){
						this.persistence.persist(it.next());
					}
				}
				
			} else {
				for(IAccount account: this.modifiedAccounts.values()){
					this.persistence.persist(account);
				}
				for(IAccountEvent event: this.modifiedAccountEvents.values()){
					this.persistence.persist(event);
				}
				for(IAccount account: this.deletedAccounts.values()){
					this.persistence.remove(account);
				}
				for(IAccountEvent event: this.deletedAccountEvents.values()){
					this.persistence.remove(event);
				}
			}
		} catch (ObjectPersistenceException e) {
			e.printStackTrace();
		}

		try {
			this.persistence.close();
		} catch (ClosingPersistenceConnectionException e) {
			e.printStackTrace();
		}
		this.modifiedAccountEvents.clear();
		this.modifiedAccounts.clear();
		Date end = new Date();
		System.out.println("Persistence time : "+(end.getTime()-start.getTime())/1000+"s");
	}
	
	private String validateFileName(String file) {
		if( !file.endsWith(STORAGE_FILE_EXTENSION) ){
			file+=STORAGE_FILE_EXTENSION;
		}
		return file;
	}

	public void load(final String file){
		this.unLoad();
		System.out.println("Loading File : "+file);
		Date start = new Date();
		try {
			this.persistence.openConnection(file);
		} catch (OpeningPersistenceConnectionException e1) {
			e1.printStackTrace();
		}

		try {
			this.accounts = this.persistence.loadAccounts();
		} catch (ObjectPersistenceException e) {
			e.printStackTrace();
		}

		try {
			this.persistence.close();
		} catch (ClosingPersistenceConnectionException e) {
			e.printStackTrace();
		}
		Date end = new Date();
		System.out.println("Loading time : "+(end.getTime()-start.getTime())/1000+"s");
	}

	public void unLoad() {
		this.accounts.clear();
		this.regularEvents.clear();
	}

	public void removeFile(String file) {
		File f = new File(file);
		if( f.exists() ){
			f.delete();
		}
	}
	
	public void deleteAccountEvent(IAccountEvent event) {
		IAccount account = this.accounts.get(event.getAccountNumber());
		if( account != null ){
			IAccountEvent accountEvent = account.deleteEvent(event.getUid());
			if( accountEvent != null ){
				this.deletedAccountEvents.put(String.valueOf(accountEvent.getUid()), accountEvent);
			}
		}
		
	}

	public void deleteRegularEvent(long uid) {
		// TODO Auto-generated method stub
	}
}
