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
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class Session extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//frame
	private static JFrame sessionsFrame = new JFrame();;

	//tableaux et scroll
	private static JTable tableSessions = new JTable();
	private JScrollPane scrollPanSess = new JScrollPane();
	private JPanel panel_sessions = new JPanel();
	private JScrollBar scrollBarSess = new JScrollBar();

	//JTextfields
	private JTextField txtDateDeLimite = new JTextField();
	private JTextField txtDateDeFin  = new JTextField();
	private JTextField txtDateSess = new JTextField();
	private JTextField txtNbPlaces = new JTextField();

	//bouttons
	private JButton btnUpdateSess = new JButton("Modifier");
	private JButton btnInsertSess = new JButton("Insérer");
	private JButton btnAcceuil = new JButton("Retourner à l'acceuil");
	private JButton btnDeleteSess = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Précédent");

	//labels
	private JLabel lblNumSession = new JLabel("numSession");
	private JLabel lblDateFin = new JLabel("dateDeFin");
	private JLabel lblTitre = new JLabel("idIntervenant");
	private JLabel lblNom = new JLabel("dateLimitInsc");
	private JLabel lblIdLieuSess = new JLabel("idLieu");
	private JLabel lblDateSess = new JLabel("dateSession");
	private JLabel lblNumForma = new JLabel("numFormation");
	private JLabel lblNbPlaces = new JLabel("nbPlaces");

	//Jcombobox
	private static JComboBox<String> comboIdInter = new JComboBox<String>();
	private static JComboBox<String> comboIdLieu = new JComboBox<String>();
	private static JComboBox<String> comboNumForma = new JComboBox<String>();
	private static JComboBox<String> comboNumSess = new JComboBox<String>();

	//constructeur
	public Session() {

		//paramétrage graphique du frame
		sessionsFrame.setTitle("Session");
		sessionsFrame.setBounds(100, 100, 893, 437);
		sessionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sessionsFrame.getContentPane().setLayout(null);
		sessionsFrame.getContentPane().setLayout(null);

		//paramétrage graphique du panel
		panel_sessions.setBounds(10, 78, 826, 245);
		panel_sessions.setForeground(Color.BLUE);
		panel_sessions.setBorder(new TitledBorder(null, "SESSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sessionsFrame.getContentPane().add(panel_sessions);
		panel_sessions.setLayout(null);

		//paramétrage graphique du scrollpane
		scrollPanSess.setBounds(6, 16, 645, 218);
		panel_sessions.add(scrollPanSess);
		scrollPanSess.setViewportView(tableSessions);
		scrollPanSess.setRowHeaderView(scrollBarSess);

		//paramétrage graphique du boutton insérer
		btnInsertSess.setBounds(661, 68, 155, 23);
		panel_sessions.add(btnInsertSess);

		//paramétrage graphique du boutton modifier
		btnUpdateSess.setBounds(661, 102, 155, 23);
		panel_sessions.add(btnUpdateSess);

		//paramétrage graphique du boutton supprimer
		btnDeleteSess.setBounds(661, 136, 155, 23);
		panel_sessions.add(btnDeleteSess);

		//paramétrage graphique du label numSession
		lblNumSession.setBounds(20, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNumSession);

		//paramétrage graphique du label nom
		lblNom.setBounds(116, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNom);

		//paramétrage graphique du label dateFin
		lblDateFin.setBounds(311, 23, 65, 14);
		sessionsFrame.getContentPane().add(lblDateFin);

		//paramétrage graphique du label titre
		lblTitre.setBounds(407, 23, 89, 14);
		sessionsFrame.getContentPane().add(lblTitre);

		//paramétrage graphique du textfield dateDeLimite
		txtDateDeLimite.setBounds(116, 36, 86, 20);
		txtDateDeLimite.setToolTipText("");
		sessionsFrame.getContentPane().add(txtDateDeLimite);
		txtDateDeLimite.setColumns(10);

		//paramétrage graphique du textfield dateDeFin
		txtDateDeFin.setBounds(311, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtDateDeFin);
		txtDateDeFin.setColumns(10);

		//paramétrage graphique du boutton acceuil
		btnAcceuil.setBounds(536, 346, 260, 23);
		sessionsFrame.getContentPane().add(btnAcceuil);

		//paramétrage graphique du label idLieu
		lblIdLieuSess.setBounds(513, 23, 65, 14);
		sessionsFrame.getContentPane().add(lblIdLieuSess);

		//paramétrage graphique du label dateSession
		lblDateSess.setBounds(212, 23, 89, 14);
		sessionsFrame.getContentPane().add(lblDateSess);
		txtDateSess.setColumns(10);
		txtDateSess.setBounds(212, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtDateSess);

		//paramétrage graphique du label numFormation
		lblNumForma.setBounds(612, 23, 105, 14);
		sessionsFrame.getContentPane().add(lblNumForma);

		//paramétrage graphique du boutton précédent
		btnPrecedent.setBounds(20, 346, 164, 23);
		sessionsFrame.getContentPane().add(btnPrecedent);

		//paramétrage graphique du jcombobox idIntervenant
		comboIdInter.setBounds(407, 35, 89, 22);
		sessionsFrame.getContentPane().add(comboIdInter);

		//paramétrage graphique du jcombobox idLieu
		comboIdLieu.setBounds(512, 35, 90, 22);
		sessionsFrame.getContentPane().add(comboIdLieu);

		//paramétrage graphique du jcombobox numFormation
		comboNumForma.setBounds(613, 35, 86, 22);
		sessionsFrame.getContentPane().add(comboNumForma);

		//paramétrage graphique du jtxtfield nbPlaces
		txtNbPlaces.setColumns(10);
		txtNbPlaces.setBounds(709, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtNbPlaces);

		//paramétrage graphique du label nbPlaces
		lblNbPlaces.setBounds(709, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNbPlaces);

		//paramétrage graphique du jcombobox numSession
		comboNumSess.setBounds(10, 35, 96, 22);
		sessionsFrame.getContentPane().add(comboNumSess);

		//ajout des collonnes au tableau sessions
		tableSessions.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"numSession", "dateLimiteInsc", "dateSession", "dateDeFin", "idIntervenant", "idLieu","numFormation", "nbPlaces"
				}
				));

		//ajout d'un actionListener sur le boutton retourner à l'acceuil
		btnAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage au frame acceuil
				Acceuil.getFrameAcceuil().setVisible(true);
				sessionsFrame.setVisible(false);		
			}
		});

		//ajout d'un actionListener sur le boutton Delete
		btnDeleteSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui supprime les champs dont la numSession correspond
				BDD.executeUpdate("DELETE FROM `session` WHERE `numSession`="+getStringNumSess());
				BDD.executeSelect("SELECT * FROM `session`");
				tableSessions.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));	

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//ajout d'un actionListener sur le boutton Update
		btnUpdateSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui met à jour les informations
				BDD.executeUpdate("UPDATE `session` SET `dateLimiteinsc`='"+getDateLimit()+"', `dateSession`='"+getDateSess()+"', `dateDeFin`='"+getDateDeFin()+"', `idIntervenant`='"+getIdIntervenant()+"',`idLieu`='"+getIdLieu()+"', `numFormation`='"+getNumFormation()+"', `nbPlaces`='"+getTxtNbPlaces()+"'  WHERE `numSession`='"+getStringNumSess()+"'");
				// mis à jour du tableau 
				BDD.executeSelect("SELECT * FROM `session`");
				tableSessions.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));	

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//ajout d'un actionListener sur le boutton insert
		btnInsertSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//insertion des champs dans la table sessions
				BDD.executeUpdate("INSERT INTO `session`( `numSession`, `dateLimiteInsc`, `dateSession`, `dateDeFin`, `idIntervenant`, `idLieu`, `numFormation`, `nbPlaces`) VALUES (NULL, '"+getDateLimit()+"','"+getDateSess()+"','"+getDateDeFin()+"','"+getIdIntervenant()+"','"+getIdLieu()+"','"+getNumFormation()+"','"+getTxtNbPlaces()+"');");
				// mis à jour des tableaux
				BDD.executeSelect("SELECT * FROM `session`");
				tableSessions.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));

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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//action du boutton précédent
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage au frame formation
				Formation.getFrameFormation().setVisible(true);
				sessionsFrame.setVisible(false);
			}
		});

	}

	//getters
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

	public static JFrame getSessionsFrame() {
		return sessionsFrame;
	}

	public static JTable getJTableSess() {
		return tableSessions;
	}

	public static JComboBox<String> getComboIdInter() {
		return comboIdInter;
	}

	public static JComboBox<String> getComboIdLieu() {
		return comboIdLieu;
	}

	public static JComboBox<String> getComboNumForma() {
		return comboNumForma;
	}

	public static JComboBox<String> getComboNumSess() {
		return comboNumSess;
	}

}
