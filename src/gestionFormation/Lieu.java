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
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class Lieu implements ActionListener {

	//frame
	private static JFrame frameLieu = new JFrame();

	//Jtextfields
	private JTextField txtNomLieu = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCodePostal = new JTextField();
	private JTextField txtVille = new JTextField();

	//labels
	private JLabel lblIdLieu = new JLabel("idLieu");
	private JLabel lblNomLieu = new JLabel("nomLieu");
	private JLabel lblAdresse = new JLabel("adresse");
	private JLabel lblCodePostal = new JLabel("codePostal");
	private JLabel lblVille = new JLabel("ville");

	//Jtable et scrollbar
	private static JTable tableLieu = new JTable();;
	private JScrollBar scrollBarLieu = new JScrollBar();

	//panel
	private JPanel panelLieu = new JPanel();

	//scrollpane
	private JScrollPane scrollPaneLieu = new JScrollPane();

	//bouttons
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Précédent");
	private JButton btnSuivant = new JButton("Suivant");
	private JButton btnAccueil = new JButton("Acceuil");

	//jcombobox
	private static JComboBox<String> comboIdLieu = new JComboBox<String>();

	//constructeur
	public Lieu() {

		//paramétrage graphique du frame
		frameLieu.setTitle("Lieu");
		frameLieu.setBounds(100, 100, 765, 416);
		frameLieu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLieu.getContentPane().setLayout(null);

		//paramétrage du panelLieu
		panelLieu.setLayout(null);
		panelLieu.setForeground(Color.BLUE);
		panelLieu.setBorder(new TitledBorder(null, "LIEU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLieu.setBounds(10, 81, 713, 245);
		frameLieu.getContentPane().add(panelLieu);

		//paramétrage graphique du scrollpane et scrollbar
		scrollPaneLieu.setBounds(6, 16, 574, 221);
		panelLieu.add(scrollPaneLieu);
		scrollPaneLieu.setViewportView(tableLieu);
		scrollPaneLieu.setRowHeaderView(scrollBarLieu);

		//paramétrage graphique du boutton insert
		btnInsert.setBounds(590, 76, 113, 23);
		panelLieu.add(btnInsert);

		//paramétrage graphique du boutton update
		btnUpdate.setBounds(590, 110, 113, 23);
		panelLieu.add(btnUpdate);

		//paramétrage graphique du boutton delete
		btnDelete.setBounds(590, 144, 113, 23);
		panelLieu.add(btnDelete);

		//paramétrage graphique du label idLieu
		lblIdLieu.setBounds(10, 27, 86, 14);
		frameLieu.getContentPane().add(lblIdLieu);

		//paramétrage graphique du label nomLieu
		lblNomLieu.setBounds(116, 27, 65, 14);
		frameLieu.getContentPane().add(lblNomLieu);

		//paramétrage graphique du label adresse
		lblAdresse.setBounds(212, 27, 65, 14);
		frameLieu.getContentPane().add(lblAdresse);

		//paramétrage graphique du label codepostal
		lblCodePostal.setBounds(308, 27, 89, 14);
		frameLieu.getContentPane().add(lblCodePostal);

		//paramétrage graphique du textfield nomLieu
		txtNomLieu.setColumns(10);
		txtNomLieu.setBounds(116, 40, 86, 20);
		frameLieu.getContentPane().add(txtNomLieu);

		//paramétrage graphique du textfield adresse
		txtAdresse.setColumns(10);
		txtAdresse.setBounds(212, 40, 86, 20);
		frameLieu.getContentPane().add(txtAdresse);

		//paramétrage graphique du textfield codepostal
		txtCodePostal.setColumns(10);
		txtCodePostal.setBounds(308, 39, 86, 20);
		frameLieu.getContentPane().add(txtCodePostal);

		//paramétrage graphique du label ville
		lblVille.setBounds(403, 27, 65, 14);
		frameLieu.getContentPane().add(lblVille);

		//paramétrage graphique du textfield ville
		txtVille.setColumns(10);
		txtVille.setBounds(403, 40, 86, 20);
		frameLieu.getContentPane().add(txtVille);

		//paramétrage graphique du boutton suivant
		btnSuivant.setBounds(549, 346, 174, 23);
		frameLieu.getContentPane().add(btnSuivant);

		//paramétrage graphique du boutton Acceuil
		btnAccueil.setBounds(277, 346, 174, 23);
		frameLieu.getContentPane().add(btnAccueil);

		//paramétrage graphique du boutton précédent
		btnPrecedent.setBounds(10, 346, 141, 23);
		frameLieu.getContentPane().add(btnPrecedent);

		//paramétrage graphique du jcombobox
		comboIdLieu.setBounds(10, 39, 96, 22);
		frameLieu.getContentPane().add(comboIdLieu);

		//action boutton modifier
		btnUpdate.addActionListener(this);

		// action listener boutton supprimer
		btnDelete.addActionListener(this);

		// actionlistener du boutton insert
		btnInsert.addActionListener(this);

		// action boutton précédent
		btnPrecedent.addActionListener(this);

		//action du boutton suivant
		btnSuivant.addActionListener(this);

		// action du boutton acceuil
		btnAccueil.addActionListener(this);

	}

	//action réalisées sur les bouttons
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDelete) {
			
			BDD.executeUpdate("DELETE FROM `lieu` WHERE `idLieu`='"+getStringLieu()+"'");

			// mis à jour du tableau lieu
			BDD.executeSelect("SELECT * FROM `lieu`");

			try {
				getTableLieu().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(event.getSource() == btnUpdate) {
			
			BDD.executeUpdate("UPDATE `lieu` SET `idLieu`='"+getStringLieu()+"',`nomLieu`='"+getTxtNomLieu()+"',`adresse`='"+getTxtAdresse()+"',`codePostal`='"+getTxtCodePostal()+"',`ville`='"+getTxtVille()+"' WHERE `idLieu`='"+getStringLieu()+"'");

			// mis à jour du tableau lieu
			BDD.executeSelect("SELECT * FROM `lieu`");

			try {
				getTableLieu().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(event.getSource() == btnInsert) {

			BDD.executeUpdate("INSERT INTO `lieu`( `nomLieu`, `adresse`, `codePostal`, `ville`) VALUES ('"+getTxtNomLieu()+"','"+getTxtAdresse()+"',"+getTxtCodePostal()+",'"+getTxtVille()+"')");

			// mis à jour du tableau lieu
			BDD.executeSelect("SELECT * FROM `lieu`");

			try {
				getTableLieu().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			//mis à jour du jcombobox idLieu
			comboIdLieu.removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					comboIdLieu.addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnPrecedent) {

			//rend visible l'ihm intervenant
			Intervenant.getInterFrame().setVisible(true);
			frameLieu.setVisible(false);

		} else if(event.getSource() == btnSuivant) {

			//rend visible l'ihm formation
			Formation.getFrameFormation().setVisible(true);
			frameLieu.setVisible(false);

			//mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				Formation.getJTableFor().setModel(BDD.buildTableModel(BDD.getRs()));
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			//mis à jour du jcombobox numFormation
			Formation.getComboNumForma().removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					Formation.getComboNumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}

		} else if(event.getSource() == btnAccueil) {

			//passage au frame acceuil
			frameLieu.setVisible(false);
			Acceuil.getFrameAcceuil().setVisible(true);
		}

	}

	//getters
	public static JFrame getFrameLieu() {
		return frameLieu;
	}

	public static JTable getTableLieu() {
		return tableLieu;
	}

	public static String getStringLieu() {
		return comboIdLieu.getSelectedItem().toString();
	}

	public static JComboBox<String> getComboIdLieu() {
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
