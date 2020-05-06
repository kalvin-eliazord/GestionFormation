package formationAdmin;

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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Intervenant extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//frame
	private static JFrame interFrame = new JFrame();;

	//tableau
	private final JScrollBar scrollBarInter = new JScrollBar();
	private final JPanel panel = new JPanel();
	private final JScrollPane scrollPanInter = new JScrollPane();
	private final static JTable tableInter = new JTable();

	//textfields
	private JTextField txtIdInter = new JTextField();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom  = new JTextField();
	private JTextField txtTitre = new JTextField();;

	//bouttons
	private JButton btnUpdate = new JButton("Modifier");
	private JButton btnInsertInter = new JButton("Insérer");
	private JButton btnSuivant = new JButton("Suivant");
	private JButton btnDelete = new JButton("Supprimer");
	private JButton btnAcceuil = new JButton("Acceuil");

	//labels
	private JLabel lbIdIntervenant = new JLabel("idIntervenant");
	private JLabel lblPrenom = new JLabel("prenom");
	private JLabel lblTitre = new JLabel("titre");
	private JLabel lblNom = new JLabel("nom");

	//constructeur
	public Intervenant() {

		//paramétrage graphique de la fenetre
		interFrame.setTitle("Intervenants");
		interFrame.getContentPane().setLayout(null);
		interFrame.setBounds(100, 100, 724, 476);
		interFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interFrame.getContentPane().setLayout(null);

		//paramétrage graphique du panel
		panel.setForeground(Color.BLUE);	
		panel.setBorder(new TitledBorder(null, "INTERVENANTS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 96, 648, 272);
		interFrame.getContentPane().add(panel);
		panel.setLayout(null);

		//paramétrage graphique du scrollpan
		scrollPanInter.setBounds(6, 16, 490, 250);
		panel.add(scrollPanInter);
		scrollPanInter.setViewportView(getTableInter());

		scrollPanInter.setViewportView(tableInter);
		scrollPanInter.setRowHeaderView(scrollBarInter);

		//paramétrage graphique du boutton insérer
		btnInsertInter.setBounds(510, 76, 111, 23);
		panel.add(btnInsertInter);

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

		//paramétrage graphique du jtextfield idIntervenant
		txtIdInter.setBounds(35, 55, 131, 20);
		interFrame.getContentPane().add(txtIdInter);
		txtIdInter.setColumns(10);

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
		btnAcceuil.setBounds(35, 379, 113, 23);
		interFrame.getContentPane().add(btnAcceuil);

		//ajout des noms des colonnes du tableau intervenant
		tableInter.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"idIntervenant", "nom", "prenom", "titre"
				}
				));

		//action listener boutton acceuil
		btnAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage au frame acceuil
				Acceuil.getFrameAcceuil().setVisible(true);
				interFrame.setVisible(false);
			}
		});

		//ajout d'un actionListener sur le boutton suivant
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage à la frame Formation
				Formation.getFrameFormation().setVisible(true);	
				interFrame.setVisible(false);

				//initialisation des champs du tableau formation
				BDD.executeSelect("SELECT * FROM `formation`");	
				Formation.getJTableFor().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		//ajout d'un actionListener sur le boutton insert
		btnInsertInter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//insertion d'un champ dans la table intervenant
				BDD.executeUpdate("INSERT INTO `intervenant`( `nom`, `prenom`, `titre`) VALUES ('"+getTxtNom()+"', '"+getTxtPrenom()+"','"+getTxtTitre()+"')");	
				// mis à jour du tableau intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				getTableInter().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		//ajout d'un actionListener sur le boutton Update
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui met à jour les informations dont l'idIntervenant correspond
				BDD.executeUpdate("UPDATE `intervenant` SET `idIntervenant`="+getTxtIdInter()+",`nom`='"+getTxtNom()+"', `prenom`='"+getTxtPrenom()+"', `titre`='"+getTxtTitre()+"' WHERE `idIntervenant`="+getTxtIdInter());
				// mis à jour du tableau intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				getTableInter().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));	
			}
		});

		//ajout d'un actionListener sur le boutton Delete
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui supprime les champs dont l'idIntervenant correspond
				BDD.executeUpdate("DELETE FROM `intervenant` WHERE `idIntervenant`="+getTxtIdInter());
				//modification dynamique du tableau intervenant
				BDD.executeSelect("SELECT * FROM `intervenant`");
				getTableInter().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));	
			}
		});

	}

	//getters 
	public String getTxtIdInter() {

		return txtIdInter.getText();
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

}
