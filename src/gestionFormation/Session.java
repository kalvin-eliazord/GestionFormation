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

public class Session extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame sessionsFrame = new JFrame();;

	private JTable tableSessions = new JTable();
	private JScrollPane scrollPanSess = new JScrollPane();
	private JPanel panel_sessions = new JPanel();

	private JTextField txtDateDeLimite = new JTextField();
	private JTextField txtDateDeFin  = new JTextField();
	private JTextField txtDateSess = new JTextField();
	private JTextField txtNbPlaces = new JTextField();

	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnDeconnexion = new JButton("Déconnexion");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnLieu = new JButton("Lieu");
	private JButton btnFormation = new JButton("Formation");
	private JButton btnIntervenant = new JButton("Intervenant");

	private JLabel lblNumSession = new JLabel("numSession");
	private JLabel lblDateFin = new JLabel("dateDeFin");
	private JLabel lblTitre = new JLabel("idIntervenant");
	private JLabel lblNom = new JLabel("dateLimitInsc");
	private JLabel lblIdLieuSess = new JLabel("idLieu");
	private JLabel lblDateSess = new JLabel("dateSession");
	private JLabel lblNumForma = new JLabel("numFormation");
	private JLabel lblNbPlaces = new JLabel("nbPlaces");

	private static JComboBox<String> comboIdInter = new JComboBox<String>();
	private static JComboBox<String> comboIdLieu = new JComboBox<String>();
	private static JComboBox<String> comboNumForma = new JComboBox<String>();
	private static JComboBox<String> comboNumSess = new JComboBox<String>();

	private Lieu leLieu;
	private Intervenant lIntervenant;
	private Formation laFormation;

	public Session() {

		leLieu = new Lieu();
		lIntervenant = new Intervenant();
		laFormation = new Formation();

		sessionsFrame.setTitle("Session");
		sessionsFrame.setBounds(100, 100, 893, 437);
		sessionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sessionsFrame.getContentPane().setLayout(null);
		sessionsFrame.getContentPane().setLayout(null);

		panel_sessions.setBounds(10, 78, 826, 245);
		panel_sessions.setForeground(Color.BLUE);
		panel_sessions.setBorder(new TitledBorder(null, "SESSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sessionsFrame.getContentPane().add(panel_sessions);
		panel_sessions.setLayout(null);

		scrollPanSess.setBounds(6, 16, 645, 218);
		panel_sessions.add(scrollPanSess);
		scrollPanSess.setViewportView(tableSessions);

		btnInsert.setBounds(661, 68, 155, 23);
		panel_sessions.add(btnInsert);

		btnUpdate.setBounds(661, 102, 155, 23);
		panel_sessions.add(btnUpdate);

		btnDelete.setBounds(661, 136, 155, 23);
		panel_sessions.add(btnDelete);

		lblNumSession.setBounds(10, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNumSession);

		lblNom.setBounds(116, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNom);

		lblDateFin.setBounds(311, 23, 65, 14);
		sessionsFrame.getContentPane().add(lblDateFin);

		lblTitre.setBounds(407, 23, 89, 14);
		sessionsFrame.getContentPane().add(lblTitre);

		txtDateDeLimite.setBounds(116, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtDateDeLimite);
		txtDateDeLimite.setColumns(10);

		txtDateDeFin.setBounds(311, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtDateDeFin);
		txtDateDeFin.setColumns(10);

		btnDeconnexion.setBounds(576, 346, 260, 23);
		sessionsFrame.getContentPane().add(btnDeconnexion);

		lblIdLieuSess.setBounds(513, 23, 65, 14);
		sessionsFrame.getContentPane().add(lblIdLieuSess);

		lblDateSess.setBounds(212, 23, 89, 14);
		sessionsFrame.getContentPane().add(lblDateSess);

		txtDateSess.setColumns(10);
		txtDateSess.setBounds(212, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtDateSess);

		lblNumForma.setBounds(612, 23, 105, 14);
		sessionsFrame.getContentPane().add(lblNumForma);

		btnLieu.setBounds(192, 346, 164, 23);
		sessionsFrame.getContentPane().add(btnLieu);

		comboIdInter.setBounds(407, 35, 89, 22);
		sessionsFrame.getContentPane().add(comboIdInter);

		comboIdLieu.setBounds(512, 35, 90, 22);
		sessionsFrame.getContentPane().add(comboIdLieu);

		comboNumForma.setBounds(613, 35, 86, 22);
		sessionsFrame.getContentPane().add(comboNumForma);

		txtNbPlaces.setColumns(10);
		txtNbPlaces.setBounds(709, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtNbPlaces);

		lblNbPlaces.setBounds(709, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNbPlaces);

		comboNumSess.setBounds(10, 35, 96, 22);
		sessionsFrame.getContentPane().add(comboNumSess);

		btnIntervenant.setBounds(10, 346, 164, 23);
		sessionsFrame.getContentPane().add(btnIntervenant);

		btnFormation.setBounds(366, 346, 164, 23);
		sessionsFrame.getContentPane().add(btnFormation);

		btnDeconnexion.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnInsert.addActionListener(this);
		btnLieu.addActionListener(this);
		btnIntervenant.addActionListener(this);
		btnFormation.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {

			//requete qui va supprimer les champs où la numSession fait référence dans la table inscriptions 
			BDD.executeUpdate("DELETE FROM `inscription` WHERE `numSession`="+getStringNumSess());
			//requete qui supprime les champs dont la numSession correspond
			BDD.executeUpdate("DELETE FROM `session` WHERE `numSession`="+getStringNumSess());
			BDD.executeSelect("SELECT * FROM `session`");

			try {
				tableSessions.setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}	

			//mis à jour des Jcombobox
			comboNumSess.removeAllItems();
			comboIdInter.removeAllItems();
			comboIdLieu.removeAllItems();
			comboNumForma.removeAllItems();
			try {

				BDD.executeSelect("SELECT `numSession` FROM `session`");
				while (BDD.getRs().next()) {  
					comboNumSess.addItem(Integer.toString(BDD.getRs().getInt("numSession")));  
				}

				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}

				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					comboIdLieu.addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					comboNumForma.addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnUpdate) {

			//requete qui met à jour les informations
			BDD.executeUpdate("UPDATE `session` SET `dateLimiteInscription`='"+getDateLimit()+"', `dateSession`='"+getDateSess()+"', `dateFinSession`='"+getDateDeFin()+"', `idIntervenant`='"+getIdIntervenant()+"',`idLieu`='"+getIdLieu()+"', `numFormation`='"+getNumFormation()+"', `nbPlaces`='"+getTxtNbPlaces()+"'  WHERE `numSession`='"+getStringNumSess()+"'");
			// mis à jour du tableau 
			BDD.executeSelect("SELECT * FROM `session`");

			try {
				tableSessions.setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}	

			//mis à jour des Jcombobox
			comboNumSess.removeAllItems();
			comboIdInter.removeAllItems();
			comboIdLieu.removeAllItems();
			comboNumForma.removeAllItems();
			try {

				BDD.executeSelect("SELECT `numSession` FROM `session`");
				while (BDD.getRs().next()) {  
					comboNumSess.addItem(Integer.toString(BDD.getRs().getInt("numSession")));  
				}

				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}

				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					comboIdLieu.addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					comboNumForma.addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

		} else if(event.getSource() == btnInsert) {

			//insertion des champs dans la table sessions
			BDD.executeUpdate("INSERT INTO `session`"
					+ "( `dateLimiteInscription`, "
					+ "`dateSession`, `dateFinSession`, `idIntervenant`, "
					+ "`idLieu`, `numFormation`, `nbPlaces`) "
					+ "VALUES ('"+getDateLimit()+"','"+getDateSess()+"',"
					+ "'"+getDateDeFin()+"','"+getIdIntervenant()+"',"
					+ "'"+getIdLieu()+"','"+getNumFormation()+"','"+getTxtNbPlaces()+"');");

			// mis à jour des tableaux
			BDD.executeSelect("SELECT * FROM `session`");

			try {
				tableSessions.setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			//mis à jour des Jcombobox
			comboNumSess.removeAllItems();
			comboIdInter.removeAllItems();
			comboIdLieu.removeAllItems();
			comboNumForma.removeAllItems();
			try {

				BDD.executeSelect("SELECT `numSession` FROM `session`");
				while (BDD.getRs().next()) {  
					comboNumSess.addItem(Integer.toString(BDD.getRs().getInt("numSession")));  
				}

				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}

				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					comboIdLieu.addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					comboNumForma.addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnDeconnexion) {

			getFrameSession().dispose();
			Connexion laConnexion = new Connexion();

			//passage au frame connexion
			laConnexion.getFrameConnexion().setVisible(true);

		} else if(event.getSource() == btnIntervenant) {

			getFrameSession().dispose();
			lIntervenant.getFrameIntervenant().setVisible(true);

			try {
				//requete qui va initialiser le tableau des intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				lIntervenant.getTableInter().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			//mis à jour du jcombobox
			lIntervenant.getComboIdInter().removeAllItems();
			try {
				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					lIntervenant.getComboIdInter().addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}


		} else if(event.getSource() == btnLieu) {

			getFrameSession().dispose();
			leLieu.getFrameLieu().setVisible(true);

			try {
				//initialisation des champs du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");	
				leLieu.getTableLieu().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox
			leLieu.getComboIdLieu().removeAllItems(); 
			try {
				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					leLieu.getComboIdLieu().addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnFormation ){

			getFrameSession().dispose();
			laFormation.getFrameFormation().setVisible(true);

			try {
				//mis à jour du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");
				laFormation.getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			//mis à jour des jcombobox numFormation concerner et formation
			laFormation.getComboFormation_NumForma().removeAllItems();
			laFormation.getComboConcerner_NumForma().removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					laFormation.getComboFormation_NumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

			try {
				//requete qui va initialiser la table concerner en affichant le nom du status et l'objectif de la formation associé
				BDD.executeSelect("SELECT * FROM concerner");
				laFormation.getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//mis à jour des jcombobox numFormation et idStatus
			laFormation.getComboConcerner_NumForma().removeAllItems();
			laFormation.getComboIdStatus().removeAllItems();
			try {

				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					laFormation.getComboConcerner_NumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  

				}

				BDD.executeSelect("SELECT `idStatus` FROM `status`");
				while (BDD.getRs().next()) {  
					laFormation.getComboIdStatus().addItem(Integer.toString(BDD.getRs().getInt("idStatus")));  

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	public String getStringNumSess() {
		return comboNumSess.getSelectedItem().toString();
	}

	public String getDateLimit() {
		return txtDateDeLimite.getText();
	}

	public String getDateSess() {
		return txtDateSess.getText();
	}

	public String getDateDeFin() {
		return txtDateDeFin.getText();
	}

	public String getIdLieu() {
		return comboIdLieu.getSelectedItem().toString();
	}

	public String getIdIntervenant() {
		return comboIdInter.getSelectedItem().toString();
	}

	public String getNumFormation() {
		return comboNumForma.getSelectedItem().toString();
	}

	public String getTxtNbPlaces() {
		return txtNbPlaces.getText();
	}

	public JFrame getFrameSession() {
		return sessionsFrame;
	}

	public JTable getJTableSess() {
		return tableSessions;
	}

	public JComboBox<String> getComboIdInter() {
		return comboIdInter;
	}

	public JComboBox<String> getComboIdLieu() {
		return comboIdLieu;
	}

	public JComboBox<String> getComboNumForma() {
		return comboNumForma;
	}

	public JComboBox<String> getComboNumSess() {
		return comboNumSess;
	}
}
