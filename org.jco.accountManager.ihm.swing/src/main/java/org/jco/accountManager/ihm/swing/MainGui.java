package org.jco.accountManager.ihm.swing;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

import org.jco.accountManager.ihm.swing.controler.GuiControler;

public class MainGui {
	
	private JFrame frame;
	private JPanel countersPanel;

	private JTabbedPane tabbedPane;

	private GuiControler guiControler;
	private JLabel lblAddCount;
	private JLabel lblAddRegularEvent;
	private JPanel regularEventsPanel;

	/**
	 * Create the application.
	 */
	public MainGui(GuiControler guiControler) {
		this.guiControler = guiControler;
		initialize();
	}

	class BlackGlassPane extends JComponent {
	    /**
		 * 
		 */
		private static final long serialVersionUID = -1508008313472778183L;

		protected void paintComponent(Graphics g) {
	    	Rectangle clip = g.getClipBounds();
	    	g.setColor(new Color(0,0,0,100));
	        g.fillRect(clip.x, clip.y, clip.width, clip.height);
	    }
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(true);
		frame.setBounds(10, 10, 1226, 750);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				System.out.println("key pressed");
				
			}

			public void keyReleased(KeyEvent e) {
				System.out.println("key released");
				
			}

			public void keyTyped(KeyEvent e) {
				System.out.println("key typed");
				
			}
			
		});
		frame.addWindowListener(new WindowListener(){

			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosing(WindowEvent e) {
				if ( JOptionPane.showConfirmDialog(frame, "Attention : le travail non sauvegardé sera perdu !", "Fermeture", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, Images.WARNING_48X48) == JOptionPane.YES_OPTION){
			            System.exit(0);
			     }
			}

			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.setGlassPane(new BlackGlassPane());
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFichiers = new JMenu("Fichiers");
		menuBar.add(mnFichiers);
		
		JMenuItem menuItemNouveau = new JMenuItem("Nouveau");
		mnFichiers.add(menuItemNouveau);
		
		menuItemNouveau.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(frame,
						"Attention : le travail non sauvegardé sera perdu !",
						"Fermeture", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, Images.WARNING_48X48) == JOptionPane.YES_OPTION) {
					guiControler.newWork();
				}
			}
		});
		
		JSeparator separator_3 = new JSeparator();
		mnFichiers.add(separator_3);
		
		JMenuItem mntmChargerUnFichier = new JMenuItem("Ouvrir");
		mnFichiers.add(mntmChargerUnFichier);
		
		mntmChargerUnFichier.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(frame, "Attention : le travail non sauvegardé sera perdu !", "Ouverture d'un fichier", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, Images.WARNING_48X48) == JOptionPane.YES_OPTION) {
					JFileChooser fileChooser = new JFileChooser();
					if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
						guiControler.openFile(fileChooser.getSelectedFile().getAbsolutePath());
					}
				}
			}
			
		});
		
		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mnFichiers.add(mntmEnregistrer);
		mntmEnregistrer.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				guiControler.save();
			}
		});
		
		JMenuItem mntmEnregistrerSous = new JMenuItem("Enregistrer sous");
		mnFichiers.add(mntmEnregistrerSous);
		mntmEnregistrerSous.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				guiControler.saveAs();
			}
		});
		
		JSeparator separator = new JSeparator();
		mnFichiers.add(separator);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnFichiers.add(mntmQuitter);
		
		JMenu mnEdition = new JMenu("Edition");
		menuBar.add(mnEdition);
		
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JSeparator separator_4 = new JSeparator();
		mnAide.add(separator_4);
		
		JMenuItem mntmAPropos = new JMenuItem("A propos ...");
		mnAide.add(mntmAPropos);
	
		mntmQuitter.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(frame,
						"Attention : le travail non sauvegardé sera perdu !",
						"Fermeture", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, Images.WARNING_48X48) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	
		frame.getContentPane().setLayout(null);
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1222, 727);
		frame.getContentPane().add(tabbedPane);
		
		//---------------------------- Counter list tab ----------------------------//
		
		countersPanel = new JPanel();
		tabbedPane.addTab("Comptes", null, countersPanel, null);
		countersPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 2, 2);
		countersPanel.add(scrollPane);
		
		JLabel lblCountName = new JLabel("Nom");
		lblCountName.setBounds(10, 11, 46, 14);
		countersPanel.add(lblCountName);
		
		JLabel lblCountNumber = new JLabel("Num\u00E9ro");
		lblCountNumber.setBounds(133, 11, 46, 14);
		countersPanel.add(lblCountNumber);
		
		JLabel lblCountType = new JLabel("Type");
		lblCountType.setBounds(281, 11, 46, 14);
		countersPanel.add(lblCountType);
		
		JLabel lblCountSolde = new JLabel("Solde");
		lblCountSolde.setBounds(432, 11, 46, 14);
		countersPanel.add(lblCountSolde);
		
		JLabel lblCountTheoricSolde = new JLabel("Solde th\u00E9orique");
		lblCountTheoricSolde.setBounds(562, 8, 99, 20);
		countersPanel.add(lblCountTheoricSolde);
		
		JLabel lblCountActions = new JLabel("Actions");
		lblCountActions.setBounds(785, 11, 46, 14);
		countersPanel.add(lblCountActions);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 26, 1207, 2);
		countersPanel.add(separator_1);
		
				lblAddCount = new JLabel("");
				lblAddCount.setIcon(Images.ADD_25X25);
				lblAddCount.setToolTipText("Ajouter");
				lblAddCount.setBounds(1192, 0, 25, 25);
				lblAddCount.addMouseListener(new MouseListener(){
					
					public void mouseClicked(MouseEvent arg0) {
					}

					public void mouseEntered(MouseEvent arg0) {
					}

					public void mouseExited(MouseEvent arg0) {
					}

					public void mousePressed(MouseEvent arg0) {
						lblAddCount.setIcon(Images.ADD_INACTIVE_25X25);
						lblAddCount.repaint();
						
					}

					public void mouseReleased(MouseEvent arg0) {
						guiControler.openNewCounterWindow();
						lblAddCount.setIcon(Images.ADD_25X25);
						lblAddCount.repaint();
					}
					
				});
				countersPanel.add(lblAddCount);
		
		regularEventsPanel = new JPanel();
		tabbedPane.addTab("Ev\u00E8nements r\u00E9guliers", null, regularEventsPanel, null);
		regularEventsPanel.setLayout(null);
		
		JLabel lblRegularEventName = new JLabel("Label");
		lblRegularEventName.setBounds(10, 11, 46, 14);
		regularEventsPanel.add(lblRegularEventName);
		
		JLabel lblRegularEventComment = new JLabel("Date");
		lblRegularEventComment.setBounds(595, 11, 46, 14);
		regularEventsPanel.add(lblRegularEventComment);
		
		JLabel lblRegularEventDuration = new JLabel("Durée");
		lblRegularEventDuration.setBounds(749, 11, 46, 14);
		regularEventsPanel.add(lblRegularEventDuration);
		
		JLabel lblSolde = new JLabel("De");
		lblSolde.setBounds(310, 11, 46, 14);
		regularEventsPanel.add(lblSolde);
		
		JLabel lblSoldeThorique = new JLabel("Vers");
		lblSoldeThorique.setBounds(449, 8, 46, 20);
		regularEventsPanel.add(lblSoldeThorique);
		
		JLabel lblActions = new JLabel("Montant");
		lblActions.setBounds(160, 11, 46, 14);
		regularEventsPanel.add(lblActions);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 26, 1207, 2);
		regularEventsPanel.add(separator_2);

		lblAddRegularEvent = new JLabel("");
		lblAddRegularEvent.setIcon(Images.ADD_25X25);
		lblAddRegularEvent.setToolTipText("Ajouter");
		lblAddRegularEvent.setBounds(1192, 0, 25, 25);
		lblAddRegularEvent.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
				lblAddRegularEvent.setIcon(Images.ADD_INACTIVE_25X25);
				lblAddRegularEvent.repaint();
			}

			public void mouseReleased(MouseEvent arg0) {
				guiControler.openNewRegularEventWindow();
				lblAddRegularEvent.setIcon(Images.ADD_25X25);
				lblAddRegularEvent.repaint();
			}
			
		});
		regularEventsPanel.add(lblAddRegularEvent);
		
		JLabel lblActions_1 = new JLabel("Actions");
		lblActions_1.setBounds(920, 11, 46, 14);
		regularEventsPanel.add(lblActions_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(189, 730, 1021, 19);
		frame.getContentPane().add(progressBar);
		
		//---------------------------- Tabs ----------------------------//
		
	}

	public void setVisible() {
		this.frame.setVisible(true);
	}

	public JPanel getCountersPanel() {
		return this.countersPanel;
	}

	public JTabbedPane getTabbedPane() {
		return this.tabbedPane;
	}

	public void repaint() {
		this.countersPanel.repaint();
		this.tabbedPane.repaint();
	}

	public JPanel getRegularEventPanel() {
		return this.regularEventsPanel;
	}
	
	public void setInactive(){
		frame.getGlassPane().setVisible(true);
		frame.setEnabled(false);
		this.repaint();
	}
	
	public void setActive(){
		frame.getGlassPane().setVisible(false);
		frame.setEnabled(true);
		this.repaint();
	}

	public Component getFrame() {
		return this.frame;
	}
}
