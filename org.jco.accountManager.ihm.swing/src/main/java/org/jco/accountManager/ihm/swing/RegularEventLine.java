package org.jco.accountManager.ihm.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jco.accountManager.api.model.events.IRegularEvent;
import org.jco.accountManager.ihm.swing.controler.GuiControler;

public class RegularEventLine implements DrawableLine {

	private JLabel lblNewLabel;
	private JLabel lblDuration;
	private JLabel lblEcheance;
	private JLabel lblCountTo;
	private JLabel lblCountFrom;
	private JLabel lblPrice;
	private JLabel lblLabel;
	private JPanel container;

	public RegularEventLine(final GuiControler controler, final JPanel container, final IRegularEvent event){
		
		this.container = container;
		lblLabel = new JLabel(event.getLabel());
		lblPrice = new JLabel(String.valueOf(event.getPay()));
		lblCountFrom = new JLabel(event.getFromAccountNumber());
		lblCountTo = new JLabel(event.getToAccountNumber());		
		lblEcheance = new JLabel(String.valueOf(event.getDayOfMonth()));		
		lblDuration = new JLabel(String.valueOf(event.getDuration()==IRegularEvent.DURATION_INFINITE?"Pas d'échéance":event.getDuration()));		
		lblNewLabel = new JLabel("");
		lblNewLabel.setToolTipText("Supprimer");
		lblNewLabel.setIcon(Images.DELETE_25X25);
		lblNewLabel.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setIcon(Images.DELETE_25X25);
				controler.removeRegularEvent(lblLabel.getText());
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				lblNewLabel.setIcon(Images.DELETE_INACTIVE_25X25);
			}

			public void mouseReleased(MouseEvent e) {
				lblNewLabel.setIcon(Images.DELETE_25X25);
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see org.jco.countManager.gui.DrawableLine#unshow()
	 */
	public void unshow(){
		container.remove(lblLabel);
		container.remove(lblPrice);
		container.remove(lblCountFrom);
		container.remove(lblCountTo);
		container.remove(lblEcheance);
		container.remove(lblDuration);
		container.remove(lblNewLabel);
	}

	/* (non-Javadoc)
	 * @see org.jco.countManager.gui.DrawableLine#show(int)
	 */
	public void show(int index) {
		container.add(lblLabel);
		lblLabel.setBounds(10, 35+(index*30), 147, 14);
		container.add(lblPrice);
		lblPrice.setBounds(160, 35+(index*30), 147, 14);
		container.add(lblCountFrom);
		lblCountFrom.setBounds(310, 35+(index*30), 134, 14);
		container.add(lblCountTo);
		lblCountTo.setBounds(449, 35+(index*30), 141, 14);
		container.add(lblEcheance);
		lblEcheance.setBounds(595, 35+(index*30), 153, 14);
		container.add(lblDuration);
		lblDuration.setBounds(749, 35+(index*30), 108, 14);
		container.add(lblNewLabel);
		lblNewLabel.setBounds(1192, 30+(index*30), 25, 25);
	}
}
