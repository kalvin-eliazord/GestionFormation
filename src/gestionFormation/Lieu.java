package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Lieu implements ActionListener {
	
	/**
	 * Cette classe permet d'ajouter, de modifier et de supprimer des
	 * champs dans la table Lieu, elle permet aussi de revenir à l'IHM Session.
	 */
	
	private JFrame frameLieu = new JFrame();

	private JTextField txtNomLieu = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCodePostal = new JTextField();
	private JTextField txtVille = new JTextField();

	private JLabel lblIdLieu = new JLabel("idLieu");
	private JLabel lblNomLieu = new JLabel("nomLieu");
	private JLabel lblAdresse = new JLabel("adresse");
	private JLabel lblCodePostal = new JLabel("codePostal");
	private JLabel lblVille = new JLabel("ville");

	private JTable tableLieu = new JTable();;
	private JPanel panelLieu = new JPanel();
	private JScrollPane scrollPaneLieu = new JScrollPane();

	private JButton btnInsert = new JButton("Insérer");
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnSession = new JButton("Retour Sessions");

	private JComboBox<String> comboIdLieu = new JComboBox<String>();

	public Lieu() {

		tableLieu.setEnabled(false);

		frameLieu.setTitle("Lieu");
		frameLieu.setBounds(100, 100, 724, 476);
		frameLieu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLieu.getContentPane().setLayout(null);

		panelLieu.setLayout(null);
		panelLieu.setForeground(Color.BLUE);
		panelLieu.setBorder(new TitledBorder(null, "LIEU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLieu.setBounds(10, 81, 713, 245);
		frameLieu.getContentPane().add(panelLieu);

		scrollPaneLieu.setBounds(6, 16, 574, 221);
		panelLieu.add(scrollPaneLieu);
		scrollPaneLieu.setViewportView(tableLieu);

		btnInsert.setBounds(579, 76, 113, 23);
		panelLieu.add(btnInsert);

		btnUpdate.setBounds(579, 110, 113, 23);
		panelLieu.add(btnUpdate);

		btnDelete.setBounds(579, 144, 113, 23);
		panelLieu.add(btnDelete);

		lblIdLieu.setBounds(10, 27, 86, 14);
		frameLieu.getContentPane().add(lblIdLieu);

		lblNomLieu.setBounds(116, 27, 65, 14);
		frameLieu.getContentPane().add(lblNomLieu);

		lblAdresse.setBounds(212, 27, 65, 14);
		frameLieu.getContentPane().add(lblAdresse);

		lblCodePostal.setBounds(308, 27, 89, 14);
		frameLieu.getContentPane().add(lblCodePostal);

		txtNomLieu.setColumns(10);
		txtNomLieu.setBounds(116, 40, 86, 20);
		frameLieu.getContentPane().add(txtNomLieu);

		txtAdresse.setColumns(10);
		txtAdresse.setBounds(212, 40, 86, 20);
		frameLieu.getContentPane().add(txtAdresse);

		txtCodePostal.setColumns(10);
		txtCodePostal.setBounds(308, 39, 86, 20);
		frameLieu.getContentPane().add(txtCodePostal);

		lblVille.setBounds(403, 27, 65, 14);
		frameLieu.getContentPane().add(lblVille);

		txtVille.setColumns(10);
		txtVille.setBounds(403, 40, 86, 20);
		frameLieu.getContentPane().add(txtVille);

		btnSession.setBounds(294, 372, 225, 23);
		frameLieu.getContentPane().add(btnSession);

		comboIdLieu.setBounds(10, 39, 96, 22);
		frameLieu.getContentPane().add(comboIdLieu);

		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnInsert.addActionListener(this);
		btnSession.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {

			try {
				//requete qui va supprimer un champ dans la table lieu
				BDD.executeUpdate("DELETE FROM `lieu` WHERE `idLieu`='"+getStringLieu()+"'");

				// mis à jour du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");
				getTableLieu().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			//mis à jour du jcombobox idLieu
			comboIdLieu.removeAllItems();
			try {
				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					comboIdLieu.addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnUpdate) {

			try {
				BDD.executeUpdate("UPDATE `lieu` "
						+ "SET `nomLieu`='"+getTxtNomLieu()+"',`adresse`='"+getTxtAdresse()+"',"
						+ "`codePostal`='"+getTxtCodePostal()+"',`ville`='"+getTxtVille()+"' "
						+ "WHERE `idLieu`='"+getStringLieu()+"'");

				// mis à jour du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");
				getTableLieu().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnInsert) {

			try {
				//requete qui va insérer un champ dans la table lieu
				BDD.executeUpdate("INSERT INTO `lieu`( `nomLieu`, `adresse`, `codePostal`, `ville`) "
						+ "VALUES ('"+getTxtNomLieu()+"','"+getTxtAdresse()+"',"+getTxtCodePostal()+",'"+getTxtVille()+"')");


				// mis à jour du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");
				getTableLieu().setModel(BDD.buildTable(BDD.getRs()));

				//mis à jour du jcombobox idLieu
				comboIdLieu.removeAllItems();
				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					comboIdLieu.addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnSession) {

			Session laSession = new Session();

			//rend visible l'ihm session
			frameLieu.setVisible(false);
			laSession.getFrameSession().setVisible(true);

			try {

				//mis à jour du tableau session
				BDD.executeSelect("SELECT * FROM `session`");
				laSession.getJTableSession().setModel(BDD.buildTable(BDD.getRs()));

				//mis à jour des Jcombobox
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
		}

	}

	public JFrame getFrameLieu() {
		return frameLieu;
	}

	public JTable getTableLieu() {
		return tableLieu;
	}

	public String getStringLieu() {
		return comboIdLieu.getSelectedItem().toString();
	}

	public JComboBox<String> getComboIdLieu() {
		return comboIdLieu;
	}

	public String getTxtNomLieu() {
		return txtNomLieu.getText();
	}

	public String getTxtAdresse() {
		return txtAdresse.getText();
	}

	public String getTxtCodePostal() {
		return txtCodePostal.getText();
	}

	public String getTxtVille() {
		return txtVille.getText();
	}

}
