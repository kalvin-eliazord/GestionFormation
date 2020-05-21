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
import javax.swing.JComboBox;

public class Formation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static JFrame frameFormation = new JFrame();;

	private static JTable jTableFormation = new JTable();
	private JPanel panelFormation = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();

	private JTextField txtObjectif  = new JTextField();
	private JTextField txtCouts = new JTextField();;

	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnAccueil = new JButton("Accueil");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Précédent");
	private JButton btnSuivant = new JButton("Suivant");

	private JLabel lblNumFor = new JLabel("numFormation");
	private JLabel lblObjectif = new JLabel("objectif");
	private JLabel lblCouts = new JLabel("couts");

	private static JComboBox<String> comboNumForma = new JComboBox<String>();

	public Formation() {

		frameFormation.setTitle("Formation");
		frameFormation.getContentPane().setLayout(null);
		frameFormation.setBounds(100, 100, 724, 476);
		frameFormation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFormation.getContentPane().setLayout(null);

		panelFormation.setBorder(new TitledBorder(null, "FORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFormation.setBounds(10, 96, 688, 272);
		frameFormation.getContentPane().add(panelFormation);
		panelFormation.setLayout(null);

		scrollPane.setBounds(6, 16, 494, 250);
		panelFormation.add(scrollPane);
		scrollPane.setViewportView(jTableFormation);

		btnInsert.setBounds(510, 77, 113, 23);
		panelFormation.add(btnInsert);

		btnUpdate.setBounds(510, 111, 113, 23);
		panelFormation.add(btnUpdate);

		btnDelete.setBounds(510, 145, 113, 23);
		panelFormation.add(btnDelete);

		lblNumFor.setBounds(35, 30, 86, 14);
		frameFormation.getContentPane().add(lblNumFor);

		lblObjectif.setBounds(187, 30, 65, 14);
		frameFormation.getContentPane().add(lblObjectif);

		lblCouts.setBounds(341, 30, 65, 14);
		frameFormation.getContentPane().add(lblCouts);

		txtObjectif.setBounds(187, 55, 134, 20);
		frameFormation.getContentPane().add(txtObjectif);
		txtObjectif.setColumns(10);

		txtCouts.setBounds(341, 54, 134, 23);
		frameFormation.getContentPane().add(txtCouts);
		txtCouts.setColumns(10);

		btnAccueil.setBounds(263, 391, 188, 23);
		frameFormation.getContentPane().add(btnAccueil);

		btnPrecedent.setBounds(10, 391, 188, 23);
		frameFormation.getContentPane().add(btnPrecedent);

		btnSuivant.setBounds(524, 391, 153, 23);
		frameFormation.getContentPane().add(btnSuivant);

		comboNumForma.setBounds(35, 55, 119, 22);
		frameFormation.getContentPane().add(comboNumForma);

		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnAccueil.addActionListener(this);
		btnPrecedent.addActionListener(this);
		btnSuivant.addActionListener(this);

	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {

			//requete qui supprime les informations dont le numFormation correspond
			BDD.executeUpdate("DELETE FROM `formation` WHERE `numFormation`="+getNumFormation());
			// mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				getJTableFor().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox numFormation
			comboNumForma.removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					comboNumForma.addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnSuivant) {

			//passage à l'ihm concerners
			frameFormation.setVisible(false);
			Concerner.getFrameConcerner().setVisible(true);

			try {
				//requete qui va initialiser la table concerner en affichant le nom du status et l'objectif de la formation associé
				BDD.executeSelect("SELECT concerner.idStatus, STATUS .libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS .idStatus");

				Concerner.getJtableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//mis à jour des jcombobox numFormation et idStatus
			Concerner.getComboNumFormation().removeAllItems();
			Concerner.getComboIdStatus().removeAllItems();
			try {

				BDD.executeSelect("SELECT `numFormation`  FROM `formation`");
				while (BDD.getRs().next()) {  
					Concerner.getComboNumFormation().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  

				}

				BDD.executeSelect("SELECT `idStatus` FROM `status`");
				while (BDD.getRs().next()) {  
					Concerner.getComboIdStatus().addItem(Integer.toString(BDD.getRs().getInt("idStatus")));  

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}


		} else if (event.getSource() == btnPrecedent) {

			frameFormation.setVisible(false);
			Lieu.getFrameLieu().setVisible(true);

		} else if (event.getSource() == btnUpdate) {	

			//requete qui met à jour les informations dont le numFormation correspond
			BDD.executeUpdate("UPDATE `formation` SET `objectif`='"+getTxtObjectif()+"', `couts`="+getTxtCouts()+" WHERE `numFormation`="+getNumFormation());
			// mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				getJTableFor().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == btnInsert) {	

			//requete qui va insérer dans la table formation
			BDD.executeUpdate("INSERT INTO `formation`( `objectif`, `couts`) VALUES ('"+getTxtObjectif()+"',"+getTxtCouts()+")");	
			// mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				jTableFormation.setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox numFormation
			comboNumForma.removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					Formation.getComboNumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnAccueil) {	

			//retour à l'accueil
			Accueil.getFrameAcceuil().setVisible(true);
			Formation.getFrameFormation().setVisible(false);
		}

	}

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
