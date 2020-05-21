package gestionFormation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;

public class Concerner implements ActionListener {

	private static JFrame frameConcerner = new JFrame();

	private static JTable jtableConcerner = new JTable();

	private JPanel panelConcerner = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();

	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnAccueil = new JButton("Accueil");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Précédent");
	private JButton btnSuivant = new JButton("Suivant");

	private JLabel lblNumFormation = new JLabel("numFormation");
	private JLabel lblIdStatus = new JLabel("idStatus");
	private JLabel lblIdStatus2 = new JLabel("idStatus");
	private JLabel lblNumFormation2 = new JLabel("numFormation");

	private static JComboBox<String> comboNumFormation = new JComboBox<String>();
	private static JComboBox<String> comboIdStatus = new JComboBox<String>();
	
	private JTextField txtIdStatus = new JTextField();
	private JTextField txtNumFormation = new JTextField();

	public Concerner() {

		frameConcerner.setTitle("Définir le status pour la formation adéquate");

		frameConcerner.setBounds(100, 100, 724, 476);
		frameConcerner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameConcerner.getContentPane().setLayout(null);
		panelConcerner.setBounds(10, 96, 688, 272);

		panelConcerner.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frameConcerner.getContentPane().add(panelConcerner);
		panelConcerner.setLayout(null);

		scrollPane.setBounds(6, 16, 536, 250);
		panelConcerner.add(scrollPane);
		scrollPane.setViewportView(jtableConcerner);

		btnInsert.setBounds(552, 111, 113, 23);
		panelConcerner.add(btnInsert);

		btnDelete.setBounds(552, 145, 113, 23);
		panelConcerner.add(btnDelete);
		
		lblNumFormation.setBounds(183, 30, 86, 14);
		frameConcerner.getContentPane().add(lblNumFormation);
		
		lblIdStatus.setBounds(34, 30, 65, 14);
		frameConcerner.getContentPane().add(lblIdStatus);
		
		comboIdStatus.setBounds(34, 55, 92, 20);
		frameConcerner.getContentPane().add(comboIdStatus);
		
		btnAccueil.setBounds(263, 391, 188, 23);
		frameConcerner.getContentPane().add(btnAccueil);
		
		btnPrecedent.setBounds(10, 391, 188, 23);
		frameConcerner.getContentPane().add(btnPrecedent);
		
		btnSuivant.setBounds(524, 391, 153, 23);
		frameConcerner.getContentPane().add(btnSuivant);
		
		comboNumFormation.setBounds(183, 55, 97, 20);
		frameConcerner.getContentPane().add(comboNumFormation);
		
		txtIdStatus.setBounds(361, 55, 86, 20);
		frameConcerner.getContentPane().add(txtIdStatus);
		txtIdStatus.setColumns(10);
		
		txtNumFormation.setBounds(469, 55, 86, 20);
		frameConcerner.getContentPane().add(txtNumFormation);
		txtNumFormation.setColumns(10);
		
		btnUpdate.setBounds(565, 54, 113, 23);
		frameConcerner.getContentPane().add(btnUpdate);
		
		lblIdStatus2.setBounds(361, 30, 65, 14);
		frameConcerner.getContentPane().add(lblIdStatus2);
		
		lblNumFormation2.setBounds(469, 30, 86, 14);
		frameConcerner.getContentPane().add(lblNumFormation2);
		
		btnUpdate.addActionListener(this);
		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAccueil.addActionListener(this);
		btnPrecedent.addActionListener(this);
		btnSuivant.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnDelete) {

			try {
				BDD.executeUpdate("DELETE FROM `concerner`"
						+ " WHERE `idStatus`='"+getIdStatus()+"'"
						+ " AND `numFormation`='"+getNumFormation()+"'");	

				// mis à jour du tableau formation
				BDD.executeSelect("SELECT concerner.idStatus, STATUS .libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS .idStatus");

				getJtableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnSuivant) {

			//rend visible l'ihm session
			frameConcerner.setVisible(false);
			Session.getSessionsFrame().setVisible(true);

			//mis à jour du tableau session
			BDD.executeSelect("SELECT * FROM `session`");

			try {
				Session.getJTableSess().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//mis à jour des Jcombobox
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
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnPrecedent) {

			//rend visible l'ihm formation
			frameConcerner.setVisible(false);
			Formation.getFrameFormation().setVisible(true);


		} else if (e.getSource() == btnUpdate) {	

			try {
				//requete qui met à jour les informations dont le numFormation correspond
				BDD.executeUpdate("UPDATE `concerner` "
						+ "SET `idStatus`='"+getTxtIdStatus()+"', `numFormation`="+getTxtNumFormation()+" "
						+ "WHERE `numFormation`="+getNumFormation()+" AND `numFormation`="+getNumFormation());

				// mis à jour du tableau concerner
				BDD.executeSelect("SELECT concerner.idStatus, STATUS .libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS .idStatus");

				getJtableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnInsert) {	

			try {
				//requete qui va insérer dans la table concerner
				BDD.executeUpdate("INSERT INTO `concerner`( `idStatus`, `numFormation`) VALUES ('"+getIdStatus()+"',"+getNumFormation()+")");	
				// mis à jour du tableau formation
				BDD.executeSelect("SELECT concerner.idStatus, STATUS .libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS .idStatus");
				jtableConcerner.setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnAccueil) {	

			//retour à l'accueil
			Accueil.getFrameAcceuil().setVisible(true);
			getFrameConcerner().setVisible(false);
		}

	}

	private String getTxtNumFormation() {
		return txtNumFormation.getText();
	}

	private String getTxtIdStatus() {
		return txtIdStatus.getText();
	}

	public static JComboBox<String> getComboNumFormation() {
		return comboNumFormation;
	}

	public static JComboBox<String> getComboIdStatus() {
		return comboIdStatus;
	}

	public static String getNumFormation() {
		return comboNumFormation.getSelectedItem().toString();
	}

	public static String getIdStatus() {
		return comboIdStatus.getSelectedItem().toString();
	}

	public static JFrame getFrameConcerner() {
		return frameConcerner;
	}

	public static JTable getJtableConcerner() {
		return jtableConcerner;
	}
}
