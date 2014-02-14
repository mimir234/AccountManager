package org.jco.accountManager.ihm.swing.subwindows;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jco.accountManager.ihm.swing.CountTools;
import org.jco.accountManager.ihm.swing.controler.GuiControler;

public class AddNewCounterWindow extends JDialog {

	private static final long serialVersionUID = -3485774484730438135L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public AddNewCounterWindow(final GuiControler controler) {
		this.setResizable(false);
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
		
		setBounds(100, 100, 450, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(5, 5, 60, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumro = new JLabel("Num\u00E9ro");
		lblNumro.setBounds(5, 30, 46, 14);
		contentPane.add(lblNumro);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(5, 55, 46, 14);
		contentPane.add(lblType);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ch\u00E8que", "Epargne"}));
		comboBox.setBounds(61, 52, 373, 20);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(61, 27, 373, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(61, 2, 373, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(345, 83, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String name = textField_1.getText();
				String number = textField.getText();
				String type = comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
				controler.createAccount(name, number, CountTools.getCountTypeId(type), "0", "0");
				dispose();
			}
		});
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(246, 83, 89, 23);
		contentPane.add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}
}
