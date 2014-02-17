package org.jco.accountManager.ihm.swing.tabs;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.api.model.events.IAccountEvent;
import org.jco.accountManager.ihm.swing.CountTools;
import org.jco.accountManager.ihm.swing.Images;
import org.jco.accountManager.ihm.swing.Serializator;
import org.jco.accountManager.ihm.swing.controler.GuiControler;

import com.toedter.calendar.JDateChooser;

public class CounterTab {

	public static final Color EFFECTIVE_EVENT_COLOR = new Color(223,255,223);
	public static final Color UNEFFECTIVE_EVENT_COLOR = new Color(255,223,223);
		
	private ArrayList<IAccountEvent> eventList = null;
	private ArrayList<IAccountEvent> shownEventList = null;
	private JTextField priceFrom;
	private JTextField priceTo;
	private JTable eventsTable;
	private GuiControler controler;
	private String name;
	private JCheckBox chckbxEffectif;
	private JCheckBox chckbxNonEffectif;
	private JComboBox comboBoxType;
	private JCheckBox chckbxCurrentMonth;
	private JDateChooser dateChooserTo;
	private JDateChooser dateChooserFrom;
	private JPanel count;
	private JTabbedPane pane;
	
	public CounterTab(final GuiControler controler, final JTabbedPane pane, final IAccount account) {
		this.pane = pane;
		this.name = account.getName();
		this.setName(name);
		this.setControler(controler);
		count = new JPanel();
		pane.addTab(name, null, count, null);
		count.setLayout(null);
		
		this.eventList = new ArrayList<IAccountEvent>();
		this.shownEventList = new ArrayList<IAccountEvent>();
		
		Iterator<IAccountEvent> it = account.getEvents().values().iterator();
		while( it.hasNext() ){
			IAccountEvent event = it.next();
			this.eventList.add(event);
			this.shownEventList.add(event);
		}
		
		setEventsTable(new JTable());
		getEventsTable().setShowHorizontalLines(false);
		getEventsTable().setEnabled(true);
		getEventsTable().setRowSelectionAllowed(true);
		getEventsTable().setModel(new MyTableModel( null, new String[] { "", "Date", "Libell\u00E9", "Montant", CountTools.getCountTypeColumnLabel(account.getType()), "From/To", "Actions" } ));
		getEventsTable().getColumnModel().getColumn(0).setPreferredWidth(15);
		getEventsTable().getColumnModel().getColumn(0).setWidth(15);
		getEventsTable().getColumnModel().getColumn(0).setResizable(false);
		getEventsTable().getColumnModel().getColumn(1).setResizable(false);
		getEventsTable().getColumnModel().getColumn(2).setResizable(false);
		getEventsTable().getColumnModel().getColumn(3).setResizable(false);
		getEventsTable().getColumnModel().getColumn(4).setResizable(false);
		getEventsTable().getColumnModel().getColumn(5).setResizable(false);
		getEventsTable().getColumnModel().getColumn(6).setResizable(false);
		getEventsTable().setBounds(84, 350, 491, -290);
		
		for (int i = 0; i < getEventsTable().getColumnCount()-1; i++) {
			getEventsTable().getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer(){
				private static final long serialVersionUID = 1L;

			    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        if ( shownEventList.get(row).isEffective() ){
			        	 c.setBackground(EFFECTIVE_EVENT_COLOR);
			        } else {
						 c.setBackground(UNEFFECTIVE_EVENT_COLOR);
			        }
			        if( column == 0 ){
			        	if ( shownEventList.get(row).getType() == IAccountEvent.EVENT_TYPE_IN ){
			        		setIcon( Images.ARROW_DOWN_GREEN_15X15 );
			        	} else {
			        		setIcon( Images.ARROW_UP_RED_15X15 );
			        	}
			        }
			        return c;
			    }
			});
		}
		
		getEventsTable().getColumnModel().getColumn(getEventsTable().getColumnCount()-1).setCellRenderer(new ButtonColumn(this));
		getEventsTable().getColumnModel().getColumn(getEventsTable().getColumnCount()-1).setCellEditor(new ButtonColumn(this));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 103, 1220, 600);
		count.add(scrollPane);
		scrollPane.setViewportView(getEventsTable());
		
		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setDateFormatString("dd/MM/yyyy");
		dateChooserFrom.getJCalendar().setTodayButtonVisible(true);
		dateChooserFrom.addPropertyChangeListener(new PropertyChangeListener() {
		
			public void propertyChange(PropertyChangeEvent arg0) {
				if( arg0.getNewValue() instanceof Date ){
					dateChooserTo.setMinSelectableDate((Date)arg0.getNewValue());
				}
			}
		});
		
		dateChooserFrom.getJCalendar().setNullDateButtonVisible(true);
		dateChooserFrom.setBounds(48, 11, 100, 20);
		dateChooserFrom.getJCalendar().setDecorationBordersVisible(false);
		dateChooserFrom.getJCalendar().setDecorationBackgroundVisible(false);
		count.add(dateChooserFrom);
		
		dateChooserTo = new JDateChooser();
		dateChooserTo.setDateFormatString("dd/MM/yyyy");
		dateChooserTo.getJCalendar().setTodayButtonVisible(true);
		dateChooserTo.getJCalendar().setNullDateButtonVisible(true);
		dateChooserTo.setBounds(160, 11, 100, 20);
		dateChooserTo.getJCalendar().setDecorationBordersVisible(false);
		dateChooserTo.getJCalendar().setDecorationBackgroundVisible(false);
		count.add(dateChooserTo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(48, 49, 182, 20);
		count.add(comboBox);
		
		JLabel lblBudget = new JLabel("Budget");
		lblBudget.setBounds(10, 52, 34, 14);
		count.add(lblBudget);
		
		chckbxEffectif = new JCheckBox("Effectif");
		chckbxEffectif.setBounds(297, 10, 97, 23);
		count.add(chckbxEffectif);
		
		chckbxNonEffectif = new JCheckBox("Non effectif");
		chckbxNonEffectif.setBounds(297, 48, 97, 23);
		count.add(chckbxNonEffectif);
		
		chckbxCurrentMonth = new JCheckBox("Mois en cours");
		chckbxCurrentMonth.setBounds(740, 10, 150, 23);
		count.add(chckbxCurrentMonth);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 14, 46, 14);
		count.add(lblDate);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(439, 14, 46, 14);
		count.add(lblMontant);
		
		priceFrom = new JTextField();
		priceFrom.setText("De");
		priceFrom.setColumns(10);
		priceFrom.setBounds(495, 11, 86, 20);
		count.add(priceFrom);
		
		priceTo = new JTextField();
		priceTo.setText("A");
		priceTo.setColumns(10);
		priceTo.setBounds(591, 11, 86, 20);
		count.add(priceTo);
		
		comboBoxType = new JComboBox(new String[]{"Tous","Cr\u00E9dit","D\u00E9bit"});
		comboBoxType.setBounds(495, 49, 182, 20);
		count.add(comboBoxType);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(439, 52, 46, 14);
		count.add(lblType);
		
		JButton btnTrier = new JButton("Trier");
		btnTrier.setBounds(1013, 69, 89, 23);
		count.add(btnTrier);
		btnTrier.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				processSort();
			}
			
		});
		this.refreshTable();
	}
	
	@SuppressWarnings("unchecked")
	protected void processSort() {
		ArrayList<IAccountEvent> linesToRemove = new ArrayList<IAccountEvent>();
		this.shownEventList.clear();
		this.shownEventList = (ArrayList<IAccountEvent>) this.eventList.clone();
		
		float priceFrom = -1;
		float priceTo = -1;	
		boolean onlyEffective = chckbxEffectif.isSelected();
		boolean onlyUneffective = chckbxNonEffectif.isSelected();
		boolean currentMonth = chckbxCurrentMonth.isSelected();
		String type_s = (String) comboBoxType.getSelectedItem();
		int type = -1;
		Date dateTo = this.dateChooserTo.getDate();
		Date dateFrom = this.dateChooserFrom.getDate();

		int month = new GregorianCalendar().get(Calendar.MONTH) + 1;
		int year = new GregorianCalendar().get(Calendar.YEAR);

		if( type_s.equals("Cr\u00E9dit")){
			type = IAccountEvent.EVENT_TYPE_IN;
		}
		if( type_s.equals("D\u00E9bit")){
			type = IAccountEvent.EVENT_TYPE_OUT;
		}
		
		if( this.priceFrom.getText().length() > 0 ){
			try {
				priceFrom = Float.parseFloat(this.priceFrom.getText());
			} catch (Exception e){
			}
		}
		if( this.priceTo.getText().length() > 0 ){
			try {
				priceTo = Float.parseFloat(this.priceTo.getText());
			} catch (Exception e){
			}
		}

		for( IAccountEvent event: this.eventList){
			if( priceFrom != -1 && event.getPay() < priceFrom ){
				linesToRemove.add(event);
			}
			if( priceTo != -1 && event.getPay() > priceTo ){
				linesToRemove.add(event);
			}
			if( event.isEffective() == false && onlyEffective ){
				linesToRemove.add(event);
			}
			if( event.isEffective() == true && onlyUneffective ){
				linesToRemove.add(event);
			}
			//TODO changer pour éviter de tester type
			if( type != -1 && event.getType() != type ){
				linesToRemove.add(event);
			}
			if( currentMonth ){
				GregorianCalendar eventDate = new GregorianCalendar();
				eventDate.setTime(event.getDate());
				int eventMonth = eventDate.get(Calendar.MONTH) + 1;
				int eventYear = eventDate.get(Calendar.YEAR);
				if( eventMonth != month || eventYear != year ){
					linesToRemove.add(event);
				}
			}
			if( dateFrom != null ){
				if( event.getDate().before(dateFrom) ){
					linesToRemove.add(event);
				}
			}
			if( dateTo != null ){
				if( event.getDate().after(dateTo) ){
					linesToRemove.add(event);
				}
			}
		}
		
		for( IAccountEvent line: linesToRemove){
			this.shownEventList.remove(line);
		}
		
		this.refreshTable();
	}

	static public class MyTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		
		boolean[] columnEditables = new boolean[] {
			false, false, false, false, false, false, true
		};
	    
		public MyTableModel(Object[][] object, Object[] strings) {
			super(object, strings);
		}

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
		
	}
	
	public void addEvent( final IAccountEvent event ){
		this.eventList.add(event);
		this.shownEventList.add(event);
		this.refreshTable();
	}
	
	public void removeEvent(IAccountEvent event) {
		this.eventList.remove(event);
		this.shownEventList.remove(event);
		this.refreshTable();
	}
	
	public void refreshTable() {
		Collections.sort(this.eventList);
		Collections.sort(this.shownEventList);
		this.getEventsTable().removeAll();
		MyTableModel model = (MyTableModel) this.getEventsTable().getModel();
		model.setRowCount(0);
		for( IAccountEvent event: this.shownEventList){
			model.insertRow(0, Serializator.getAccountEventToRow(event));
		}
		((DefaultTableModel)getEventsTable().getModel()).fireTableDataChanged();
	}

	public JTable getEventsTable() {
		return eventsTable;
	}

	public void setEventsTable(JTable eventsTable) {
		this.eventsTable = eventsTable;
	}

	public ArrayList<IAccountEvent> getEventList() {
		return eventList;
	}

	public GuiControler getControler() {
		return controler;
	}

	public void setControler(GuiControler controler) {
		this.controler = controler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<IAccountEvent> getShownEventList() {
		return this.shownEventList;
	}

	public void unshow(){
		this.pane.remove(this.count);
	}

	public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

		private static final long serialVersionUID = -4509491056067711306L;
		
		private int mnemonic;
		private Border originalBorder;
		private Border focusBorder;

		private JButton renderButton;
		private JButton editButton;
		private Object editorValue;
		private CounterTab counterTab;

		public ButtonColumn(CounterTab counterTab) {
			this.counterTab = counterTab;
			renderButton = new JButton();
			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);
			originalBorder = editButton.getBorder();
		}
		
		public void setButtonText(String text){
			this.editButton.setText(text);
			this.renderButton.setText(text);
			this.editButton.repaint();
			this.renderButton.repaint();
			((DefaultTableModel)counterTab.getEventsTable().getModel()).fireTableDataChanged();
		}

		public Border getFocusBorder() {
			return focusBorder;
		}

		public void setFocusBorder(Border focusBorder) {
			this.focusBorder = focusBorder;
			this.editButton.setBorder(focusBorder);
		}

		public int getMnemonic() {
			return mnemonic;
		}

		public void setMnemonic(int mnemonic) {
			this.mnemonic = mnemonic;
			renderButton.setMnemonic(mnemonic);
			editButton.setMnemonic(mnemonic);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			IAccountEvent event = this.counterTab.getShownEventList().get(row);
			if( !event.isEffective() ){
				setButtonText("Rendre effectif");
			} else {
				setButtonText("Supprimer");
			}

			this.editorValue = value;
			return editButton;
		}

		public Object getCellEditorValue() {
			return editorValue;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				renderButton.setForeground(table.getSelectionForeground());
				renderButton.setBackground(table.getSelectionBackground());
			} else {
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}

			if (hasFocus) {
				renderButton.setBorder(focusBorder);
			} else {
				renderButton.setBorder(originalBorder);
			}

			IAccountEvent event = this.counterTab.getShownEventList().get(row);
			if( !event.isEffective() ){
				setButtonText("Rendre effectif");
			} else {
				setButtonText("Supprimer");
			}

			return renderButton;
		}

		public void actionPerformed(ActionEvent e) {
			int row = this.counterTab.getEventsTable().convertRowIndexToModel(this.counterTab.getEventsTable().getEditingRow());
			IAccountEvent event = this.counterTab.getShownEventList().get(row);
			if( !event.isEffective() ){
				this.counterTab.getControler().setEventEffectif(event);
				refreshTable();
				this.counterTab.getEventsTable().repaint();
				setButtonText("Supprimer");
			} else {
				this.counterTab.getControler().removeAccountEvent(event);
				refreshTable();
				this.counterTab.getEventsTable().repaint();
			}
		}
	}
}
