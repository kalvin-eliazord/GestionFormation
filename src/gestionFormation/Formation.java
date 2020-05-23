package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class Formation extends JFrame implements ActionListener {

	/**
	 * Cette Classe permet d'ajouter, de modifier et de supprimer des champs 
	 * dans la table Formation et Concerner, elle permet aussi de revenir à l'IHM Session. 
	 */

	private static final long serialVersionUID = 1L;

	private JFrame frameFormation = new JFrame();;

	private JTable tableFormation = new JTable();
	private JTable tableConcerner = new JTable();

	private JScrollPane scrollPanFormation = new JScrollPane();
	private JScrollPane scrollPanConcerner = new JScrollPane();

	private JPanel panelFormation = new JPanel();
	private JPanel panelConcerner = new JPanel();

	private JTextField txtFieldObjectif = new JTextField();
	private JTextField txtFieldCout = new JTextField();

	private JButton btnUpdateFormation = new JButton("Modifier");
	private JButton btnInsertFormation = new JButton("Insérer");
	private JButton btnSession = new JButton("Retour Sessions");
	private JButton btnDeleteFormation = new JButton("Supprimer");
	private JButton btnInsertConcerner = new JButton("Insérer");
	private JButton btnUpdateConcerner = new JButton("Modifier");
	private JButton btnDeleteConcerner = new JButton("Supprimer");

	private JLabel lblNumFormaF = new JLabel("Formation.numFormation");
	private JLabel lblNumFormaC = new JLabel("Concerner.numFormation");
	private JLabel lblObjectif = new JLabel("objectif");
	private JLabel lblCout = new JLabel("cout");
	private JLabel lblIdStatus = new JLabel("idStatus");
	private JLabel lblIdStatusM = new JLabel("Pour modifier idStatus");
	private JLabel lblNumFormaM = new JLabel("Pour modifier numFormation");

	private JComboBox<String> comboIdStatus = new JComboBox<String>();
	private JComboBox<String> comboNumFormaF = new JComboBox<String>();
	private JComboBox<String> comboNumFormaC = new JComboBox<String>();
	private JComboBox<String> comboIdStatusM = new JComboBox<String>();
	private JComboBox<String> comboNumFormaM = new JComboBox<String>();

	public Formation() {

		//j'empêche la modification manuelle des jtables
		tableFormation.setEnabled(false);
		tableConcerner.setEnabled(false);

		frameFormation.setTitle("Formation / Concerner");
		frameFormation.setBounds(100, 100, 893, 702);
		frameFormation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFormation.getContentPane().setLayout(null);

		panelFormation.setBounds(10, 79, 840, 234);
		panelFormation.setForeground(Color.BLUE);
		panelFormation.setBorder(new TitledBorder(null, "FORMATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frameFormation.getContentPane().add(panelFormation);
		panelFormation.setLayout(null);

		scrollPanFormation.setBounds(10, 21, 699, 196);
		panelFormation.add(scrollPanFormation);
		scrollPanFormation.setViewportView(tableFormation);

		btnInsertFormation.setBounds(719, 69, 111, 23);
		panelFormation.add(btnInsertFormation);

		btnUpdateFormation.setBounds(719, 103, 111, 23);
		panelFormation.add(btnUpdateFormation);

		btnDeleteFormation.setBounds(719, 137, 111, 23);
		panelFormation.add(btnDeleteFormation);
		lblNumFormaF.setBounds(7, 23, 157, 14);

		frameFormation.getContentPane().add(lblNumFormaF);
		lblObjectif.setBounds(174, 23, 139, 14);

		frameFormation.getContentPane().add(lblObjectif);
		lblNumFormaC.setBounds(174, 335, 157, 14);

		frameFormation.getContentPane().add(lblNumFormaC);
		txtFieldObjectif.setBounds(174, 36, 104, 20);

		frameFormation.getContentPane().add(txtFieldObjectif);
		txtFieldObjectif.setColumns(10);

		btnSession.setBounds(274, 629, 260, 23);
		frameFormation.getContentPane().add(btnSession);

		lblCout.setBounds(323, 23, 110, 14);
		frameFormation.getContentPane().add(lblCout);

		txtFieldCout.setBounds(323, 36, 129, 20);
		txtFieldCout.setColumns(10);
		frameFormation.getContentPane().add(txtFieldCout);

		lblIdStatus.setBounds(10, 335, 119, 14);
		frameFormation.getContentPane().add(lblIdStatus);

		comboIdStatus.setBounds(10, 348, 126, 22);
		frameFormation.getContentPane().add(comboIdStatus);

		comboNumFormaC.setBounds(174, 348, 130, 22);
		frameFormation.getContentPane().add(comboNumFormaC);

		panelConcerner.setBounds(10, 380, 840, 234);
		panelConcerner.setForeground(Color.BLUE);
		panelConcerner.setBorder(new TitledBorder(null, "CONCERNER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frameFormation.getContentPane().add(panelConcerner);
		panelConcerner.setLayout(null);

		scrollPanConcerner.setBounds(10, 21, 703, 200);
		panelConcerner.add(scrollPanConcerner);
		scrollPanConcerner.setViewportView(tableConcerner);

		btnInsertConcerner.setBounds(723, 70, 107, 23);
		panelConcerner.add(btnInsertConcerner);

		btnUpdateConcerner.setBounds(723, 104, 107, 23);
		panelConcerner.add(btnUpdateConcerner);

		btnDeleteConcerner.setBounds(723, 140, 107, 23);
		panelConcerner.add(btnDeleteConcerner);

		comboNumFormaF.setBounds(10, 35, 130, 22);
		frameFormation.getContentPane().add(comboNumFormaF);

		lblNumFormaM.setBounds(597, 335, 192, 14);
		frameFormation.getContentPane().add(lblNumFormaM);

		lblIdStatusM.setBounds(434, 333, 176, 18);
		frameFormation.getContentPane().add(lblIdStatusM);

		comboNumFormaM.setBounds(597, 348, 139, 22);
		frameFormation.getContentPane().add(comboNumFormaM);

		comboIdStatusM.setBounds(434, 348, 130, 22);
		frameFormation.getContentPane().add(comboIdStatusM);

		btnDeleteFormation.addActionListener(this);
		btnUpdateFormation.addActionListener(this);
		btnInsertFormation.addActionListener(this);
		btnSession.addActionListener(this);
		btnDeleteConcerner.addActionListener(this);
		btnUpdateConcerner.addActionListener(this);
		btnInsertConcerner.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDeleteFormation) {

			try {	
				//requete qui va supprimer tout les champs dont le numFormation correspond dans la table concerner
				BDD.executeUpdate("DELETE FROM concerner WHERE numFormation ='"+getStringComboNumFormaF()+"'");

				// mis à jour du tableau concerner
				BDD.executeSelect("SELECT concerner.idStatus, STATUS.libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS.idStatus");

				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

				//requete qui supprime le champ dont le numFormation correspond dans la table formation
				BDD.executeUpdate("DELETE FROM `formation` WHERE `numFormation`="+getStringComboNumFormaF());

				// mis à jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

				//je vide les jcombobox que je vais remplir avant pour pas qu'il y ai de doublons
				getComboNumFormaF().removeAllItems();
				getComboNumFormaC().removeAllItems();
				getComboConcerner_NumFormaM().removeAllItems();

				//requete qui va récupérer tout les numFormation
				BDD.executeSelect("SELECT `numFormation` FROM `formation` ORDER BY `numFormation`");

				//insertion des champs de la requete dans les jcombobox
				while (BDD.getRs().next()) {  
					getComboNumFormaC().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					getComboNumFormaF().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					getComboConcerner_NumFormaM().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if(event.getSource() == btnUpdateFormation) {

			try {
				//requete qui met à jour les champs dont le numFormation correspond dans la table Formation
				BDD.executeUpdate("UPDATE `formation` "
						+ "SET `objectif`='"+getTxtFieldObjectif()+"', `couts`="+getTxtFieldCouts()+" "
						+ "WHERE `numFormation`="+getStringComboNumFormaF());

				//mis à jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == btnInsertFormation) {

			try {
				//requete qui va insérer dans la table formation
				BDD.executeUpdate("INSERT INTO `formation`( `objectif`, `couts`) VALUES ('"+getTxtFieldObjectif()+"',"+getTxtFieldCouts()+")");	

				// mis à jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

				//je vide les jcombobox que je vais remplir avant pour pas qu'il y ai de doublons
				getComboNumFormaF().removeAllItems();
				getComboNumFormaC().removeAllItems();
				getComboConcerner_NumFormaM().removeAllItems();

				//je récupère les numFormation puis je les insère dans les jcombobx
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					getComboNumFormaF().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					getComboNumFormaC().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					getComboConcerner_NumFormaM().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

				//requete qui va récupérer les champs de la table concerner et leurs appellations respectives
				// qui sont dans la table status et formation
				BDD.executeSelect("SELECT concerner.idStatus, STATUS.libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS.idStatus");
				
				//mise à jour du tableau concerner
				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if(event.getSource() == btnSession) {

			Session laSession = new Session();

			//passage au frame session en libérant en mémoire l'ihm formation
			laSession.getFrameSession().setVisible(true);
			frameFormation.dispose();

			try {
				//mis à jour du tableau session
				BDD.executeSelect("SELECT * FROM `session`");
				laSession.getJTableSession().setModel(BDD.buildTable(BDD.getRs()));

				//mise à jour des jcombobox
				laSession.getComboNumSess().removeAllItems();
				laSession.getComboIdInter().removeAllItems();
				laSession.getComboIdLieu().removeAllItems();
				laSession.getComboNumForma().removeAllItems();
				BDD.executeSelect("SELECT `numSession` FROM `session` ORDER BY `numSession");
				while (BDD.getRs().next()) {  
					laSession.getComboNumSess().addItem(Integer.toString(BDD.getRs().getInt("numSession")));  
				}

				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					laSession.getComboIdInter().addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}

				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					laSession.getComboIdLieu().addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					laSession.getComboNumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnDeleteConcerner) {

			try {
				//requete qui va supprimer le champ dont l'idStatus et le numFormation correspond
				BDD.executeUpdate("DELETE FROM `concerner`"
						+ " WHERE `idStatus`='"+getStringComboIdStatus()+"'"
						+ " AND `numFormation`='"+getStringComboNumFormaC()+"'");	

				//rafraichissement du tableau concerner
				BDD.executeSelect("SELECT concerner.idStatus, STATUS.libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS.idStatus");

				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnUpdateConcerner) {	

			try {
				//requete qui mettre à jour les informations dont le numFormation et l'idStatus correspond
				BDD.executeUpdate("UPDATE `concerner` "
						+ "SET `idStatus`='"+getStringComboIdStatusM()+"', `numFormation`="+getStringComboConcerner_NumFormaM()+" "
						+ "WHERE `idStatus`="+getStringComboIdStatus()+" AND `numFormation`="+getStringComboNumFormaC());

				// mis à jour du tableau concerner
				BDD.executeSelect("SELECT concerner.idStatus, STATUS.libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS.idStatus");

				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnInsertConcerner) {	

			try {
				//requete qui va insérer un champ dans la table concerner
				BDD.executeUpdate("INSERT INTO `concerner`"
						+ "( `idStatus`, `numFormation`) "
						+ "VALUES ('"+getStringComboIdStatus()+"',"+getStringComboNumFormaC()+")");	

				// mis à jour du tableau concerner
				BDD.executeSelect("SELECT concerner.idStatus, STATUS.libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS.idStatus");

				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private String getStringComboNumFormaF() {
		return comboNumFormaF.getSelectedItem().toString();
	}

	private String getTxtFieldCouts() {
		return txtFieldCout.getText();
	}

	private String getTxtFieldObjectif() {
		return txtFieldObjectif.getText();
	}

	public String getStringComboConcerner_NumFormaM() {
		return comboNumFormaM.getSelectedItem().toString();
	}

	public String getStringComboIdStatusM() {
		return comboIdStatusM.getSelectedItem().toString();
	}

	public String getStringComboNumFormaC() {
		return comboNumFormaC.getSelectedItem().toString();
	}

	public String getStringComboIdStatus() {
		return comboIdStatus.getSelectedItem().toString();
	}

	public JFrame getFrameFormation() {
		return frameFormation;
	}

	public JTable getJTableFormation() {
		return tableFormation;
	}

	public JTable getJTableConcerner() {
		return tableConcerner;
	}

	public JComboBox<String> getComboIdStatus() {
		return comboIdStatus;
	}

	public JComboBox<String> getComboNumFormaF() {
		return comboNumFormaF;
	}

	public JComboBox<String> getComboNumFormaC() {
		return comboNumFormaC;
	}

	public JComboBox<String> getComboConcerner_NumFormaM() {
		return comboNumFormaM;
	}

	public JComboBox<String> getComboConcerner_IdStatusM() {
		return comboIdStatusM;
	}
}
