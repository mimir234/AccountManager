package org.jco.accountManager.application;

import java.awt.EventQueue;

import org.jco.accountManager.controler.AccountsControler;
import org.jco.accountManager.ihm.swing.controler.GuiControler;
import org.jco.accountManager.model.factories.AccountFactory;
import org.jco.accountManager.model.factories.CreditEventFactory;
import org.jco.accountManager.model.factories.DebitEventFactory;
import org.jco.accountManager.model.factories.EventFactory;
import org.jco.accountManager.model.factories.RegularEventFactory;
import org.jco.accountManager.persistence.sqlite.SqliteBroker;

public class StartApp {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SqliteBroker persistence = new SqliteBroker();
					AccountFactory accountFactory = new AccountFactory();
					persistence.setAccountFactory(accountFactory);
					persistence.setCreditEventFactory(new CreditEventFactory());
					persistence.setDebitEventFactory(new DebitEventFactory());
					EventFactory eventFactory = new EventFactory();
					AccountsControler controler = new AccountsControler(eventFactory, new RegularEventFactory(), accountFactory, persistence);
					new GuiControler(controler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
