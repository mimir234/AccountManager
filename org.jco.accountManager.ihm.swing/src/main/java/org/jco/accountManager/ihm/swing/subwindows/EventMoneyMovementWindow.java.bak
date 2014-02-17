package org.jco.accountManager.ihm.swing.subwindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jco.accountManager.ihm.swing.controler.GuiControler;

import com.toedter.calendar.JDateChooser;

public class EventMoneyMovementWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField montantTextField;
	private JTextField labelTextField;
	private JComboBox fromComboBox;
	private JComboBox toComboBox;
	private JDateChooser dateChooser;

	/**
	 * Create the frame.
	 */
	public EventMoneyMovementWindow(final GuiControler controler, String[] from, String[] to, boolean enableFrom, boolean enableTo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosed(WindowEvent arg0) {
				controler.activateMainGui();
			}
			
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setBounds(100, 100, 294, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setBounds(10, 11, 46, 14);
		contentPane.add(lblMontant);
		
		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(10, 36, 46, 14);
		contentPane.add(lblDe);
		
		JLabel lblVers = new JLabel("Vers");
		lblVers.setBounds(10, 61, 46, 14);
		contentPane.add(lblVers);
		
		fromComboBox = new JComboBox(from);
		fromComboBox.setBounds(66, 33, 211, 20);
		fromComboBox.setEnabled(enableFrom);
		contentPane.add(fromComboBox);
		
		toComboBox = new JComboBox(to);
		toComboBox.setBounds(66, 58, 211, 20);
		toComboBox.setEnabled(enableTo);
		contentPane.add(toComboBox);
		
		montantTextField = new JTextField();
		montantTextField.setBounds(66, 8, 211, 20);
		contentPane.add(montantTextField);
		montantTextField.setColumns(10);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(89, 142, 89, 23);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(188, 142, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String from = fromComboBox.getSelectedItem().toString();
				String to = toComboBox.getSelectedItem().toString();
				String montant = montantTextField.getText();
				String label = labelTextField.getText();
				Date date = dateChooser.getDate();
				controler.performEvent(from, to, montant, label, date);
				dispose();
			}
		});
		
		JLabel lblLibell = new JLabel("Libell\u00E9");
		lblLibell.setBounds(10, 86, 46, 14);
		contentPane.add(lblLibell);
		
		labelTextField = new JTextField();
		labelTextField.setColumns(10);
		labelTextField.setBounds(66, 83, 211, 20);
		contentPane.add(labelTextField);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.getJCalendar().setTodayButtonVisible(true);
		dateChooser.getJCalendar().setNullDateButtonVisible(true);
		dateChooser.setBounds(66, 111, 211, 20);
		dateChooser.getJCalendar().setDecorationBordersVisible(false);
		dateChooser.getJCalendar().setDecorationBackgroundVisible(false);
		
		contentPane.add(dateChooser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 117, 46, 14);
		contentPane.add(lblDate);
	}
}
