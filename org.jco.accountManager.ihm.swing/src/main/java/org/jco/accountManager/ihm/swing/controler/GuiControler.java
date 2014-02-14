package org.jco.accountManager.ihm.swing.controler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jco.accountManager.api.controler.IAccountsControler;
import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.api.model.events.IEvent;
import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.api.model.exceptions.InvalidDurationException;
import org.jco.accountManager.ihm.swing.CounterLine;
import org.jco.accountManager.ihm.swing.DrawableLine;
import org.jco.accountManager.ihm.swing.Images;
import org.jco.accountManager.ihm.swing.MainGui;
import org.jco.accountManager.ihm.swing.RegularEventLine;
import org.jco.accountManager.ihm.swing.subwindows.AddNewCounterWindow;
import org.jco.accountManager.ihm.swing.subwindows.AddNewRegularEventWindow;
import org.jco.accountManager.ihm.swing.subwindows.EventMoneyMovementWindow;
import org.jco.accountManager.ihm.swing.tabs.CounterTab;

public class GuiControler {

	private HashMap<String, DrawableLine> counterLineMap = new HashMap<String, DrawableLine>();
	private HashMap<String, CounterTab> counterTabMap = new HashMap<String, CounterTab>();
	private HashMap<String, DrawableLine> regularEventsList = new HashMap<String, DrawableLine>();
	private MainGui gui;

	private int counterLineIndex = 0;
	private int regularEventLineIndex = 0;
	private IAccountsControler controler;
	
	private String file = null;

	public GuiControler(final IAccountsControler controler) {
		this.controler = controler;
		gui = new MainGui(this);
		gui.setVisible();
	}

	public void openIncomingEventWindow(final String name) {
		gui.setInactive();
		List<String> from = this.getKeyList(this.counterLineMap);
		from.add("Extérieur");
		from.remove(name);
		EventMoneyMovementWindow ew = new EventMoneyMovementWindow(this, Arrays.copyOf(from.toArray(), from.size(), String[].class), new String[] { name }, true, false);
		ew.setVisible(true);
	}

	public void openNewCounterWindow() {
		gui.setInactive();
		AddNewCounterWindow w = new AddNewCounterWindow(this);
		w.setVisible(true);
	}

	public void openNewRegularEventWindow() {
		gui.setInactive();
		List<String> list = this.getKeyList(this.counterLineMap);
		list.add("Extérieur");
		String[] array = Arrays.copyOf(list.toArray(), list.size(), String[].class);
		AddNewRegularEventWindow w = new AddNewRegularEventWindow(this, array, array);
		w.setVisible(true);
	}

	public void openOutcomingEventWindow(final String name) {
		gui.setInactive();
		EventMoneyMovementWindow ew = new EventMoneyMovementWindow(this, new String[] { name }, new String[] { "Extérieur" }, false, false);
		ew.setVisible(true);
	}

	public void openInboardEventWindow(final String name) {
		gui.setInactive();
		List<String> from = this.getKeyList(this.counterLineMap);
		from.remove(name);
		if (from.size() > 0) {
			EventMoneyMovementWindow ew = new EventMoneyMovementWindow(this, new String[] { name }, Arrays.copyOf(from.toArray(), from.size(), String[].class), false, true);
			ew.setVisible(true);
		} else {
//			this.openAlertPopup("Pas assez de comptes pour faire un mouvement entre comptes.");
		}
	}

	public void addRegularEvent(final String pay, final String label, final String fromCount, final String toCount, final int dayOfMonth, final int duration) {
		 gui.setActive();
		if (this.regularEventsList.get(label) == null) {
			IRegularEvent event = null;
			try {
				event = this.controler.getRegularEventFactory().getEvent(fromCount, toCount, Float.parseFloat(pay), label, dayOfMonth, duration, new Date());
				this.controler.addRegularEvent(event);

				RegularEventLine line = new RegularEventLine(this, this.gui.getRegularEventPanel(), event);
				this.regularEventsList.put(label, line);
				line.show(this.regularEventLineIndex);
				this.regularEventLineIndex++;
				this.gui.repaint();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (InvalidDurationException e) {
				e.printStackTrace();
			}
			
		} else {
//			this.openAlertPopup("L'évènement régulier existe déjà.");
		}
	}

	public void createAccount(String name, String number, int type, String balance, String theoricBalance) {
		gui.setActive();
		if (this.counterLineMap.get(name) == null) {
			this.controler.addAccount(this.controler.getAccountFactory().getAccount(name, number, type, Float.parseFloat(balance), Float.parseFloat(theoricBalance)));
			paintAccountsList();
		} else {
//			this.openAlertPopup("Le compte existe déjà.");
		}
	}

	private void paintAccountsList() {
		clearGui();
		
		Collection<IAccount> accounts = this.controler.getAccounts();
		Iterator<IAccount> iterator = accounts.iterator();
		
		while( iterator.hasNext() ){
			IAccount account = iterator.next();
			CounterLine line = new CounterLine(this, this.gui.getCountersPanel(), account);
			CounterTab tab = new CounterTab(this, this.gui.getTabbedPane(), account);

			this.counterLineMap.put(account.getNumber(), line);
			this.counterTabMap.put(account.getNumber(), tab);
		}
		
		this.repaintCountsLines();
	}

	private void clearGui() {
		this.counterLineIndex = 0;
	
		Iterator<CounterTab> tabIterator = this.counterTabMap.values().iterator();
		while( tabIterator.hasNext() ){
			tabIterator.next().unshow();
		}
		
		Iterator<DrawableLine> accountLineIterator = this.counterLineMap.values().iterator();
		while( accountLineIterator.hasNext() ){
			accountLineIterator.next().unshow();
		}
		
		this.counterLineMap.clear();
		this.counterTabMap.clear();
	}

	private List<String> getKeyList(HashMap<String, DrawableLine> hashmap) {
		Set<String> keys = hashmap.keySet();
		List<String> keyList = new ArrayList<String>();
		for (String key : keys) {
			keyList.add(key);
		}
		return keyList;
	}

	public void performEvent(final IEvent event) {
		this.controler.addEvent(event);
		this.updateAccountLine(event.getFromAccountNumber());
		this.updateAccountLine(event.getToAccountNumber());
		this.addEventToAccountTab(event.getFromAccountNumber(), event.getFromAccountEvent());
		this.addEventToAccountTab(event.getToAccountNumber(), event.getToAccountEvent());
	}

	private void addEventToAccountTab(final String accountNumber, final IAccountEvent accountEvent) { 
		CounterTab tab = this.counterTabMap.get(accountNumber);
		if (tab != null) {
			tab.addEvent(accountEvent);
		}
	}
	
	private void removeEventFromAccountTab(final String accountNumber, final IAccountEvent accountEvent) { 
		CounterTab tab = this.counterTabMap.get(accountNumber);
		if (tab != null) {
			System.out.println(accountEvent.getLabel());
			tab.removeEvent(accountEvent);
		}
	}

	private void updateAccountLine(final String number) {
		IAccount account = this.controler.getAccount(number);
		CounterLine line = (CounterLine) this.counterLineMap.get(number);
		if (account != null) {
			line.setBalance(String.valueOf(account.getBalance()));
			line.setTheoricalBalance(String.valueOf(account.getTheoricalBalance()));
			this.repaintCountsLines();
		}
	}

	public void removeAccountEvent(final IAccountEvent event) {
		IAccount account = this.controler.getAccount(event.getAccountNumber());
		if( account != null ){
			this.controler.deleteAccountEvent(event);
			this.updateAccountLine(account.getName());
			this.repaintCountsLines();
			paintAccountsList();
			removeEventFromAccountTab(account.getNumber(), event);
		} 
	}

	public void removeRegularEvent(String label) {
		this.regularEventsList.get(label).unshow();
		this.regularEventsList.remove(label);
		this.repaintRegularEventsLines();
	}

	public void removeAccount(String number) {
		this.counterLineMap.get(number).unshow();
		this.counterTabMap.get(number).unshow();
		this.counterLineMap.remove(number);
		this.counterTabMap.remove(number);
		this.controler.deleteAccount(number);
		this.repaintCountsLines();
	}

	private void repaintCountsLines() {
		this.counterLineIndex = repaintDrawableLines(this.counterLineMap, this.counterLineIndex);
	}

	private void repaintRegularEventsLines() {
		this.regularEventLineIndex = repaintDrawableLines(this.regularEventsList, this.regularEventLineIndex);
	}

	private int repaintDrawableLines(final HashMap<String, DrawableLine> lines, int index) {
		for (DrawableLine line : lines.values()) {
			line.unshow();
		}
		index = 0;
		for (DrawableLine line : lines.values()) {
			line.show(index);
			index++;
		}
		this.gui.repaint();
		return index;
	}

	public void activateMainGui() {
		this.gui.setActive();
		this.gui.setVisible();
	}

	public void performEvent(final String from, final String to, final String pay, final String label, final Date date) {
		String fromNumber = this.controler.getAccountNumberFromName(from);
		String toNumber = this.controler.getAccountNumberFromName(to);

		this.performEvent(this.controler.getEventFactory().getEvent(fromNumber, toNumber, Float.valueOf(pay), label, date));
	}

	public void setEventEffectif(final IAccountEvent event) {
		this.controler.setEventEffective( event.getAccountNumber(), event.getUid() );
		this.updateAccountLine(event.getAccountNumber());
	}

	public void save(){
		this.gui.setInactive();
		Thread t = new Thread(){
			public void run(){
				if( file != null ){
					controler.save(file, false);
				} else {
					JFileChooser fileChooser = new JFileChooser();
					if(fileChooser.showOpenDialog(gui.getFrame()) == JFileChooser.APPROVE_OPTION){
						file = fileChooser.getSelectedFile().getAbsolutePath();
						
						File f = new File(file);
						if( f.exists() ) {
							if (JOptionPane.showConfirmDialog(gui.getFrame(),
									"Attention : le fichier existe déjà, celui-ci sera écrasé.",
									"Validation", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, Images.WARNING_48X48) == JOptionPane.YES_OPTION) {
									controler.removeFile(file);
									controler.save(file, true);
							}
						} else {
							controler.save(file, true);
						}
					}
				}

				activateMainGui();
			}
		};
		t.start();
	}
	
	public void saveAs(){
		this.gui.setInactive();
		Thread t = new Thread(){
			public void run(){
				JFileChooser fileChooser = new JFileChooser();
				if(fileChooser.showOpenDialog(gui.getFrame()) == JFileChooser.APPROVE_OPTION){
					file=fileChooser.getSelectedFile().getAbsolutePath();
					File f = new File(file);
					if( f.exists() ) {
						if (JOptionPane.showConfirmDialog(gui.getFrame(),
								"Attention : le fichier existe déjà, celui-ci sera écrasé.",
								"Validation", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, Images.WARNING_48X48) == JOptionPane.YES_OPTION) {
								controler.removeFile(file);
								controler.save(file, true);
						}
					} else {
						controler.save(file, true);
					}
				}
				activateMainGui();
			}
		};
		t.start();
	}
	
	public void openFile(final String absolutePath) {
		gui.setInactive();
		Thread t = new Thread(){
			public void run(){
				controler.unLoad();
				controler.load(absolutePath);
				file = absolutePath;
				paintAccountsList();
				activateMainGui();
			}
		};
		t.start();
	}

	public void newWork() {
		gui.setInactive();
		Thread t = new Thread(){
			public void run(){
				controler.unLoad();
				file = null;
				paintAccountsList();
				activateMainGui();
			}
		};
		t.start();
	}

}
