package org.jco.accountManager.ihm.swing.subwindows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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

import com.toedter.components.JSpinField;

public class AddNewRegularEventWindow extends JDialog {

	private static final long serialVersionUID = 6605694751597933277L;
	
	private JPanel contentPane;
	private JTextField montant;
	private JSpinField echeance;
	private JTextField label;

	private JComboBox fromComboBox;

	private JComboBox toComboBox;

	private JLabel dayOfMonth;

	/**
	 * Create the frame.
	 */
	public AddNewRegularEventWindow(final GuiControler controler, String[] from, String[] to) {
		setResizable(false);
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
		setBounds(100, 100, 309, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblMontant = new JLabel("Montant");
		GridBagConstraints gbc_lblMontant = new GridBagConstraints();
		gbc_lblMontant.gridwidth = 3;
		gbc_lblMontant.insets = new Insets(0, 0, 5, 5);
		gbc_lblMontant.gridx = 0;
		gbc_lblMontant.gridy = 0;
		contentPane.add(lblMontant, gbc_lblMontant);
		
		montant = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		contentPane.add(montant, gbc_textField);
		montant.setColumns(10);
		
		dayOfMonth = new JLabel("Ech\u00E9ance");
		GridBagConstraints gbc_lblEchance = new GridBagConstraints();
		gbc_lblEchance.gridwidth = 3;
		gbc_lblEchance.insets = new Insets(0, 0, 5, 5);
		gbc_lblEchance.gridx = 0;
		gbc_lblEchance.gridy = 1;
		contentPane.add(dayOfMonth, gbc_lblEchance);
		
		echeance = new JSpinField(1,30);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		contentPane.add(echeance, gbc_textField_1);

		JLabel lblFrom = new JLabel("De");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.gridwidth = 3;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 0;
		gbc_lblFrom.gridy = 2;
		contentPane.add(lblFrom, gbc_lblFrom);
		
		fromComboBox = new JComboBox(from);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		contentPane.add(fromComboBox, gbc_comboBox);
		
		JLabel lblTo = new JLabel("Vers");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.gridwidth = 3;
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 0;
		gbc_lblTo.gridy = 3;
		contentPane.add(lblTo, gbc_lblTo);
		
		toComboBox = new JComboBox(to);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 3;
		contentPane.add(toComboBox, gbc_comboBox_1);
		
		JLabel lblLibell = new JLabel("Libell\u00E9");
		GridBagConstraints gbc_lblLibell = new GridBagConstraints();
		gbc_lblLibell.gridwidth = 3;
		gbc_lblLibell.insets = new Insets(0, 0, 5, 5);
		gbc_lblLibell.gridx = 0;
		gbc_lblLibell.gridy = 4;
		contentPane.add(lblLibell, gbc_lblLibell);

		label = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 4;
		contentPane.add(label, gbc_textField_2);
		label.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 5;
		contentPane.add(btnOk, gbc_btnOk);
		
		JButton btnAnnuler = new JButton("Annuler");
		GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
		gbc_btnAnnuler.anchor = GridBagConstraints.EAST;
		gbc_btnAnnuler.insets = new Insets(0, 0, 5, 5);
		gbc_btnAnnuler.gridx = 1;
		gbc_btnAnnuler.gridy = 5;
		contentPane.add(btnAnnuler, gbc_btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();			
			}
		});
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String montant_f = montant.getText();
				String label_s = label.getText();
				String fromCount = (String) fromComboBox.getSelectedItem();
				String toCount = (String) toComboBox.getSelectedItem();
				int dayOfMonth = echeance.getValue();
				controler.addRegularEvent(montant_f, label_s, fromCount, toCount, dayOfMonth, CountTools.INFINTE);
				dispose();	
			}	
		});
	}
}
