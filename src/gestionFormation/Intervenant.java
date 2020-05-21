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

	private static final long serialVersionUID = 1L;

	private static JFrame interFrame = new JFrame();;

	private JPanel panel = new JPanel();
	private JScrollPane scrollPanInter = new JScrollPane();
	private static JTable tableInter = new JTable();

	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom  = new JTextField();
	private JTextField txtTitre = new JTextField();;

	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnSuivant = new JButton("Suivant");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnAccueil = new JButton("Accueil");

	private static JLabel lbIdIntervenant = new JLabel("idIntervenant");
	private JLabel lblPrenom = new JLabel("prenom");
	private JLabel lblTitre = new JLabel("titre");
	private JLabel lblNom = new JLabel("nom");
	
	private final static JComboBox<String> comboIdInter = new JComboBox<String>();

	public Intervenant() {

		interFrame.setTitle("Intervenant");
		interFrame.getContentPane().setLayout(null);
		interFrame.setBounds(100, 100, 724, 476);
		interFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interFrame.getContentPane().setLayout(null);

		panel.setForeground(Color.BLUE);	
		panel.setBorder(new TitledBorder(null, "INTERVENANT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 96, 648, 272);
		interFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPanInter.setBounds(6, 16, 490, 250);
		panel.add(scrollPanInter);
		scrollPanInter.setViewportView(getTableInter());
		scrollPanInter.setViewportView(tableInter);

		btnInsert.setBounds(510, 76, 111, 23);
		panel.add(btnInsert);

		btnUpdate.setBounds(510, 110, 111, 23);
		panel.add(btnUpdate);

		btnDelete.setBounds(510, 144, 111, 23);
		panel.add(btnDelete);

		lbIdIntervenant.setBounds(35, 30, 86, 14);
		interFrame.getContentPane().add(lbIdIntervenant);

		lblNom.setBounds(209, 30, 65, 14);
		interFrame.getContentPane().add(lblNom);

		lblPrenom.setBounds(366, 30, 65, 14);
		interFrame.getContentPane().add(lblPrenom);

		lblTitre.setBounds(524, 30, 65, 14);
		interFrame.getContentPane().add(lblTitre);

		txtNom.setBounds(203, 55, 134, 20);
		interFrame.getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtPrenom.setBounds(358, 55, 134, 20);
		interFrame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);

		txtTitre.setBounds(524, 54, 134, 23);
		interFrame.getContentPane().add(txtTitre);
		txtTitre.setColumns(10);

		btnSuivant.setBounds(504, 379, 154, 23);
		interFrame.getContentPane().add(btnSuivant);

		btnAccueil.setBounds(35, 379, 113, 23);
		interFrame.getContentPane().add(btnAccueil);

		comboIdInter.setBounds(35, 55, 134, 22);
		interFrame.getContentPane().add(comboIdInter);

		btnAccueil.addActionListener(this);
		btnSuivant.addActionListener(this);
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {

			//requete qui supprime les champs dont l'idIntervenant correspond
			BDD.executeUpdate("DELETE FROM `intervenant` WHERE `idIntervenant`="+getStringInter());
			//modification dynamique du tableau intervenant
			BDD.executeSelect("SELECT * FROM `intervenant`");
			
			try {
				getTableInter().setModel(BDD.buildTable(BDD.getRs()));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	

			//mis à jour du jcombobox
			comboIdInter.removeAllItems();
			try {
				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnUpdate) {

			//requete qui met à jour les informations dont l'idIntervenant correspond
			BDD.executeUpdate("UPDATE `intervenant` SET `nom`='"+getTxtNom()+"', `prenom`='"+getTxtPrenom()+"', `titre`='"+getTxtTitre()+"' WHERE `idIntervenant`="+getStringInter());
			// mis à jour du tableau intervenants
			BDD.executeSelect("SELECT * FROM `intervenant`");

			try {
				getTableInter().setModel(BDD.buildTable(BDD.getRs()));
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	

		} else if(event.getSource() == btnInsert) {

			//insertion d'un champ dans la table intervenant
			BDD.executeUpdate("INSERT INTO `intervenant`( `nom`, `prenom`, `titre`) VALUES ('"+getTxtNom()+"', '"+getTxtPrenom()+"','"+getTxtTitre()+"')");	
			// mis à jour du tableau intervenants
			BDD.executeSelect("SELECT * FROM `intervenant`");
			
			try {
				getTableInter().setModel(BDD.buildTable(BDD.getRs()));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox
			comboIdInter.removeAllItems(); 
			try {
				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} else if(event.getSource() == btnSuivant) {

			//passage à la frame lieu
			Lieu.getFrameLieu().setVisible(true);	
			interFrame.setVisible(false);

			//initialisation des champs du tableau lieu
			BDD.executeSelect("SELECT * FROM `lieu`");	
			
			try {
				Lieu.getTableLieu().setModel(BDD.buildTable(BDD.getRs()));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox
			Lieu.getComboIdLieu().removeAllItems(); 
			try {
				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					Lieu.getComboIdLieu().addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} else if(event.getSource() == btnAccueil) {

			//passage au frame acceuil
			Accueil.getFrameAcceuil().setVisible(true);
			interFrame.setVisible(false);
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

	public static JFrame getInterFrame() {
		return interFrame;
	}

	public static JTable getTableInter() {
		return tableInter;
	}

	public static JComboBox<String> getComboIdInter() {
		return comboIdInter;
	}

}
