package org.jco.accountManager.ihm.swing;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jco.accountManager.api.model.IAccount;
import org.jco.accountManager.ihm.swing.controler.GuiControler;


public class CounterLine implements DrawableLine {
	
	private static final Color POSITIVE_COLOR = new Color(100,230,90);
	private static final Color NEGATIVE_COLOR = new Color(236,0,0);

	private JLabel lblCompte;
	private JLabel label;
	private JLabel lblChque;
	private JLabel lblSolde;
	private JLabel lblTheoricSolde;
	private JLabel incomingButton;
	private JLabel countVirementButton;
	private JLabel payementButton;
	private JLabel graphEditionButton;
	private JLabel lblNewLabel;
	private JLabel budgetsButton;
	private JPanel container;
	private String type;

	public CounterLine(final GuiControler controler, JPanel container, final IAccount account){
		
		this.container = container;
		lblCompte = new JLabel(account.getName());
		label = new JLabel(account.getNumber());
		this.type = String.valueOf(account.getType());
		lblChque = new JLabel(type);
		lblSolde = new JLabel(String.valueOf(account.getBalance()));
		lblTheoricSolde = new JLabel(String.valueOf(account.getTheoricalBalance()));
		incomingButton = new JLabel("");
		incomingButton.setToolTipText("D\u00E9clarer une entr\u00E9e");
		incomingButton.setIcon(Images.ARROW_DOWN_GREEN_25X25);

		incomingButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				controler.openIncomingEventWindow(account.getName());
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				incomingButton.setIcon(Images.ARROW_DOWN_GREEN_INACTIVE_25X25);			
			}

			public void mouseReleased(MouseEvent e) {
				incomingButton.setIcon(Images.ARROW_DOWN_GREEN_25X25);
			}
			
		});
		
		countVirementButton = new JLabel("");
		countVirementButton.setToolTipText("Virement vers un compte");
		countVirementButton.setIcon(Images.ARROW_RIGHT_GREEN_25X25);
		countVirementButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				controler.openInboardEventWindow(account.getName());
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				countVirementButton.setIcon(Images.ARROW_RIGHT_GREEN_INACTIVE_25X25);
			}

			public void mouseReleased(MouseEvent e) {
				countVirementButton.setIcon(Images.ARROW_RIGHT_GREEN_25X25);
			}
			
		});
		
		budgetsButton = new JLabel("");
		switch (CountTools.getCountTypeId(type)) {
		case IAccount.COUNT_TYPE_SAVING:
			budgetsButton.setToolTipText("G\u00E9rer mes \u00E9pargnes");
			break;
		case IAccount.COUNT_TYPE_CHECK:
			payementButton = new JLabel("");
			payementButton.setToolTipText("D\u00E9clarer un paiement");
			payementButton.setIcon(Images.ARROW_UP_RED_25X25);

			payementButton.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent arg0) {
					payementButton.setIcon(Images.ARROW_UP_RED_25X25);
				}

				public void mousePressed(MouseEvent arg0) {
					payementButton.setIcon(Images.ARROW_UP_RED_INACTIVE_25X25);
				}

				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				public void mouseClicked(MouseEvent arg0) {
					controler.openOutcomingEventWindow(account.getName());
					
				}
			});
			budgetsButton.setToolTipText("G\u00E9rer mes budgets");
			break;
		}
		budgetsButton.setIcon(Images.CHART_25X25);
		graphEditionButton = new JLabel("");
		graphEditionButton.setToolTipText("Editer des graphes");
		graphEditionButton.setIcon(Images.PIE_DIAGRAM_25X25);
		lblNewLabel = new JLabel("");
		lblNewLabel.setToolTipText("Supprimer");
		lblNewLabel.setIcon(Images.DELETE_25X25);
		lblNewLabel.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				lblNewLabel.setIcon(Images.DELETE_25X25);
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				lblNewLabel.setIcon(Images.DELETE_INACTIVE_25X25);
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				lblNewLabel.setIcon(Images.DELETE_25X25);
				controler.removeAccount(account.getNumber());
			}
		});

	}

	private Color getColor(String value) {
		if( Float.parseFloat(value) >= 0 ) return POSITIVE_COLOR;
		else return NEGATIVE_COLOR;
	}

	public void setBalance(String solde) {
		lblSolde.setText(solde);
		lblSolde.setForeground(this.getColor(solde));
	}

	public void setTheoricalBalance(String theoricSolde) {
		lblTheoricSolde.setText(theoricSolde);
		lblTheoricSolde.setForeground(this.getColor(theoricSolde));
	}

	public String getTheoricSolde() {
		return this.lblTheoricSolde.getText();
	}
	
	public String getSolde() {
		return this.lblSolde.getText();
	}

	public void unshow() {
		container.remove(lblCompte);
		container.remove(label);
		container.remove(lblChque);
		container.remove(lblSolde);
		container.remove(lblTheoricSolde);
		container.remove(incomingButton);
		container.remove(countVirementButton);
		if( CountTools.getCountTypeId(type) == IAccount.COUNT_TYPE_CHECK ){
			container.remove(payementButton);
		} 
		container.remove(budgetsButton);
		container.remove(graphEditionButton);
		container.remove(lblNewLabel);
	}

	public void show(int index) {
		lblCompte.setBounds(10, 35+(index*30), 120, 14);
		container.add(lblCompte);
		label.setBounds(133, 35+(index*30), 140, 14);
		container.add(label);
		lblChque.setBounds(281, 35+(index*30), 150, 14);
		container.add(lblChque);
		lblSolde.setBounds(432, 35+(index*30), 125, 14);
		lblSolde.setForeground(this.getColor(getSolde()));
		container.add(lblSolde);
		lblTheoricSolde.setBounds(562, 35+(index*30), 240, 14);
		lblTheoricSolde.setForeground(this.getColor(getTheoricSolde()));
		container.add(lblTheoricSolde);
		incomingButton.setBounds(805, 30+(index*30), 25, 25);
		container.add(incomingButton);
		countVirementButton.setBounds(835, 30+(index*30), 25, 25);
		container.add(countVirementButton);
		if( CountTools.getCountTypeId(type) == IAccount.COUNT_TYPE_CHECK ){
			payementButton.setBounds(775, 30+(index*30), 25, 25);
			container.add(payementButton);
		} 
		budgetsButton.setBounds(865, 30+(index*30), 25, 25);
		container.add(budgetsButton);
		graphEditionButton.setBounds(895, 30+(index*30), 25, 25);
		container.add(graphEditionButton);
		lblNewLabel.setBounds(1192, 30+(index*30), 25, 25);
		container.add(lblNewLabel);
	}
}
