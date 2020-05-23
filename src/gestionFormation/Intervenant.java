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

public class Intervenant extends JFrame implements ActionListener {

	/**
	 * Cette classe permet d'ajouter, de modifier et de supprimer des
	 * champs dans la table Intervenant, elle permet aussi de revenir à l'IHM Session.
	 */
	
	private static final long serialVersionUID = 1L;

	private JFrame frameInter = new JFrame();;

	private JPanel panel = new JPanel();
	private JScrollPane scrollPanInter = new JScrollPane();
	private JTable tableIntervenant = new JTable();

	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom  = new JTextField();
	private JTextField txtTitre = new JTextField();;

	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnSession = new JButton("Retour Sessions");
	private JButton btnDelete = new JButton("Supprimer");

	private JLabel lbIdIntervenant = new JLabel("idIntervenant");
	private JLabel lblPrenom = new JLabel("prenom");
	private JLabel lblTitre = new JLabel("titre");
	private JLabel lblNom = new JLabel("nom");

	private static JComboBox<String> comboIdInter = new JComboBox<String>();

	public Intervenant() {

		tableIntervenant.setEnabled(false);

		frameInter.setTitle("Intervenant");
		frameInter.getContentPane().setLayout(null);
		frameInter.setBounds(100, 100, 724, 476);
		frameInter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameInter.getContentPane().setLayout(null);

		panel.setForeground(Color.BLUE);	
		panel.setBorder(new TitledBorder(null, "INTERVENANT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 96, 648, 272);
		frameInter.getContentPane().add(panel);
		panel.setLayout(null);

		scrollPanInter.setBounds(6, 16, 490, 250);
		panel.add(scrollPanInter);
		scrollPanInter.setViewportView(getTableInter());
		scrollPanInter.setViewportView(tableIntervenant);

		btnInsert.setBounds(510, 76, 111, 23);
		panel.add(btnInsert);

		btnUpdate.setBounds(510, 110, 111, 23);
		panel.add(btnUpdate);

		btnDelete.setBounds(510, 144, 111, 23);
		panel.add(btnDelete);

		lbIdIntervenant.setBounds(35, 30, 86, 14);
		frameInter.getContentPane().add(lbIdIntervenant);

		lblNom.setBounds(209, 30, 65, 14);
		frameInter.getContentPane().add(lblNom);

		lblPrenom.setBounds(366, 30, 65, 14);
		frameInter.getContentPane().add(lblPrenom);

		lblTitre.setBounds(524, 30, 65, 14);
		frameInter.getContentPane().add(lblTitre);

		txtNom.setBounds(203, 55, 134, 20);
		frameInter.getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtPrenom.setBounds(358, 55, 134, 20);
		frameInter.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);

		txtTitre.setBounds(524, 54, 134, 23);
		frameInter.getContentPane().add(txtTitre);
		txtTitre.setColumns(10);

		btnSession.setBounds(469, 379, 189, 23);
		frameInter.getContentPane().add(btnSession);

		comboIdInter.setBounds(35, 55, 134, 22);
		frameInter.getContentPane().add(comboIdInter);
		btnSession.addActionListener(this);
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {

			try {
				//requete qui supprime les champs dont l'idIntervenant correspond
				BDD.executeUpdate("DELETE FROM `intervenant` WHERE `idIntervenant`="+getStringInter());

				//mise à jour du tableau intervenant
				BDD.executeSelect("SELECT * FROM `intervenant`");
				getTableInter().setModel(BDD.buildTable(BDD.getRs()));

				//mis à jour du jcombobox
				comboIdInter.removeAllItems();
				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	

		} else if(event.getSource() == btnUpdate) {

			try {
				//requete qui met à jour les informations dont l'idIntervenant correspond
				BDD.executeUpdate("UPDATE `intervenant` "
						+ "SET `nom`='"+getTxtNom()+"', `prenom`='"+getTxtPrenom()+"', `titre`='"+getTxtTitre()+"' "
						+ "WHERE `idIntervenant`="+getStringInter());

				// mis à jour du tableau intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				getTableInter().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}	

		} else if(event.getSource() == btnInsert) {

			try {
				//insertion d'un champ dans la table intervenant
				BDD.executeUpdate("INSERT INTO `intervenant`( `nom`, `prenom`, `titre`) "
						+ "VALUES ('"+getTxtNom()+"', '"+getTxtPrenom()+"','"+getTxtTitre()+"')");	

				// mis à jour du tableau intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				getTableInter().setModel(BDD.buildTable(BDD.getRs()));

				//mis à jour du jcombobox
				comboIdInter.removeAllItems(); 

				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if(event.getSource() == btnSession) {
			//rend visible l'ihm session en libérant en mémoire l'ihm intervenant
			Session laSession = new Session();
			laSession.getFrameSession().setVisible(true);
			getFrameIntervenant().dispose();

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

	public String getStringInter() {
		return comboIdInter.getSelectedItem().toString();
	}

	public String getTxtNom() {
		return txtNom.getText();
	}

	public String getTxtPrenom() {
		return txtPrenom.getText();
	}

	public String getTxtTitre() {
		return txtTitre.getText();
	}

	public JFrame getFrameIntervenant() {
		return frameInter;
	}

	public JTable getTableInter() {
		return tableIntervenant;
	}

	public JComboBox<String> getComboIdInter() {
		return comboIdInter;
	}

}
