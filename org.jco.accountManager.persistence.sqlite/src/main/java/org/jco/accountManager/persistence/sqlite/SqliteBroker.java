package org.jco.accountManager.persistence.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.exceptions.persistence.ClosingPersistenceConnectionException;
import org.jco.accountManager.api.model.exceptions.persistence.OpeningPersistenceConnectionException;
import org.jco.accountManager.api.model.factories.IAccountEventFactory;
import org.jco.accountManager.api.model.factories.IAccountFactory;
import org.jco.accountManager.api.persistence.IPersistence;
import org.jco.accountManager.api.persistence.ObjectPersistenceException;

public class SqliteBroker implements IPersistence {

	private Connection sqliteConnection = null;

	private IAccountFactory accountFactory = null;

	private IAccountEventFactory creditEventFactory = null;

	private IAccountEventFactory debitEventFactory = null;

	public void setAccountFactory(IAccountFactory factory){
		this.accountFactory = factory;
	}

	public void setCreditEventFactory(IAccountEventFactory factory){
		this.creditEventFactory = factory;
	}

	public void openConnection(String fileName) throws OpeningPersistenceConnectionException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new OpeningPersistenceConnectionException(e.getMessage(), e);
		}
		try {
			this.sqliteConnection = DriverManager.getConnection("jdbc:sqlite:"+fileName);
		} catch (SQLException e) {
			throw new OpeningPersistenceConnectionException(e.getMessage(), e);
		}
		
		try {
			this.createDatabase();
		} catch (SQLException e) {
			throw new OpeningPersistenceConnectionException(e.getMessage(), e);
		}
	}
	
	private void createDatabase() throws SQLException {
		this.createTable("accounts", "number INT PRIMARY KEY NOT NULL, "
				+ "name CHAR(256) NOT NULL, "
				+ "type INT NOT NULL, "
				+ "balance FLOAT NOT NULL, "
				+ "theorical_balance FLOAT NOT NULL");
		this.createTable("accounts_events", "uid INT PRIMARY KEY NOT NULL, "
				+ "date INT NOT NULL, "
				+ "type INT NOT NULL, "
				+ "effective INT NOT NULL, "
				+ "pay FLOAT NOT NULL, "
				+ "label CHAR(256) NOT NULL, "
				+ "account_number INT NOT NULL");
	}
	
	private void createTable(String tableName, String fields) throws SQLException {
		this.executeUpdate("CREATE TABLE IF NOT EXISTS "+tableName+" ("+fields+")");
	}
	
	private ResultSet executeSelect( String selectRequest ) throws SQLException {
		System.out.println(selectRequest);
		Statement statement = this.sqliteConnection.createStatement();
		ResultSet result = statement.executeQuery(selectRequest);
		return result;
	}

	private void executeUpdate( String sqlRequest ) throws SQLException{
		System.out.println(sqlRequest);
		Statement statement = this.sqliteConnection.createStatement();
		statement.executeUpdate(sqlRequest);
		statement.close();
	}
	
	public void persist(IAccountEvent event) throws ObjectPersistenceException {
		try {
			if( this.doesLineExists("accounts_events", "uid", String.valueOf(event.getUid())) ){
				this.executeUpdate("UPDATE accounts_events SET effective="
						+ (event.isEffective()==true?1:0) +" WHERE uid="+event.getUid());
			} else {
				this.executeUpdate("INSERT INTO accounts_events(uid, date, effective, pay, label, account_number, type) VALUES ("
						+ event.getUid()+",'"
						+ event.getDate().getTime()+"',"
						+ (event.isEffective()==true?1:0)+","
						+ event.getPay()+",'"
						+ event.getLabel()+"',"
						+ event.getAccountNumber()+","
						+ event.getType()+")");
			}
		} catch (SQLException e) {
			throw new ObjectPersistenceException(e.getMessage());
		}
	}

	public void persist(IAccount account) throws ObjectPersistenceException {
		try {
			if( this.doesLineExists("accounts", "number", account.getNumber()) ){
				this.executeUpdate("UPDATE accounts SET balance="
						+ account.getBalance()+", theorical_balance="
						+ account.getTheoricalBalance()+" WHERE number="+account.getNumber());
			} else {
				this.executeUpdate("INSERT INTO accounts(number, name, type, balance, theorical_balance) VALUES ("
						+ account.getNumber()+",'"
						+ account.getName()+"',"
						+ account.getType()+","
						+ account.getBalance()+","
						+ account.getTheoricalBalance()+")");
			}
		} catch (SQLException e) {
			throw new ObjectPersistenceException(e.getMessage());
		}
	}

	private boolean doesLineExists(String tableName, String fieldName, String fieldValue) throws SQLException {
		boolean lineExists = false;
		ResultSet result = this.executeSelect("SELECT COUNT(*) FROM "+tableName+" WHERE "+fieldName+"="+fieldValue);
		if ( result.getInt(1) > 0 ){
			lineExists = true;
		} 
		result.close();
		return lineExists;
	}

	public void close() throws ClosingPersistenceConnectionException {
		try {
			this.sqliteConnection.close();
		} catch (SQLException e) {
			throw new ClosingPersistenceConnectionException(e.getMessage(), e);
		}
		
	}

	public HashMap<String, IAccount> loadAccounts() throws ObjectPersistenceException {
		if ( this.accountFactory == null ){
			throw new ObjectPersistenceException("The account factory is null.");
		}
		if ( this.creditEventFactory == null ){
			throw new ObjectPersistenceException("The credit event factory is null.");
		}
		if ( this.debitEventFactory == null ){
			throw new ObjectPersistenceException("The debit event factory is null.");
		}
		
		HashMap<String, IAccount> accountsMap = new HashMap<String, IAccount>();
		try {
			ResultSet accounts = this.executeSelect("SELECT * FROM accounts");
			while( accounts.next() ){
				String name = accounts.getString("name");
				String number = accounts.getString("number");
				int type = accounts.getInt("type");
				float balance = accounts.getFloat("balance");
				float theoricalBalance = accounts.getFloat("theorical_balance");

				IAccount account = this.accountFactory.getAccount(name, number, type, balance, theoricalBalance);

				ResultSet events = this.executeSelect("SELECT * FROM accounts_events WHERE account_number='"+account.getNumber()+"'");
				while( events.next() ){
					long uid = events.getLong("uid");
					boolean effective = events.getInt("effective")==1?true:false;
					int eventType = events.getInt("type");
					float pay = events.getFloat("pay");
					String label = events.getString("label");
					Date date = new Date( events.getLong("date") );
				
					IAccountEvent event = null;
				
					switch( eventType ){
					case IAccountEvent.EVENT_TYPE_IN :
						event = this.creditEventFactory.getEvent(account.getNumber(), uid, pay, label, date, effective);
						break;
					default :
					case IAccountEvent.EVENT_TYPE_OUT :
						event = this.debitEventFactory.getEvent(account.getNumber(), uid, pay, label, date, effective);
						break;
					}

					account.addEvent(event, false);
				}
				
				accountsMap.put(number, account);
			}
			accounts.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountsMap;
	}

	public void setDebitEventFactory(IAccountEventFactory factory) {
		this.debitEventFactory = factory;
	}

	public void remove(IAccount account) throws ObjectPersistenceException {
		try {
			if( this.doesLineExists("accounts", "number", account.getNumber()) ){
				this.executeUpdate("DELETE FROM accounts WHERE number="+account.getNumber());
			} 
		} catch (SQLException e) {
			throw new ObjectPersistenceException(e.getMessage());
		}
	}

	public void remove(IAccountEvent event) throws ObjectPersistenceException {
		try {
			if( this.doesLineExists("accounts_events", "uid", String.valueOf(event.getUid())) ){
				this.executeUpdate("DELETE FROM accounts_events WHERE uid="+event.getUid());
			} 
		} catch (SQLException e) {
			throw new ObjectPersistenceException(e.getMessage());
		}
	}

}
