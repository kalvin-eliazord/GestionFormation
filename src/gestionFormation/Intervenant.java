package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//frame
	private static JFrame interFrame = new JFrame();;

	//tableau
	private JScrollBar scrollBarInter = new JScrollBar();
	private JPanel panel = new JPanel();
	private JScrollPane scrollPanInter = new JScrollPane();
	private static JTable tableInter = new JTable();


	///textfields
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom  = new JTextField();
	private JTextField txtTitre = new JTextField();;

	//bouttons
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnSuivant = new JButton("Suivant");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnAccueil = new JButton("Acceuil");

	//labels
	private static JLabel lbIdIntervenant = new JLabel("idIntervenant");
	private JLabel lblPrenom = new JLabel("prenom");
	private JLabel lblTitre = new JLabel("titre");
	private JLabel lblNom = new JLabel("nom");

	//jcombobox
	private final static JComboBox<String> comboIdInter = new JComboBox<String>();

	//constructeur
	public Intervenant() {

		//paramétrage graphique de la fenetre
		interFrame.setTitle("Intervenant");
		interFrame.getContentPane().setLayout(null);
		interFrame.setBounds(100, 100, 724, 476);
		interFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interFrame.getContentPane().setLayout(null);

		//paramétrage graphique du panel
		panel.setForeground(Color.BLUE);	
		panel.setBorder(new TitledBorder(null, "INTERVENANT", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 96, 648, 272);
		interFrame.getContentPane().add(panel);
		panel.setLayout(null);

		//paramétrage graphique du scrollpan et scrollbar
		scrollPanInter.setBounds(6, 16, 490, 250);
		panel.add(scrollPanInter);
		scrollPanInter.setViewportView(getTableInter());
		scrollPanInter.setViewportView(tableInter);
		scrollPanInter.setRowHeaderView(scrollBarInter);

		//paramétrage graphique du boutton insérer
		btnInsert.setBounds(510, 76, 111, 23);
		panel.add(btnInsert);

		//paramétrage graphique du boutton modifier
		btnUpdate.setBounds(510, 110, 111, 23);
		panel.add(btnUpdate);

		//paramétrage graphique du boutton supprimer
		btnDelete.setBounds(510, 144, 111, 23);
		panel.add(btnDelete);

		//paramétrage graphique du label idIntervenant
		lbIdIntervenant.setBounds(35, 30, 86, 14);
		interFrame.getContentPane().add(lbIdIntervenant);

		//paramétrage graphique du label nom
		lblNom.setBounds(209, 30, 65, 14);
		interFrame.getContentPane().add(lblNom);

		//paramétrage graphique du label prenom
		lblPrenom.setBounds(366, 30, 65, 14);
		interFrame.getContentPane().add(lblPrenom);

		//paramétrage graphique du label titre
		lblTitre.setBounds(524, 30, 65, 14);
		interFrame.getContentPane().add(lblTitre);

		//paramétrage graphique du jtextfield nom
		txtNom.setBounds(203, 55, 134, 20);
		interFrame.getContentPane().add(txtNom);
		txtNom.setColumns(10);

		//paramétrage graphique du jtextfield prenom
		txtPrenom.setBounds(358, 55, 134, 20);
		interFrame.getContentPane().add(txtPrenom);
		txtPrenom.setColumns(10);

		//paramétrage graphique du jtextfield titre
		txtTitre.setBounds(524, 54, 134, 23);
		interFrame.getContentPane().add(txtTitre);
		txtTitre.setColumns(10);

		//paramétrage graphique du boutton suivant
		btnSuivant.setBounds(504, 379, 154, 23);
		interFrame.getContentPane().add(btnSuivant);

		//paramétrage graphique du boutton acceuil
		btnAccueil.setBounds(35, 379, 113, 23);
		interFrame.getContentPane().add(btnAccueil);

		//paramétrage graphique du jcombobox
		comboIdInter.setBounds(35, 55, 134, 22);
		interFrame.getContentPane().add(comboIdInter);

		//action listener boutton acceuil
		btnAccueil.addActionListener(this);

		//ajout d'un actionListener sur le boutton suivant
		btnSuivant.addActionListener(this);

		//ajout d'un actionListener sur le boutton insert
		btnInsert.addActionListener(this);

		//ajout d'un actionListener sur le boutton Update
		btnUpdate.addActionListener(this);

		//ajout d'un actionListener sur le boutton Delete
		btnDelete.addActionListener(this);

	}
	
	//action réalisées sur les bouttons
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {

			//requete qui supprime les champs dont l'idIntervenant correspond
			BDD.executeUpdate("DELETE FROM `intervenant` WHERE `idIntervenant`="+getStringInter());
			//modification dynamique du tableau intervenant
			BDD.executeSelect("SELECT * FROM `intervenant`");
			
			try {
				getTableInter().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnUpdate) {

			//requete qui met à jour les informations dont l'idIntervenant correspond
			BDD.executeUpdate("UPDATE `intervenant` SET `idIntervenant`="+getStringInter()+",`nom`='"+getTxtNom()+"', `prenom`='"+getTxtPrenom()+"', `titre`='"+getTxtTitre()+"' WHERE `idIntervenant`="+getStringInter());
			// mis à jour du tableau intervenants
			BDD.executeSelect("SELECT * FROM `intervenant`");

			try {
				getTableInter().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	

		} else if(event.getSource() == btnInsert) {

			//insertion d'un champ dans la table intervenant
			BDD.executeUpdate("INSERT INTO `intervenant`( `nom`, `prenom`, `titre`) VALUES ('"+getTxtNom()+"', '"+getTxtPrenom()+"','"+getTxtTitre()+"')");	
			// mis à jour du tableau intervenants
			BDD.executeSelect("SELECT * FROM `intervenant`");
			
			try {
				getTableInter().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//mis à jour du jcombobox
			comboIdInter.removeAllItems(); // suppresion des item du jcombobox pour éviter les doublons d'id
			try {
				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					comboIdInter.addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(event.getSource() == btnSuivant) {

			//passage à la frame lieu
			Lieu.getFrameLieu().setVisible(true);	
			interFrame.setVisible(false);

			//initialisation des champs du tableau lieu
			BDD.executeSelect("SELECT * FROM `lieu`");	
			
			try {
				Lieu.getTableLieu().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//mis à jour du jcombobox
			Lieu.getComboIdLieu().removeAllItems(); //pour empêcher les doublons d'id quand on va naviguer entre les ihm
			try {
				BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
				while (BDD.getRs().next()) {  
					Lieu.getComboIdLieu().addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(event.getSource() == btnAccueil) {

			//passage au frame acceuil
			Acceuil.getFrameAcceuil().setVisible(true);
			interFrame.setVisible(false);
		}

	}

	//getters 
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
