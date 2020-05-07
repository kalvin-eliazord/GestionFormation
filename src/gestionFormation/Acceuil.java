package gestionFormation;

import javax.swing.JFrame;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Acceuil {

	//frame
	private static JFrame  frameAcceuil = new JFrame();

	//bouttons
	private JButton btnLogout = new JButton("Déconnexion");
	private JButton btnAjoutModif = new JButton("Ajout/Modification");

	//panel
	private JPanel panelGestionForma = new JPanel();

	//constructeur
	public Acceuil() {

		//paramétrage de la fenetre d'Acceuil
		frameAcceuil.setTitle("Accueil");
		frameAcceuil.setBounds(100, 100, 436, 287);
		frameAcceuil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAcceuil.getContentPane().setLayout(null);

		//paramétrage du panel 
		panelGestionForma.setBorder(new TitledBorder(null, "GESTIONNAIRE DE FORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGestionForma.setBounds(84, 94, 240, 56);
		frameAcceuil.getContentPane().add(panelGestionForma);
		panelGestionForma.setLayout(null);

		//paramétrage graphique du boutton ajout/modification
		btnAjoutModif.setBounds(6, 16, 228, 30);
		panelGestionForma.add(btnAjoutModif);

		//paramétrage graphique du boutton Déconnexion
		btnLogout.setBounds(296, 11, 114, 23);
		frameAcceuil.getContentPane().add(btnLogout);

		//action du boutton ajout/modification
		btnAjoutModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage à la frame intervenants
				frameAcceuil.setVisible(false);
				Intervenant.getInterFrame().setVisible(true);

				//requete qui va itialiser le tableau des intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				Intervenant.getTableInter().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));

				//mis à jour du jcombobox
				Intervenant.getComboIdInter().removeAllItems();
				try {
					BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
					while (BDD.getRs().next()) {  
						Intervenant.getComboIdInter().addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		//paramétrage de l'action du boutton Déconnexion
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage à la fenetre login
				Connexion.getFrameLogin().setVisible(true);
				frameAcceuil.setVisible(false);
			}
		});

	}

	//getter
	public static JFrame getFrameAcceuil() {
		return frameAcceuil;
	}
}
