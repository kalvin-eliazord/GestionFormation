package formationAdmin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class Lieu {

	//frame
	private static JFrame frameLieu = new JFrame();

	//Jtextfields
	private JTextField txtIdLieu = new JTextField();
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

	//Jtable
	private static JTable tableLieu = new JTable();;

	//panels
	private JPanel panelLieu = new JPanel();

	//scrollpane
	private JScrollPane scrollPaneLieu = new JScrollPane();

	//bouttons
	private JButton btnInsert = new JButton("Insérer");
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Précédent");
	private JButton btnSuivant = new JButton("Suivant");
	private JButton btnAcceuil = new JButton("Acceuil");
	private final JScrollBar scrollBarLieu = new JScrollBar();

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
		panelLieu.setBorder(new TitledBorder(null, "LIEU DE LA SESSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLieu.setBounds(10, 81, 713, 245);
		frameLieu.getContentPane().add(panelLieu);

		//paramétrage graphique et ajout du scrollpane
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
		
		//paramétrage graphique du textfield idLieu
		txtIdLieu.setColumns(10);
		txtIdLieu.setBounds(20, 40, 86, 20);
		frameLieu.getContentPane().add(txtIdLieu);
		
		//paramétrage graphique du label idLieu
		lblIdLieu.setBounds(20, 27, 86, 14);
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

		//ajout des noms de colonnes au tableau lieu
		tableLieu.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"idLieu", "nomLieu", "adresse", "codePostal", "ville"
				}
				));


		//action boutton modifier
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BDD.executeUpdate("UPDATE `lieu` SET `idLieu`='"+getTxtIdLieu()+"',`nomLieu`='"+getTxtNomLieu()+"',`adresse`='"+getTxtAdresse()+"',`codePostal`='"+getTxtCodePostal()+"',`ville`='"+getTxtVille()+"' WHERE `idLieu`='"+getTxtIdLieu()+"'");
				// mis à jour du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");
				getTableLieu().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		// action listener boutton supprimer
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BDD.executeUpdate("DELETE FROM `lieu` WHERE `idLieu`='"+getTxtIdLieu()+"'");
				// mis à jour du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");
				getTableLieu().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		// actionlistener du boutton insert
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BDD.executeUpdate("INSERT INTO `lieu`( `nomLieu`, `adresse`, `codePostal`, `ville`) VALUES ('"+getTxtNomLieu()+"','"+getTxtAdresse()+"',"+getTxtCodePostal()+",'"+getTxtVille()+"')");
				System.out.println(getTxtNomLieu());
				// mis à jour du tableau lieu
				BDD.executeSelect("SELECT * FROM `lieu`");
				getTableLieu().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		// action boutton précédent
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Formation.getFrameFormation().setVisible(true);
				frameLieu.setVisible(false);
			}
		});

		btnPrecedent.setBounds(20, 346, 141, 23);
		frameLieu.getContentPane().add(btnPrecedent);

		//action du boutton suivant
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frameLieu.setVisible(false);
				Sessions.getSessionsFrame().setVisible(true);
				BDD.executeSelect("SELECT * FROM `session`");
				Sessions.getJTableSess().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		btnSuivant.setBounds(565, 346, 174, 23);
		frameLieu.getContentPane().add(btnSuivant);

		//paramétrage graphique du boutton Acceuil
		btnAcceuil.setBounds(277, 346, 174, 23);
		frameLieu.getContentPane().add(btnAcceuil);

		// action du boutton acceuil
		btnAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage au frame acceuil
				frameLieu.setVisible(false);
				Acceuil.getFrameAcceuil().setVisible(true);
			}
		});

	}

	//getters
	public static JFrame getFrameLieu() {
		return frameLieu;
	}

	public static JTable getTableLieu() {
		return tableLieu;
	}

	public String getTxtIdLieu() {

		return txtIdLieu.getText();
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
