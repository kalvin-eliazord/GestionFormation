package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Formation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//frame
	private static JFrame frameFormation = new JFrame();;

	//compositions du Tableau
	private static JTable jTableFormation = new JTable();
	private JScrollBar scrollBar = new JScrollBar();
	private JPanel panelFormation = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollBar scrollBarForma = new JScrollBar();
	private JTextField txtObjectif  = new JTextField();
	private JTextField txtCouts = new JTextField();;

	//bouttons
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Ins�rer");
	private JButton btnAcceuil = new JButton("Acceuil");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Pr�c�dent");
	private JButton btnSuivant = new JButton("Suivant");

	//labels
	private JLabel lblNumFor = new JLabel("numFormation");
	private JLabel lblObjectif = new JLabel("objectif");
	private JLabel lblCouts = new JLabel("couts");

	//jcombobox
	private static JComboBox<String> comboNumForma = new JComboBox<String>();

	//constructeur
	public Formation() {

		// param�trage de la fenetre
		frameFormation.setTitle("Formation");
		frameFormation.getContentPane().setLayout(null);
		frameFormation.setBounds(100, 100, 724, 476);
		frameFormation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFormation.getContentPane().setLayout(null);

		// param�trage du panel
		panelFormation.setBorder(new TitledBorder(null, "FORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFormation.setBounds(10, 96, 688, 272);
		frameFormation.getContentPane().add(panelFormation);
		panelFormation.setLayout(null);

		//// param�trage du scrollpane et du scrollbar
		scrollPane.setBounds(6, 16, 494, 250);
		panelFormation.add(scrollPane);
		scrollPane.setViewportView(jTableFormation);
		scrollPane.setRowHeaderView(scrollBarForma);

		//param�trage graphique du boutton insert
		btnInsert.setBounds(510, 77, 113, 23);
		panelFormation.add(btnInsert);

		//param�trage graphique du boutton update
		btnUpdate.setBounds(510, 111, 113, 23);
		panelFormation.add(btnUpdate);

		//param�trage graphique du boutton supprimer
		btnDelete.setBounds(510, 145, 113, 23);
		panelFormation.add(btnDelete);
		frameFormation.getContentPane().add(scrollBar, BorderLayout.EAST);

		//param�trage graphique du label numFormation
		lblNumFor.setBounds(35, 30, 86, 14);
		frameFormation.getContentPane().add(lblNumFor);

		//param�trage graphique du label Objectif
		lblObjectif.setBounds(187, 30, 65, 14);
		frameFormation.getContentPane().add(lblObjectif);

		//param�trage graphique du label couts
		lblCouts.setBounds(341, 30, 65, 14);
		frameFormation.getContentPane().add(lblCouts);

		//param�trage graphique du jtextfield objectif
		txtObjectif.setBounds(187, 55, 134, 20);
		frameFormation.getContentPane().add(txtObjectif);
		txtObjectif.setColumns(10);

		//param�trage graphique du jtextfield couts
		txtCouts.setBounds(341, 54, 134, 23);
		frameFormation.getContentPane().add(txtCouts);
		txtCouts.setColumns(10);

		//param�trage graphique du boutton Retour � l'acceuil
		btnAcceuil.setBounds(263, 391, 188, 23);
		frameFormation.getContentPane().add(btnAcceuil);

		//param�trage graphique du boutton pr�c�dent
		btnPrecedent.setBounds(10, 391, 188, 23);
		frameFormation.getContentPane().add(btnPrecedent);

		//param�trage graphique du boutton suivant
		btnSuivant.setBounds(524, 391, 153, 23);
		frameFormation.getContentPane().add(btnSuivant);

		//param�trage graphique du combobox numFormation
		comboNumForma.setBounds(35, 55, 119, 22);
		frameFormation.getContentPane().add(comboNumForma);

		//initialisation des collonnes du tableau 
		jTableFormation.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"numFormation", "nbPlaces", "objectif", "couts"} ));

		// action du boutton ins�rer
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui va ins�rer dans la table formation
				BDD.executeUpdate("INSERT INTO `formation`( `objectif`, `couts`) VALUES ('"+getTxtObjectif()+"',"+getTxtCouts()+")");	
				// mis � jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				jTableFormation.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));

				//mis � jour du jcombobox numFormation
				comboNumForma.removeAllItems();
				try {
					BDD.executeSelect("SELECT `numFormation` FROM `formation`");
					while (BDD.getRs().next()) {  
						Formation.getComboNumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//action boutton modifier
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui met � jour les informations dont le numFormation correspond
				BDD.executeUpdate("UPDATE `formation` SET `numFormation`="+getNumFormation()+", `objectif`='"+getTxtObjectif()+"', `couts`="+getTxtCouts()+" WHERE `numFormation`="+getNumFormation());
				// mis � jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				getJTableFor().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		//action du boutton supprimer
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui supprime les informations dont le numFormation correspond
				BDD.executeUpdate("DELETE FROM `formation` WHERE `numFormation`="+getNumFormation());
				// mis � jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				getJTableFor().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));

				//mis � jour du jcombobox numFormation
				comboNumForma.removeAllItems();
				try {
					BDD.executeSelect("SELECT `numFormation` FROM `formation`");
					while (BDD.getRs().next()) {  
						comboNumForma.addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//action du boutton acceuil
		btnAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//retour � l'accueil
				Acceuil.getFrameAcceuil().setVisible(true);
				Formation.getFrameFormation().setVisible(false);
			}
		});

		// action du bouton pr�c�dent
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frameFormation.setVisible(false);
				Lieu.getFrameLieu().setVisible(true);
			}
		});

		//action listener btn suivant
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//rend visible l'ihm session
				frameFormation.setVisible(false);
				Session.getSessionsFrame().setVisible(true);

				//mis � jour du tableau session
				BDD.executeSelect("SELECT * FROM `session`");
				Session.getJTableSess().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));

				//mis � jour des Jcombobox
				Session.getComboNumSess().removeAllItems();
				Session.getComboIdInter().removeAllItems();
				Session.getComboIdLieu().removeAllItems();
				Session.getComboNumForma().removeAllItems();
				try {

					BDD.executeSelect("SELECT `numSession` FROM `session`");
					while (BDD.getRs().next()) {  
						Session.getComboNumSess().addItem(Integer.toString(BDD.getRs().getInt("numSession")));  
					}

					BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
					while (BDD.getRs().next()) {  
						Session.getComboIdInter().addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
					}

					BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
					while (BDD.getRs().next()) {  
						Session.getComboIdLieu().addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
					}

					BDD.executeSelect("SELECT `numFormation` FROM `formation`");
					while (BDD.getRs().next()) {  
						Session.getComboNumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	//getters 
	public static JComboBox<String> getComboNumForma() {
		return comboNumForma;
	}

	public static String getNumFormation() {
		return comboNumForma.getSelectedItem().toString();
	}

	public String getTxtObjectif() {
		return txtObjectif.getText();
	}

	public String getTxtCouts() {
		return txtCouts.getText();
	}

	public static JFrame getFrameFormation() {
		return frameFormation;
	}

	public static JTable getJTableFor() {
		return jTableFormation;
	}

}